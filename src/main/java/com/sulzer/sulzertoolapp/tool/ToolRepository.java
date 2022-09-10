package com.sulzer.sulzertoolapp.tool;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
   Page<Tool> findByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value = "select (count(t) > 0) from Tool t where t.toolNumber = :toolNumber and (:id is null or t.id not in (:id))")
    Boolean existsByToolNumber(@Param("toolNumber") String toolNumber, @Param("id") Long id);

    @Query(value = "select (count(t) > 0) from Tool t where concat(t.toolAtms, t.toolAtmsNumber) = concat(:toolAtms, :toolAtmsNumber) and (:id is null or t.id not in (:id))")
    Boolean existsByToolAtmsNumber(@Param("toolAtmsNumber") String toolAtmsNumber, @Param("toolAtms") String toolAtms, @Param("id") Long id);
}
