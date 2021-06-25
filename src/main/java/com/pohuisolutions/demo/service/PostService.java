package com.pohuisolutions.demo.service;

import com.pohuisolutions.demo.model.Post;
import com.pohuisolutions.demo.repository.PostRepository;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.io.IOException;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Post post = new Post(fileName, file.getContentType(), file.getBytes());
        return postRepository.save(post);
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }
}
