package com.jobs.jobstrackingportal.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;


import com.jobs.jobstrackingportal.Dtos.UserDto;
import com.jobs.jobstrackingportal.Entity.AppConstants;
import com.jobs.jobstrackingportal.Entity.Role;
import com.jobs.jobstrackingportal.Entity.User;
import com.jobs.jobstrackingportal.Exceptions.ResourceNotFoundException;
import com.jobs.jobstrackingportal.Repository.RoleRepo;
import com.jobs.jobstrackingportal.Repository.UserRepository;
import com.jobs.jobstrackingportal.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User sevedUser = userRepository.save(user);
		return this.userToDto(sevedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		User user =this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User sevedUser = this.userRepository.save(user);

		UserDto userDto1 = this.userToDto(sevedUser);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("user","id",userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Long userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("user","Id" ,userId));
		this.userRepository.delete(user);

	}
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);

//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;

	}

	private UserDto userToDto(User user) {
		UserDto userDto= this.modelMapper.map(user,UserDto.class);

//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());

		return userDto;
	}

	@Override
	public UserDto registerNewuser(UserDto userDto) {

		User user= this.modelMapper.map(userDto,User.class);

//      encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		Role role= roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepository.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}

}
