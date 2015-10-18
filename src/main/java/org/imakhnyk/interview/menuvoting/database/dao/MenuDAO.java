package org.imakhnyk.interview.menuvoting.database.dao;

import java.util.List;

import org.imakhnyk.interview.menuvoting.database.model.MenuDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * MenuDAO service to make CRUD operations with MenuDAO model
 * 
 * @author Ivan Makhnyk
 *
 */
@Component
public interface MenuDAO extends CrudRepository<MenuDB, Long> {

	/**
	 * Find all menus by Restaurant and Date
	 * 
	 * @param restaurantId
	 * @param date
	 * @return
	 */
	public List<MenuDB> findAllByRestaurantIdAndDate(long restaurantId,
			String date);

	/**
	 * Find all menus by Restaurant
	 * 
	 * @param restaurantId
	 * @return
	 */
	public List<MenuDB> findAllByRestaurantId(long restaurantId);
}