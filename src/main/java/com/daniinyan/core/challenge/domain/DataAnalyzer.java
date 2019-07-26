package com.daniinyan.core.challenge.domain;

import com.daniinyan.core.challenge.dao.InputFileDAO;
import com.daniinyan.core.challenge.dao.OutputFileDAO;
import com.daniinyan.core.challenge.service.InputFileService;
import com.daniinyan.core.challenge.service.OutputFileService;

public class DataAnalyzer extends Thread {

    private InputFileService inputFileService;
    private OutputFileService outputFileService;

    public DataAnalyzer(String directoryPath) {
        inputFileService = new InputFileService(new InputFileDAO(directoryPath));
        outputFileService = new OutputFileService(new OutputFileDAO(directoryPath));
    }

    @Override
    public void run() {
        System.out.println("DataAnalyzer");
    }

    public void update() {
        outputFileService.create();
        setTotalCustomers();
    }

    private void setTotalCustomers() {
        String fieldName = Field.TOTAL_CUSTOMERS.getFieldName();
        long numberOfCostumers =  inputFileService.countCustomers();
        outputFileService.updateTotalCustomers(fieldName, numberOfCostumers);
    }

}
