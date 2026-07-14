package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.entity.Member;
import com.example.demo.request.MemberRequest;

/**
 * メンバーDTO
 * 
 * @see MemberForm
 * @see Member
 */
public class MemberDto {
	
	/*
	 * メンバーID
	 */
	private String memberId;
	/**
	 * 名前
	 */
	private String memberName;
	/**
	 * 年齢
	 */
	private BigDecimal age;
	/**
	 * 性別
	 */
	private BigDecimal sexFlg;
	/**
	 * 住所
	 */
	private String address;
	/**
	 * 電話番号
	 */
	private String telephone;
	/**
	 * メールアドレス
	 */
	private String mail;
	/**
	 * 役職ID
	 */
	private String positionId;
	/**
	 * 事業所ID
	 */
	private String placeId;
	/**
	 * 登録日
	 */
	private LocalDateTime registDate;
	/**
	 * 更新日
	 */
	private LocalDateTime updateDate;
	/**
	 * 削除フラグ
	 */
	private BigDecimal deleteFlg;
	/**
	 * 以前のメンバーid
	 */
	private String beforeMemberId;
	
	/**
	 * @return id
	 */
	public String getMemberId() {
		return memberId;
	}

	/** 
	 * Sets the id.
	 * 
	 * @param id the id
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	/** 
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public String getMemberName() {
		return memberName;
	}

	/** 
	 * Sets the name.
	 * 
	 * @param name the name
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	/** 
	 * Returns the age.
	 * 
	 * @return the age
	 */
	public BigDecimal getAge() {
		return age;
	}

	/** 
	 * Sets the age.
	 * 
	 * @param age the age
	 */
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	
	/** 
	 * Returns the sex.
	 * 
	 * @return the sex
	 */
	public BigDecimal getSexFlg() {
		return sexFlg;
	}

	/** 
	 * Sets the sex.
	 * 
	 * @param sex the sex
	 */
	public void setSexFlg(BigDecimal sexFlg) {
		this.sexFlg = sexFlg;
	}
	
	/** 
	 * Returns the address.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/** 
	 * Sets the address.
	 * 
	 * @param address the address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/** 
	 * Returns the tel.
	 * 
	 * @return the tel
	 */
	public String getTelephone() {
		return telephone;
	}

	/** 
	 * Sets the tel.
	 * 
	 * @param tel the tel
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/** 
	 * Returns the mail.
	 * 
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/** 
	 * Sets the mail.
	 * 
	 * @param mail the mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/** 
	 * Returns the postion.
	 * 
	 * @return the postion
	 */
	public String getPositionId() {
		return positionId;
	}

	/** 
	 * Sets the positionId.
	 * 
	 * @param positionId the positionId
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	
	/** 
	 * Returns the placeId.
	 * 
	 * @return the placeId
	 */
	public String getPlaceId() {
		return placeId;
	}

	/** 
	 * Sets the placeId.
	 * 
	 * @param placeId the placeId
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	/** 
	 * Returns the registDate.
	 * 
	 * @return the registDate
	 */
	public LocalDateTime getRegistDate() {
		return registDate;
	}

	/** 
	 * Sets the registDate.
	 * 
	 * @param registDate the registDate
	 */
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	
	/** 
	 * Returns the updateDate.
	 * 
	 * @return the updateDate
	 */
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	/** 
	 * Sets the placeId.
	 * 
	 * @param placeId the placeId
	 */
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	/** 
	 * Returns the deleteFlg.
	 * 
	 * @return the deleteFlg
	 */
	public BigDecimal getDeleteFlg() {
		return deleteFlg;
	}

