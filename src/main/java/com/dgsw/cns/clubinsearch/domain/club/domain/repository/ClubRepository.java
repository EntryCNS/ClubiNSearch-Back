package com.dgsw.cns.clubinsearch.domain.club.domain.repository;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Boolean existsByName(String name);

    Optional<Club> findAllByNameContaining(String name);

    Optional<Club> findByName(String name);
}
