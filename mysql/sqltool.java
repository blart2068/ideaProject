package mysql;
import java.sql.*;


public class sqltool {

	String ur="";
	String user="";
	String passwd="";
	
//    public static void main(String[] args){
//    	//增加一条----
//      //add("2018-01-13 15:26:19", "绿地长岛【宝琴（移动）】", "奥巴马","189366521");
//
//    	//查询-----
//    	//System.out.println( new sqltool("jdbc:mysql://www.blart.pw:3306/blartsql"). show());
//    	//System.out.println( new sqltool("jdbc:mysql://www.blart.pw:3306/blartsql"). mhcz("2018-03-19","192.168.1.95"));
//
//    	//修改----------
//        //update("绿地海湾【宝琴（pc）】","18939980599","aya","8");
//
//        //删除---------
//        //del("7");
//
//    }
    public sqltool(String url,String userl,String passwdl) {
		ur=url;
		user=userl;
		passwd=passwdl;
	}
    
    //插入操作
    public  void add(String time,String website,String name,String tel,String ip) {
         
        String sql="insert into baomingbiao (time,website,name,tel,ip) values (?,?,?,?,?)";
        DBConnection db = new DBConnection(ur,user,passwd);
        try {        
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(sql);
            preStmt.setString(1, time);
            preStmt.setString(2, website);
            preStmt.setString(3, name);
            preStmt.setString(4, tel);
            preStmt.setString(5, ip);
            preStmt.executeUpdate();
            //Statement statement = (Statement) db.conn.createStatement();
            //statement.executeUpdate(sql);
            
            preStmt.close();
            db.close();//关闭连接 
            System.out.println("添加完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    //模糊查找、
    public boolean mhcz(String time,String ip){
    	int i=0;
    	
    	 String sql ="select * from baomingbiao where time like '%"+time+"%' and ip = '"+ip+"'";
    
    	 
         DBConnection db = new DBConnection(ur,user,passwd);
         try {
             Statement stmt = (Statement) db.conn.createStatement();
             ResultSet rs = (ResultSet) stmt.executeQuery(sql);
             while(rs.next()){
                
                 if (rs.getString("ip").equals(ip)) {
                	 i++;
                	 if (i>2) {
                		 rs.close();
                         db.close();//关闭连接 
    					return false;
					}
                	
				}
             }
             rs.close();
             db.close();//关闭连接 
         } catch (SQLException e) {
             e.printStackTrace();
         } 
         return true;
    }
    
    //全部遍历查找操作
    public  String show(){
         String sql ="select * from baomingbiao";
         DBConnection db = new DBConnection(ur,user,passwd);
         String biaobi = "------------------------------------------------------------------------------------\n"+
        		"编号"+"\t\t\t"+"时间" +"\t\t\t"+ "项目" +"\t\t"+ "姓名"+"\t\t"+ "电话"+"\t\t\t\t"+"IP\n"+
         "------------------------------------------------------------------------------------\n";
         String suju ="";
         
         try {
            Statement stmt = (Statement) db.conn.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            while(rs.next()){
            	String number = rs.getString("number");
                String time = rs.getString("time");
                String website = rs.getString("website");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String ip = rs.getString("ip");
                //可以将查找到的值写入类，然后返回相应的对象
                //这里 先用输出的端口显示一下
                suju =suju+ (number+"\t"+time +"\t"+ website +"\t"+ name+"\t"+ tel+"\t"+ ip+"\n");
            }
            rs.close();
            db.close();//关闭连接 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
         //System.out.println(biaobi+suju);
         return biaobi+suju;
    }
    //条件查找操作---------------------------------
    public  String tjshow(String str,int i){
        String sql ="";
        if (i == 1) {
            sql ="select * from baomingbiao where number = '"+str+"'";
        }else if (i == 2) {
            sql ="select * from baomingbiao where name = '"+str+"'";
        }else if (i == 3) {
            sql ="select * from baomingbiao where tel = '"+str+"'";
        }else {
            return "输入错误无法查询";
        }

         DBConnection db = new DBConnection(ur,user,passwd);
         String biaobi = "------------------------------------------------------------------------------------\n"+
        		"编号"+"\t\t\t"+"时间" +"\t\t\t"+ "项目" +"\t\t"+ "姓名"+"\t\t"+ "电话"+"\t\t\t\t"+"IP\n"+
         "------------------------------------------------------------------------------------\n";
         String suju ="";

         try {
            Statement stmt = (Statement) db.conn.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            while(rs.next()){
            	String number = rs.getString("number");
                String time = rs.getString("time");
                String website = rs.getString("website");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String ip = rs.getString("ip");
                //可以将查找到的值写入类，然后返回相应的对象
                //这里 先用输出的端口显示一下
                suju =suju+ (number+"\t"+time +"\t"+ website +"\t"+ name+"\t"+ tel+"\t"+ ip+"\n");
            }
            rs.close();
            db.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
         //System.out.println(biaobi+suju);
         return biaobi+suju;
    }
    //更新修改操作
    public void update(String website,String tel,String name,String number) {
       
        String sql="update baomingbiao set website=?,tel=?,name=? where number=?";
        DBConnection db = new DBConnection(ur,user,passwd);
        
        try {
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(sql);
            preStmt.setString(1, website);
            preStmt.setString(2, tel);
            preStmt.setString(3, name);
            preStmt.setString(4, number);
 
            preStmt.executeUpdate();
            
            preStmt.close();
            db.close();//关闭连接 
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
    //删除操作
    public void del(String number) {
       
        String sql="delete from baomingbiao where number=?";
        DBConnection db = new DBConnection(ur,user,passwd);
        try {    
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(sql);
            preStmt.setString(1, number);
            preStmt.executeUpdate();
            
            preStmt.close();
            db.close();//关闭连接 
        } catch (SQLException e){
            e.printStackTrace();
        }
        
    }
}