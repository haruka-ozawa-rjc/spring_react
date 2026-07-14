package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Place;
import com.example.demo.repository.PlacesRepository;

/**
 * 事業所のサービスクラス
 */
@Service
public class PlacesService {
	
	@Autowired
	public PlacesRepository placesRepository;
	
	/**
	 * DB登録されている全事業所情報を返す
	 */
	public List<Place> getAll() {
		return placesRepository.findAll();
	}
}
