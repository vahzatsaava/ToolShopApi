package com.example.toolshopapi.mapping;

import com.example.toolshopapi.dto.CommentModel;
import com.example.toolshopapi.model.models.product.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    CommentModel toCommentModel(Comment comment);

    @InheritInverseConfiguration
    Comment toComment(CommentModel commentModel);
}
