package org.nurdin.school.service.impl;

import org.nurdin.school.entity.BidForStudyEntity;
import org.nurdin.school.repository.BidForStudyRepository;
import org.nurdin.school.service.BidForStudyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidForStudyServiceImpl implements BidForStudyService {

    private final BidForStudyRepository bidForStudyRepository;



    public BidForStudyServiceImpl(BidForStudyRepository bidForStudyRepository) {
        this.bidForStudyRepository = bidForStudyRepository;
    }

    @Override
    public BidForStudyEntity findById(Long id) {
        return bidForStudyRepository.findById(id).get();
    }

    @Override
    public BidForStudyEntity saveBidForStudy(BidForStudyEntity bidForStudy) {
        return bidForStudyRepository.save(bidForStudy);
    }

    @Override
    public BidForStudyEntity findByEmailParent(String email) {
        return bidForStudyRepository.findBidForStudyEntityByEmailParent(email);
    }

    @Override
    public List<BidForStudyEntity> getAllBidForStudy() {
        return bidForStudyRepository.findAll();
    }
}
