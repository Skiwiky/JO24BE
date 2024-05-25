package com.studi.sapioce.JO24BE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studi.sapioce.JO24BE.pojo.Billet;

public interface BilletsRepository extends JpaRepository<Billet, Long> {
	
	 List<Billet> findByUserId(Long userId);
	 
	 boolean existsByReservatioKey(String reservationKey);
}
