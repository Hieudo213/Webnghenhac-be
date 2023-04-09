package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Integer> {

    @Query("select  p  from Playlist p")
    List<Playlist> getAllPlaylist();

    @Query("select  p  from Playlist p where p.status = false and p.isValid = false ")
    List<Playlist> getAllInProcessPlaylist();

    @Query("select  p from  Playlist  p where  p.isValid =true  and  p.status =false")
    List<Playlist> getDonePlaylist();

    @Query("select  p from  Playlist  p where  p.isValid = true  and  p.status =true")
    List<Playlist> getAllActivatedPlaylist();
}
