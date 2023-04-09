package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {
    @Query("select a from Artist a where a.isValid = true and a.status = true ")
    List<Artist> getActivedArtist();

    @Query("select a from Artist a where a.isValid = false and  a.status = false ")
    List<Artist> getAllInProcessArtist();

    @Query("select a from Artist a where a.isValid = true and  a.status = false ")
    List<Artist> getAllDoneArtist();

}
