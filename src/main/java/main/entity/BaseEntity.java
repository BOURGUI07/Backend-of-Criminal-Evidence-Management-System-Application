/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author hp
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
@JsonIgnoreProperties(value = { "lastModifiedDate", "createdBy", "lastModifiedBy"})
public class BaseEntity {

    @CreatedDate
    @Column(name="createdAt", nullable=false,updatable=false)
    @JsonProperty
    private Instant createdDate;

    @LastModifiedDate
    @Column(name="lastModifiedAt")
    private Instant lastModifiedDate;

    @CreatedBy
    @Column(name="createdBy")
    private String createdBy;

    @LastModifiedBy
    @Column(name="lastModifiedBy")
    private String lastModifiedBy;
}
