package com.admin.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	
	private long userId;
	private String firstName;
	private String lastName; 
	private String email;
	private String phoneNumber;
	private String gender;
	private String age;
	private String username;
	private String password;
	
}
