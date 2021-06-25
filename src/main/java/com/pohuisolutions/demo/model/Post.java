package com.pohuisolutions.demo.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;
    private String name;
    private String type;

    @Lob
    private byte[] data;

    @Transient
    private String base64Img;


    public Post(String name, String type, byte[] data){
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
