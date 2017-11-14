package com.cbre.cm.dsf.ds.deal.model;

import com.cbre.cm.dsf.ds.deal.persistence.DealDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JsonTest
public class DealTests {
    @Autowired
    private JacksonTester<Deal> json;
    @Test
    public void testClone() throws Exception {
        String content = "{\n" +
                "\t\"name\": \"new york deal\",\n" +
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
                "\t\"originationFee\": {\n" +
                "\t\t\"amount\": \"2495\"\n" +
                "\t},\n" +
                "\t\"referralFee\": {\n" +
                "\t\t\"amount\": \"1365\"\n" +
                "\t}\n" +
                "}";

        Deal deal = json.parseObject(content);

        String cloneContent = "{\n" +
                "\t\"name\": \"new york deal\"\n"+
                "}";

        Deal deal2 = json.parseObject(cloneContent);
        deal.update(deal2);
        assertEquals(deal.getName(),"new york deal");
    }
}
