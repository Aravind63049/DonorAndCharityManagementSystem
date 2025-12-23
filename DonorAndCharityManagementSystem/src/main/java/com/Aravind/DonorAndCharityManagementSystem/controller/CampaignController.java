package com.Aravind.DonorAndCharityManagementSystem.controller;


import com.Aravind.DonorAndCharityManagementSystem.requestdto.CampaignRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.CampaignResponseDto;
import com.Aravind.DonorAndCharityManagementSystem.serviceinter.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    public final CampaignService campaignService;
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<CampaignResponseDto> createCampaign(@RequestBody CampaignRequestDto campaignRequestDto) {
        return new ResponseEntity<>(campaignService.createCampaign(campaignRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignResponseDto> getCampaignById(@PathVariable Long id) {
        return new ResponseEntity<>(campaignService.getCampaignById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CampaignResponseDto>> getAllCampaigns() {
        return new ResponseEntity<>(campaignService.getAllCampaigns(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignResponseDto> updateCampaign(@PathVariable Long id, @RequestBody CampaignRequestDto campaignRequestDto) {
        return new ResponseEntity<>(campaignService.updateCampaign(id, campaignRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
