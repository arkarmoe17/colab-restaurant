package com.colab.restaurant.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Arkar on 27-Dec-2021
 **/
@Data
@NoArgsConstructor
public class UserReq {
    private String name;
    private String username;
    private String password;
}
