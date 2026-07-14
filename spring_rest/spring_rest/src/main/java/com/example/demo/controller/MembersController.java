package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDto;
import com.example.demo.exception.ConnectionException;
import com.example.demo.exception.DatabaseException;
import com.example.demo.service.MembersService;
import com.example.demo.service.PlacesService;
import com.example.demo.service.PositionsService;

/**
 * メンバー機能のコントロールクラス
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class MembersController {
	
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private PlacesService placeService;
	
	@Autowired
	private PositionsService positionsService;
	
	/**
	 * メンバー一覧機能の表示
	 */
	@GetMapping("/members/list")
	private ResponseEntity<?> showMembers(
			@RequestParam(name = "sortField", required = false, defaultValue = "memberId") String sortField, 
			@RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
		
//		サービスクラスからMemberDtoインスタンスが格納されたPageクラスを受け取る
		try {
			
			HashMap<String, Object> response = new HashMap<>();
			
			Page<MemberDto> membersDtoPage = membersService.getAll(sortField, sortDirection);
			response.put("members", membersDtoPage.getContent());
			response.put("positions", positionsService.getAll());
			response.put("places", placeService.getAll());
			
//			メンバー一覧を表示する
			return ResponseEntity.ok(response);
		
//		接続に関する例外
		} catch (ConnectionException e) {
			
			return ResponseEntity
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(e.getMessage());	
		
//		DBの例外
		} catch(DatabaseException e) {
			
			return ResponseEntity
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(e.getMessage());	
		}
	}
	
	/**
	 * 新規登録画面の表示
	 */
	@RequestMapping("/members/insert")
	private ResponseEntity<?> showInsert() {
		
		HashMap<String, Object> response = new HashMap<>();

		response.put("positions", positionsService.getAll());
		response.put("places", placeService.getAll());
		
		return ResponseEntity.ok(response);
	}

	/**
	 * 新規登録機能
	 */
	@PostMapping("/members/insertComp")
	private ResponseEntity<?> add(@RequestBody MemberDto memberDto) {
		
//		サービスクラスでメンバー新規登録
		try {
			
			membersService.insert(memberDto);
			
			return ResponseEntity.ok().build();
			
		} catch (ConnectionException e) {
			
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());	
		
		} catch(DatabaseException e) {
			
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());	
		}
	}
}
