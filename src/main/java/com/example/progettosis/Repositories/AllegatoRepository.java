package com.example.progettosis.Repositories;

import com.example.progettosis.Entities.Allegato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllegatoRepository extends JpaRepository<Allegato,String > {


}
