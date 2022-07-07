package com.example.demo.model;


import lombok.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "kunde_id_gen", sequenceName = "kunde_id_seq")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Kunde
{

    @Id //Primärschlüssel
    @GeneratedValue(generator = "kunde_id_gen")
    @Column(name = "kunde_id")
    private Integer kundeId;

    @Column(name = "kunde_name")
    private String kundeName;

    @Column(name = "strassen_name")
    private String strasseName;

    @Column(name = "hausnummer")
    private Integer hausNummer;

    private String bestellung;

    @Column(name = "anzahl_bestellungen")
    private Integer anzahlBestellungen;


    public Kunde(String kundeName) {
        this.kundeName = kundeName;
    }

}
