package com.jobs.jobstrackingportal.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
 
private String message;
private Boolean success;
  
  public ApiResponse(String string, boolean b) {
		this.message =string;
		this.success =b;
	}
}
