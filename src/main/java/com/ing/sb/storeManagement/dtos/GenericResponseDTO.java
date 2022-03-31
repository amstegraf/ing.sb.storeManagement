package com.ing.sb.storeManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDTO {
    private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

    Integer code;
    String message;
    Object body;
    String correlationId;

    public GenericResponseDTO(Integer code, String message) {
        this(code, message, null, MDC.get(CORRELATION_ID_LOG_VAR_NAME));
    }
}
