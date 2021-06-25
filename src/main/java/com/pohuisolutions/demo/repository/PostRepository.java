package com.pohuisolutions.demo.repository;

import com.pohuisolutions.demo.model.Post;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {}