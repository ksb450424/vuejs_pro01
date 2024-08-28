package com.company.app1.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dataroom")
public class Dataroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dno;

    private String title;
    private String content;
    private String author;
    private String datafile;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime resdate;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int hits;

    // getters and setters
}