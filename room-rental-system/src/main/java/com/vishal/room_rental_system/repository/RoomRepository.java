package com.vishal.room_rental_system.repository;

import com.vishal.room_rental_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
