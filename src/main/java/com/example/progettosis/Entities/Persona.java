package com.example.progettosis.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name = "persona", schema = "public")
public class Persona {

    @Id
    @Column(name = "mail",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String mail;

    /*
    @OneToMany(mappedBy = "pers")
    @JsonIgnore
    private List<Allegato> allegatoList;

    @OneToMany(mappedBy = "use")
    @JsonIgnore
    private List<Storico> storicoList;

     */

}
