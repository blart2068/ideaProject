package mysql;
import jdk.nashorn.internal.ir.CatchNode;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class LinksqlMain {
    static String cxnr="";static int cxbh=0;

    //创建表格sql语句
    static String creatsql = "CREATE TABLE baomingbiao1("
            + "number int(6) not null,"
            + "time varchar(20) not null,"
            + "website varchar(20) not null,"
            + "name varchar(10) not null,"
            + "tel varchar(15) not null,"
            + "ip varchar(20) not null"
            + ")charset=utf8;";
    //查询语句数据库中有无baoming表

    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    //指定连接数据库的url
    final static String DB_URL = "jdbc:mysql://m.blart.top:3306/blarttest";
    //mysql用户名
    final static String user = "blarttest";
    //mysql密码
    final static String pwd = "rB5(duyKkS";

    public static void main(String[] args) {//main方法
        //----------------集合 增删改查 建表方法-----------------------

        while (true) {
            Scanner in = new Scanner(System.in);
            int i=0;
            System.out.println("MYSQL数据库操作系统增删改查 请选择输入操作编号");
            System.out.println("1增；2删；3改；4查；5条件查询；6建表");
            try {
                i = in.nextInt();
            }catch (Exception e){ System.out.println("错误字符！！"); }

            if (i == 1 | i == 2 | i == 3 | i == 4 | i == 5 | i == 6) {
                if (i == 1) {
                    System.out.println(addsql()+"添加成功");//增
                    break;
                }
                if (i == 2) {
                    System.out.println(delsql()+"添加成功");//删除
                    break;
                }
                if (i == 3) {
                    System.out.println(upsql()+"添加成功");//改
                    break;
                }
                if (i == 4) {
                    System.out.println(showsql()+"查询成功");//全部遍历查询
                    break;
                }
                if (i == 5) {
                    while (true){
                        Scanner in2 = new Scanner(System.in);
                        System.out.println("请输入要查询的条件 1：按编号查询 ；2：按姓名查询；3：按电话号码查询");
                        try {
                            cxbh = in2.nextInt();
                            System.out.println("请输入要查询内容");
                            cxnr =in2.next();
                            System.out.println(tjsql()+"查询成功");//按条件查询
                            break;
                        }catch (Exception e){ System.out.println("错误字符！！"); }

                    }
                    break;
                }
                if (i == 6) {
                    create();//建表
                    break;
                }


            } else {
                System.out.println("输入错误请输入1-4的数字编号，请从新输入");

            }
        }

    }


    public static void create() {//建表

        Connection conn = null;
        Statement stmt = null;
        try
        {
            //注册jdbc驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            System.out.println("//连接数据库");
            conn = DriverManager.getConnection(DB_URL,user,pwd);
            //执行创建表
            System.out.println("//创建表");
            stmt = conn.createStatement();
            if(0 == stmt.executeLargeUpdate(creatsql))
            {
                System.out.println("成功创建表！");
            }
            else
            {
                System.out.println("创建表失败！");
            }
            //------------------------------------------------------


            //-----------------------------------------------------
            stmt.close();
            conn.close();
            System.out.println("//关闭资源");
        }
        catch(Exception e)
        {
            System.out.println("创建表失败！over");
            e.printStackTrace();
        }
    }
    //--------------------------------------------------------------------------

    static String showsql(){//遍历查询表----------------------------------------------

        String rn="";

        try {
            rn=new sqltool(DB_URL,user,pwd). show();
            System.out.println("查询成功");
        } catch (Exception e) {
            System.out.println("查询失败");
        }
        return rn;
    }
    static String tjsql(){//条件查询表----------------------------------------------

        String rn="";

        try {
            rn=new sqltool(DB_URL,user,pwd). tjshow(cxnr,cxbh);
            System.out.println("查询成功");
        } catch (Exception e) {
            System.out.println("查询失败");
        }
        return rn;
    }

    static String addsql(){//添加表----------------------------

        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Scanner in=new Scanner(System.in);
        System.out.println("请输入项目内容");
        String website=in.next();
        System.out.println("请输入姓名");
        String name=in.next();
        System.out.println("请输入电话");
        String tel=in.next();
        System.out.println("请输入ip");
        String ip=in.next();

        try {
            new sqltool(DB_URL,user,pwd). add(time,website,name,tel,ip);


        } catch (Exception e) {
            System.out.println("查询失败");
        }

        return showsql();
    }
    static String upsql(){//修改表----------------------------
        Scanner in=new Scanner(System.in);
        System.out.println("请输入编号确定修改对象");
        String number=in.next();
        System.out.println("请输入修改后的姓名");
        String name=in.next();
        System.out.println("请输入修改后的电话");
        String tel=in.next();
        System.out.println("请输入修改后的项目");
        String website=in.next();
        try {
            new sqltool(DB_URL,user,pwd). update(website,tel,name,number);
            System.out.println("修改成功");

        } catch (Exception e) {
            System.out.println("修改失败");
        }
        return showsql();
    }
    static String delsql() {//删除表----------------------------
        Scanner in=new Scanner(System.in);
        System.out.println("请输入删除对象的编号");
        String number=in.next();
        try {
            new sqltool(DB_URL,user,pwd). del(number);
            System.out.println("修改成功");

        } catch (Exception e) {
            System.out.println("修改失败");
        }

        return showsql();
    }

}