package ro.fasttrackit.securityapiclient.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String role;
}
