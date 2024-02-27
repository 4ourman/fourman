package org.fourman.sojuproject.reposittory;

import org.fourman.sojuproject.domain.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
