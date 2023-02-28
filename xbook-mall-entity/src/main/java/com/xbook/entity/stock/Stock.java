package com.xbook.entity.stock;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("`stock`")
public class Stock {
    /**
     * 用户ID
     */
    @TableId(value = "stock_id", type = IdType.AUTO)
    private Integer stockId;

    /**
     * 商品ID
     */
    @TableField(value = "product_id")
    private Integer productId;
    /**
     * 库存数量
     */
    @TableField(value = "stock_num")
    private Integer stockNum;


    //创建人
    @TableField(value = "create_by")
    private String createBy;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private String createTime;
    //更新人
    @TableField(value = "update_by")
    private String updateBy;
    //更新时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}

