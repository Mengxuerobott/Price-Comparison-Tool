package com.market.dao;

import com.market.vo.AvgView;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Elias on 2019/7/19
 */
@Repository
public interface AnalyseDao {
    @Select("select avg(gss_current_price),gss_spu_id from bis_goods_spu_source order by gss_spu_id ")
    List<AvgView> getCounts();
}
