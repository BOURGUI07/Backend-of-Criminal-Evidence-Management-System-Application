/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import main.util.enums.TrackAction;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author hp
 */
@Entity
@Table(name="detective",indexes={
    @Index(name="idx_track_entity_id",columnList="id")
})
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Accessors(chain=true)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level=AccessLevel.PRIVATE)
public class TrackEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",updatable=false)
    Integer id;
    
    
    @Column(name="date",updatable=false)
    LocalDateTime date;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="evidence_id",updatable=false)
    @JsonBackReference
    @ToString.Exclude
    Evidence evidence;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="detective_id",updatable=false)
    @JsonBackReference
    @ToString.Exclude
    Detective detective;
    
    @Enumerated(EnumType.STRING)
    @Column(name="action",nullable=false)
    TrackAction action;
    
    @Column(name="reason")
    String reason;
}
