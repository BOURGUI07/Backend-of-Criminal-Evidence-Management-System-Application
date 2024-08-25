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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author hp
 */
@Entity
@Table(name="detective",indexes={
    @Index(name="idx_evidence_id",columnList="id"),
    @Index(name="idx_evidence_number",columnList="evidenceNumber"),
    @Index(name="idx_item_name",columnList="itemName")
})
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Accessors(chain=true)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Evidence extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",updatable=false)
    Integer id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="criminal_case_id")
    @JsonBackReference
    @ToString.Exclude
    CriminalCase criminalCase;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="storage_id")
    @JsonBackReference
    @ToString.Exclude
    Storage storage;
    
    
    @Column(name="number",nullable=false,unique=true, updatable=false)
    String evidenceNumber;
    
    @Column(name="item_name",nullable=false,unique=true)
    String itemName;
    
    @Column(name="notes")
    String notes;
    
    @Column(name="archived")
    Boolean archived=false;
    
    @OneToMany(mappedBy="evidence",cascade=CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    Set<TrackEntity> trackEntities = new HashSet<>();
}
