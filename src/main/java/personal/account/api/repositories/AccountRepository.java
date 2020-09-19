package personal.account.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.account.api.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
