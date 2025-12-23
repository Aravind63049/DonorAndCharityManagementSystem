package com.Aravind.DonorAndCharityManagementSystem.repository;

import com.Aravind.DonorAndCharityManagementSystem.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    CampaignRepository extends JpaRepository<Campaign,Long> {
}
