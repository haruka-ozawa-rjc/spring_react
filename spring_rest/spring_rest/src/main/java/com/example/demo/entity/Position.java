package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 役職のエンティティ
 */
@Entity
@Table(name = "mst_position")
public class Position {
	
	/**
	 * 役職id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "position_id")
	private String id;
	
	/**
	 * 役職名
	 */
	@Column(name = "position_name")
	private String positionName;
	
	/**
	 * 役職idを返す
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 役職idをセットする
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 役職名を返す
	 */
	public String getPositionName() {
		return positionName;
	}
	
	/**
	 * 役職名をセットする
	 */
	public void setName(String positionName) {
		this.positionName = positionName;
	}
}