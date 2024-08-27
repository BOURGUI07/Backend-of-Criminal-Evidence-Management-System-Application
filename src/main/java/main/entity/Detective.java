/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import main.util.enums.EmploymentStatus;
import main.util.enums.Rank;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author hp
 */
@Entity
@Table(name="detective",indexes={
    @Index(name="idx_detective_id",columnList="id"),
    @Index(name="idx_badge_number",columnList="badgeNumber")
})
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Accessors(chain=true)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Detective extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",updatable=false)
    Integer id;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="person_id",unique=true,nullable=false,updatable=false)
    Person person;
    
    
    @Column(name="badge_number",unique=true, nullable=false,updatable=false)
    String badgeNumber;
    
    @Column(name = "detective_rank",nullable=false)
    @Enumerated(EnumType.STRING)
    Rank rank;
    
    @Column(name="armed")
    Boolean armed= false;
    
    @Column(name = "status",nullable=false)
    @Enumerated(EnumType.STRING)
    EmploymentStatus status;
    
    @ManyToMany
    @JoinTable(
        name = "detective_criminalCase",
        joinColumns = @JoinColumn(name = "detective_id"),
        inverseJoinColumns = @JoinColumn(name = "criminal_case_id")
    )
    @JsonManagedReference
    Set<CriminalCase> criminalCases = new HashSet<>();
    
    
    @OneToMany(mappedBy="detective",cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference
    List<TrackEntity> trackEntities = new ArrayList<>();
    
    
}