	/** 
	 * Sets the placeId.
	 * 
	 * @param placeId the placeId
	 */
	public void setDeleteFlg(BigDecimal deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	
	/**
	 * @return id
	 */
	public String getBeforeMemberId() {
		return beforeMemberId;
	}

	/** 
	 * Sets the id.
	 * 
	 * @param id the id
	 */
	public void setBeforeMemberId(String beforeMemberId) {
		this.beforeMemberId = beforeMemberId;
	}
	
	/**
	 * 
	 * @param member 変換前
	 * @return 変換したmemberDto
	 */
	public static final MemberDto convertEntityToDto(Member member) {
		
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemberId(member.getMemberId());
		memberDto.setMemberName(member.getMemberName());
		memberDto.setAge(member.getAge());
		memberDto.setSexFlg(member.getSexFlg());
		memberDto.setAddress(member.getAddress());
		memberDto.setTelephone(member.getTelephone());
		memberDto.setMail(member.getMail());
		memberDto.setPositionId(member.getPositionId());
		memberDto.setPlaceId(member.getPlaceId());
		memberDto.setRegistDate(member.getRegistDate());
		memberDto.setUpdateDate(member.getUpdateDate());
		memberDto.setDeleteFlg(member.getDeleteFlg());
		
		return memberDto;
	}
	
	/**
	 * 
	 * @param memberForm 変換前
	 * @return 変換したmemberDto
	 */
	public static final MemberDto convertRequestToDto(MemberRequest memberRequest) {
		
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemberId(memberRequest.getMemberId());
		memberDto.setMemberName(memberRequest.getMemberName());
		memberDto.setAge(memberRequest.getAge());
		memberDto.setSexFlg(memberRequest.getSexFlg());
		memberDto.setAddress(memberRequest.getAddress());
		memberDto.setTelephone(memberRequest.getTelephone());
		memberDto.setMail(memberRequest.getMail());
		memberDto.setPositionId(memberRequest.getPositionId());
		memberDto.setPlaceId(memberRequest.getPlaceId());
		memberDto.setRegistDate(memberRequest.getRegistDate());
		memberDto.setUpdateDate(memberRequest.getUpdateDate());
		memberDto.setDeleteFlg(memberRequest.getDeleteFlg());
		memberDto.setBeforeMemberId(memberRequest.getBeforeMemberId());
		
		return memberDto;
	}
	
	/**
	 * 
	 * @param memberDto 変換前
	 * @return 変換したmembers
	 */
	public static final Member convertDtoToEntity(MemberDto memberDto) {
		
		Member members = new Member();
		
		members.setMemberId(memberDto.getMemberId());
		members.setMemberName(memberDto.getMemberName());
		members.setAge(memberDto.getAge());
		members.setSexFlg(memberDto.getSexFlg());
		members.setAddress(memberDto.getAddress());
		members.setTelephone(memberDto.getTelephone());
		members.setMail(memberDto.getMail());
		members.setPositionId(memberDto.getPositionId());
		members.setPlaceId(memberDto.getPlaceId());
		members.setRegistDate(memberDto.getRegistDate());
		members.setUpdateDate(memberDto.getUpdateDate());
		members.setDeleteFlg(memberDto.getDeleteFlg());
		
		return members;
	}
	
	/**
	 * 
	 * @param memberDto 変換前
	 * @return 変換したmemberForm
	 */
	public static final MemberRequest convertDtoToForm(MemberDto memberDto) {
		
		MemberRequest memberRequest = new MemberRequest();
		
		memberRequest.setMemberId(memberDto.getMemberId());
		memberRequest.setMemberName(memberDto.getMemberName());
		memberRequest.setAge(memberDto.getAge());
		memberRequest.setSexFlg(memberDto.getSexFlg());
		memberRequest.setAddress(memberDto.getAddress());
		memberRequest.setTelephone(memberDto.getTelephone());
		memberRequest.setMail(memberDto.getMail());
		memberRequest.setPositionId(memberDto.getPositionId());
		memberRequest.setPlaceId(memberDto.getPlaceId());
		memberRequest.setRegistDate(memberDto.getRegistDate());
		memberRequest.setUpdateDate(memberDto.getUpdateDate());
		memberRequest.setDeleteFlg(memberDto.getDeleteFlg());
		memberRequest.setBeforeMemberId(memberDto.getBeforeMemberId());
		
		return memberRequest;
	}
}
