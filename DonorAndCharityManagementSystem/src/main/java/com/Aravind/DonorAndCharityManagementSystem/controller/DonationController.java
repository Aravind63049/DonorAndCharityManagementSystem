package com.Aravind.DonorAndCharityManagementSystem.controller;



import com.Aravind.DonorAndCharityManagementSystem.requestdto.DonationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.DonationResponseDto;
import com.Aravind.DonorAndCharityManagementSystem.serviceinter.DonationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    public final DonationService donationService;
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping
    public ResponseEntity<DonationResponseDto> createDonation(@RequestBody DonationRequestDto donationRequestDto) {
        return new ResponseEntity<>(donationService.createDonation(donationRequestDto), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DonationResponseDto> getDonationById(@PathVariable Long id){
        return new ResponseEntity<>(donationService.getDonationById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DonationResponseDto>> getAllDonations(){
        return new ResponseEntity<>(donationService.getAllDonations(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationResponseDto> updateDonation(@PathVariable Long id, @RequestBody DonationRequestDto donationRequestDto){
        return new ResponseEntity<>(donationService.updateDonation(id, donationRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id){
        donationService.deleteDonation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
