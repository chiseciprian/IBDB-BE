package ro.fasttrackit.securityservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.securityapiclient.dto.AccountRequestDto;
import ro.fasttrackit.securityapiclient.dto.AccountResponseDto;
import ro.fasttrackit.securityapiclient.dto.AccountUpdateRequestDto;
import ro.fasttrackit.securityservice.service.AccountService;

import java.util.List;

@RestController
@RequestMapping(path = "/security")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountControllerImpl {

    private final AccountService accountService;

    @PostMapping(path = "/create-account")
    public void createAccount(@RequestBody AccountRequestDto request) {
        accountService.createAccount(request);
    }

    @PutMapping(path = "/account/{id}")
    public void updateAccount(@PathVariable Long id, @RequestBody AccountUpdateRequestDto request) {
        accountService.updateAccount(id, request);
    }

    @PutMapping(path = "/account/{id}/change-password")
    public void changePassword(@PathVariable Long id, @RequestBody String newPassword) {
        accountService.changePassword(id, newPassword);
    }

    @DeleteMapping(path = "/account/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @GetMapping(path = "/accounts")
    public List<AccountResponseDto> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping(path = "/account-email/{email}/")
    public AccountResponseDto getAccountByEmail(@PathVariable String email) {
        return accountService.getAccountByEmail(email);
    }

    @GetMapping(path = "/account-userName/{userName}")
    public AccountResponseDto getAccountByUserName(@PathVariable String userName) {
        return accountService.getAccountByUserName(userName);
    }
}
