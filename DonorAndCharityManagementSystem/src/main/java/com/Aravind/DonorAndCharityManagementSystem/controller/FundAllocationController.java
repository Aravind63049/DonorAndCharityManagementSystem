package com.Aravind.DonorAndCharityManagementSystem.controller;

import com.Aravind.DonorAndCharityManagementSystem.requestdto.FundAllocationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.FundAllocationResponseDto;
import com.Aravind.DonorAndCharityManagementSystem.serviceinter.FundAllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fund-allocations")
public class FundAllocationController {

    public final FundAllocationService fundAllocationService;
    public FundAllocationController(FundAllocationService fundAllocationService) {
        this.fundAllocationService = fundAllocationService;
    }

    @PostMapping
    public ResponseEntity<FundAllocationResponseDto> createFundAllocation(@RequestBody FundAllocationRequestDto fundAllocationRequestDto) {
        return new ResponseEntity<>(fundAllocationService.createFundAllocation(fundAllocationRequestDto), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FundAllocationResponseDto> getFundAllocationById(@PathVariable Long id) {
        return new ResponseEntity<>(fundAllocationService.getFundAllocationById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FundAllocationResponseDto>> getAllFundAllocations() {
        return new ResponseEntity<>(fundAllocationService.getAllFundAllocations(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FundAllocationResponseDto> updateFundAllocation(@PathVariable Long id, @RequestBody FundAllocationRequestDto fundAllocationRequestDto) {
        return new ResponseEntity<>(fundAllocationService.updateFundAllocation(id, fundAllocationRequestDto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundAllocation(@PathVariable Long id) {
        fundAllocationService.deleteFundAllocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
