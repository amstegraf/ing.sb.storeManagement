package com.ing.sb.storeManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDTO {

    Integer code;
    String message;
    Object body;

    public GenericResponseDTO(Integer code, String message) {
        this(code, message, null);
    }
}
