package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.BidForWorkDTO;
import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.service.impl.BidForWorkServiceImpl;
import org.nurdin.school.util.mappers.BidForWorkMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {

    private final BidForWorkServiceImpl bidForWorkServiceImpl;
    private final BidForWorkMapper bidForWorkMapper;

    public EmployeeController(BidForWorkServiceImpl bidForWorkServiceImpl, BidForWorkMapper bidForWorkMapper) {
        this.bidForWorkServiceImpl = bidForWorkServiceImpl;
        this.bidForWorkMapper = bidForWorkMapper;
    }

    @PostMapping(value = "/createBidForWork")
    @Operation(summary = "Создание заявки на вступление в ряды работников в школе")
    public ResponseEntity<String> createBidForWork(@RequestBody BidForWorkDTO bidForWorkDTO) {


        bidForWorkServiceImpl.saveBidForWork(bidForWorkMapper.bidForWorkDtoToEntity(bidForWorkDTO));
        return ResponseEntity.ok("заявка успешно создана");
    }


}
