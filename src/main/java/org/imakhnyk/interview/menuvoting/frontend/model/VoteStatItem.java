package org.imakhnyk.interview.menuvoting.frontend.model;

/**
 * DTO model for frontend
 * 
 * @author Ivan Makhnyk
 *
 */
public class VoteStatItem {
	private long count;
	private long restaurantId;
	private String restaurantName;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

}
