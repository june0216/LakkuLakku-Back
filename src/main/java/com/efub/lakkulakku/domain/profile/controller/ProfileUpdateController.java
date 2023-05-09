package com.efub.lakkulakku.domain.profile.controller;

import com.efub.lakkulakku.domain.profile.service.ProfileService;
import com.efub.lakkulakku.domain.users.dto.ProfileUpdateResDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileUpdateController {

	private final ProfileService profileService;

	@PostMapping
	public ResponseEntity<ProfileUpdateResDto> updateProfile(@RequestParam(value = "nickname") String nickname,
															@RequestParam(value = "image", required = false) MultipartFile image,
															@RequestParam(value = "bio") String bio) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(profileService.updateUserProfile(nickname, image, bio));
	}
}
