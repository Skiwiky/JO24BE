package com.studi.sapioce.JO24BE.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studi.sapioce.JO24BE.pojo.BilletDisponible;
import com.studi.sapioce.JO24BE.pojo.Enum.BilletStatut;

public interface BilletsDisponibleRepository extends JpaRepository<BilletDisponible, Long>{

	List<BilletDisponible> findBySportAndStatutAndCategoryAndDateEvent(String sport, BilletStatut statut, String category, LocalDate dateEvent);

	//permet de filtrer en fonction des parametres saisie en une seule requete
    @Query("SELECT b FROM BilletDisponible b WHERE b.statut = ?1 AND (?2 IS NULL OR b.sport = ?2) AND (?3 IS NULL OR b.category = ?3)")
    List<BilletDisponible> findByStatutAndOptionalSportAndCategory(BilletStatut statut, String sport, String category);

}
