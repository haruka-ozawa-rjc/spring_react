package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Position;
import com.example.demo.repository.PositionsRepository;

/**
 * 役職のサービスクラス
 */
@Service
public class PositionsService {
	
	@Autowired
	private PositionsRepository positionsRepository;
	
	public List<Position> getAll() {
		return positionsRepository.findAll();
	}
}
