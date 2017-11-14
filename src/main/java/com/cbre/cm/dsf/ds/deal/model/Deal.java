package com.cbre.cm.dsf.ds.deal.model;

import com.cbre.cm.dsf.ds.deal.model.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Deal {
    private Long id;
    private String name;
    private Integer amount;
    private DealStage dealStage;
    private PropertyType propertyType;
    private DealStructure dealStructure;
    private FeeType primaryFeeType;
    private Double cbreNetFee;
    private DealStatus dealStatus;
    private FinancingPurpose financingPurpose;
    private String sponsorName;
    private String borrowerName;
    private Long investorId;
    private Long investorDealNumber;
    private Date expectedCloseDate;
    private Double grossFeePct;
    private Date created;
    private Date updated;
    private Double originationFeeAmount;
    private List<Installment> originationFeeInstallments;
    private Double referralFeeAmount;
    private List<Installment> referralFeeInstallments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public DealStage getDealStage() {
        return dealStage;
    }

    public void setDealStage(DealStage dealStage) {
        this.dealStage = dealStage;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public DealStructure getDealStructure() {
        return dealStructure;
    }

    public void setDealStructure(DealStructure dealStructure) {
        this.dealStructure = dealStructure;
    }

    public FeeType getPrimaryFeeType() {
        return primaryFeeType;
    }

    public void setPrimaryFeeType(FeeType primaryFeeType) {
        this.primaryFeeType = primaryFeeType;
    }

    public Double getCbreNetFee() {
        return cbreNetFee;
    }

    public void setCbreNetFee(Double cbreNetFee) {
        this.cbreNetFee = cbreNetFee;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public FinancingPurpose getFinancingPurpose() {
        return financingPurpose;
    }

    public void setFinancingPurpose(FinancingPurpose financingPurpose) {
        this.financingPurpose = financingPurpose;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public Long getInvestorDealNumber() {
        return investorDealNumber;
    }

    public void setInvestorDealNumber(Long investorDealNumber) {
        this.investorDealNumber = investorDealNumber;
    }

    public Date getExpectedCloseDate() {
        return expectedCloseDate;
    }

    public void setExpectedCloseDate(Date expectedCloseDate) {
        this.expectedCloseDate = expectedCloseDate;
    }

    public Double getGrossFeePct() {
        return grossFeePct;
    }

    public void setGrossFeePct(Double grossFeePct) {
        this.grossFeePct = grossFeePct;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Double getOriginationFeeAmount() {
        return originationFeeAmount;
    }

    public void setOriginationFeeAmount(Double originationFeeAmount) {
        this.originationFeeAmount = originationFeeAmount;
    }

    public List<Installment> getOriginationFeeInstallments() {
        return originationFeeInstallments;
    }

    public void setOriginationFeeInstallments(List<Installment> originationFeeInstallments) {
        this.originationFeeInstallments = originationFeeInstallments;
    }

    public Double getReferralFeeAmount() {
        return referralFeeAmount;
    }

    public void setReferralFeeAmount(Double referralFeeAmount) {
        this.referralFeeAmount = referralFeeAmount;
    }

    public List<Installment> getReferralFeeInstallments() {
        return referralFeeInstallments;
    }

    public void setReferralFeeInstallments(List<Installment> referralFeeInstallments) {
        this.referralFeeInstallments = referralFeeInstallments;
    }

    public boolean update(Deal deal) {
        Optional<Boolean> result = Arrays.stream(this.getClass().getMethods())
                .filter(method -> method.getName().startsWith("set"))
                .map((dealSetter) -> {
                    String getterName = dealSetter.getName().replaceFirst(Pattern.quote("set"), "get");
                    try {
                        Method dealGetter = deal.getClass().getMethod(getterName);
                        Object getterValue = dealGetter.invoke(deal);
                        if(getterValue!=null) {
                            dealSetter.invoke(this, getterValue);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                })
                .reduce((a,b) -> a&&b);
        return result.orElse(false);
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", dealStage=" + dealStage +
                ", propertyType=" + propertyType +
                ", dealStructure=" + dealStructure +
                ", primaryFeeType=" + primaryFeeType +
                ", cbreNetFee=" + cbreNetFee +
                ", dealStatus=" + dealStatus +
                ", financingPurpose=" + financingPurpose +
                ", sponsorName='" + sponsorName + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", investorId=" + investorId +
                ", investorDealNumber=" + investorDealNumber +
                ", expectedCloseDate=" + expectedCloseDate +
                ", grossFeePct=" + grossFeePct +
                ", created=" + created +
                ", updated=" + updated +
                ", originationFeeAmount=" + originationFeeAmount +
                ", originationFeeInstallments=" + originationFeeInstallments +
                ", referralFeeAmount=" + referralFeeAmount +
                ", referralFeeInstallments=" + referralFeeInstallments +
                '}';
    }
}
