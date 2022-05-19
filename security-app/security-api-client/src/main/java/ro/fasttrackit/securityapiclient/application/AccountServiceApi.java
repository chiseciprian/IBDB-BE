package ro.fasttrackit.securityapiclient.application;


import ro.fasttrackit.securityapiclient.domain.request.AccountRequestDto;
import ro.fasttrackit.securityapiclient.domain.response.AccountResponseDto;
import ro.fasttrackit.securityapiclient.domain.response.UserIdentityResponseDto;

import java.util.List;

public interface AccountServiceApi {

    void createAccount(AccountRequestDto request);

    void updateAccount(Long id, AccountRequestDto request);

    void changePassword(Long id, String newPassword);

    void deleteAccount(Long id);

    List<AccountResponseDto> getAccounts();

    AccountResponseDto getAccountByEmail(String email);

    AccountResponseDto getAccountByUserName(String userName);

    UserIdentityResponseDto getUserIdentity(String user, String password);
}
