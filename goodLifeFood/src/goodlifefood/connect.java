package goodlifefood;

import java.sql.*;
import java.util.Properties;
public class connect
{
    private static Connection conn = null;
    static
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Properties prop = new Properties();    
            prop.put("charset", "utf-8");
            conn = DriverManager.getConnection("jdbc:ucanaccess://E:\\java_source\\goodLifeFood\\java.db\\GLF.accdb",prop);
        }
        catch (ClassNotFoundException error)
        {
            error.printStackTrace();
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        }
    }

    public static Connection GetConnection()
    {
        return conn;
    }
}
