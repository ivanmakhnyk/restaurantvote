package org.imakhnyk.interview.menuvoting.database.dao;

import java.util.List;

import org.imakhnyk.interview.menuvoting.database.model.VoteDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

/**
 * VoteDAO service to make CRUD operations with VoteDB model
 * 
 * @author Ivan Makhnyk
 *
 */
@Component
public interface VoteDAO extends JpaRepository<VoteDB, Long> {

	/**
	 * Get user vote by date
	 * 
	 * @param accountId
	 * @param date
	 * @return
	 */
	public VoteDB findByAccountIdAndDate(long accountId, String date);

	/**
	 * @param date
	 * @return
	 */
	@Query(value = "SELECT v.restaurantId, COUNT(v) FROM VoteDB v WHERE v.date = :date GROUP BY v.restaurantId")
	public List<Object[]> votesStat(@Param("date") String date);
}