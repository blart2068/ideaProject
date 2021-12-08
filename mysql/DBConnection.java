package mysql;
import java.sql.*;

//mysql连接类
public class DBConnection {
   public Connection conn;
   

//   static String user = "blarttest";
//    static String ur = "jdbc:mysql://192.168.1.220:3306/blarttest";
//   static String password = "Gd7m4wtkfhitfa62";
//
//    public static void main(String[] args) {
//    	DBConnection sdsd=new DBConnection(ur,user, password);
//    	sdsd.close();
//      }


    public DBConnection(String ur,String user,String password) {
    	

        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载驱动程序
            conn = (Connection) DriverManager.getConnection(ur, user, password);// 连续数据库
            
            if(!conn.isClosed()){
                System.out.println("Succeeded connecting to the Database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}