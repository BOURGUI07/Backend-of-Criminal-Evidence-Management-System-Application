/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.repo;

import java.util.List;
import java.util.Optional;
import main.entity.CriminalCase;
import main.util.enums.CaseStatus;
import main.util.enums.CaseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface CriminalCaseRepo extends JpaRepository<CriminalCase,Integer>{
    @Override
    Page<CriminalCase> findAll(Pageable pageable);
    Optional<CriminalCase> findByLeadDetectiveId(Integer id);
    boolean existsByCaseNumber(String number);
    List<CriminalCase> findByCaseTypeAndCaseStatus(CaseType type,CaseStatus status);
}
