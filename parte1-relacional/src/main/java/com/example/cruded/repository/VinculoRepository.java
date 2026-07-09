package com.example.cruded.repository;

import com.example.cruded.model.Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinculoRepository extends JpaRepository<Vinculo, Integer> {
}