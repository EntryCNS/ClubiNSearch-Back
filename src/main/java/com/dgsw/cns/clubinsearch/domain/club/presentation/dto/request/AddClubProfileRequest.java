package com.dgsw.cns.clubinsearch.domain.club.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class AddClubProfileRequest {
    private Long clubId;
    private MultipartFile profile;
}
