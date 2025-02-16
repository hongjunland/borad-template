package com.hongjunland.bbstemplate.post.domain;

import com.hongjunland.bbstemplate.common.domain.BaseTimeEntity;
import com.hongjunland.bbstemplate.user.domain.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "user_id"}) // ✅ 중복 좋아요 방지
})
@Builder
@AllArgsConstructor
public class PostLikeJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostJpaEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

}
