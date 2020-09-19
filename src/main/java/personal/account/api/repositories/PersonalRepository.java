package personal.account.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.account.api.models.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
