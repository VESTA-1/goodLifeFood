package goodlifefood;

import java.util.Scanner;
import java.io.*;
import java.sql.SQLException;

public class interfaceShow
{
    Scanner sc = new Scanner(System.in);
    
    public void showMenu() throws SQLException
    {
        int input;
        do {
        System.out.print("請輸入你所需要的服務:\n1) 登入系統 2) 註冊新帳號 3) 離開\n");
        input = sc.nextInt();
        getMenuInput(input);
        }while(input != 1 && input != 2 && input != 3);
    }
    
    public void getMenuInput(int input)throws SQLException 
    {
        if (input == 1)
            showLogin();
        else if (input == 2)
            showRegister();
        else if (input == 3)
            System.exit(0);
        else
            System.out.print("輸入錯誤請重新輸入\n");
    }
    
    public void showLogin()
    {
        System.out.print("請輸入你的帳號:\n");
        String strA = sc.next();
        System.out.print("請輸入你的密碼:\n");
        String strP = sc.next();
    }
    
    public void showRegister()throws SQLException 
    {
        System.out.print("請輸入你的帳號:\n");
        String strI = sc.next();
        System.out.print("請輸入你的密碼:\n");
        String strP = sc.next();
        System.out.print("請輸入你的位址:\n");
        String strA = sc.next();
        if( !"".equals(strI) && !"".equals(strP) && !"".equals(strA))
        {
            guests g = new guests();
            if(g.register(strI, strP, strA, 0) == 1)
            {
                System.out.println("註冊成功");
                showMenu();
            }
            else
                System.out.println("註冊失敗");
        }
        else
            System.out.println("你有欄位未填");
    }
}
