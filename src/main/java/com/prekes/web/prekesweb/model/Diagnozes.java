package com.prekes.web.prekesweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diagnozes implements Comparable<Diagnozes> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int pacientoId;
    private int gydytojoId;
    private String diagnoze;
    private String data;

    public Diagnozes() {
    }

    public Diagnozes(int pacientoId, int gydytojoId, String diagnoze, String data) {
        this.pacientoId = pacientoId;
        this.gydytojoId = gydytojoId;
        this.diagnoze = diagnoze;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Diagnoze [id =" + id + ", pacientoId=" + pacientoId + ", gydytojoId=" + gydytojoId + ", diagnoze=" + diagnoze + ", data=" + data + "]";
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
        Diagnozes other = (Diagnozes) obj;
        return id == other.id;
    }

    @Override
    public int compareTo(Diagnozes o) {
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

    public int getPacientoId() {
        return pacientoId;
    }

    public void setPacientoId(int pacientoId) {
        if (pacientoId < 0) {
            throw new NullPointerException();
        }
        this.pacientoId = pacientoId;
    }

    public int getGydytojoId() {
        return gydytojoId;
    }

    public void setGydytojoId(int gydytojoId) {
        if (gydytojoId < 0) {
            throw new NullPointerException();
        }
        this.gydytojoId = gydytojoId;
    }

    public String getDiagnoze() {
        return diagnoze;
    }

    public void setDiagnoze(String diagnoze) {
        if (diagnoze.isBlank()) {
            throw new NullPointerException();
        }
        this.diagnoze = diagnoze;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data.isBlank()) {
            throw new NullPointerException();
        }
        this.data = data;
    }
}
