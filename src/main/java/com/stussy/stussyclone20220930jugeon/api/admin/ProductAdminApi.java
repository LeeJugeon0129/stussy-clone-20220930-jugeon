package com.stussy.stussyclone20220930jugeon.api.admin;

import com.stussy.stussyclone20220930jugeon.aop.annotation.LogAspect;
import com.stussy.stussyclone20220930jugeon.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220930jugeon.dto.CMRespDto;
import com.stussy.stussyclone20220930jugeon.dto.admin.ProductRegisterDtlReqDto;
import com.stussy.stussyclone20220930jugeon.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclone20220930jugeon.service.admin.ProductManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductAdminApi {

    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(
            @Valid @RequestBody ProductRegisterReqDto productRegisterReqDto, BindingResult bindingResult) throws Exception {

        String name = productRegisterReqDto.getName();

        Random random = new Random();

        for(int i=0; i<100; i++){
            productRegisterReqDto.setCategory(i/10 + 1);
            productRegisterReqDto.setName(name+ (i+1));
            productRegisterReqDto.setPrice((random.nextInt(10) +1) * 100000); //1부터 10까지 랜덤 * 10만원 -> 100만원까지
            productManagementService.registerMst(productRegisterReqDto);

        }
        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", true));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() throws Exception {

        return ResponseEntity.ok()
                .body(new CMRespDto<>("get successfully", productManagementService.getCategoryList()));
    }

    @GetMapping("/option/products/mst")
    public ResponseEntity<?> getProductList() throws Exception {
        return ResponseEntity.ok()
                .body(new CMRespDto<>("Get successfully", productManagementService.getProductMstList()));
    }

    @GetMapping("/option/products/size/{productId}")
    public ResponseEntity<?> getSizeList(@PathVariable int productId) throws Exception {
        return ResponseEntity.ok()
                .body(new CMRespDto<>("Get successfully", productManagementService.getSizeList(productId)));
    }

    @PostMapping("/product/dtl")
    public ResponseEntity<?> registerDtl(@RequestBody ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {

        productManagementService.checkDuplicatedColor(productRegisterDtlReqDto);
        productManagementService.registerDtl(productRegisterDtlReqDto);

        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register successfully", true));

    }

}
