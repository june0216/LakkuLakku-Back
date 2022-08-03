package com.efub.lakkulakku.domain.diary.controller;

import com.efub.lakkulakku.domain.diary.dto.DiaryLookupResDto;
import com.efub.lakkulakku.domain.diary.dto.DiaryMessageResDto;
import com.efub.lakkulakku.domain.diary.dto.DiarySaveReqDto;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.exception.DuplicateDiaryException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

@RestController
@RequestMapping("/api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {

	private final DiaryService diaryService;
	private final DiaryRepository diaryRepository;

	@GetMapping("/{date}")
	public ResponseEntity<DiaryLookupResDto> getDiaryByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		diaryService.checkDiaryIsInDate(date);
		if (!diaryRepository.existsByDate(date))
			return ResponseEntity.ok()
					.body(new DiaryLookupResDto(null, null, null, null, null, null, null));

		Diary diary = diaryRepository.findByDate(date).orElseThrow(DiaryNotFoundException::new);
		return ResponseEntity.ok()
				.body(diaryService.getDiaryInfo(diary));
	}

	@PostMapping("/{date}")
	public ResponseEntity<DiaryMessageResDto> createDiary(@AuthUsers Users user,
														  @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		diaryService.checkDiaryIsInDate(date);
		if (diaryRepository.existsByDate(date))
			throw new DuplicateDiaryException();
		diaryService.createDiary(user, date);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_CREATE_SUCCESS));
	}

	@DeleteMapping("/{date}")
	public ResponseEntity<DiaryMessageResDto> deleteDiary(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		diaryService.deleteDiary(date);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_DELETE_SUCCESS));
	}

	@PostMapping(value = "/save/{date}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<DiaryMessageResDto> saveDiary(@AuthUsers Users user,
														@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
														@RequestPart(value = "files", required = false) List<MultipartFile> files,
														@RequestPart(value = "key") DiaryLookupResDto diaryLookupResDto) throws IOException {

		DiarySaveReqDto diarySaveReqDto = DiarySaveReqDto.builder()
				.user(user)
				.multipartFileList(files)
				.diaryLookupResDto(diaryLookupResDto)
				.build();
		diaryService.saveDiary(date, diarySaveReqDto);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_UPDATE_SUCCESS));
	}

	@PatchMapping(value = "/update/{date}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<DiaryMessageResDto> updateDiary(@AuthUsers Users user,
														  @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
														  @RequestPart(value = "files", required = false) List<MultipartFile> files,
														  @RequestPart(value = "key") DiaryLookupResDto diaryLookupResDto) throws IOException {
		DiarySaveReqDto diarySaveReqDto = DiarySaveReqDto.builder()
				.user(user)
				.multipartFileList(files)
				.diaryLookupResDto(diaryLookupResDto)
				.build();
		diaryService.updateDiary(date, diarySaveReqDto);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_UPDATE_SUCCESS));
	}

}
