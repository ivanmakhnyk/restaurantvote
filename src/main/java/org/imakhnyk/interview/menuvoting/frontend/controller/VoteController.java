package org.imakhnyk.interview.menuvoting.frontend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.imakhnyk.interview.menuvoting.frontend.exception.MenuNotFoundException;
import org.imakhnyk.interview.menuvoting.frontend.exception.WrongVoteException;
import org.imakhnyk.interview.menuvoting.frontend.model.StatusResponse;
import org.imakhnyk.interview.menuvoting.frontend.model.VoteStatItem;
import org.imakhnyk.interview.menuvoting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * VoteController to manage voting and statistis
 * 
 * @author Ivan Makhnyk
 *
 */
@Api(description = "Vote and voting results service")
@Controller
@RestController
@RequestMapping("/vote")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@ApiOperation(value = "Return list of restaurant votes for today")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.GET)
	public List<VoteStatItem> getStatForToday() {
		return voteService.voteStatForToday();
	}

	@ApiOperation(value = "Return list of restaurant votes for specified date")
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "{date}", method = RequestMethod.GET)
	public List<VoteStatItem> getStat(@PathVariable("date") String date) {
		return voteService.voteStat(date);
	}

	@ApiOperation(value = "Vote for specified restaurant")
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public StatusResponse vote(@PathVariable("id") long menuId) {
		String userLogin = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		voteService.vote(menuId, userLogin);
		return new StatusResponse("ok", "vote counted");
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public StatusResponse handleMenuNotFound(MenuNotFoundException ex) {
		return new StatusResponse("error", ex.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusResponse handleWrongVote(WrongVoteException ex) {
		return new StatusResponse("error", ex.getMessage());
	}
}