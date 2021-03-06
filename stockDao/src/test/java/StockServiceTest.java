import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.dao.StockDaoMap;
import edu.zju.cst.w3.service.IStockService;
import edu.zju.cst.w3.service.StockService;
import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
/**
 * stock's name = stockId + "Inc."
 * just for the convenience of testing
 */

public class StockServiceTest {
    private IStockDAO stockDao = null;
    private IStockService iStockService = null;
    private Calendar calendar;

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
      assertEquals(iStockService.getStockName("000000"),"000000Inc.");
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
        assertEquals(66.66,price);
    }

    @Test
    public void testGetStockList(){
        iStockService.insertStockClosingPrice("000001",calendar.getTime(),
                66.66);
        iStockService.insertStockClosingPrice("000002",calendar.getTime(),
                66.66);
        iStockService.insertStockClosingPrice("000003",calendar.getTime(),
                66.66);
        System.out.println(iStockService.getStockList());
    }

    @Test
    public void testGetBestStock(){

        iStockService.insertStockClosingPrice("201709",calendar.getTime(),
                66.66);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        iStockService.insertStockClosingPrice("201710",calendar.getTime(),
                76.66);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        iStockService.insertStockClosingPrice("201711",calendar.getTime(),
                1926.66);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        iStockService.insertStockClosingPrice("201712",calendar.getTime(),
                86.66);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = calendar.getTime();
        assertEquals("201711",iStockService.getBestStock(startDate,endDate));
    }

    @Test
    public void dateTest(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        System.out.println(fmt.format(calendar.getTime()));
    }
}
