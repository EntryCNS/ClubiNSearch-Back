package com.dgsw.cns.clubinsearch.domain.resume.domain.repository;

import com.dgsw.cns.clubinsearch.domain.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
