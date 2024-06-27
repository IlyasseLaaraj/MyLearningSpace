package com.advancia.exceptions;

import org.hibernate.exception.ConstraintViolationException;

import com.advancia.models.enums.FailureReason;

public class InvalidAddException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final FailureReason reason;
	
	public InvalidAddException(String message, Throwable t) {
		super(message,t);
		if( t instanceof ConstraintViolationException) {
			reason = FailureReason.CONTRAINT_VIOLATION;
		}else{
			reason = FailureReason.BAD_DATA;
		}
	}
	
	public FailureReason getReason() {
		return reason;
	}

}
