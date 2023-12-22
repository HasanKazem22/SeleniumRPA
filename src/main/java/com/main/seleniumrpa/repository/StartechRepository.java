package com.main.seleniumrpa.repository;

import com.main.seleniumrpa.model.StartechModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartechRepository extends JpaRepository<StartechModel, Integer> {
}
