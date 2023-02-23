package com.endava.tmd.bookclubproject.repository;

import com.endava.tmd.bookclubproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
//    Role saveRole(Role role);

}
