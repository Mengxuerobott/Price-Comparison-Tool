package com.market.dao;

import com.market.entity.CommentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.market.vo.CommentView;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 评论表  Mapper 接口
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Repository
public interface CommentDao extends BaseMapper<CommentEntity> {
    @Select("select comment_id, comment_user_id,comment_buy_source,comment_content, " +
            " comment_goods_score,user_nickname,user_headImgurl,gs_name,gs_main_url from bis_spu_comment " +
            "left join bis_user on comment_user_id=user_id left join bis_goods_source on comment_buy_source=gs_id" +
            " where comment_spu_id=#{spuId} order by comment_created")
    List<CommentView> getSpuComments(Long spuId);
}
