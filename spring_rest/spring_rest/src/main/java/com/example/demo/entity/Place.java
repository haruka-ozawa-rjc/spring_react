package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 事業所のエンティティ
 */
@Entity
@Table(name = "mst_place")
public class Place {
	
	/**
	 * 事業所id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "place_id")
	private String id;
	
	/**
	 * 事業所名
	 */
	@Column(name = "place_name")
	private String placeName;
	
	/**
	 * 事業所idを返す
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 事業所idをセットする
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 事業所名を返す
	 */
	public String getPlaceName() {
		return placeName;
	}
	
	/**
	 * 事業所名をセットする
	 */
	public void setName(String placeName) {
		this.placeName = placeName;
	}
}
