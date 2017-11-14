package com.cbre.cm.dsf.ds.deal.persistence;

import com.cbre.cm.dsf.ds.deal.persistence.DealDTO;
import org.springframework.data.repository.CrudRepository;

public interface DealRepository extends CrudRepository<DealDTO,Long> {

}
