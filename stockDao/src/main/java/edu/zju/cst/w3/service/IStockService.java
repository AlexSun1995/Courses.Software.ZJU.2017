package edu.zju.cst.w3.service;
import java.util.Date;
import java.util.List;

public interface IStockService {
    double getStockClosingPrice(String stockId, Date date);

    void insertStockClosingPrice(String stockId, Date date, double closingPrice);

    String getStockName(String id);

    List<String> getStockList();

    String getBestStock(Date startDate, Date endDate);
}
