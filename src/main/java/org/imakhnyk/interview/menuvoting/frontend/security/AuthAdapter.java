package org.imakhnyk.interview.menuvoting.frontend.security;

import org.imakhnyk.interview.menuvoting.database.dao.AccountDAO;
import org.imakhnyk.interview.menuvoting.database.model.AccountDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Custom GlobalAuthenticationConfigurerAdapter
 * 
 * @author Ivan Makhnyk
 *
 */
@Configuration
class AuthAdapter extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	AccountDAO accountDao;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	/**
	 * @return custom UserDetailsService
	 */
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username)
					throws UsernameNotFoundException {
				AccountDB accountDB = accountDao.findByLogin(username);
				if (accountDB != null) {
					return new User(accountDB.getLogin(), accountDB.getPassword(),
							true, true, true, true,
							AuthorityUtils.createAuthorityList(accountDB
									.getRole().toString()));
				} else {
					throw new UsernameNotFoundException(
							"could not find the user '" + username + "'");
				}
			}

		};
	}
}
