package com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.custom;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import java.util.List;

public interface RecruitmentCustomRepository {

    List<Recruitment> filterRecruitment(String clubName, String search, String position, String employmentType);

}
