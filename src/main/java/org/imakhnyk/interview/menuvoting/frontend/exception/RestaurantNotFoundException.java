package org.imakhnyk.interview.menuvoting.frontend.exception;

public class RestaurantNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8097478741514643840L;

	public RestaurantNotFoundException() {
		super("Restaurant not found");
	}

}
