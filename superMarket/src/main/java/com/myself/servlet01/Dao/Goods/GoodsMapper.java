package com.myself.servlet01.Dao.Goods;

import com.myself.servlet01.pojo.Goods;
import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    List<Goods> getAllProducts();
    void addProduct(Map<String,String> Info);
    void deleteByName(String text);
    void updateByName(Map<String,String> map);
}
