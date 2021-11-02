package com.gydytojai.web.gydytojaiweb.repository;

import com.gydytojai.web.gydytojaiweb.model.Gydytojas;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//http://localhost:8080/zmonesRestRepository
//http://localhost:8080/zmonesRestRepository/1
//http://localhost:8080/zmonesRestRepository/?size=3
//http://localhost:8080/zmonesRestRepository/?page=1&size=2
//http://localhost:8080/zmonesRestRepository/?sort=vardas,desc

//@Param("role") and go to http://localhost:8080/zmonesRestRepository/search/findByRole?role=Admin

@RepositoryRestResource(path = "gydytojaiRestRepository", collectionResourceRel = "gydytojai")
public interface GydytojasRestRepository extends PagingAndSortingRepository<Gydytojas, Integer> {

    List<Gydytojas> findByTelNr(@Param("telNr") String telNr);
}
