package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.dto.CommentInputDto;
import com.example.toolshopapi.dto.CommentInputUpdateDto;
import com.example.toolshopapi.dto.CommentModel;
import com.example.toolshopapi.dto.product_dto.ProductDto;


import java.security.Principal;
import java.util.List;

public interface CommentService {
    ProductDto createComment(CommentInputDto commentInputDto);

    ProductDto updateComment(CommentInputUpdateDto commentUpdateDto, Principal principal);

    void deleteOwnCommentByUser(Long commentId, Principal principal);

    void deleteCommentByAdmin(Long commentId);

    List<CommentModel> getAllMyComments(Principal principal);

    List<CommentModel> getProductComments(Long productId);
}
