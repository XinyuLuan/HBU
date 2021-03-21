package com.hbu.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DemoUserRepository extends JpaRepository<DemoUser, Long> {
}
