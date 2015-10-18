package org.imakhnyk.interview.menuvoting.frontend.exception;

public class MenuNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8097478741514643840L;

	public MenuNotFoundException() {
		super("Menu not found");
	}

}
