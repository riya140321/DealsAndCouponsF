package com.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.admin.Controller.AdminController;
import com.admin.Model.Admin;
import com.admin.Repository.AdminRepository;



@SpringBootTest(classes = AdminserviceApplication.class)
class AdminserviceApplicationTests {

	@Autowired
	AdminController adminController;
	
	@MockBean
	AdminRepository adminRepository;
	
	@Test
	public void addAdminTests()
	{
		Admin admin=new Admin(1L,"xyz","xyz@gmail.com","xyz23","****");
		  when(adminRepository.save(admin)).thenReturn(admin);
		  assertEquals(admin, adminController.addAdmin(admin));
	}
	
	@Test
	public void getAllTests()
	{
		when(adminRepository.findAll()).thenReturn(
				  Stream.of(new Admin(1L,"xyz","xyz@gmail.com","xyz23","****"))
				  .collect(Collectors.toList()));
				  
		  assertEquals(1, adminController.getAll().size());
	}
	
	@Test
	public  void deleteAdminTests()
	{
		long adminId=1L;
		  Admin admin=new Admin(1L,"xyz","xyz@gmail.com","xyz23","****");
		  adminRepository.deleteById(adminId);
		  verify(adminRepository).deleteById(adminId);
	}
	
	@Test
	public void updateAdminTests()
	{
		long adminId=1L;
		Admin admin=new Admin(1L,"xyz","xyz@gmail.com","xyz23","****");
		  adminRepository.save(admin);
		  verify(adminRepository).save(admin);
	}
}
