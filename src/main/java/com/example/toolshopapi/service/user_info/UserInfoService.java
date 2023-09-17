package com.example.toolshopapi.service.user_info;

import com.example.toolshopapi.model.models.UserInfo;

import java.security.Principal;

public interface UserInfoService {

    void saveUserInfo(UserInfo user);
    void confirmEmail(Principal principal);
}
