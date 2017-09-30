package cst.zju.edu.alexsun.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    /**
     * 获取与数据库的连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() {
        try {
            return DriverManager
                    .getConnection("jdbc:mysql://10.82.59.88:3306/maven",
                            "maven", "maventest");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
