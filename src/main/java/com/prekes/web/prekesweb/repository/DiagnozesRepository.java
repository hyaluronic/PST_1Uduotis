package com.prekes.web.prekesweb.repository;

import com.prekes.web.prekesweb.model.Diagnozes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnozesRepository extends CrudRepository<Diagnozes, Integer> {

    List<Diagnozes> findByDiagnoze(String diagnoze);

    Diagnozes findOneByDiagnoze(String diagnoze);

    List<Diagnozes> findByGydytojoId(int gydytojoId);

    List<Diagnozes> findByPacientoId(int pacientoId);

    List<Diagnozes> findByData(String data);

    List<Diagnozes> findByPacientoIdAndGydytojoIdAndDiagnoze(int pacientoId, int gydytojoId, String diagnoze);
}
