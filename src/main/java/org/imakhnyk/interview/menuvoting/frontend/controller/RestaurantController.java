package org.imakhnyk.interview.menuvoting.frontend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import org.imakhnyk.interview.menuvoting.frontend.exception.RestaurantNotFoundException;
import org.imakhnyk.interview.menuvoting.frontend.model.Menu;
import org.imakhnyk.interview.menuvoting.frontend.model.Restaurant;
import org.imakhnyk.interview.menuvoting.frontend.model.StatusResponse;
import org.imakhnyk.interview.menuvoting.service.MenuService;
import org.imakhnyk.interview.menuvoting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Restaurant CRUS REST service
 * 
 * @author Ivan Makhnyk
 *
 */
@Api(description = "CRUD operations for Restaurant records")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private MenuService menuService;

	@ApiOperation(value = "Load Restaurant by ID")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Restaurant getById(@PathVariable("id") long id) {
		return restaurantService.findById(id);
	}

	@ApiOperation(value = "Returns list of Restaurants")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.GET)
	public List<Restaurant> getAll() {
		return restaurantService.findAll();
	}

	@ApiOperation(value = "Load Restaurant Menu list for today")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "{rId}/menu/today", method = RequestMethod.GET)
	public List<Menu> getAllForToday(@PathVariable("rId") long restaurantId) {
		return menuService.findAllForToday(restaurantId);
	}

	@ApiOperation(value = "Load Restaurant Menu list")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "{rId}/menu", method = RequestMethod.GET)
	public List<Menu> getAllForRestaurant(@PathVariable("rId") long restaurantId) {
		return menuService.findAllForRestaurant(restaurantId);
	}

	// --------- Admin only

	@ApiOperation(value = "Create Restaurant")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant create(@RequestBody @Valid Restaurant restaurant) {
		return restaurantService.create(restaurant);
	}

	@ApiOperation(value = "Delete Restaurant by ID")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Restaurant delete(@PathVariable("id") long id) {
		return restaurantService.delete(id);
	}

	@ApiOperation(value = "Update Restaurant")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Restaurant update(@RequestBody @Valid Restaurant restaurant) {
		return restaurantService.update(restaurant);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public StatusResponse handleMenuNotFound(RestaurantNotFoundException ex) {
		return new StatusResponse("error", ex.getMessage());
	}
}