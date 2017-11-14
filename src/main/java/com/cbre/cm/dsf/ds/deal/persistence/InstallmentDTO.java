package com.cbre.cm.dsf.ds.deal.persistence;

import com.cbre.cm.dsf.ds.deal.model.Installment;
import com.cbre.cm.dsf.ds.deal.model.enums.InstallmentType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "installment")
public class InstallmentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "installmenttype")
    private InstallmentType installmentType;
    @Column(name = "duedate")
    private Date dueDate;
    @Column(name = "totalallocationamt")
    private Integer totalAllocationAmt;
    @Column(name = "bookeddate")
    private Date bookedDate;
    @CreationTimestamp
    @Column(name = "created_dt")
    private Date createdDate;
    @UpdateTimestamp
    @Column(name = "updated_dt")
    private Date updatedDate;

    public InstallmentDTO() {
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public InstallmentDTO(Installment feeInstallment) {
        DTOHelper.copyModelToDTO(feeInstallment,this);
    }

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
