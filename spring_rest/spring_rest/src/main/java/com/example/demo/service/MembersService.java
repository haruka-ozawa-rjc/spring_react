package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.Member;
import com.example.demo.exception.ConnectionException;
import com.example.demo.exception.DatabaseException;
import com.example.demo.repository.MembersRepository;

/**
 * メンバー機能のサービスクラス
 */
@Service
public class MembersService {
	
	@Autowired
	private MembersRepository membersRepository;
	
	/**
	 * メンバー一覧を返す
	 * @param sortField ソート対象カラム
	 * @param sortDirection ソート順
	 * @return Page<MemberDto> ページネートされたメンバー一覧
	 */
	public Page<MemberDto> getAll(String sortField, String sortDirection) {
		
//		検索条件を用意
//		sortDirectionが「ASC」の場合昇順で、それ以外は降順を指定
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
						? Sort.by(sortField).ascending()
						: Sort.by(sortField).descending();
		
//		検索処理を実行（SELECT文を発行するfindAllを呼び出す）
//		findAllにsortを渡す
		
		try {
			
			List<Member> members = membersRepository.findAllByDeleteFlg(BigDecimal.ZERO, sort);
			
//			EntityをDtoに変換
//			ラムダ式とストリームでMemberDtoクラスの「convertEntityToDto」を呼び出し、
//			検索結果のすべてをそれぞれDtoに入れ替える
			List<MemberDto> memberList = members.stream()
											.map(MemberDto::convertEntityToDto)
											.collect(Collectors.toList());
			
//			DtoリストをPage型に再構築して返す
			return new PageImpl<MemberDto>(memberList);
			
		} catch (CannotGetJdbcConnectionException e) {
			
			throw new ConnectionException("データベースに接続できません", e);

		} catch (DataAccessException e) {
			
			throw new DatabaseException("データベースへの取得中にエラーが発生しました。", e);

		}
	}
	
	/**
	 * 社員IDの重複チェック
	 * 
	 * @param memberId メンバーid
	 * @return idが存在する場合true
	 */
	public boolean existsById(String memberId) {
		
		if(membersRepository.existsByMemberId(memberId)) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * メンバー登録処理
	 */
	public Member insert(MemberDto memberDto) {
		
//		dtoからエンティティに変換
		Member member = MemberDto.convertDtoToEntity(memberDto);
		
		try {
			
//			リポジトリに保存
			membersRepository.save(member);
			return member;
			
		} catch (CannotGetJdbcConnectionException e) {
			
			throw new ConnectionException("データベースに接続できません", e);

		} catch (DataAccessException e) {
			
			throw new DatabaseException("データベースへの登録中にエラーが発生しました。", e);

		}
	}
	
	/**
	 * idでmemberDtoを検索して返す
	 */
	public MemberDto findByMemberId(String id) throws NotFoundException {
		
		try {
			
//			idで検索したmemberをrepositoryから取得
			Optional<Member> member = membersRepository.findByMemberId(id);
			
			if(member.isEmpty()) {
				
				throw new NotFoundException();
			}
			
			return MemberDto.convertEntityToDto(member.get());
			
		} catch (CannotGetJdbcConnectionException e) {
			
			throw new ConnectionException("データベースに接続できません", e);

		} catch (DataAccessException e) {
			
			throw new DatabaseException("データベースへの取得中にエラーが発生しました。", e);

		}
	}
	
	/**
	 * 更新機能
	 * 
	 * @param memberForm 入力されたフォーム
	 */
	public Member update(MemberDto memberDto) throws NotFoundException{
		
		try {
			
//			dtoのidからエンティティを検索
			Optional<Member> beforeMemberOpt = membersRepository.findByMemberId(memberDto.getBeforeMemberId());
			
//			該当するメンバーがいない場合、例外投げる
			if(beforeMemberOpt.isEmpty()) {
				
				throw new NotFoundException();
			}
			
//			エンティティを取得
			Member beforeMember = beforeMemberOpt.get();
		
//			dtoから書き換えるエンティティを取得
			Member member = MemberDto.convertDtoToEntity(memberDto);
		
//			更新前の値をセット
			member.setRegistDate(beforeMember.getRegistDate());
			member.setDeleteFlg(beforeMember.getDeleteFlg());
			
			membersRepository.save(member);
			return member;
		
//		データベースに接続できないとき
		} catch (CannotGetJdbcConnectionException e) {
			
			throw new ConnectionException("データベースに接続できません", e);
			
		} catch(DataAccessException e) {
		
			throw new DatabaseException("データベースへの更新中にエラーが発生しました。", e);
		}
	}
	
	/**
	 * idでmemberを検索して返す
	 */
	public Member findById(String id) {
		
//		idで検索したmemberをrepositoryから取得
		Optional<Member> member = membersRepository.findByMemberId(id);
		
		if(member.isEmpty()) {
			return null;
		}
		
		return member.get();
	}
	
	/**
	 * メンバー論理削除の機能
	 */
	public MemberDto delete(String id) throws NotFoundException{
		
//		保存
		try {
			
//			idよりoptionalインスタンスを取得
			Optional<Member> memberOpt = membersRepository.findById(id);
			
//			空の場合例外投げる
			if(memberOpt.isEmpty()) {
				throw new NotFoundException();
			}
			
//			エンティティを取得
			Member member = memberOpt.get();
			
//			削除フラグ更新
			member.setDeleteFlg(BigDecimal.ONE);
			
			membersRepository.save(member);
//			dtoを取得
			return MemberDto.convertEntityToDto(member);
		
		} catch (CannotGetJdbcConnectionException e) {
			
			throw new ConnectionException("データベースに接続できません", e);
			
//		データベースに接続できないとき
		} catch(DataAccessException e) {
		
			throw new DatabaseException("データベースへの削除中にエラーが発生しました。", e);
		}
	}
}
