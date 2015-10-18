package org.imakhnyk.interview.menuvoting.database.dao;

import org.imakhnyk.interview.menuvoting.database.model.AccountDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * AccountDAO service to make CRUD operations with Account model
 * 
 * @author Ivan Makhnyk
 *
 */
@Component
public interface AccountDAO extends CrudRepository<AccountDB, Long> {

	/**
	 * Return the user having the passed login or null if no user is found.
	 * 
	 * @param email
	 *            the user email.
	 */
	public AccountDB findByLogin(String login);

}