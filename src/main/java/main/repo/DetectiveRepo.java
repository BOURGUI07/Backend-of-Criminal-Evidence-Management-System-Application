/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import main.entity.Detective;
import main.util.enums.EmploymentStatus;
import main.util.enums.Rank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface DetectiveRepo extends JpaRepository<Detective,Integer>{
    @Override
    Page<Detective> findAll(Pageable pageable);
    boolean existsByBadgeNumberIgnoreCase(String badgeNumber);
    Optional<Detective> findByPersonFirstnameIgnoreCaseAndPersonLastnameIgnoreCase(String firstname,String lastname);
    Optional<Detective> findByPersonId(Integer id);
    List<Detective> findByArmedTrue();
    List<Detective> findByPersonHiringDateBetween(LocalDateTime start, LocalDateTime end);
    List<Detective> findByArmedAndRankAndStatus(Boolean armed, Rank rank,EmploymentStatus status);
}
