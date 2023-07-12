package com.efub.lakkulakku.domain.comment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateResDto {

	private UUID commentId;
	private UUID userId;
	private String profileImageUrl;
	private String nickname;
	private UUID parentId;
	private String content;
	private Boolean isSecret;
	private LocalDateTime modifiedOn;

	@Builder
	public CommentUpdateResDto(UUID commentId, UUID userId, String profileImageUrl, String nickname, UUID parentId,
		String content,
		Boolean isSecret, LocalDateTime modifiedOn) {
		this.commentId = commentId;
		this.userId = userId;
		this.profileImageUrl = profileImageUrl;
		this.nickname = nickname;
		this.parentId = parentId;
		this.content = content;
		this.isSecret = isSecret;
		this.modifiedOn = modifiedOn;

	}

}

