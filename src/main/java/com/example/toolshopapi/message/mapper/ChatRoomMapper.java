package com.example.toolshopapi.message.mapper;

import com.example.toolshopapi.message.entity.ChatRoomEntity;
import com.example.toolshopapi.message.entity.UserChatRoomEntity;
import com.example.toolshopapi.message.model.ChatRoomModel;
import com.example.toolshopapi.message.model.ChatRoomWithAdminRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {

    ChatRoomEntity toChatRoomEntity(ChatRoomWithAdminRequest request);

    @Mapping(target = "users", source = "users", qualifiedByName = "toUsersId")
    @Mapping(target = "lastMessages", source = "messages")
    @Mapping(target = "picture", source = "picture")
    @Mapping(target = "color", source = "color")
    ChatRoomModel toChatRoomModel(ChatRoomEntity chatRoom);

    @Named("toUsersId")
    default List<Long> toUsersId(List<UserChatRoomEntity> users) {
        return users.stream().map(x -> x.getUser().getId()).collect(Collectors.toList());
    }
}
