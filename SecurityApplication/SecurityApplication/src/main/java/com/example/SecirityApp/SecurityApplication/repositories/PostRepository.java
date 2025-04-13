package com.example.SecirityApp.SecurityApplication.repositories;

import com.example.SecirityApp.SecurityApplication.dto.PostDTO;
import com.example.SecirityApp.SecurityApplication.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
