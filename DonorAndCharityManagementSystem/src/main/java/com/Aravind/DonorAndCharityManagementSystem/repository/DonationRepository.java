package com.Aravind.DonorAndCharityManagementSystem.repository;

import com.Aravind.DonorAndCharityManagementSystem.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation,Long> {
}