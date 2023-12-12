package com.lorram.menu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.menu.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
