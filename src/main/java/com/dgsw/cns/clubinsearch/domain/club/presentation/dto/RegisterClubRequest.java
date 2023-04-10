package com.dgsw.cns.clubinsearch.domain.club.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterClubRequest {

    @NotBlank(message = "동아리 이름은 필수 정보 입니다")
    private String clubName;

}
