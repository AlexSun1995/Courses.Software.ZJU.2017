import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.dao.StockDaoMap;
import edu.zju.cst.w3.service.IStockService;
import edu.zju.cst.w3.model.Stock;
import edu.zju.cst.w3.service.StockService;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class StockServiceTest {
    private IStockDAO stockDao = null;
    private IStockService iStockService = null;
    private Stock stock = null;
    private Calendar calendar;
    private Stock baseStock = new Stock();

    @Before
    public void setUp(){
        calendar = Calendar.getInstance();
        stockDao = new StockDaoMap();
        calendar.set(1926,8,17);
        iStockService = new StockService(stockDao);
        iStockService.insertStockClosingPrice("000000",calendar.getTime(),
                66.66);

    }

    @Test
    public void testGetStockName(){

    }

    @Test
    public void testInsertStockClosingPrice(){
        iStockService.insertStockClosingPrice("666666",calendar.getTime(),
                22.33);
        //System.out.println(iStockService.getStockName("666666"));
        assertEquals(iStockService.getStockName("666666"),"666666Inc.");
    }

    @Test
    public void testGetStockClosingPrice(){
        double price = iStockService.getStockClosingPrice("000000",calendar.getTime());
        assertEquals(price,66.66);
    }

    @Test
    public void testGetStockList(){

    }

    @Test
    public void testGetBestStock(){

    }

}
