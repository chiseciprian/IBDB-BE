package ro.fasttrackit.securityapiclient.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenWrapperDto {

    private UserResponseDto user;
    private String token;
}
