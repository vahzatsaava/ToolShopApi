package com.example.toolshopapi.repository;

import com.example.toolshopapi.model.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByEmailConfirmationToken(String token);
    UserInfo findByUserEmail(String email);
}
