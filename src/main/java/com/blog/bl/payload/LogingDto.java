package com.blog.bl.payload;

import lombok.Data;

@Data
public class LogingDto {

    private String usernameOrEmail;
    private String password;
}
