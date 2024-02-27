package org.fourman.sojuproject.reposittory;

import org.fourman.sojuproject.domain.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
