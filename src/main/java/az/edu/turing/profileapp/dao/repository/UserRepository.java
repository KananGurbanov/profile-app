package az.edu.turing.profileapp.dao.repository;

import az.edu.turing.profileapp.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsById(Long id);
}
