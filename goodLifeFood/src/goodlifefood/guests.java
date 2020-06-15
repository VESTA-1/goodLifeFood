package goodlifefood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class guests extends user    
{
    private String address;
    
    public int register(String userID, String password, String address, int authority) throws SQLException
    {
        int judge = 0;
        Connection conn = connect.GetConnection();//連接資料庫
        Statement stmtR1 = conn.createStatement();
        try
        {
            String sqla = "Select account from user where account =" + "'" + userID + "'";
            ResultSet rsa = stmtR1.executeQuery(sqla);
            if(rsa.isBeforeFirst())
            {
            }
            else
            {
                Statement stmtR2 = conn.createStatement();
                String sqlb = "Insert into user (account, password, address, authority) values (" + "'" + userID + "','" + password + "','" + address + "','" + authority + "')";
                stmtR2.execute(sqlb);
                judge = 1;
            }
        }
        catch(SQLException se)
        {
        }
        return judge;
    }
}
