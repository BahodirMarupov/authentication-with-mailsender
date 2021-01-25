package uz.themind.springauthentication.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.themind.springauthentication.model.domen.UserPrincipal;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<UserPrincipal, Long> {
    Optional<UserPrincipal> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserPrincipal u set u.enabled=true where u.email=?1")
    int enableUser(String email);
}
