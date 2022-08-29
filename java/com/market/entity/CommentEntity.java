package com.market.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论表 
 * </p>
 *
 * @author Elias123
 * @since 2019-07-11
 */
@TableName("bis_spu_comment")
public class CommentEntity extends Model<CommentEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;
    /**
     * 评论人id
     */
    @TableField("comment_user_id")
    private String commentUserId;
    /**
     * 评论对象(即商品)id
     */
    @TableField("comment_spu_id")
    private Long commentSpuId;
    /**
     * 购买来源
     */
    @TableField("comment_buy_source")
    private Long commentBuySource;
    /**
     * 评论内容
     */
    @TableField("comment_content")
    private String commentContent;
    /**
     * 打分
     */
    @TableField("comment_goods_score")
    private Integer commentGoodsScore=5;
    /**
     * 状态标志
     */
    @TableField("comment_state")
    private String commentState;
    /**
     * 创建时间
     */
    @TableField("comment_created")
    private Date commentCreated;
    /**
     * 修改时间
     */
    @TableField("comment_updated")
    private Date commentUpdated;


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public Long getCommentSpuId() {
        return commentSpuId;
    }

    public void setCommentSpuId(Long commentSpuId) {
        this.commentSpuId = commentSpuId;
    }

    public Long getCommentBuySource() {
        return commentBuySource;
    }

    public void setCommentBuySource(Long commentBuySource) {
        this.commentBuySource = commentBuySource;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getCommentGoodsScore() {
        return commentGoodsScore;
    }

    public void setCommentGoodsScore(Integer commentGoodsScore) {
        this.commentGoodsScore = commentGoodsScore;
    }

    public String getCommentState() {
        return commentState;
    }

    public void setCommentState(String commentState) {
        this.commentState = commentState;
    }

    public Date getCommentCreated() {
        return commentCreated;
    }

    public void setCommentCreated(Date commentCreated) {
        this.commentCreated = commentCreated;
    }

    public Date getCommentUpdated() {
        return commentUpdated;
    }

    public void setCommentUpdated(Date commentUpdated) {
        this.commentUpdated = commentUpdated;
    }

    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
        ", commentId=" + commentId +
        ", commentUserId=" + commentUserId +
        ", commentSpuId=" + commentSpuId +
        ", commentBuySource=" + commentBuySource +
        ", commentContent=" + commentContent +
        ", commentGoodsScore=" + commentGoodsScore +
        ", commentState=" + commentState +
        ", commentCreated=" + commentCreated +
        ", commentUpdated=" + commentUpdated +
        "}";
    }
}
