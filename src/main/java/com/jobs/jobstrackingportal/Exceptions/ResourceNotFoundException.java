package com.jobs.jobstrackingportal.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	String resourceName;
	String fieldName;
	long fieldVlaue;
	String feildValueString;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldVlaue) {
		super(String.format("%s is not found with %s : %s",resourceName,fieldName,fieldVlaue));
		this.resourceName= resourceName;
		this.fieldName = fieldName;
		this.fieldVlaue = fieldVlaue;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String feildValueString) {
		super(String.format("%s is not found with %s : %s",resourceName,fieldName,feildValueString));
		this.resourceName= resourceName;
		this.fieldName = fieldName;
		this.feildValueString = feildValueString;
	}

	public ResourceNotFoundException(String string) {
		// TODO Auto-generated constructor stub
	}
	
	
	

}
