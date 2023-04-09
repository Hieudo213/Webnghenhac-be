package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Browse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrowseRepository extends JpaRepository<Browse,Integer> {

}
