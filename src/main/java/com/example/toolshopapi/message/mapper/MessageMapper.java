package com.example.toolshopapi.message.mapper;

import com.example.toolshopapi.message.entity.MessageEntity;
import com.example.toolshopapi.message.model.MessageModel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageModel toMessageModel(MessageEntity entity);
}
