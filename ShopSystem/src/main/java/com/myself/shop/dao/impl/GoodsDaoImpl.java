package com.myself.shop.dao.impl;

import com.myself.shop.dao.GoodsDao;
import com.myself.shop.pojo.Goods;
import com.myself.shop.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    Connection connection = DBUtil.getConnection();

    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goods = new ArrayList<Goods>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from goods");

            getRs(goods, preparedStatement);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return goods;
    }

    @Override
    public void delGoodsById(int id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("delete from goods where id = ?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertGoods(Goods goods) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("insert into goods values (null,?,?,?,?,?,?)");
            setPreparedStatement(goods, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGoods(Goods goods) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("update goods set " +
                    "name=?,type=?,price=?,stock=?,operator=?,operationTime=? where id=?");

            setPreparedStatement(goods, preparedStatement);
            preparedStatement.setInt(7,goods.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Goods> getGoodsWithType(String type) {
        List<Goods> goods = new ArrayList<Goods>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from goods where type=?");
            preparedStatement.setString(1,type);

            getRs(goods, preparedStatement);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return goods;
    }

    private void getRs(List<Goods> goods, PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            Goods product = new Goods(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("operator"),
                    rs.getDate("operationTime")
            );
            goods.add(product);
        }
    }

    private void setPreparedStatement(Goods goods, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,goods.getName());
        preparedStatement.setString(2,goods.getType());
        preparedStatement.setDouble(3,goods.getPrice());
        preparedStatement.setInt(4,goods.getStock());
        preparedStatement.setString(5,goods.getOperator());
        preparedStatement.setDate(6, new java.sql.Date(new Date().getTime()));
    }
}
