package com.jobs.jobstrackingportal.Controller;

import com.jobs.jobstrackingportal.Dtos.ApiResponse;
import com.jobs.jobstrackingportal.Dtos.UserDto;
import com.jobs.jobstrackingportal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(value = "*",allowedHeaders = "*")
@RequestMapping("/api/v1/users")
public class UserController {
	
    @Autowired
	private UserService userService;
    
    @PostMapping("/saveUser")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto){
    	UserDto createUserDto= this.userService.createUser(userDto);
    	
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
    	
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Validated @RequestBody UserDto userDto,@PathVariable Long userId){
    	UserDto  updatedUserDto= this.userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUserDto,HttpStatus.CREATED);
    	
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId) {
    	this.userService.deleteUser(userId);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("user delete successfully",true),HttpStatus.OK);
    }
	
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId){
    	return ResponseEntity.ok(this.userService.getUserById(userId));
    }
    
    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUsers(){
    	return ResponseEntity.ok(this.userService.getAllUser());
    }
}
