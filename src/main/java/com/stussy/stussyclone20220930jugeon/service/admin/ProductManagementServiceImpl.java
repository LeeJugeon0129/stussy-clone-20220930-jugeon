package com.stussy.stussyclone20220930jugeon.service.admin;

import com.stussy.stussyclone20220930jugeon.domain.ProductImg;
import com.stussy.stussyclone20220930jugeon.dto.admin.*;
import com.stussy.stussyclone20220930jugeon.exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220930jugeon.exception.CustomValidationException;
import com.stussy.stussyclone20220930jugeon.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    @Value("${file.path}") //yml에 등록해놓고 전역으로 사용 //스프링부트라서 가능
    private String filePath;
    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<CategoryResponseDto>();
        productManagementRepository.getCategoryList().forEach(category -> {
            categoryResponseDtos.add(category.toDto());
        });
        return categoryResponseDtos;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception{
        if(productManagementRepository.saveProductMst(productRegisterReqDto.toEntity()) == 0){ //insert 안 되면
            throw new CustomInternalServerErrorException("상품 등록 실패"); //이 에러를 띄워라.
        }

    }

    @Override
    public List<ProductMstOptionRespDto> getProductMstList() throws Exception {
        List<ProductMstOptionRespDto> list = new ArrayList<ProductMstOptionRespDto>();
        productManagementRepository.getProductMstList().forEach(pdtMst -> {
            list.add(pdtMst.toDto());
        });
        return list;
    }

    @Override
    public List<?> getSizeList(int productId) throws Exception {
        List<ProductSizeOptionRespDto> list = new ArrayList<ProductSizeOptionRespDto>();

        productManagementRepository.getSizeList(productId).forEach(size -> {
            list.add(size.toDto());
        });
        return list;
    }

    @Override
    public void checkDuplicatedColor(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if(productManagementRepository.findProductColor(productRegisterDtlReqDto.toEntity()) > 0){
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미 등록된 상품입니다.");
            throw new CustomValidationException("Duplicate Error", errorMap); //400에러 던짐.
        }
    }

    @Override
    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if(productManagementRepository.saveProductDtl(productRegisterDtlReqDto.toEntity()) == 0 ) {
            throw new CustomInternalServerErrorException("상품 등록 오류");
        }
    }

    @Override
    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception {
        log.info("pdtId >>> " + productImgReqDto.getPdtId());

        if(productImgReqDto.getFiles() == null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미지를 선택하지 않았습니다.");
            throw new CustomValidationException("Img Error", errorMap);
        }

        List<ProductImg> productImgs = new ArrayList<ProductImg>();

        productImgReqDto.getFiles().forEach(file -> {
            String originName = file.getOriginalFilename();
            String extension = originName.substring(originName.lastIndexOf("."));
            String saveName = UUID.randomUUID().toString().replaceAll("-", "") + extension;

            Path path = Paths.get(filePath + "product/" + saveName);//이미지 저장할 경로

            File f = new File(filePath, "product");
            if(! f.exists()) { //없으면
                f.mkdirs(); //make directorys - s 붙이면 하위경로까지 만들어주는 것
            }

            try {
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                throw new CustomInternalServerErrorException(e.getMessage());
            }

            productImgs.add(ProductImg.builder()
                            .pdt_id(productImgReqDto.getPdtId())
                            .origin_name(originName)
                            .save_name(saveName)
                            .build());

        });

        productManagementRepository.saveProductImg(productImgs);
    }
}
