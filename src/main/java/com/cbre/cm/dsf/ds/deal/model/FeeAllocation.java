package com.cbre.cm.dsf.ds.deal.model;

import java.util.List;

public class FeeAllocation {
        private long producerId;
        private long businessUnitId;
        private Double totalAllocation;
        private Long referrerId;
        private List<FeeAllocation> feeAllocations;
}
