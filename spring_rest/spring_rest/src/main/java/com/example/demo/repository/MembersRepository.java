package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Repository
public interface MembersRepository extends JpaRepository<Member, String>{
	
//	List<Members> findBy(String mail);
	
	Boolean existsByMemberId(String memberId);
	
	Optional<Member> findByMemberId(String memberId);
	
	List<Member> findAllByDeleteFlg(BigDecimal deleteFlg, Sort sort);
}
