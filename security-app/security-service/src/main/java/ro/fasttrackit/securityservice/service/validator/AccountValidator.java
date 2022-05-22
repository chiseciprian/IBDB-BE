package ro.fasttrackit.securityservice.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.exceptions.ValidationException;
import ro.fasttrackit.securityapiclient.dto.AccountRequestDto;
import ro.fasttrackit.securityapiclient.dto.AccountUpdateRequestDto;
import ro.fasttrackit.securityservice.model.AccountEntity;
import ro.fasttrackit.securityservice.repository.AccountRepository;

import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class AccountValidator {

    private final AccountRepository accountRepository;

    public void validateCreateAccount(AccountRequestDto accountRequestDto) {
        validateCreate(accountRequestDto).ifPresent(ex -> {
            throw ex;
        });
    }

    public void validateUpdateAccount(AccountUpdateRequestDto accountRequestDto, AccountEntity accountEntity) {
        validateUpdate(accountRequestDto, accountEntity).ifPresent(ex -> {
            throw ex;
        });
    }

    private Optional<ValidationException> validateUpdate(AccountUpdateRequestDto request, AccountEntity entity) {
        AccountEntity byEmail = accountRepository.findByEmail(request.getEmail());
        AccountEntity byUserName = accountRepository.findByUserName(request.getUserName());
        if (request.getEmail() == null) {
            return Optional.of(new ValidationException("Email cannot be null"));
        } else if (request.getUserName() == null) {
            return Optional.of(new ValidationException("Username cannot be null"));
        } else if (request.getFirstName() == null) {
            return Optional.of(new ValidationException("First name cannot be null"));
        } else if (request.getLastName() == null) {
            return Optional.of(new ValidationException("Last name cannot be null"));
        } else if (byEmail != null && !byEmail.getId().equals(entity.getId())) {
            return Optional.of(new ValidationException("There is an account with that email address"));
        } else if (byUserName != null && !byUserName.getId().equals(entity.getId())) {
            return Optional.of(new ValidationException("There is an account with that username"));
        } else {
            return empty();
        }
    }

    private Optional<ValidationException> validateCreate(AccountRequestDto request) {
        if (request.getEmail() == null) {
            return Optional.of(new ValidationException("Email cannot be null"));
        } else if (request.getUserName() == null) {
            return Optional.of(new ValidationException("Username cannot be null"));
        } else if (request.getFirstName() == null) {
            return Optional.of(new ValidationException("First name cannot be null"));
        } else if (request.getLastName() == null) {
            return Optional.of(new ValidationException("Last name cannot be null"));
        } else if (request.getPassword() == null) {
            return Optional.of(new ValidationException("Password cannot be nul"));
        } else if (accountRepository.existsAccountEntityByEmail(request.getEmail())) {
            return Optional.of(new ValidationException("There is an account with that email address"));
        } else if (accountRepository.existsAccountEntityByUserName(request.getUserName())) {
            return Optional.of(new ValidationException("There is an account with that username"));
        } else {
            return empty();
        }
    }
}
