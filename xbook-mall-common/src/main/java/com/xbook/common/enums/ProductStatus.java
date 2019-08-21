package com.xbook.common.enums;

import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum  ProductStatus {
    ON(1, "上架"),
    DOWN(2, "下架"),
    DELETED(3, "删除"),

    ;
    private int code;
    private String msg;

    ProductStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
