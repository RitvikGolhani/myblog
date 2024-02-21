package com.blog.bl.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingnUpDto {


    private String name;
    private String username;
    private String password;
    private String email;

}
