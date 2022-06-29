package com.ad.ecom.products.dto;

import com.ad.ecom.products.stubs.ProductBrand;
import com.ad.ecom.products.stubs.ProductCategory;
import com.ad.ecom.products.stubs.ProductSubCategory;
import com.ad.ecom.util.DataRange;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductsFilter implements Serializable {
    private String productName;
    private List<ProductCategory> productCategories;
    private List<ProductSubCategory> productSubCategories;
    private List<ProductBrand> brands;
    private DataRange<Long> priceRange;
}