/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import main.util.enums.CaseStatus;
import main.util.enums.CaseType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author hp
 */
@Entity
@Table(name="criminal_case",indexes={
    @Index(name="idx_case_id",columnList="id"),
    @Index(name="idx_case_number",columnList="caseNumber")
})
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Accessors(chain=true)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level=AccessLevel.PRIVATE)
public class CriminalCase extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",updatable=false)
    Integer id;
    
    @Column(name="case_number",unique=true,nullable=false,updatable=false)
    String caseNumber;
    
    @Enumerated(EnumType.STRING)
            @Column(name="case_type",nullable=false)
    CaseType caseType;
    
    @Column(name="short_desc",nullable=false,length=500)
    String shortDesc;
    
    
    @Column(name="long_desc",length=10000)
    String longDesc;
    
    
    @Column(name="notes")
    String notes;
    
    @Enumerated(EnumType.STRING)
            @Column(name="case_status",nullable=false)
    CaseStatus caseStatus;
    
    @ManyToMany(mappedBy="criminalCases")
    @JsonBackReference
    @ToString.Exclude
    Set<Detective> detectives = new HashSet<>();
    
    
    @OneToOne
    @JoinColumn(name="detective_id",nullable=false)
    Detective leadDetective;
    
    
    @OneToMany(mappedBy="criminalCase",cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference
    Set<Evidence> evidences = new HashSet<>();
    
    
    @Version
    Integer version;
    
}
