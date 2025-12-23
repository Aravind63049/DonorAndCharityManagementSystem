package com.Aravind.DonorAndCharityManagementSystem.controller;

import com.Aravind.DonorAndCharityManagementSystem.requestdto.OrganizationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.OrganizationResponseDto;
import com.Aravind.DonorAndCharityManagementSystem.serviceinter.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    public final OrganizationService organizationService;
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationResponseDto> createOrganization(@RequestBody OrganizationRequestDto organizationRequestDto){
        return new ResponseEntity<>(organizationService.createOrganization(organizationRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> getOrganizationById(@PathVariable Long id) {
        return new ResponseEntity<>(organizationService.getOrganizationById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDto>> getAllOrganizations(){
        return new ResponseEntity<>(organizationService.getAllOrganizations(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> updateOrganization(@PathVariable Long id, @RequestBody OrganizationRequestDto organizationRequestDto){
        return new ResponseEntity<>(organizationService.updateOrganization(id, organizationRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id){
        organizationService.deleteOrganization(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
