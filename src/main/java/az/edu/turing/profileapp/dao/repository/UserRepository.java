package az.edu.turing.profileapp.dao.repository;

import az.edu.turing.profileapp.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(attributePaths = {"profiles"})
    List<UserEntity> findAll();

    @EntityGraph(attributePaths = {"profiles"})
    Optional<UserEntity> findById(Long id);
}
