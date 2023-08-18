package com.dgsw.cns.clubinsearch.global.interceptor;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNotFoundException;
import com.dgsw.cns.clubinsearch.domain.user.facade.UserFacade;
import com.dgsw.cns.clubinsearch.global.annotation.ClubAuth;
import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClubAuthorizationInterceptor implements HandlerInterceptor {

    private final ClubRepository clubRepository;

    private final UserFacade userFacade;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ClubAuth clubAuth = handlerMethod.getMethodAnnotation(ClubAuth.class);
        if (clubAuth == null) {
            return true;
        }

        Club club = clubRepository.findById(Long.valueOf(request.getHeader("clubId")))
                .orElseThrow(() -> ClubNotFoundException.EXCEPTION);

        if (!userFacade.getCurrentUser().getClub().equals(club)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "타 동아리에 접근할 수 없습니다");
        }
        return true;
    }
}
