package edu.zju.cst.w3.dao;
import edu.zju.cst.w3.model.Stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StockDao implements IStockDAO {

    public double getStockClosingPrice(String stockId, Date date) {
        return 0;
    }

    public void insertStockClosingPrice(String stockId, String name, Date date, double closingPrice) {

    }


    public String getStockName(String stockId) {
        return null;
    }

    public List<String> getStockIdList() {
        return null;
    }

    public HashMap<Date, ArrayList<Stock>> getDateMap() {
        return null;
    }

}
