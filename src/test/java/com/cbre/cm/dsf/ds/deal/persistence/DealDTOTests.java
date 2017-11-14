package com.cbre.cm.dsf.ds.deal.persistence;

import com.cbre.cm.dsf.ds.deal.model.Deal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JsonTest
public class DealDTOTests {
    @Autowired
    private JacksonTester<Deal> json;

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\n" +
                "\t\"name\": \"new jersey deal\",\n" +
                "\t\"amount\": \"150000\",\n" +
                "\t\"dealStage\": \"CLOSED\",\n" +
                "\t\"propertyType\": \"MULTIFAMILY\",\n" +
                "\t\"dealStructure\": \"Debt_Permanent\",\n" +
                "\t\"primaryFeeType\": \"FINANCE\",\n" +
                "\t\"cbreNetFee\": \"2495\",\n" +
                "\t\"dealStatus\": \"ACTIVE\",\n" +
                "\t\"financingPurpose\": \"REFINANCE\",\n" +
                "\t\"sponsorName\": \"Vincent Cozzi\",\n" +
                "\t\"borrowerName\": \"Dave Hanusa\",\n" +
                "\t\"investorId\": \"8888\",\n" +
                "\t\"investorDealNumber\": \"2233\",\n" +
                "\t\"expectedCloseDate\": \"\",\n" +
                "\t\"grossFeePct\": \"2.573\",\n" +
                "\t\"originationFeeAmount\":50000,\n" +
                "\t\"originationFeeInstallments\": [{\n" +
                "\t\t\"type\":\"Deal_Closing\",\n" +
                "\t\t\"totalAllocationAmt\": \"15000\",\n" +
                "\t\t\"dueDate\":\"2018-03-24\"\n" +
                "\t},{\n" +
                "\t\t\"type\":\"Deal_Closing\",\n" +
                "\t\t\"totalAllocationAmt\": \"10000\",\n" +
                "\t\t\"dueDate\":\"2018-06-24\"\n" +
                "\t},{\n" +
                "\t\t\"type\":\"Deal_Closing\",\n" +
                "\t\t\"totalAllocationAmt\": \"25000\",\n" +
                "\t\t\"dueDate\":\"2018-12-24\"\n" +
                "\t}]\n" +
                "}";
        Deal deal = json.parseObject(content);
        DealDTO dealDTO = new DealDTO(deal);
        assertEquals(dealDTO.getBorrowerName(),"Dave Hanusa");
    }
}
