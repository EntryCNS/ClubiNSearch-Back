package com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    List<Recruitment> findByClub_IdAndTitleAndPositionAndEmploymentType(Long id, String search, String position, String employmentType);

}
