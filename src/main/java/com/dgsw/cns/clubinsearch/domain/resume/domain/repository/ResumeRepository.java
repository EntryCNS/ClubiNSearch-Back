package com.dgsw.cns.clubinsearch.domain.resume.domain.repository;

import com.dgsw.cns.clubinsearch.domain.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findAllByRecruitment_Id(Long id);
}
