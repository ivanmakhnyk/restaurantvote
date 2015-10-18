package org.imakhnyk.interview.menuvoting;

import javax.annotation.PostConstruct;

import org.imakhnyk.interview.menuvoting.database.dao.AccountDAO;
import org.imakhnyk.interview.menuvoting.database.dao.MenuDAO;
import org.imakhnyk.interview.menuvoting.database.dao.RestaurantDAO;
import org.imakhnyk.interview.menuvoting.database.model.AccountDB;
import org.imakhnyk.interview.menuvoting.database.model.MenuDB;
import org.imakhnyk.interview.menuvoting.database.model.RestaurantDB;
import org.imakhnyk.interview.menuvoting.database.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main application config
 * 
 * @author Ivan Makhnyk
 *
 */
@Configuration
@EnableSwagger2
public class AppConfig {

	@Autowired
	private AccountDAO accountDao;

	@Autowired
	private RestaurantDAO restaurantDao;

	@Autowired
	private MenuDAO menuDao;

	/**
	 * Populate mock DB data. Should be removed when connected to real DB
	 * 
	 */
	@PostConstruct
	private void populateMockDB() {
		accountDao.save(new AccountDB("user", "userp", Role.USER));
		accountDao.save(new AccountDB("user1", "user1p", Role.USER));
		accountDao.save(new AccountDB("user2", "user2p", Role.USER));

		accountDao.save(new AccountDB("admin", "adminp", Role.ADMIN));
		accountDao.save(new AccountDB("admin1", "admin1p", Role.ADMIN));

		RestaurantDB restaurantDB1 = new RestaurantDB("Restaurant Perkins");
		RestaurantDB restaurantDB2 = new RestaurantDB(
				"Restaurant Cracker Barrel");
		RestaurantDB restaurantDB3 = new RestaurantDB(
				"Restaurant le Jules Verne - Tour Eiffel Paris");
		restaurantDao.save(restaurantDB1);
		restaurantDao.save(restaurantDB2);
		restaurantDao.save(restaurantDB3);
		menuDao.save(new MenuDB(
				"Salads & Soups",
				"Dressings: White Balsamic Vinaigrette, Blue Cheese, French, Honey Mustard, Buttermilk Ranch and Thousand Island",
				9.99, restaurantDB1.getId()));
		menuDao.save(new MenuDB(
				"Angus Burgers",
				"Our USDA 100% Angus burgers are specially seasoned and cooked medium well",
				9.99, restaurantDB1.getId()));
		menuDao.save(new MenuDB(
				"Melts and Sandwiches",
				"Served with choice of fries, tots, garden salad, fruit or cup of soup. Sub Onion Rings for an additional charge",
				9.99, restaurantDB2.getId()));
		menuDao.save(new MenuDB("Fork-Worthy Entrées",
				"Served with a dinner roll and choice of two sides.", 9.99,
				restaurantDB2.getId()));
		menuDao.save(new MenuDB(
				"Vegetables n' Sides",
				"From our hashbrown casserole, to our home made dumplins, our traditional country style vegetables are prepared in our very own kitchens for you to enjoy.",
				9.99, restaurantDB3.getId()));
		menuDao.save(new MenuDB("Country Salads",
				"Fresh greens tossed with a bit of country love.", 9.99,
				restaurantDB3.getId()));
		menuDao.save(new MenuDB(
				"Sandwich Platters",
				"Classic sandwiches served with a side of southern hospitality.",
				9.99, restaurantDB3.getId()));
	}

	/**
	 * Provides Swagger API docs and test client.
	 * 
	 * Accessible by /swagger-ui.html or /v2/api-docs
	 * 
	 * @return
	 */
	@Bean
	public Docket menuVotingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("org.imakhnyk.interview.menuvoting.frontend"))
				.paths(PathSelectors.any()).build();
	}
}