package com.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	
}