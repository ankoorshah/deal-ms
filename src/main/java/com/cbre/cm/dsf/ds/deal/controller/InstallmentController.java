package com.cbre.cm.dsf.ds.deal.controller;


import com.cbre.cm.dsf.ds.deal.model.Deal;
import com.cbre.cm.dsf.ds.deal.model.Installment;
import com.cbre.cm.dsf.ds.deal.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deal/{id}/installments")
public class InstallmentController {
    @Autowired
    DealService dealService;

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<?> addInstallment(@PathVariable("id") long dealId, @RequestBody List<Installment> installments){
        Deal dealCurrent = dealService.findDealById(dealId);
        dealCurrent.setOriginationFeeInstallments(installments);
        dealService.createDeal(dealCurrent);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(dealCurrent.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Installment> getInstallments(@PathVariable("id") long dealId){
        Deal deal = dealService.findDealById(dealId);
        return deal.getOriginationFeeInstallments();
    }
}
