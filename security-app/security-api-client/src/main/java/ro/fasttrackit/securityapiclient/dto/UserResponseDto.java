package ro.fasttrackit.securityapiclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String role;
}
