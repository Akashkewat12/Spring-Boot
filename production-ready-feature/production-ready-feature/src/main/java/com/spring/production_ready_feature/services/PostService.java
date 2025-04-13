package com.spring.production_ready_feature.services;

import com.spring.production_ready_feature.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);


    PostDTO updatePost(PostDTO inputPost, Long postId);
}

