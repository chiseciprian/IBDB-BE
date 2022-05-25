package ro.fasttrackit.securityapiclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String role;
}
