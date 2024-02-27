package org.fourman.sojuproject.reposittory;

import org.fourman.sojuproject.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
