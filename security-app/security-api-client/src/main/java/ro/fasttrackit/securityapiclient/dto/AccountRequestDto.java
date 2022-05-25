package ro.fasttrackit.securityapiclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDto {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String role;
}
