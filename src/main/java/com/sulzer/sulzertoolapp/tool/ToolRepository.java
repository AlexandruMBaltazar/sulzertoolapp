package com.sulzer.sulzertoolapp.tool;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    Boolean existsByToolNumber(String toolNumber);
    Boolean existsByToolAtmsNumber(String toolAtmsNumber);
}
