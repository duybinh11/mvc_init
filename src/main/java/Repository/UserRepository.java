package Repository;


import Entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @EntityGraph(attributePaths = {"carts", "carts.item"})
    Optional<UserEntity> findById(int idUser);

    Optional<UserEntity> findByUsername(String username);


    @EntityGraph(attributePaths = {"userHasRoles", "userHasRoles.role"})
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findUserWithRoles(@Param("username") String username);

}
