package com.vishal.room_rental_system.repository;


import com.vishal.room_rental_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
