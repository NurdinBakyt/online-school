package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.service.impl.BidForWorkServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {

    private final BidForWorkServiceImpl bidForWorkServiceImpl;

    public EmployeeController(BidForWorkServiceImpl bidForWorkServiceImpl) {
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
