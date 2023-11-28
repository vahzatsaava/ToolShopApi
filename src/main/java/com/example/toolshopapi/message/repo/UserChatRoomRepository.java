package com.example.toolshopapi.message.repo;

import com.example.toolshopapi.message.entity.UserChatRoomEntity;
import com.example.toolshopapi.model.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChatRoomRepository extends JpaRepository<UserChatRoomEntity,Long> {
    List<UserChatRoomEntity> findByUser(User user);
}
