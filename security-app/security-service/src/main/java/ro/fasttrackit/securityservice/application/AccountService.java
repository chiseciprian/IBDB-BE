package ro.fasttrackit.securityservice.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fasttrackit.securityapiclient.application.AccountServiceApi;
import ro.fasttrackit.securityapiclient.domain.request.AccountRequestDto;
import ro.fasttrackit.securityapiclient.domain.response.AccountResponseDto;
import ro.fasttrackit.securityapiclient.domain.response.UserIdentityResponseDto;
import ro.fasttrackit.securityapiclient.exception.rest.BadUserException;
import ro.fasttrackit.securityapiclient.exception.rest.UserAlreadyExistException;
import ro.fasttrackit.securityservice.config.properties.UserProperties;
import ro.fasttrackit.securityservice.domain.AccountEntity;
import ro.fasttrackit.securityservice.infrastructure.AccountRepository;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService implements AccountServiceApi {

    private final AccountRepository accountRepository;
    private final UserProperties userProperties;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @Override
    public void createAccount(AccountRequestDto request) {
        if (accountRepository.existsAccountEntityByEmail(request.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + request.getEmail());
        }

        AccountEntity accountEntity = convert(request, AccountEntity.class);
        accountEntity.setPassword(getEncodedPassword(request.getPassword()));
        accountEntity.setRole(userProperties.getRole());
        accountRepository.save(accountEntity);
    }

    @Override
    public void updateAccount(Long id, AccountRequestDto request) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(NullPointerException::new);
        modelMapper.map(request, accountEntity);
        accountRepository.save(accountEntity);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(NullPointerException::new);
        accountEntity.setPassword(getEncodedPassword(newPassword));
        accountRepository.save(accountEntity);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountResponseDto> getAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        return convertAccountToDto(accountEntities);
    }

    @Override
    public AccountResponseDto getAccountByEmail(String email) {
        AccountEntity accountEntity = accountRepository.findByEmail(email);
        return convertAccount(accountEntity);
    }

    @Override
    public AccountResponseDto getAccountByUserName(String userName) {
        AccountEntity accountEntity = accountRepository.findByUserName(userName);
        return convertAccount(accountEntity);
    }

    @Override
    public UserIdentityResponseDto getUserIdentity(String user, String password) {
        if (accountRepository.findByUserName(user) != null) {
            AccountEntity accountEntity = accountRepository.findByUserName(user);
            return checkUserAndReturn(accountEntity, password);
        } else if (accountRepository.findByEmail(user) != null) {
            AccountEntity accountEntity = accountRepository.findByEmail(user);
            return checkUserAndReturn(accountEntity, password);
        }
        throw new BadUserException("Utilizator greșit");
    }

    private String getEncodedPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    private String getDecodedPassword(String encodedPassword) {
        return new String(Base64.getDecoder().decode(encodedPassword.getBytes()));
    }

    private List<AccountResponseDto> convertAccountToDto(List<AccountEntity> accountEntities) {
        return accountEntities.stream().map(this::convertAccount).collect(Collectors.toList());
    }

    private UserIdentityResponseDto checkUserAndReturn(AccountEntity accountEntity, String password) {
        if (password.equals(getDecodedPassword(accountEntity.getPassword()))) {
            return convert(accountEntity, UserIdentityResponseDto.class);
        }
        throw new BadUserException("Utilizator greșit");
    }

    private AccountResponseDto convertAccount(AccountEntity accountEntity) {
        AccountResponseDto accountResponse = new AccountResponseDto();
        accountResponse.setId(accountEntity.getId());
        accountResponse.setFirstName(accountEntity.getFirstName());
        accountResponse.setLastName(accountEntity.getLastName());
        accountResponse.setUserName(accountEntity.getUserName());
        accountResponse.setEmail(accountEntity.getEmail());
        return accountResponse;
    }

    private <T> T convert(Object fromValue, Class<T> tClass) {
        return objectMapper.convertValue(fromValue, tClass);
    }
}
