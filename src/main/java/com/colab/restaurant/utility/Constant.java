package com.colab.restaurant.utility;

/**
 * Created by Arkar on 27-Dec-2021
 **/
public interface Constant {
    class Token {
        public static final String SECRET = "24f8c5e5-fcb5-bdfc-aac0-7bff0e800334";
        public static final String ROLES = "roles";
        public static final String ACCESS_TOKEN = "access_token";
        public static final String REFRESH_TOKEN = "refresh_token";
        public static final String MENU_PERMISSION = "menu_permission";
        public static final int ACCESS_TOKEN_EXPIRE = 30 * 60 * 1000;
        public static final int REFRESH_TOKEN_EXPIRE = 60 * 60 * 1000;
    }

    class Message {
        public static final String SHOP_CODE_IS_EXISTED = "Shop code is already existed.";
        public static final String SHOP_CODE_NOT_FOUND = "Shop code is not found.";
        public static final String ID_NOT_FOUND = "Id is not found.";
    }

}
