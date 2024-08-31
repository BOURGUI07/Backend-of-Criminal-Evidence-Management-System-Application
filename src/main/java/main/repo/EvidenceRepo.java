/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.repo;

import java.util.List;
import main.entity.Evidence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface EvidenceRepo extends JpaRepository<Evidence,Integer>{
    List<Evidence> findByCriminalCaseId(Integer id);
    List<Evidence> findByStorageId(Integer id);
    boolean existsByEvidenceNumber(String number);
    List<Evidence> findByArchivedTrue();
    @Override
    Page<Evidence> findAll(Pageable pageable);
}
