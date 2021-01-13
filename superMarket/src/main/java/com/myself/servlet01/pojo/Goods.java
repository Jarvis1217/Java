package com.myself.servlet01.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Goods")
public class Goods {
    private String product;
    private Double price;
    private Integer stock;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "product='" + product + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
