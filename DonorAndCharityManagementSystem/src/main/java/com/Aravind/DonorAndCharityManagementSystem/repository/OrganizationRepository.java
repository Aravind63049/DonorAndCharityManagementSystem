package com.Aravind.DonorAndCharityManagementSystem.repository;

import com.Aravind.DonorAndCharityManagementSystem.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
}
