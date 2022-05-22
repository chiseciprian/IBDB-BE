package ro.fasttrackit.securityservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fasttrackit.exceptions.BadUserException;
import ro.fasttrackit.securityapiclient.dto.AccountRequestDto;
import ro.fasttrackit.securityapiclient.dto.AccountResponseDto;
import ro.fasttrackit.securityapiclient.dto.AccountUpdateRequestDto;
import ro.fasttrackit.securityapiclient.dto.UserIdentityResponseDto;
import ro.fasttrackit.securityservice.config.properties.UserProperties;
import ro.fasttrackit.securityservice.model.AccountEntity;
import ro.fasttrackit.securityservice.repository.AccountRepository;
import ro.fasttrackit.securityservice.service.validator.AccountValidator;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserProperties userProperties;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final AccountValidator accountValidator;

    public void createAccount(AccountRequestDto request) {
        accountValidator.validateCreateAccount(request);
        AccountEntity accountEntity = convert(request, AccountEntity.class);
        accountEntity.setPassword(getEncodedPassword(request.getPassword()));
        accountEntity.setRole(userProperties.getRole());
        accountRepository.save(accountEntity);
    }


    public void updateAccount(Long id, AccountUpdateRequestDto request) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(NullPointerException::new);
        accountValidator.validateUpdateAccount(request, accountEntity);
        modelMapper.map(request, accountEntity);
        accountRepository.save(accountEntity);
    }


    public void changePassword(Long id, String newPassword) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(NullPointerException::new);
        accountEntity.setPassword(getEncodedPassword(newPassword));
        accountRepository.save(accountEntity);
    }


    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }


    public List<AccountResponseDto> getAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        return convertAccountToDto(accountEntities);
    }


    public AccountResponseDto getAccountByEmail(String email) {
        AccountEntity accountEntity = accountRepository.findByEmail(email);
        return convertAccount(accountEntity);
    }


    public AccountResponseDto getAccountByUserName(String userName) {
        AccountEntity accountEntity = accountRepository.findByUserName(userName);
        return convertAccount(accountEntity);
    }


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
