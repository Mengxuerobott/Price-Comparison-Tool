package com.market.vo;

import com.market.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Elias on 2019/7/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {
    private Long commentId;
    private Long commentUserId;
    private Long commentBuySource;
    private String commentContent;
    private Integer commentGoodScore;
    private String userNickname;
    private String userHeadImgurl;
    private String gsName;
    private String gsMainUrl;
}
