package org.imakhnyk.interview.menuvoting.database.dao;

import org.imakhnyk.interview.menuvoting.database.model.RestaurantDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * RestaurantDAO service to make CRUD operations with RestaurantDB model
 * 
 * @author Ivan Makhnyk
 *
 */
@Component
public interface RestaurantDAO extends CrudRepository<RestaurantDB, Long> {

}