package com.Aravind.DonorAndCharityManagementSystem.controller;

import com.Aravind.DonorAndCharityManagementSystem.requestdto.DonorRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.DonorResponseDto;
import com.Aravind.DonorAndCharityManagementSystem.serviceinter.DonorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {

    public final DonorService donorService;
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping
    public ResponseEntity<DonorResponseDto> createDonor(@RequestBody DonorRequestDto donorRequestDto){
        return new ResponseEntity<>(donorService.createDonor(donorRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonorResponseDto> getDonorById(@PathVariable Long id) {
        return new ResponseEntity<>(donorService.getDonorById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DonorResponseDto>> getAllDonors() {
        return new ResponseEntity<>(donorService.getAllDonors(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonorResponseDto> updateDonor(@PathVariable Long id, @RequestBody DonorRequestDto donorRequestDto){
        return new ResponseEntity<>(donorService.updateDonor(id, donorRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
