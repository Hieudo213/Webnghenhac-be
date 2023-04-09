package com.tmt.project.webnghenhac.repository;

import com.tmt.project.webnghenhac.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("select c from Category c where c.isValid=true ")
    List<Category> findGenre();
}
