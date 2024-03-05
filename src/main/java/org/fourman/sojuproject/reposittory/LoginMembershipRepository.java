package org.fourman.sojuproject.reposittory;

import org.fourman.sojuproject.domain.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginMembershipRepository extends JpaRepository<Membership, Long> {
    Membership findBymEmail(String email);
}
