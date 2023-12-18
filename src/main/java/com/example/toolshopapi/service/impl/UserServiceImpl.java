package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.UserAdditionalDto;
import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.notification.NotificationDto;
import com.example.toolshopapi.mapping.AddressMapper;
import com.example.toolshopapi.mapping.UserMapper;
import com.example.toolshopapi.model.email.constants.NotificationType;
import com.example.toolshopapi.model.models.User;
import com.example.toolshopapi.model.models.UserInfo;
import com.example.toolshopapi.repository.UserRepository;
import com.example.toolshopapi.service.user_info.UserInfoService;
import com.example.toolshopapi.service.iterfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationContext applicationContext;
    private final UserInfoService userInfoService;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;


    @Override
    @Transactional(readOnly = true)
    public UserDto findUserByEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("email is null, we cannot find user by email");
        }
        User user = findByEmail(email);
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto save(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("entity user is null ");
        }
        User userEntity = userRepository.save(userMapper.toEntity(user));

        return userMapper.toUserDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto updateUserAddressAndName(Principal principal, UserAdditionalDto userAdditionalDto) {
        if (userAdditionalDto == null) {
            throw new IllegalArgumentException("entity user is null ");
        }
        User user = findByEmail(principal.getName());
        saveUserInfoAndSetToUser(user,userAdditionalDto);

        applicationContext.publishEvent(getNotificationDto(user, NotificationType.APPROVE_EMAIL));
        applicationContext.publishEvent(getNotificationDto(user,NotificationType.ADDRESS));
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException("email is null, we cannot find user by email");
        }
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void deleteAccount(Principal principal) {

        User user = findByEmail(principal.getName());
        userRepository.delete(user);

        applicationContext.publishEvent(getNotificationDto(user,NotificationType.DELETE));
    }

    @Override
    @Transactional
    public void deleteUserByAdmin(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null, check value ");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" entity with id " + id + " not found"));
        userRepository.delete(user);

        applicationContext.publishEvent(getNotificationDto(user, NotificationType.REJECTED));
    }

    @Override
    public User findById(Long id) {
        if (id == null){
            throw new NullPointerException("id by user " + id + " is null ");
        }
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user by id " + id + " not found"));
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("User must be authenticated");
        }
        String email = authentication.getName();

        return findByEmail(email);
    }



    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("entity with email " + email + "not found "));
    }

    private void updateUserFields(UserInfo user, UserAdditionalDto userAdditionalDto) {
        user.setFirstName(userAdditionalDto.getFirstName());
        user.setLastName(userAdditionalDto.getLastName());
        user.setShippingAddress(addressMapper.toEntity(userAdditionalDto.getAddressDto()));
    }
    private NotificationDto getNotificationDto(User user,NotificationType notificationType){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setNotificationType(notificationType);
        notificationDto.setEmail(user.getEmail());
        notificationDto.setFirstName(user.getEmail());
        return notificationDto;
    }
    private void saveUserInfoAndSetToUser(User user,UserAdditionalDto userAdditionalDto){
        if (user.getUserInfo() == null) {
            UserInfo info = new UserInfo();
            updateUserFields(info, userAdditionalDto);
            info.setUser(user);
            user.setUserInfo(info);
            userInfoService.saveUserInfo(info);
        } else {
            UserInfo userInfo = user.getUserInfo();
            updateUserFields(userInfo, userAdditionalDto);
            userInfoService.saveUserInfo(userInfo);
        }
    }
}
