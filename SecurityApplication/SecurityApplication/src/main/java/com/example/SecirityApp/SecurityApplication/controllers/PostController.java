package com.example.SecirityApp.SecurityApplication.controllers;


import com.example.SecirityApp.SecurityApplication.dto.PostDTO;
import com.example.SecirityApp.SecurityApplication.sercices.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    private List<PostDTO> getAllPosts() {
        return  postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    private PostDTO getPostById(@PathVariable Long postId) {
        return  postService.getPostById(postId);
    }

    @PostMapping
    private PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }
}
