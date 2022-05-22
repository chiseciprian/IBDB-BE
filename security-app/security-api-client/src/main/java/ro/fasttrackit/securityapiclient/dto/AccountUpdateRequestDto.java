package ro.fasttrackit.securityapiclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateRequestDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
}
