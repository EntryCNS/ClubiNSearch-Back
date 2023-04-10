package com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.custom.RecruitmentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>, RecruitmentCustomRepository {
}
