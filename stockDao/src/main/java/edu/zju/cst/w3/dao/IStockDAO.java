package edu.zju.cst.w3.dao;

import edu.zju.cst.w3.model.Stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IStockDAO {
    double getStockClosingPrice(String stockId, Date date);
    void insertStockClosingPrice(String stockId,String name, Date date, double closingPrice);
    String getStockName(String stockId);
    List<String> getStockIdList();
    public HashMap<Date, ArrayList<Stock>> getDateMap();
}
