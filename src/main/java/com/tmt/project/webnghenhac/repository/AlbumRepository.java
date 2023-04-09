package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Integer> {
    @Query("select a from Album  a where a.isValid = true and a.status = true")
   List<Album> getAllActivatedAlbums();

    @Query("select a from Album  a where a.isValid = false and a.status = false")
    List<Album> getAllInProcessAlbum();

    @Query("select a from Album  a where a.isValid = true and a.status = false")
    List<Album> getAllDoneAlbum();


}
