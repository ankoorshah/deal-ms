package com.cbre.cm.dsf.ds.deal.controller;

import com.cbre.cm.dsf.ds.deal.model.Deal;
import com.cbre.cm.dsf.ds.deal.service.DealService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/deal")
public class DealController {

    @Autowired
    DealService dealService;



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Deal getDeal(@PathVariable("id") Long id) {
        return dealService.findDealById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Deal deal) {
        long dealId = dealService.createDeal(deal);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dealId).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable("id") Long dealId, @RequestBody Deal deal) {
        Deal dealCurrent = dealService.findDealById(dealId);
        dealCurrent.update(deal);
        dealService.createDeal(dealCurrent);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dealCurrent.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
