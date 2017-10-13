package edu.zju.cst.w3.service;
import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.model.Stock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StockService implements IStockService {
    private IStockDAO stockDao;

    public StockService(IStockDAO stockDao){
        this.stockDao = stockDao;
    }

    public double getStockClosingPrice(String stockId, Date date) {
        return stockDao.getStockClosingPrice(stockId,date);
    }

    public void insertStockClosingPrice(String stockId, Date date, double closingPrice) {
        if(stockId.length()!=6 && closingPrice < 0){
            System.err.println("inserting ERROR, guess why");
            return;
        }
        stockDao.insertStockClosingPrice(stockId,stockId+"Inc.",date,closingPrice);
    }

    public String getStockName(String id) {
        return stockDao.getStockName(id);
    }

    public List<String> getStockList() {
        return stockDao.getStockIdList();
    }

    public String getBestStock(Date startDate, Date endDate) {
        double ans = -1;
        String id = "";
        Date curDate = startDate;
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        while(curDate != endDate){
            ArrayList<Stock> arrayList = stockDao.getDateMap().get(curDate);
            for(Stock stock : arrayList){
                if(stock.getClosingPrice() > ans){
                    ans = stock.getClosingPrice();
                    id = stock.getId();
                }
            }
            c.add(Calendar.DAY_OF_MONTH, 1);
            curDate = c.getTime();
        }
        if(ans != -1){
            return id;
        }
        else{
            System.err.println("Best stock not found");
            return null;
        }
    }
}
