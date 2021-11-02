package com.gydytojai.web.gydytojaiweb.repository;

import com.gydytojai.web.gydytojaiweb.model.Gydytojas;
import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "diagnozesRestRepository", collectionResourceRel = "diagnozes")
public interface DiagnozesRestRepository extends PagingAndSortingRepository<Diagnozes, Integer> {

    List<Gydytojas> findByDiagnoze(@Param("diagnoze") String diagnoze);
}
