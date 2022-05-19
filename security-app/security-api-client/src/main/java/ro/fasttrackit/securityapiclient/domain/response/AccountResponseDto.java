package ro.fasttrackit.securityapiclient.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
}
