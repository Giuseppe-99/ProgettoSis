package com.example.progettosis.Repositories;

import com.example.progettosis.Entities.Storico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rep extends JpaRepository<Storico, Integer> {
    List<Storico> findByEmail(String email);
}