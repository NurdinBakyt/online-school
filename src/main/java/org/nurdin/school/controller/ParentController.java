package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.BidForStudyDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parent")
public class ParentController {

    @PostMapping(value = "/createBidForStudy")
    @Operation(summary = "Для подачи заявку на вступления ребёнка в школу", description = "Вроде не чего сложного")
    public void sendBid(@RequestBody BidForStudyDTO BidForStudyDTO) {
        //в общем щас надо BidForStudyDTO превратить BidForStudyEntity в мапере
        //и после надо BidForStudyEntity сохранить
        // подумать нужно ли что-то возращать методом ну или эндпоинтом этим

    }
}