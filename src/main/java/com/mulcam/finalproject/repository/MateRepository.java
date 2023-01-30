package com.mulcam.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mulcam.finalproject.entity.Mate;

@Repository
public interface MateRepository extends JpaRepository<Mate, Long> {
}