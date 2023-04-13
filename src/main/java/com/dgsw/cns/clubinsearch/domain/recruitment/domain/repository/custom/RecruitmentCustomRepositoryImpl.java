package com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.custom;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dgsw.cns.clubinsearch.domain.recruitment.domain.QRecruitment.recruitment;

@RequiredArgsConstructor
public class RecruitmentCustomRepositoryImpl implements RecruitmentCustomRepository{
        private final JPAQueryFactory jpaQueryFactory;

        @Override
        public List<Recruitment> searchRecruitment(String clubName, String search, String position, String employmentType) {
             return jpaQueryFactory
                    .selectFrom(recruitment)
                    .where(
                            eqClubName(clubName),
                            eqSearch(search),
                            eqPosition(position),
                            eqEmploymentType(employmentType)
                    )
                     .fetch();
        }

        private BooleanExpression eqSearch(String search) {
            return search.isEmpty() ? null : recruitment.title.contains(search);
        }

        private BooleanExpression eqPosition(String position) {
            return position.isEmpty() ? null : recruitment.position.eq(position);
        }

        private BooleanExpression eqEmploymentType(String employmentType) {
            return employmentType.isEmpty() ? null : recruitment.employmentType.eq(employmentType);
        }

        private BooleanExpression eqClubName(String clubName) {
            return clubName.isEmpty() ? null : recruitment.club.name.eq(clubName);
        }
}
