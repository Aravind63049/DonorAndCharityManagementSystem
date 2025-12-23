package com.Aravind.DonorAndCharityManagementSystem.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, Object resourceId) {
        super(resourceName + " not found with ID: " + resourceId);
    }
}
