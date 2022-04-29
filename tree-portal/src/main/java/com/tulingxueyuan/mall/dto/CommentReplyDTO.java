package com.tulingxueyuan.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="评论回复信息")
public class CommentReplyDTO {
    private Long id;
    private Long parentCommentId;
    private Long commentId;
    private String commentUser;
    private String headImg;
    private String commentContent;
    private String updatedDate;
    private Boolean show;
    private Boolean inputShow;
    private String toCommentUser;
}
