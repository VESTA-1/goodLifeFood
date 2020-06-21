package goodlifefood;

import java.sql.*;
public class connect
{
    private static Connection conn = null;
    static
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\user\\Desktop\\java.db\\GLF.accdb");
        }
        catch (ClassNotFoundException | SQLException error)
        {
        }
    }

    public static Connection GetConnection()
    {
        return conn;
    }
    
}
