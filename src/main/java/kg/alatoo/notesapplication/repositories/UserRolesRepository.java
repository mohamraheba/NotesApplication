package kg.alatoo.notesapplication.repositories;

import kg.alatoo.notesapplication.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRolesRepository extends CrudRepository<Roles,String >, JpaRepository<Roles,String> {
    Optional<Roles> findByRoleName(Roles.Name roleName);
}
