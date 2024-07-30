package com.praveenukkoji.user_service.repository;

import com.praveenukkoji.user_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role r where r.roleName = ?1")
    Optional<Role> findByName(String roleName);
}
