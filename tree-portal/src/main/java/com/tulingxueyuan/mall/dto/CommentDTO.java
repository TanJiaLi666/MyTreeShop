package com.tulingxueyuan.mall.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsCommentReplay;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="评论信息")
public class CommentDTO {
    private Long commentId;
    private String commentUser;
    private String headImg;
    private String commentContent;
    private Integer star;
    private String updatedDate;
    private Boolean show;
    private Boolean inputShow;
    private Boolean replyShow;
    private List<CommentReplyDTO> reply;
}
