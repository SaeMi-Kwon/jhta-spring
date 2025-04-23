package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "imgboard")
@EntityListeners(AuditingEntityListener.class)
public class ImgBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fnum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private BoardUser boardUser;
    private String title;
    private String content;
    private String orgfilename;
    private String savefilename;
    private long filesize;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

}
