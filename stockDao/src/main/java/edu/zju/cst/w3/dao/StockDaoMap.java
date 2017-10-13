package edu.zju.cst.w3.dao;
import edu.zju.cst.w3.model.Stock;

import java.text.SimpleDateFormat;
import java.util.*;

public class StockDaoMap implements IStockDAO {

    private Map<String,ArrayList<Stock>> map = new HashMap<String, ArrayList<Stock>>();
    public HashMap<Date, ArrayList<Stock>> dateMap = new
            HashMap<Date, ArrayList<Stock>>();

    public double getStockClosingPrice(String stockId, Date date) {

        ArrayList<Stock> list = map.get(stockId);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        double ans = -1;
        for(int i=0;i<list.size();i++){
            if(fmt.format(list.get(i).getDate()).equals(fmt.format(date))){
                ans =  list.get(i).getClosingPrice();
            }
        }
        if(ans == -1){
            System.err.println("not found");
            return -1;
        }
        else return ans;

    }

    public void insertStockClosingPrice(String stockId, String name, Date date,
                                        double closingPrice) {
        Stock stock = new Stock();
        stock.setDate(date);
        stock.setClosingPrice(closingPrice);
        stock.setId(stockId);
        stock.setName(name);

        if(dateMap.containsKey(date)){
            dateMap.get(date).add(stock);
        }
        else{
            ArrayList<Stock> arr = new ArrayList<Stock>();
            String justForRemoveYellowLine = null;
            arr.add(stock);
            dateMap.put(date,arr);
        }

        if(map.containsKey(stockId)){
            map.get(stockId).add(stock);
        }
        else{
            ArrayList<Stock> arrayList = new ArrayList<Stock>();
            arrayList.add(stock);
            map.put(stockId, arrayList);
        }
    }

    public String getStockName(String stockId) {
        return map.get(stockId).get(0).getName();
    }

    public List<String> getStockIdList() {
        Set<String> mset = map.keySet();
        List<String> list = new LinkedList<String>();
        for(String str : mset){
            list.add(str);
        }
       return list;
    }

    public HashMap<Date, ArrayList<Stock>> getDateMap() {
        return dateMap;
    }
}
