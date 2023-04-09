package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Integer> {
}
