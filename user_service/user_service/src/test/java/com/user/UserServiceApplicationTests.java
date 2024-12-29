package com.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.RequestBody;
import com.user.Controller.UserController;
import com.user.Entity.User;
import com.user.Repository.UserRepository;

@SpringBootTest(classes = UserServiceApplication.class)
class UserServiceApplicationTests {

	  @Autowired
	  UserController userController;
	  
	  @MockBean
      UserRepository userRepository;
      
	  @Test
	  public void addUserTests()
	  {
		  User user=new User(1L,"xyz","abc","xyz@gmail.com","6784574739","female","23","xyz23","****");
		  when(userRepository.save(user)).thenReturn(user);
		  assertEquals(user, userController.addUser(user));
	  }
	  
	  @Test
	  public void getAllUserTests() 
	  {
		  when(userRepository.findAll()).thenReturn(
				  Stream.of(new User(1L,"xyz","abc","xyz@gmail.com","6784574739","female","23","xyz23","****"))
				  .collect(Collectors.toList()));
				  
		  assertEquals(1, userController.getAllUser().size());
				    
	  }
	  
	  @Test
	  public void updateUserTests()
	  {
		  long userId=1L;
		  User user=new User(1L,"xyz","abc","xyz@gmail.com","6784574739","female","23","xyz23","****");
		  userRepository.save(user);
		  verify(userRepository).save(user);
	  }
	  
	  @Test
	  public void deleteUserTests()
	  {
		  long userId=1L;
		  User user=new User(1L,"xyz","abc","xyz@gmail.com","6784574739","female","23","xyz23","****");
		  userRepository.deleteById(userId);
		  verify(userRepository).deleteById(userId);
	  }
	  
	 
}
