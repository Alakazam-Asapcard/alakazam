package com.alakazam.javaconsumer.controller;

import org.junit.jupiter.api.Test;

public class TransactionControllerTest {

    @Test
    public void testDatabaseInsert() {

        String payload = "{\"transaction_id\":\"fb5df7df-c99d-4ea9-9d6d-594002fe78e6\",\"transaction_date\":\"2023-01-08T14:00:43Z\",\"person_id\":\"62463094013\",\"name\":\"Baelor I Targaryen\",\"age\":\"30\",\"amount\":\"20.95\",\"installment_number\":\"5\"}";

        TransactionController controller = new TransactionController();

        try {
            controller.receive(payload);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
