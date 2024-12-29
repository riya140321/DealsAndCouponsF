package com.user.Entity;




import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	//@NotEmpty(message = "FirstName should not be Empty")
	private String firstName;
	private String lastName; 
	//@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must be in the format name@gmail.com")
	//@NotEmpty(message = "Email should not be Empty")
	//@Email(message="Invalid Email")
	private String email;
	//@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Mobile number must be exactly 10 digits")
	//@NotEmpty(message = "PhoneNumber should not be Empty")
	private String phoneNumber;
	//@Pattern(regexp = "^(?i)male|female$", message = "Gender must be either 'male' or 'female'")
	//@NotEmpty(message = "Gender should not be Empty")
	private String gender;
	//@NotEmpty(message = "Age should not be Empty")
	private String age;
	//@NotEmpty(message = "UserName should not be Empty")
	//@NotNull(message = "Username is not entered")
	private String username;
	//@NotEmpty(message = "Password should not be Empty")
	//@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,20}$",message="Password is mismatched")
	private String password;
}
