package com.ing.sb.storeManagement.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private String id;
    @NotEmpty(message = "Title can't be empty")
    private String title;
    private String description;
    private Integer price;

    private LocalDate dateAdded;
    private LocalDateTime lastModified;

    @JsonIgnore
    private Integer startPrice;
    @JsonIgnore
    private Integer endPrice;
}
