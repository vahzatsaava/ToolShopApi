package com.example.toolshopapi.controller;

import com.example.toolshopapi.dto.CommentInputDto;
import com.example.toolshopapi.dto.CommentInputUpdateDto;
import com.example.toolshopapi.dto.CommentModel;
import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.service.iterfaces.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> saveComment(@Valid @RequestBody CommentInputDto commentInputDto) {
        return new ResponseEntity<>(commentService.createComment(commentInputDto), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateComment(@Valid @RequestBody CommentInputUpdateDto commentInputDto, Principal principal) {
        return new ResponseEntity<>(commentService.updateComment(commentInputDto, principal), HttpStatus.OK);
    }

    @GetMapping("/get-all-user-comments")
    public ResponseEntity<List<CommentModel>> getUserComments(Principal principal) {
        return new ResponseEntity<>(commentService.getAllMyComments(principal), HttpStatus.OK);
    }

    @GetMapping("/get-all-product-comments")
    public ResponseEntity<List<CommentModel>> getProductComments(@RequestParam Long productId) {
        return new ResponseEntity<>(commentService.getProductComments(productId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-own-comment")
    public ResponseEntity<String> deleteOwnComment(@RequestParam Long commentId, Principal principal) {
        String msg = String.format("comment was deleted by id %s and userEmail - %s", commentId, principal.getName());
        commentService.deleteOwnCommentByUser(commentId, principal);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/delete-comment-by-admin")
    public ResponseEntity<String> deleteCommentByAdmin(@RequestParam Long commentId) {
        String msg = String.format("comment was deleted by id %s ", commentId);
        commentService.deleteCommentByAdmin(commentId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


}
