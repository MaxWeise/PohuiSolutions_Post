package com.pohuisolutions.demo.controller;

import com.pohuisolutions.demo.model.Post;
import com.pohuisolutions.demo.service.PostService;
import com.pohuisolutions.demo.message.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.ui.Model;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.io.IOException;

@Controller
@AllArgsConstructor
@Slf4j
public class PostController {

    @Autowired
    private final PostService postService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestPart("file") MultipartFile file) {
        String message = "";
        try {
            postService.store(file);
            message = "FILE UPLOAD SUCCESSFULLY: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (IOException e) {
            message = "Could not upload the file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/upload")
    public void show(Model map){
        List<Post> posts = postService.getAll();
        posts.forEach(img->{
            img.setBase64Img("data:image/png;base64," + Base64.toBase64String(img.getData()));
        });
        System.out.println(posts.get(0).getBase64Img());
        map.addAttribute("images", posts);
    }
}
