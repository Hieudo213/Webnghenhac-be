package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music,Integer> {

}
