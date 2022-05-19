package ro.fasttrackit.securityapiclient.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class RestErrorDto {

    private HttpStatus status;
    private int statusCode;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private RestErrorDto() {
        timestamp = LocalDateTime.now();
    }

    public RestErrorDto(HttpStatus status) {
        this();
        this.status = status;
        this.statusCode = status.value();
    }
}
