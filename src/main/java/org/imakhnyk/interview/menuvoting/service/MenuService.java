package org.imakhnyk.interview.menuvoting.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.imakhnyk.interview.menuvoting.database.dao.MenuDAO;
import org.imakhnyk.interview.menuvoting.database.dao.RestaurantDAO;
import org.imakhnyk.interview.menuvoting.database.model.MenuDB;
import org.imakhnyk.interview.menuvoting.frontend.exception.MenuNotFoundException;
import org.imakhnyk.interview.menuvoting.frontend.exception.RestaurantNotFoundException;
import org.imakhnyk.interview.menuvoting.frontend.model.Menu;
import org.imakhnyk.interview.menuvoting.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MenuService provides CRUD operations and some additional
 * 
 * @author Ivan Makhnyk
 *
 */
@Service
public class MenuService {

	@Autowired
	private MenuDAO menuDao;

	@Autowired
	private RestaurantDAO restaurantDao;

	/**
	 * Returns all menu records from DB
	 * 
	 * @return
	 */
	public List<Menu> findAll() {
		List<Menu> all = new ArrayList<Menu>();
		Iterator<MenuDB> allDB = this.menuDao.findAll().iterator();
		return convertList(all, allDB);
	}

	/**
	 * Returns converted list DBModel -> DTO (frontend) model
	 * 
	 * @param all
	 * @param allDB
	 * @return
	 */
	private List<Menu> convertList(List<Menu> all, Iterator<MenuDB> allDB) {
		while (allDB.hasNext()) {
			MenuDB menuDB = allDB.next();
			Menu menu = new Menu();
			BeanUtils.copyProperties(menuDB, menu);
			all.add(menu);
		}
		return all;
	}

	/**
	 * Returns all menu records for specified restaurant
	 * 
	 * @param restaurantId
	 * @return
	 */
	public List<Menu> findAllForRestaurant(long restaurantId) {
		if (!restaurantDao.exists(restaurantId)) {
			throw new RestaurantNotFoundException();
		}

		List<Menu> all = new ArrayList<Menu>();
		Iterator<MenuDB> allDB = this.menuDao.findAllByRestaurantId(
				restaurantId).iterator();
		return convertList(all, allDB);
	}

	/**
	 * Returns all today's menu records for specified restaurant
	 * 
	 * @param restaurantId
	 * @return
	 */
	public List<Menu> findAllForToday(long restaurantId) {
		if (!restaurantDao.exists(restaurantId)) {
			throw new RestaurantNotFoundException();
		}
		List<Menu> all = new ArrayList<Menu>();
		Iterator<MenuDB> allDB = this.menuDao.findAllByRestaurantIdAndDate(
				restaurantId, DateUtils.getStringOfCurentDate()).iterator();
		return convertList(all, allDB);
	}

	/**
	 * Returns menu by ID
	 * 
	 * @param id
	 * @return
	 */
	public Menu findById(long id) {
		MenuDB menuDB = this.menuDao.findOne(id);
		if (menuDB == null) {
			throw new MenuNotFoundException();
		}
		Menu menu = new Menu();
		BeanUtils.copyProperties(menuDB, menu);
		return menu;
	}

	/**
	 * Creates menu record
	 * 
	 * @param menu
	 * @return
	 */
	public Menu create(Menu menu) {
		MenuDB menuDB = new MenuDB();
		BeanUtils.copyProperties(menu, menuDB);
		if (!restaurantDao.exists(menuDB.getRestaurantId())) {
			throw new RestaurantNotFoundException();
		}
		this.menuDao.save(menuDB);
		BeanUtils.copyProperties(menuDB, menu);
		return menu;
	}

	/**
	 * Delete menu record
	 * 
	 * @param id
	 * @return
	 */
	public Menu delete(long id) {
		MenuDB menuDB = this.menuDao.findOne(id);
		if (menuDB == null) {
			throw new MenuNotFoundException();
		}
		this.menuDao.delete(id);
		Menu menu = new Menu();
		BeanUtils.copyProperties(menuDB, menu);
		return menu;
	}

	/**
	 * Update menu record
	 * 
	 * @param menu
	 * @return
	 */
	public Menu update(Menu menu) {
		MenuDB menuDB = this.menuDao.findOne(menu.getId());
		if (menuDB == null) {
			throw new MenuNotFoundException();
		}
		BeanUtils.copyProperties(menu, menuDB);
		if (!restaurantDao.exists(menuDB.getRestaurantId())) {
			throw new RestaurantNotFoundException();
		}
		this.menuDao.save(menuDB);
		BeanUtils.copyProperties(menuDB, menu);
		return menu;
	}

}
