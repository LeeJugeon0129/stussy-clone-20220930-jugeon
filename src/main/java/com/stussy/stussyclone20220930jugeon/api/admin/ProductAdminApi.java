package com.stussy.stussyclone20220930jugeon.api.admin;

import com.stussy.stussyclone20220930jugeon.aop.annotation.LogAspect;
import com.stussy.stussyclone20220930jugeon.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220930jugeon.dto.CMRespDto;
import com.stussy.stussyclone20220930jugeon.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclone20220930jugeon.service.admin.ProductManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductAdminApi {

    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(
            @Valid @RequestBody ProductRegisterReqDto productRegisterReqDto, BindingResult bindingResult) {
        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", null));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() throws Exception {

        return ResponseEntity.ok()
                .body(new CMRespDto<>("get successfully", productManagementService.getCategoryList()));
    }
}
