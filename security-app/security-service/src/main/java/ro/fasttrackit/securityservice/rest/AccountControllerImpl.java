package ro.fasttrackit.securityservice.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.securityapiclient.application.AccountServiceApi;
import ro.fasttrackit.securityapiclient.domain.request.AccountRequestDto;
import ro.fasttrackit.securityapiclient.domain.response.AccountResponseDto;
import ro.fasttrackit.securityapiclient.rest.AccountControllerApi;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountControllerImpl implements AccountControllerApi {

    private final AccountServiceApi accountService;

    @Override
    public void createAccount( AccountRequestDto request) {
        accountService.createAccount(request);
    }

    @Override
    public void updateAccount(Long id, AccountRequestDto request) {
        accountService.updateAccount(id, request);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        accountService.changePassword(id, newPassword);
    }

    @Override
    public void deleteAccount(Long id) {
        accountService.deleteAccount(id);
    }

    @Override
    public List<AccountResponseDto> getAccounts() {
        return accountService.getAccounts();
    }

    @Override
    public AccountResponseDto getAccountByEmail(String email) {
        return accountService.getAccountByEmail(email);
    }

    @Override
    public AccountResponseDto getAccountByUserName(String userName) {
        return accountService.getAccountByUserName(userName);
    }
}
