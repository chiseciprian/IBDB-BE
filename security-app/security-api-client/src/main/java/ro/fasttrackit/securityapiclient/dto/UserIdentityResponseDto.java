package ro.fasttrackit.securityapiclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdentityResponseDto {

        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String userName;
        private String password;
        private String role;
}
