package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Habitants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitantsRepository extends JpaRepository<Habitants, Long> {
}
