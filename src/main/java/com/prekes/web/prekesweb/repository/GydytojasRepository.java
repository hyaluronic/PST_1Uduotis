package com.prekes.web.prekesweb.repository;

import com.prekes.web.prekesweb.model.Gydytojas;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GydytojasRepository extends CrudRepository<Gydytojas, Integer> {

    List<Gydytojas> findByTelNr(String telNr);

    Gydytojas findOneByVardas(String vardas);

    List<Gydytojas> findByVardasAndTelNr(String vardas, String telNr);
}
