package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Single;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleRepository extends JpaRepository<Single, Integer> {
    @Query("select s from Single s where s.isValid = true  and s.status = true")
    List<Single> getAllActivatedSingle();

    @Query("select s from Single s where s.isValid = false  and s.status = false ")
    List<Single> getAllInProcessSingle();

    @Query("select s from Single s where s.isValid = true and s.status = false ")
    List<Single> getAllDoneSingle();
}
