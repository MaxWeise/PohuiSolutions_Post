package com.pohuisolutions.demo.dto;

import lombok.*;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Data
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
}
