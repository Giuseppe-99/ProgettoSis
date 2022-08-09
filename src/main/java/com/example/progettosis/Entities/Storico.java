package com.example.progettosis.Entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Table(name = "storico", schema = "public")
public class Storico {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="data",nullable = true)
    private LocalDateTime data;

    @Basic
    @Column(name="tipo",nullable = true)
    private String tipo;

    @Basic
    @Column(name="email",nullable = true)
    private String email;

    /*
    @ManyToOne(optional = false)
    @JoinColumn(name = "email")
    private Persona use;

     */

    public Storico(String email, String tipo){
        this.data= LocalDateTime.now();
        this.email=email;
        this.tipo=tipo;
    }

    public Storico() {

    }
}
