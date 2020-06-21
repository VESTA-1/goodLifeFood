package goodlifefood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class order
{
    private int money, fare, cake;
    private StringBuilder serialNumber = new StringBuilder();
    private String message = "";
    
    public void setFare()
    {
        if (money >= 1200)
            fare = 0;
        else 
            fare = 200;
    }
    
    public int getFare()
    {
        return fare;
    }
    
    public int getMoney()
    {
        return money;
    }
    
    public void setMoney(int q, int unitPrice)
    {
        money += q * unitPrice;
    }
    
    public void setIniMoney()
    {
        money = 0;
    }
        
    public int getTotal()
    {
        return money + fare;
    }
    
    public void setCake(int i)
    {
        cake = i;
    }
    
    public int getCake()
    {
        return cake;
    }
    
    public void setSerialNumber() throws SQLException
    {
        int z;
        for (int i = 0; i < 8; i++)
        {
            z = (int) ((Math.random() * 7) % 3);
            if (z == 1)
                serialNumber.append((int) ((Math.random() * 10) + 48));
            else if (z == 2)
                serialNumber.append((char) (((Math.random() * 26) + 65)));
            else
                serialNumber.append(((char) ((Math.random() * 26) + 97)));
        }
        
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmt1 = conn.createStatement();
        try
        {
            String sql = "Select serialNumber from order where serialNumber =" + "'" + serialNumber.toString() + "'";
            ResultSet rs1 = stmt1.executeQuery(sql);
            if(rs1.isBeforeFirst())
            {
                setSerialNumber();
            }
        }
        catch(SQLException se)
        {
        }
    }
    
    public String getSerialNumber()
    {
        return serialNumber.toString();
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public int judgeMoney(int money)
    {
        if (money < 400)
            return 0;
        else
            return 1;
    }
    
}
