package com.clay.springcloud.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "payment")
public class Payment {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "serial")
    private String serial;
}