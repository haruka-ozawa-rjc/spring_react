package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/**
	 * 
	 * @param memberId メンバーid
	 * @return idが存在する場合true
	 */
	@GetMapping("/members/insert/check/{memberId}")
	private boolean checkMemberId(@PathVariable String memberId) {
		return membersService.existsByMemberId(memberId);
	}
	
	/**
	 * 詳細画面の表示
	 * 
	 * @param id ユーザーid
	 * @param model ビューに渡す引数
	 * @param redirAttrs リダイレクトの引数
	 * @return ユーザーが存在する場合：詳細画面を表示、いない場合：一覧画面へリダイレクト
	 */
	@GetMapping("/members/detail/{id}")
	private ResponseEntity<?> showDetail(@PathVariable String id) {
		
		try {
			
//			サービスクラスからidでmemberDtoを取得
			MemberDto memberDto = membersService.findByMemberId(id);
			
//			フロントに送るhashmapを用意
			HashMap<String, Object> response = new HashMap<>();
			
//			viewに渡す
			response.put("member", memberDto);
			response.put("positions", positionsService.getAll());
			response.put("places", placeService.getAll());
			
//			詳細画面を表示
			return ResponseEntity.ok(response);
			
		} catch (NotFoundException e) {
			
//		    メンバーが見つからない場合、エラーメッセージを返す
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
			
		} catch (ConnectionException | DatabaseException e) {
		
//			データベースのエラーで、エラーメッセージを返す
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
		
		} 
	}
	

	/**
	 * 更新画面の表示
	 */
	@GetMapping("/members/update/{id}")
	private ResponseEntity<?> showUpdate(@PathVariable String id) {
		
		try {	
//				サービスクラスからidでmemberDtoを取得
				MemberDto memberDto = membersService.findByMemberId(id);
//				更新前のidをセットして渡す
				memberDto.setBeforeMemberId(memberDto.getMemberId());
				
//				フロントに渡すリストを作成
				HashMap<String, Object> response = new HashMap<>();
					
//				フロントに渡す
				response.put("member", memberDto);
				response.put("positions", positionsService.getAll());
				response.put("places", placeService.getAll());
				
//					更新画面を表示
				return ResponseEntity.ok(response);
			
		} catch (NotFoundException e) {
			
//		    メンバーが見つからない場合、エラーメッセージを返す
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
			
		} catch (ConnectionException | DatabaseException e) {
			
//		    データベースのエラーで、エラーメッセージを返す
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
		}
	}
	
	/**
	 * 更新機能
	 */
	@PostMapping("/members/updateComp")
	private ResponseEntity<?> update(@RequestBody MemberDto memberDto) {
		
		try {
			
//			サービスクラスでメンバー新規登録
			membersService.update(memberDto);
			
			return ResponseEntity.ok().build();
			
		} catch (NotFoundException e) {
			
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
		
		} catch (ConnectionException | DatabaseException e) {
			
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
		
		} 
	}
	
	/**
	 * 削除画面の表示
	 */
	@GetMapping("/members/delete/{id}")
	private ResponseEntity<?> showDelete(@PathVariable String id) {
		
		try {
			
//			サービスクラスからidでmemberDtoを取得
			MemberDto memberDto = membersService.findByMemberId(id);
			
//			リストを作成
			HashMap<String, Object> response = new HashMap<>();
			
//			フロントに渡す
			response.put("member", memberDto);
			response.put("positions", positionsService.getAll());
			response.put("places", placeService.getAll());
			
//			削除画面を表示
			return ResponseEntity.ok(response);
			
		} catch (NotFoundException e) {
			
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
			
		} catch (ConnectionException | DatabaseException e) {
			
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
		
		
		} 
	}
	
	/**
	 * 削除機能
	 */
	@PostMapping("/members/deleteComp")
	private ResponseEntity<?> delete(@RequestBody MemberDto memberDto) {
		
		try {
			
//			削除
			MemberDto deleteMember = membersService.delete(memberDto);
			
//			リストを生成
			HashMap<String, Object> response = new HashMap<>();
			
//			フロントに渡す
			response.put("member", deleteMember);
			response.put("positions", positionsService.getAll());
			response.put("places", placeService.getAll());
			
//			削除できた場合、削除完了ページに遷移
			return ResponseEntity.ok(response);
		
			
		} catch (NotFoundException e) {
			
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());
			
		} catch (ConnectionException | DatabaseException e) {
			
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(e.getMessage());

		} 
	}
}
