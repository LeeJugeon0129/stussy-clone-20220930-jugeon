package com.stussy.stussyclone20220930jugeon.domain;

import com.stussy.stussyclone20220930jugeon.dto.admin.ProductSizeOptionRespDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionProductSize {
    private int size_id;
    private String size_name;

    public ProductSizeOptionRespDto toDto() {
        return ProductSizeOptionRespDto.builder()
                .sizeId(size_id)
                .sizeName(size_name)
                .build();
    }

}
