package goodlifefood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class guests extends user    
{
    private String address;
    Map<String, String> shopCart = new HashMap<>();
    
    public int register(String userID, String pw, String address, int authority) throws SQLException
    {
        int judge = 0;
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt1 = conn.createStatement();
        try
        {
            String sql = "Select account from user where account =" + "'" + userID + "'";
            ResultSet rs1 = stmt1.executeQuery(sql);
            if(rs1.isBeforeFirst())
            {
            }
            else
            {
                Statement stmt2 = conn.createStatement();
                String sql2 = "Insert into user (account, password, address, authority) values (" + "'" + userID + "','" + pw + "','" + address + "','" + authority + "')";
                stmt2.execute(sql2);
                judge = 1;
            }
        }
        catch(SQLException se)
        {
        }
        return judge;
    }
    
    public int login(String userID,String pw) throws SQLException
    {   
        int judge = 0;
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmtL = conn.createStatement();
        try
        {
        String sqlc = "Select account from user where account =" + "'" + userID + "'" + "and password ="+"'"+pw+"'";//尋找跟帳號符合的密碼
        ResultSet rsc = stmtL.executeQuery(sqlc);
        if(rsc.isBeforeFirst())
            judge = 1;
        }
        catch(SQLException se)
        {
        }
        return judge;
    }
    
    public void getShopCart(order o, commodity c) throws SQLException
    {
        if(shopCart.isEmpty())
            System.out.println("您的購物車裡沒有東西");
        else
        {
            Connection conn = connect.GetConnection();//連接資料庫
            Statement stmt = conn.createStatement();
            o.setIniMoney();
            for (Object key : shopCart.keySet())
            {
                try
                {
                    c.setAllCommodityName(key);
                    c.setAllCommodityUnit(key);
                    c.setAllCommodityUnitPrice(key);         
                    o.setMoney(Integer.valueOf(shopCart.get(key)), c.getUnitPrice());
                }
                catch(SQLException se)
                {
                }
                System.out.println(c.getName() + " " + shopCart.get(key) + c.getUnit() + "(" + c.getUnitPrice() + "/" + c.getUnit() + ")");
            }   
            o.setFare();
            System.out.println("總共:" +  o.getTotal() + "元(含運費" + o.getFare() + "元)");
        }
    }
    
    public int sendOrder(order o, commodity c) throws SQLException
    {        
            Connection conn = connect.GetConnection();//連接資料庫
            Statement stmt = conn.createStatement();
            try
            {
                String sql1 = "Insert into order(serialNum, userID, fare, money, cake, message) values (";
                sql1 += "'" + o.getSerialNumber() + "','" + userID + "','" + o.getFare() + "','" + o.getMoney() + "','" + o.getCake() + "','" + o.getMessage() + "')";
                stmt.execute(sql1);
            }
            catch(SQLException se){System.out.println("error1");}
            sendOrderDetail(o, c);
            return 1;
    }
    
    public void sendOrderDetail(order o, commodity c) throws SQLException
    {
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt = conn.createStatement();
        shopCart.keySet().forEach((key) -> {
            try
            {
                c.setAllCommodityName(key);
                String sql = "Insert into orderDetail(serialNum, commodityID, commodityName, quantity) values (";
                sql += "'" + o.getSerialNumber() + "','" + key + "','" + c.getName() + "','" + shopCart.get(key) + "')";
      
                stmt.execute(sql);
            }
            catch(SQLException se){System.out.println("error2");}
        });
    }
    
    public void getUOrder() throws SQLException
    {
        int i = 1;
        int j = 1;
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        try
        {
            String sql = "select * from order where userID = " + "'" + userID + "'";
            ResultSet res = stmt1.executeQuery(sql); 
            if(res.next())
            {
                res.previous();
                while(res.next())
                {                             
                    System.out.println("訂單編號:" + i + ") 流水號:" + res.getString(1) + "\t總金額:" + res.getString(4) + "元\t運費:"
                    + res.getString(5) + "元   客製化訊息:" + res.getString(6));
                    String sq2 = "select * from orderDetail where serialNum = " + "'" + res.getString(1) + "'";
                    ResultSet res2 = stmt1.executeQuery(sq2);
                    while(res2.next())
                    {        
                        System.out.println("項目" + j + ":"+ res2.getString(3) + "\t數量:" + res2.getString(4));
                        j++;
                    }
                    i++;
                }
            }
            else
                System.out.println("你沒有任何訂單");
        }
        catch(java.sql.SQLException e){}
    }
    
    public void getUOrderDetail(int num, order o) throws SQLException
    {
        int i = 1;
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        try
        {
            String sql = "select * from orderDetail where serialNum = " + "'" + o.getSerialNumber() + "'";
            System.out.println(sql);
            ResultSet res = stmt1.executeQuery(sql);
            while(res.next())
            {                             
                System.out.println("項目" + i + ":"+ res.getString(3) + "\t數量:" + res.getString(4));
                i++;
            }
        }
        catch(java.sql.SQLException e){}

    }
    
}
