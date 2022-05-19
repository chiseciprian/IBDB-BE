package ro.fasttrackit.securityservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.securityservice.domain.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByEmail(String email);

    AccountEntity findByUserName(String userName);

    boolean existsAccountEntityByEmail(String email);
}
