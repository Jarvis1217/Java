package com.hbue.calc.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RateUtil {
    private static InputStream in;

    /**
     * 参数:
     * String from -> 待兑换币种代码
     * String to -> 期望兑换币种代码
     *
     * 返回值:
     * List<String> result
     * result[0] -> 转换币种
     * result[1] -> 汇率
     * result[2] -> 数据更新时间
     */
    public static List<String> getExchangeRate(String from,String to) {
        List<String> result = new ArrayList<>();
        URL url;
        try {
            url = new URL("http://api.k780.com/?app=finance.rate&scur="+ from +"&tcur="+ to +
                    "&appkey=57423&sign=6c2565e74778f8b6fd1afad657ea06f4&format=json");
            in = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            byte[] buf = new byte[1024];
            int read;
            while ((read = in.read(buf)) > 0)
                out.write(buf, 0, read);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String jsonString = out.toString(StandardCharsets.UTF_8);

        // 解析转换币种
        Pattern p1 = Pattern.compile("\"ratenm\":\"(.*?)\",");
        Matcher m1 = p1.matcher(jsonString);
        while(m1.find()) {
            result.add(m1.group(1));
        }

        // 解析汇率
        Pattern p2 = Pattern.compile("\"rate\":\"(.*?)\",");
        Matcher m2 = p2.matcher(jsonString);
        while(m2.find()) {
            result.add(m2.group(1));
        }

        // 解析数据更新时间
        Pattern p3 = Pattern.compile("\"update\":\"(.*?)\"");
        Matcher m3 = p3.matcher(jsonString);
        while(m3.find()) {
            result.add(m3.group(1));
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getExchangeRate("USD","CNY"));
    }
}
