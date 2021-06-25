package com.pohuisolutions.demo.controller;

import com.pohuisolutions.demo.message.ResponseMessage;
import com.pohuisolutions.demo.model.Post;
import com.pohuisolutions.demo.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class PostController {


    @Autowired
    private final PostService postService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestPart("file") MultipartFile file) {
        String message = "";
//        log.info("send post to 8082");
        try {
            postService.store(file);
            message = "FILE UPLOAD SUCCESSFULLY: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (IOException e) {
            message = "Could not upload the file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

        }




//    private final PostService postService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> upload(Post post, @RequestParam("file")MultipartFile file){
//        log.info("upload file");
//        postService.upload(post, file);
//        return new ResponseEntity<>("UPLOAD SUCCESSFUL", HttpStatus.OK);
//
//    }

//    @GetMapping("/upload")
//    String uploadHtml(String upload, Model model){
//        log.info("MICROSERVICE UPLOAD HTML");
//        model.addAttribute("Post", new Post());
//        log.info("RETURN UPLOAD HTML AND CREATE NEW POST()");
//        return upload;
//        }

    }
    @GetMapping("/upload")
    public void show(Model map){
        List<Post> posts = postService.getAll();
        posts.forEach(img->{
            img.setBase64Img("data:image/png;base64," + Base64.toBase64String(img.getData()));
        });
//        posts.get(0).setBase64Img("data:image/png;base64," + Base64.encode(posts.get(0).getData()));
        System.out.println(posts.get(0).getBase64Img());
        String encoded = Base64.toBase64String(posts.get(0).getData());
//        System.out.println(encoded);
        map.addAttribute("images", posts);
//        return "upload";
    }

}
