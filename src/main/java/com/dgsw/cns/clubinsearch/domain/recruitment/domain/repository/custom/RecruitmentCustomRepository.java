package com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.custom;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;

import java.util.List;

public interface RecruitmentCustomRepository {

    List<Recruitment> searchRecruitment(String clubName, String search, String position, EmploymentType employmentType);

}
