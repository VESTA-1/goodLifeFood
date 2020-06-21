package goodlifefood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class commodity
{
    private int unitPrice, ID;
    private String unit, name;
    
    public void getAllCommodity() throws SQLException
    {
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt1 = conn.createStatement();
        try
        {
            ResultSet result = stmt1.executeQuery("SELECT * FROM commodity"); 
            while(result.next())
            { 
            System.out.print("編號" + result.getString(1) + " " + result.getString(2) + " " + result.getString(4) + "/"+ result.getString(3) + "\n"); 
            }
        }
        catch(java.sql.SQLException a)
        {
        }
    }    

    public int getCount() throws SQLException
    {        
        int count = 0;
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmtC = conn.createStatement();
        try
        {
        String sql = "select count(*) from commodity";
        ResultSet r = stmtC.executeQuery(sql);
        r.next();
        count = r.getInt(1);
        }
        catch(java.sql.SQLException a)
        {
        }        
        return count;
    }
    
    public int getUnitPrice()
    {
        return unitPrice;
    }
    
    public void setUnitPrice(int unitPrice)
    {
        this.unitPrice = unitPrice;
    }
    
    public String getUnit()
    {
        return unit;
    }
    
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getID()
    {
        return ID;
    }
    
    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    public void setAllCommodityName(Object key) throws SQLException
    {
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt = conn.createStatement();
        try
        {
            String sql = "Select commodityName from commodity where ID =" + "'" + key + "'";
            ResultSet rs1 = stmt.executeQuery(sql);
            if(rs1.next())
                setName(rs1.getString("commodityName"));
        }
        catch(SQLException se){}
    }
    
    public void setAllCommodityUnit(Object key) throws SQLException
    {
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt = conn.createStatement();
        try
        {
            String sql = "Select unit from commodity where ID =" + "'" + key + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                setUnit(rs.getString("unit"));
        }
        catch(SQLException se){}
    }
    
    public void setAllCommodityUnitPrice(Object key) throws SQLException
    {
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt = conn.createStatement();
        try
        {
            String sql = "Select unitPrice from commodity where ID =" + "'" + key + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                setUnitPrice(Integer.valueOf(rs.getString("unitPrice"))); 
        }
        catch(SQLException se){}
    }

}
