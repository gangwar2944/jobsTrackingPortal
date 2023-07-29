package com.jobs.jobstrackingportal.Service;

import com.jobs.jobstrackingportal.Dtos.UserDto;

import java.util.List;

public interface UserService {
  
	 UserDto registerNewuser(UserDto user);
	
	 UserDto createUser(UserDto user);
	 
	 UserDto updateUser(UserDto user,Long userId);
	 
	 UserDto getUserById(Long userId);
	 
	 List<UserDto> getAllUser();
	 
	 void deleteUser(Long userId);
	
}
