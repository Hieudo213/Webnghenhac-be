package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song,Integer> {
    @Query("select s from Song s where s.status = true and s.isValid = true")
    List<Song> getAllActivatedSong();

    @Query("select s from Song s where s.status = false and s.isValid = false")
    List<Song> getAllInProcessSong();

    @Query("select s from Song s where s.status = false and s.isValid = true")
    List<Song> getAllDoneSong();
}
