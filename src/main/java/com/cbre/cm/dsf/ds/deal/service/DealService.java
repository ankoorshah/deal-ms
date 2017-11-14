package com.cbre.cm.dsf.ds.deal.service;

import com.cbre.cm.dsf.ds.deal.persistence.DealRepository;
import com.cbre.cm.dsf.ds.deal.model.Deal;
import com.cbre.cm.dsf.ds.deal.persistence.DealDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DealService {
    @Autowired
    private DealRepository dealRepository;

    public Long createDeal(Deal deal){
        DealDTO dealDTO = new DealDTO(deal);
        dealRepository.save(dealDTO);
        return dealDTO.getId();
    }

    public Deal findDealById(Long id){
        Deal deal =  dealRepository.findOne(id).toDeal();
        return deal;
    }
}
