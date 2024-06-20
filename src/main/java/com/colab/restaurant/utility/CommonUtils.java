package com.colab.restaurant.utility;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class CommonUtils {
    public static String getUUID(String input) {
        input += Timestamp.valueOf(LocalDateTime.now());
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return UUID.nameUUIDFromBytes(bytes).toString();
    }
}
