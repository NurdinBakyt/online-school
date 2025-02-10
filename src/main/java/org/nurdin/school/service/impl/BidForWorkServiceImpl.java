package org.nurdin.school.service.impl;

import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.repository.BidForWorkRepository;
import org.nurdin.school.service.BidForWorkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidForWorkServiceImpl implements BidForWorkService {
    private final BidForWorkRepository bidForWorkRepository;

    public BidForWorkServiceImpl(BidForWorkRepository bidForWorkRepository) {
        this.bidForWorkRepository = bidForWorkRepository;
    }
    @Override
    public void saveBidForWork(BidForWorkEntity bidForWorkEntity) {
        bidForWorkRepository.save(bidForWorkEntity);
    }

    @Override
    public void deleteBidForWork() {

    }

    @Override
    public void updateBidForWork(BidForWorkEntity bidForWorkEntity) {

    }


    @Override
    public List<BidForWorkEntity> getAllBidForWork() {
         return bidForWorkRepository.findAll();
    }

    @Override
    public BidForWorkEntity getBidForWorkByUserEmail(String userEmail) {
        return bidForWorkRepository.findByEmail(userEmail);
    }
}
