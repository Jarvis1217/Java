package com.myself.blog_api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.fastjson.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private int bId;
    private String bTitle;
    private String bContent;
    private Date bDate;

    @Override
    public String toString() {
        return "Blog{" +
                "bId=" + bId +
                ", bTitle='" + bTitle + '\'' +
                ", bContent='" + bContent + '\'' +
                ", bDate=" + bDate +
                '}';
    }
}