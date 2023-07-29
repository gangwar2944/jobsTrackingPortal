package com.jobs.jobstrackingportal.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name="Users")
@NoArgsConstructor
@Setter
@Getter
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="user_name",nullable =false,length=20)
	private String name;
	
	@Column(name="email",nullable = false)
	private String email;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name = "about" ,nullable = false)
	private String about;
	
	

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	
//	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//	private List<Post> posts = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
	@JoinTable(name ="user_role",joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id"))
	private Set<Role> roles= new HashSet<>();



	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	List<SimpleGrantedAuthority> grantedAuthorities=this.roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


//	public List<UserDto> getRoles() {
//		
//		return UserDto;
//	} 
	
}
