package com.example.demo.db;

import com.example.demo.model.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KundeRepository extends JpaRepository<Kunde, Integer>
{
    Kunde findByKundeId(Integer kundeId);

}
