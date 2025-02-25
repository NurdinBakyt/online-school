package org.nurdin.school.service.impl;

import org.nurdin.school.entity.RejectionOfTheBidForWorkEntity;
import org.nurdin.school.repository.RejectionOfTheBidRepository;
import org.nurdin.school.service.RejectionOfTheBidService;
import org.springframework.stereotype.Service;


@Service
public class RejectionOfTheBidServiceImpl implements RejectionOfTheBidService {
    private final RejectionOfTheBidRepository rejectionOfTheBidRepository;

    public RejectionOfTheBidServiceImpl(RejectionOfTheBidRepository rejectionOfTheBidRepository) {
        this.rejectionOfTheBidRepository = rejectionOfTheBidRepository;
    }


    @Override
    public RejectionOfTheBidForWorkEntity save(RejectionOfTheBidForWorkEntity rejectionOfTheBidForWorkEntity) {
        return rejectionOfTheBidRepository.save(rejectionOfTheBidForWorkEntity);
    }
}
