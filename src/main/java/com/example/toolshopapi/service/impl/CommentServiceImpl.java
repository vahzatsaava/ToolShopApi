package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.CommentInputDto;
import com.example.toolshopapi.dto.CommentInputUpdateDto;
import com.example.toolshopapi.dto.CommentModel;
import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.mapping.CommentsMapper;
import com.example.toolshopapi.mapping.ProductMapper;
import com.example.toolshopapi.model.models.User;
import com.example.toolshopapi.model.models.product.Comment;
import com.example.toolshopapi.model.models.product.Product;
import com.example.toolshopapi.repository.CommentsRepository;
import com.example.toolshopapi.service.iterfaces.CommentService;
import com.example.toolshopapi.service.iterfaces.ProductService;
import com.example.toolshopapi.service.iterfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentsRepository commentsRepository;
    private final UserService userService;
    private final ProductMapper productMapper;
    private final CommentsMapper commentsMapper;
    private final ProductService productService;


    @Transactional
    @Override
    public ProductDto createComment(CommentInputDto commentInputDto) {
        User author = userService.getCurrentUser();
        Product product = productService.findById(commentInputDto.getProductId());

        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setAuthor(author);
        comment.setCreated(LocalDateTime.now());
        comment.setText(commentInputDto.getText());


        commentsRepository.save(comment);

        return productMapper.toProductDto(product);
    }

    @Transactional
    @Override
    public ProductDto updateComment(CommentInputUpdateDto commentUpdateDto, Principal principal) {
        Comment comment = findByIdAndUserEmail(commentUpdateDto.getCommentId(),principal.getName());
        comment.setText(commentUpdateDto.getText());

        return productMapper.toProductDto(comment.getProduct());

    }
    @Transactional
    @Override
    public void deleteOwnCommentByUser(Long commentId, Principal principal){
        Comment comment = findByIdAndUserEmail(commentId,principal.getName());
        commentsRepository.delete(comment);

    }
    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCommentByAdmin(Long commentId){
        Comment comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("comment not found by id %s ",commentId)));
        commentsRepository.delete(comment);

    }

    @Override
    public List<CommentModel> getAllMyComments(Principal principal){
        User author = userService.getCurrentUser();
        return author.getComments().stream()
                .map(commentsMapper::toCommentModel)
                .toList();
    }

    @Override
    public List<CommentModel> getProductComments(Long productId){
        Product product = productService.findById(productId);
        return product.getComments().stream()
                .map(commentsMapper::toCommentModel)
                .toList();
    }
    private Comment findByIdAndUserEmail(Long commentId,String userEmail){
        return commentsRepository.findByIdAndAuthorEmail(commentId, userEmail)
                .orElseThrow(() -> new EntityNotFoundException(String.format("comment is not exist by user %s or id %s ", userEmail,commentId)));

    }

}
