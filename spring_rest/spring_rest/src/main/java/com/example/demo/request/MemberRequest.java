package com.example.demo.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * メンバー登録画面・メンバー更新画面用フォーム
 */
public class MemberRequest {
	
	/**
	 * id
	 */
	@NotBlank(message = "IDは必須です")
	@Length(max = 10)
	private String memberId;
	/**
	 * 名前
	 */
	@NotBlank(message = "名前は必須です")
	@Length(max = 40)
	private String memberName;
	/**
	 * 年齢
	 */
	@Max(999)
	private BigDecimal age;
	/**
	 * 性別フラグ
	 */
	@NotNull(message = "{NotBlank}")
	private BigDecimal sexFlg;
	/**
	 * 住所
	 */
	@NotBlank(message = "住所は必須です")
	@Length(max = 50)
	private String address;
	/**
	 * 電話番号
	 */
	@Length(max = 11)
	private String telephone;
	/**
	 * メールアドレス
	 */
	@Length(max = 20)
	private String mail;
	/**
	 * 役職id
	 */
	private String positionId;
	/**
	 * 事業所id
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
	 * 更新前のメンバーid
	 */
	private  String beforeMemberId;
	
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
	 * @return beforeid
	 */
	public String getBeforeMemberId() {
		return beforeMemberId;
	}

	/** 
	 * Sets the beforeid.
	 * 
	 * @param id the beforeid
	 */
	public void setBeforeMemberId(String beforeMemberId) {
		this.beforeMemberId = beforeMemberId;
	}
}
