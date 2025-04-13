package com.example.SecirityApp.SecurityApplication.sercices;

import com.example.SecirityApp.SecurityApplication.dto.PostDTO;
import com.example.SecirityApp.SecurityApplication.entities.PostEntity;
import com.example.SecirityApp.SecurityApplication.entities.User;
import com.example.SecirityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.example.SecirityApp.SecurityApplication.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements  PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {

        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {

        PostEntity postEntity= modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);

    }

    @Override
    public PostDTO getPostById(Long postId) {
//        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info("user {}", user);

        PostEntity postEntity=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post not found with id : "+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }
}
