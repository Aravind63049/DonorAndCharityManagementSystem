package com.Aravind.DonorAndCharityManagementSystem.serviceinter;



import com.Aravind.DonorAndCharityManagementSystem.requestdto.FundAllocationRequestDto;
import com.Aravind.DonorAndCharityManagementSystem.responsedto.FundAllocationResponseDto;

import java.util.List;

public interface FundAllocationService {
    FundAllocationResponseDto createFundAllocation(FundAllocationRequestDto dto);
    FundAllocationResponseDto getFundAllocationById(Long fundAllocationId);
    List<FundAllocationResponseDto> getAllFundAllocations();
    FundAllocationResponseDto updateFundAllocation(Long fundAllocationId, FundAllocationRequestDto dto);
    void deleteFundAllocation(Long fundAllocationId);
}
