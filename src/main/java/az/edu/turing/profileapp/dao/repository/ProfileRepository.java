package az.edu.turing.profileapp.dao.repository;

import az.edu.turing.profileapp.dao.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    boolean existsByMailAddressOrPassword(String mailAddress, String password);

    @Query(value = "select * from profiles where user_id = :userId and id = :id ", nativeQuery = true)
    Optional<ProfileEntity> findByUserIdAndProfileId(@Param("userId") Long userId, @Param("id") Long id);

    @Query(value = "select * from profiles where user_id = :userId", nativeQuery = true)
    List<ProfileEntity> findByUserId(@Param("userId") Long userId);
}
