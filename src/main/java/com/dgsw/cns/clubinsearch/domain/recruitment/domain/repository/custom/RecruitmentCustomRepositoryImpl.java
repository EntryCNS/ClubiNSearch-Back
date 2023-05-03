package com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.custom;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dgsw.cns.clubinsearch.domain.recruitment.domain.QRecruitment.recruitment;

@RequiredArgsConstructor
public class RecruitmentCustomRepositoryImpl implements RecruitmentCustomRepository{
        private final JPAQueryFactory jpaQueryFactory;

        @Override
        public List<Recruitment> searchRecruitment(String clubName, String search, String position, EmploymentType employmentType) {
             return jpaQueryFactory
                    .selectFrom(recruitment)
                    .where(
                            containsClubName(clubName),
                            containsSearch(search),
                            containsPosition(position),
                            eqEmploymentType(employmentType)
                    )
                     .fetch();
        }

        private BooleanExpression containsSearch(String search) {
            return search.isEmpty() ? null : recruitment.title.contains(search);
        }

        private BooleanExpression containsPosition(String position) {
            return position.isEmpty() ? null : recruitment.position.contains(position);
        }

        private BooleanExpression eqEmploymentType(EmploymentType employmentType) {
            return employmentType == null ? null : recruitment.employmentType.eq(employmentType);
        }

        private BooleanExpression containsClubName(String clubName) {
            return clubName.isEmpty() ? null : recruitment.club.name.contains(clubName);
        }
}
