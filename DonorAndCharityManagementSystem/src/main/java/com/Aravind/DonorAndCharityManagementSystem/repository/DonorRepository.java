package com.Aravind.DonorAndCharityManagementSystem.repository;

import com.Aravind.DonorAndCharityManagementSystem.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor,Long> {
}
