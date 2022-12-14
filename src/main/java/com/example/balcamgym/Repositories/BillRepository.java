package com.example.balcamgym.Repositories;

import com.example.balcamgym.Models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long> {
    Bill findBillById(long id);
}
