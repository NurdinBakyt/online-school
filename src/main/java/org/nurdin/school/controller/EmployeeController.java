package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.BidForWorkDTO;
import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.service.impl.BidForWorkServiceImpl;
import org.nurdin.school.util.BidForWorkDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {

    private final BidForWorkDTOMapper bidForWorkDTOMapper;
    private final BidForWorkServiceImpl bidForWorkServiceImpl;

    public EmployeeController(BidForWorkDTOMapper bidForWorkDTOMapper, BidForWorkServiceImpl bidForWorkServiceImpl) {
        this.bidForWorkDTOMapper = bidForWorkDTOMapper;
        this.bidForWorkServiceImpl = bidForWorkServiceImpl;
    }

    @PostMapping(value = "/createBidForWork")
    @Operation(summary = "Создание заявки на вступление в ряды работников в школе")
    public void createBidForWork(
            @RequestBody BidForWorkEntity bidForWorkEntity
    ) {
        bidForWorkServiceImpl.saveBidForWork(bidForWorkEntity);
    }

    @GetMapping(value = "/getAllBids")
    @Operation(summary = "Добавление заявок")
    public List<BidForWorkEntity> getAllBids() {
        return bidForWorkServiceImpl.getAllBidForWork();
    }


}