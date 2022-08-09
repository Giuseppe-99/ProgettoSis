package com.example.progettosis.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "allegato", schema = "public")
public class Allegato {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String filename;
    private String filetype;

    private String mail;

    @Lob
    private byte[] data;

    /*
    @ManyToOne
    @JoinColumn(name = "mail")
    private Persona pers;

     */

    public Allegato(String filename, String filetype,String mail, byte[] data) {
        this.filename = filename;
        this.filetype = filetype;
        this.mail = mail;
        this.data = data;
    }
}
