package org.imakhnyk.interview.menuvoting.frontend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.imakhnyk.interview.menuvoting.frontend.exception.MenuNotFoundException;
import org.imakhnyk.interview.menuvoting.frontend.model.Menu;
import org.imakhnyk.interview.menuvoting.frontend.model.StatusResponse;
import org.imakhnyk.interview.menuvoting.service.MenuService;
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
 * Menu CRUS REST service
 * 
 * @author Ivan Makhnyk
 *
 */
@Api(description = "CRUD operations for Menu records")
@RestController
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@ApiOperation(value = "Load Menu by ID")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Menu getById(@PathVariable("id") long id) {
		return menuService.findById(id);
	}

	// --------- Admin only

	@ApiOperation(value = "Create Menu")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Menu create(@RequestBody @Valid Menu menu) {
		return menuService.create(menu);
	}

	@ApiOperation(value = "Delete Menu by ID")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Menu delete(@PathVariable("id") long id) {
		return menuService.delete(id);
	}

	@ApiOperation(value = "Update Menu")
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Menu update(@RequestBody @Valid Menu menu) {
		return menuService.update(menu);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public StatusResponse handleMenuNotFound(MenuNotFoundException ex) {
		return new StatusResponse("error", ex.getMessage());
	}
}