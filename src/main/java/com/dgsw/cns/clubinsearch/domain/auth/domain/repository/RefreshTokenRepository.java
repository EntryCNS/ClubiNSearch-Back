package com.dgsw.cns.clubinsearch.domain.auth.domain.repository;

import com.dgsw.cns.clubinsearch.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
