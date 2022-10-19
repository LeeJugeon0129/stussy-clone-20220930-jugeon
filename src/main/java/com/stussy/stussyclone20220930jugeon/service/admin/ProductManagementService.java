package com.stussy.stussyclone20220930jugeon.service.admin;

import com.stussy.stussyclone20220930jugeon.dto.admin.CategoryResponseDto;
import com.stussy.stussyclone20220930jugeon.dto.admin.ProductRegisterReqDto;

import java.util.List;

public interface ProductManagementService {
    public List<CategoryResponseDto> getCategoryList() throws Exception;
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;

}
