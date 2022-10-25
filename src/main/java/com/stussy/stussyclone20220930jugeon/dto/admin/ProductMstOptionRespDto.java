package com.stussy.stussyclone20220930jugeon.dto.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductMstOptionRespDto {
    private int pdtId;
    private String category;
    private String pdtName;
}
