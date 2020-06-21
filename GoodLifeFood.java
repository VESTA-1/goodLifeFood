package goodlifefood;

import java.sql.SQLException;
import java.util.Scanner;

public class GoodLifeFood
{
    public static void main(String[] args)throws SQLException 
    {
        Scanner sc = new Scanner(System.in);
        interfaceShow menu = new interfaceShow();
        menu.showBLMenu();
    }
    
}
