package com.myself.cashBook.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.sampled.EnumControl;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class bill {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String type;
    private Double price;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String date;
    private String mark;

}
