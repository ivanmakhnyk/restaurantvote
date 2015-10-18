package org.imakhnyk.interview.menuvoting.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.imakhnyk.interview.menuvoting.database.dao.RestaurantDAO;
import org.imakhnyk.interview.menuvoting.database.model.RestaurantDB;
import org.imakhnyk.interview.menuvoting.frontend.exception.RestaurantNotFoundException;
import org.imakhnyk.interview.menuvoting.frontend.model.Restaurant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RestaurantService provides CRUD operations and some additional
 * 
 * @author Ivan Makhnyk
 *
 */
@Service
public class RestaurantService {

	@Autowired
	private RestaurantDAO restaurantDao;

	/**
	 * Returns all restaurant records from database
	 * 
	 * @return
	 */
	public List<Restaurant> findAll() {
		List<Restaurant> all = new ArrayList<Restaurant>();
		Iterator<RestaurantDB> allDB = this.restaurantDao.findAll().iterator();
		while (allDB.hasNext()) {
			RestaurantDB restaurantDB = allDB.next();
			Restaurant restaurant = new Restaurant();
			BeanUtils.copyProperties(restaurantDB, restaurant);
			all.add(restaurant);
		}
		return all;
	}

	/**
	 * Load by ID
	 * 
	 * @param id
	 * @return
	 */
	public Restaurant findById(long id) {
		RestaurantDB restaurantDB = this.restaurantDao.findOne(id);
		if (restaurantDB == null) {
			throw new RestaurantNotFoundException();
		}
		Restaurant restaurant = new Restaurant();
		BeanUtils.copyProperties(restaurantDB, restaurant);
		return restaurant;
	}

	/**
	 * Create Restaurant
	 * 
	 * @param restaurant
	 * @return
	 */
	public Restaurant create(Restaurant restaurant) {
		RestaurantDB restaurantDB = new RestaurantDB();
		BeanUtils.copyProperties(restaurant, restaurantDB);
		this.restaurantDao.save(restaurantDB);
		BeanUtils.copyProperties(restaurantDB, restaurant);
		return restaurant;
	}

	/**
	 * Delete restaurant
	 * 
	 * @param id
	 * @return
	 */
	public Restaurant delete(long id) {
		RestaurantDB restaurantDB = this.restaurantDao.findOne(id);
		if (restaurantDB == null) {
			throw new RestaurantNotFoundException();
		}
		this.restaurantDao.delete(id);
		Restaurant restaurant = new Restaurant();
		BeanUtils.copyProperties(restaurantDB, restaurant);
		return restaurant;
	}

	/**
	 * Update restaurant
	 * 
	 * @param restaurant
	 * @return
	 */
	public Restaurant update(Restaurant restaurant) {
		RestaurantDB restaurantDB = this.restaurantDao.findOne(restaurant
				.getId());
		if (restaurantDB == null) {
			throw new RestaurantNotFoundException();
		}
		BeanUtils.copyProperties(restaurant, restaurantDB);
		this.restaurantDao.save(restaurantDB);
		BeanUtils.copyProperties(restaurantDB, restaurant);
		return restaurant;
	}

}
