package com.taiyue.tool.common.exception;

public class DinfoDaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DinfoDaoException(Throwable ex) {
		super(ex);
	}

	public DinfoDaoException(RuntimeException ex) {
		super(ex);
	}
}
