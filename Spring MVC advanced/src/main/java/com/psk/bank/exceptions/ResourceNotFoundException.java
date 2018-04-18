package com.psk.bank.exceptions;

public class ResourceNotFoundException extends Exception {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceId;
 
    public String getResourceId() {
		return resourceId;
	}

	public ResourceNotFoundException(String resourceId) {
        this.resourceId = resourceId;
    }
}