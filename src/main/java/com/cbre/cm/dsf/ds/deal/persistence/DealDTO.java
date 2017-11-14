package com.cbre.cm.dsf.ds.deal.persistence;

import com.cbre.cm.dsf.ds.deal.model.Deal;
import com.cbre.cm.dsf.ds.deal.model.enums.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
@Table(name = "deal")
@Data
public class DealDTO implements Serializable {
    private static final long serialVersionUID = 5223248192580051759L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private Integer amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "dealstage")
    private DealStage dealStage;
    @Enumerated(EnumType.STRING)
    @Column(name = "propertytype")
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    @Column(name = "dealstructure")
    private DealStructure dealStructure;
    @Enumerated(EnumType.STRING)
    @Column(name = "primaryfeetype")
    private FeeType primaryFeeType;
    @Column(name = "cbrenetfee")
    private Double cbreNetFee;
    @Enumerated(EnumType.STRING)
    @Column(name = "dealstatus")
    private DealStatus dealStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "financingpurpose")
    private FinancingPurpose financingPurpose;
    @Column(name = "sponsorid")
    private String sponsorName;
    @Column(name = "borrowerid")
    private String borrowerName;
    @Column(name = "investorid")
    private Long investorId;
    @Column(name = "investordealnumber")
    private Long investorDealNumber;
    @Column(name = "expectedclosedate")
    private Date expectedCloseDate;
    @Column(name = "grossfeepct")
    private Double grossFeePct;
    @Column(name = "created_dt")
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    @Column(name = "updated_dt")
    private Date updated;
    @Column(name = "originationfeeamount")
    private Double originationFeeAmount;
    @OneToMany(cascade = CascadeType.ALL)
    @IgnoreAutoCopy(value=true)
    private List<InstallmentDTO> originationFeeInstallments;


    public DealDTO() {
    }

    public DealDTO(Deal deal) {
        DTOHelper.copyModelToDTO(deal, this);

        this.originationFeeInstallments = deal.getOriginationFeeInstallments().stream()
                .map(feeInstallment -> new InstallmentDTO(feeInstallment))
                .collect(Collectors.toList());
    }



    public Deal toDeal(){
        Deal deal = new Deal();

        Optional<Boolean> result = Arrays.stream(this.getClass().getMethods())
                .filter(method -> method.getName().startsWith("set"))
                .map((dealDTOSetter) -> {
                    String getterName = dealDTOSetter.getName().replaceFirst(Pattern.quote("set"), "get");
                    try {
                        Optional<Method> dealDTOGetterO = Optional.of(this.getClass().getMethod(getterName));
                        if(dealDTOGetterO.isPresent()){
                            Method dealDTOGetter = dealDTOGetterO.get();
                            Method dealSetter = deal.getClass().getMethod(dealDTOSetter.getName(), dealDTOGetter.getReturnType());
                            dealSetter.invoke(deal, dealDTOGetter.invoke(this));

                        }
                        } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                })
                .reduce((a,b) -> a&&b);
        return deal;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Double getOriginationFeeAmount() {
        return originationFeeAmount;
    }

    public void setOriginationFeeAmount(Double originationFeeAmount) {
        this.originationFeeAmount = originationFeeAmount;
    }

    public List<InstallmentDTO> getOriginationFeeInstallments() {
        return originationFeeInstallments;
    }

    public void setOriginationFeeInstallments(List<InstallmentDTO> originationFeeInstallments) {
        this.originationFeeInstallments = originationFeeInstallments;
    }

    @Override
    public String toString() {
        return "DealDTO{" +
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
                '}';
    }
}
