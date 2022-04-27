package ru.itis.hotel.controller.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {

    /** Описание пути, по которому возникла исключительная ситуация */
    private String endpoint;

    /** Сообщение исключения */
    private String message;

    /** Детальное сообщение исключения */
    private String detailMessage;

    /** Наименование исключения */
    private String exceptionName;

}
