package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

/**
 * メンバーテーブル用のエンティティ
 */
@Entity
@Table(name = "tbl_member")
public class Member{
	
	/*
	 * メンバーID
	 */
	@Id
	@Column(name = "member_id")
	private String memberId;
	
	/**
	 * 名前
	 */
	@Column(name = "member_name")
	private String memberName;
	
	/**
	 * 年齢
	 */
	@Column(name = "age")
	private BigDecimal age;
	
	/**
	 * 性別
	 */
	@Column(name = "sex_flg")
	private BigDecimal sexFlg;
	
	/**
	 * 住所
	 */
	@Column(name = "address")
	private String address;
	
	/**
	 * 電話番号
	 */
	@Column(name = "telephone")
	private String telephone;
	
	/**
	 * メールアドレス
	 */
	@Column(name = "mail")
	private String mail;
	
	/**
	 * 役職ID
	 */
	@Column(name = "position_Id")
	private String positionId;
	
	/**
	 * 事業所ID
	 */
	@Column(name = "place_id")
	private String placeId;
	
	/**
	 * 登録日
	 */
	@Column(name = "regist_date")
	private LocalDateTime registDate;
	
	/**
	 * 更新日
	 */
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
	/**
	 * 削除フラグ
	 */
	@Column(name = "delete_flg")
	private BigDecimal deleteFlg;
	
	/**
	 * 
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
	 * Sets the updateDate.
	 * 
	 * @param updateDate the updateDate
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
	 * Sets the deleteFlg.
	 * 
	 * @param deleteFlg the deleteFlg
	 */
	public void setDeleteFlg(BigDecimal deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	
	/**
	 * 登録前処理を行う.
	 */
	@PrePersist
	public void onPrePersist() {
		LocalDateTime now = LocalDateTime.now();
		this.setRegistDate(now);
		this.setUpdateDate(now);
		this.setDeleteFlg(BigDecimal.ZERO);
	}
	
	/**
	 * 更新前処理を行う.
	 */
	@PreUpdate
	public void onPreUpdate() {
		this.setUpdateDate(LocalDateTime.now());
	}
}

