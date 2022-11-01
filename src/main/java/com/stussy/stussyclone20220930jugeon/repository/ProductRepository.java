package com.stussy.stussyclone20220930jugeon.repository;

import com.stussy.stussyclone20220930jugeon.domain.CollectionsProduct;
import com.stussy.stussyclone20220930jugeon.domain.PaymentProduct;
import com.stussy.stussyclone20220930jugeon.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRepository {
    public List<CollectionsProduct> getProductList(Map<String, Object> map) throws Exception;

    public Product getProduct(int pdtId) throws Exception;
    public PaymentProduct getPaymentProduct(int pdtDtlId) throws Exception;

}
