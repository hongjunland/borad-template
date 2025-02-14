package com.hongjunland.boardtemplate.post.domain;

import com.hongjunland.boardtemplate.board.domain.BoardJpaEntity;
import com.hongjunland.boardtemplate.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "posts")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private BoardJpaEntity board;

    private String title;
    private String content;
    private String author;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
