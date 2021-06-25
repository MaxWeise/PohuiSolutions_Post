package com.pohuisolutions.demo.service;

import antlr.StringUtils;
import com.pohuisolutions.demo.controller.ImageUtil;
import com.pohuisolutions.demo.dto.PostRequest;
import com.pohuisolutions.demo.model.Post;
import com.pohuisolutions.demo.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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








//    private final PostRepository postRepository;
//
//    @Transactional
//    public void upload(Post post, @RequestParam("file")MultipartFile file) {
//        String fileName = file.getOriginalFilename();
//        post.setName(fileName);
//        postRepository.save(post);
//    }

}
