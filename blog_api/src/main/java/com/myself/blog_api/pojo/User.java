package com.myself.blog_api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int uId;
    private String token;

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uPassword='" + token + '\'' +
                '}';
    }
}
