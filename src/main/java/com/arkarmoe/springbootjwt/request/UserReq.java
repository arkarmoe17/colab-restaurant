package com.arkarmoe.springbootjwt.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * Created by Arkar on 27-Dec-2021
 * **/
@Data
@NoArgsConstructor
public class UserReq {
    private Long id;
    private String name;
    private String username;
    private String password;
    private List<Long> roleIds;
}
