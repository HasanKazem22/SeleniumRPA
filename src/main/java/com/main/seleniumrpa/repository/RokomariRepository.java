package com.main.seleniumrpa.repository;

import com.main.seleniumrpa.model.RokomariModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RokomariRepository extends JpaRepository<RokomariModel, Integer> {
}
