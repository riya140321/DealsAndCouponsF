package com.admin.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long adminId;
	//@NotEmpty(message = "Name should not be Empty")
	private String adminName;
	//@NotEmpty(message="Email should not be Empty")
	//@Email(message="Invalid Email")
	private String email;
	//@NotEmpty(message="Username should not be Empty")
	private String username;
	//@NotEmpty(message ="password should not be Empty")
	//@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,20}$", message="Password is mismatched")
	private String password;
}
