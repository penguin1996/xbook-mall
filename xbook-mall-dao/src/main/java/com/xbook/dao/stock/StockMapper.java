package com.xbook.dao.stock;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xbook.entity.stock.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 库存映射类
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {
    @Select("SELECT stock_id, product_id, stock_num, create_time, create_by, update_time, update_by FROM stock where product_id = #{productId}")
    Stock getStockByProductId(Integer productId);
}

