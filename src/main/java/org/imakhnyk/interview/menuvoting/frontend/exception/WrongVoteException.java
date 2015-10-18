package org.imakhnyk.interview.menuvoting.frontend.exception;

public class WrongVoteException extends RuntimeException {

	public WrongVoteException() {
		super("Wrong vote");
	}

	public WrongVoteException(String message) {
		super(message);
	}

}
