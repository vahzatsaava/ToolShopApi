package com.example.toolshopapi.service.user_info;

import com.example.toolshopapi.model.models.UserInfo;
import com.example.toolshopapi.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Override
    @Transactional
    public void saveUserInfo(UserInfo userInfo) {
        if (userInfo == null){
            throw new IllegalArgumentException("userInfo is null check arguments");
        }
        String emailToken = generateEmailToken();

        userInfo.setEmailConfirmationToken(emailToken);
        userInfoRepository.save(userInfo);
    }

    @Override
    @Transactional
    public void confirmEmail(Principal principal) {
        UserInfo userInfo = userInfoRepository.findByUserEmail(principal.getName());
        if (userInfo.getEmailConfirmationToken() != null){
            userInfo.setEmailConfirmed(true);
        }
    }

    private String generateEmailToken() {
        return UUID.randomUUID().toString();
    }

}
