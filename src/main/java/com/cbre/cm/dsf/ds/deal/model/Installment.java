package com.cbre.cm.dsf.ds.deal.model;

import com.cbre.cm.dsf.ds.deal.model.enums.InstallmentType;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

public class Installment {
    private Integer id;
    private InstallmentType installmentType;
    private Date dueDate;
    private Integer totalAllocationAmt;
    private Date bookedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InstallmentType getInstallmentType() {
        return installmentType;
    }

    public void setInstallmentType(InstallmentType installmentType) {
        this.installmentType = installmentType;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getTotalAllocationAmt() {
        return totalAllocationAmt;
    }

    public void setTotalAllocationAmt(Integer totalAllocationAmt) {
        this.totalAllocationAmt = totalAllocationAmt;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    @Override
    public String toString() {
        return "Installment{" +
                "id=" + id +
                ", installmentType=" + installmentType +
                ", dueDate=" + dueDate +
                ", totalAllocationAmt=" + totalAllocationAmt +
                ", bookedDate=" + bookedDate +
                '}';
    }
}
