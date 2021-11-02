package com.prekes.web.prekesweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gydytojas implements Comparable<Gydytojas> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String vardas;
    private String telNr;

    public Gydytojas() {
    }

    public Gydytojas(String vardas, String telNr) {
        this.vardas = vardas;
        this.telNr = telNr;
    }

    @Override
    public String toString() {
        return "Gydytojas [id=" + id + ", vardas=" + vardas + ", telNr=" + telNr + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Gydytojas other = (Gydytojas) obj;
        return id == other.id;
    }

    @Override
    public int compareTo(Gydytojas o) {
        return Integer.compare(this.id, o.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new NullPointerException();
        }
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        if (vardas.isBlank()) {
            throw new NullPointerException();
        }
        this.vardas = vardas;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        if (telNr.isBlank()) {
            throw new NullPointerException();
        }
        this.telNr = telNr;
    }
}
