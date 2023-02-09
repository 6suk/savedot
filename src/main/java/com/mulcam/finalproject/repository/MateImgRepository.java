package com.mulcam.finalproject.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mulcam.finalproject.entity.MateImg;

@Repository
public interface MateImgRepository extends JpaRepository<MateImg, UUID> {
}