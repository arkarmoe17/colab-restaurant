package com.colab.restaurant.model.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("diningTables")
@Data
public class DiningTable {
    @Id
    private String id;
    private String shopCode;
    private String name;
    private String description;
    private String tableCode; // auto-generated
    private boolean isActive;
}
