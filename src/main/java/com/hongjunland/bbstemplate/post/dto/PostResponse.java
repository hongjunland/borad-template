package com.hongjunland.bbstemplate.post.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostResponse(
        Long id,
        Long boardId,
        String boardName,
        String title,
        String content,
        String author,
        long likeCount,
        long commentCount,

        boolean isLiked,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
