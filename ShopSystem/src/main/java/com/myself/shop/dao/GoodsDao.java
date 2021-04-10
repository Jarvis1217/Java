package com.myself.shop.dao;

import com.myself.shop.pojo.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> getAllGoods();
    void delGoodsById(int id);
    void insertGoods(Goods goods);
    void updateGoods(Goods goods);
    List<Goods> getGoodsWithType(String type);
}
