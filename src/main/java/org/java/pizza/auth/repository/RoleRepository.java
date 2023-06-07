package org.java.pizza.auth.repository;

import org.java.pizza.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
