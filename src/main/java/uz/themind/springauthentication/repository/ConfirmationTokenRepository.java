package uz.themind.springauthentication.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.themind.springauthentication.model.domen.ConfirmationToken;
import uz.themind.springauthentication.model.domen.UserPrincipal;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("update ConfirmationToken c set c.confirmedAt=?2 where c.token=?1")
    int updateConfirmedAt(String token, LocalDateTime dateTime);
}
