package com.myself.blog.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog")
@ApiModel("个人博客实体类")
public class Blog {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;
    private String intro;
    private String content;
    private Date postTime;
}
