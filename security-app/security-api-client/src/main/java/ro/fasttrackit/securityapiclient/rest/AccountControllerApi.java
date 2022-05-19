package ro.fasttrackit.securityapiclient.rest;


import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.securityapiclient.domain.request.AccountRequestDto;
import ro.fasttrackit.securityapiclient.domain.response.AccountResponseDto;

import java.util.List;

@RestController
@RequestMapping(path = "/security")
public interface AccountControllerApi {

    @PostMapping(path = "/create-account")
    void createAccount(@RequestBody AccountRequestDto request);

    @PutMapping(path = "/account/{id}")
    void updateAccount(@PathVariable Long id, @RequestBody AccountRequestDto request);

    @PutMapping(path = "/account/{id}/change-password")
    void changePassword(@PathVariable Long id, @RequestBody String newPassword);

    @DeleteMapping(path = "/account/{id}")
    void deleteAccount(@PathVariable Long id);

    @GetMapping(path = "/accounts")
    List<AccountResponseDto> getAccounts();

    @GetMapping(path = "/account-email/{email}/")
    AccountResponseDto getAccountByEmail(@PathVariable String email);

    @GetMapping(path = "/account-cnp/{userName}")
    AccountResponseDto getAccountByUserName(@PathVariable String userName);
}
