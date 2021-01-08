package com.myself.servwork.Dao.Impl;

import com.myself.servwork.Dao.shoppingDao;
import com.myself.servwork.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class shoppingDaoImpl implements shoppingDao {
    private final Connection connection = DBUtil.getConnection();

    @Override
    public List<String> findAll() {
        List<String> result = new ArrayList<>();
        Connection connection = DBUtil.getConnection();

        try {

            PreparedStatement ptmt = connection.prepareStatement("select good from goods");
            ResultSet rs = ptmt.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("good"));
            }

            ptmt.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void deleteByText(String text) {
        try {

            String sql = "delete from goods where good = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,text);
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addContent(String text) {
        try {

            String sql = "insert into goods(good) values(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,text);
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
