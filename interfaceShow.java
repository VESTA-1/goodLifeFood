package goodlifefood;

import java.io.IOException;
import java.util.Scanner;
import java.sql.SQLException;

public class interfaceShow
{
    Scanner sc = new Scanner(System.in);
    guests l = new guests();
    order o = new order();
    commodity c = new commodity();
    
    public void showBLMenu() throws SQLException
    {
        int input;
        do {
        System.out.print("請輸入你所需要的服務:\n1) 登入系統 2) 註冊新帳號 3) 離開\n");
        input = sc.nextInt();
        getBLMenuInput(input);
        }while(input < 1 || input > 3);
    }
    
    public void getBLMenuInput(int input)throws SQLException 
    {
        switch (input)
        {
            case 1:
                showLoginMenu();
                break;
            case 2:
                showRegisterMenu();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.print("輸入錯誤請重新輸入\n");
                break;
        }
    }
    
    public void showALMenu() throws SQLException
    {
        int input;
        do {
        System.out.print("請輸入你所需要的服務:\n1) 選購物品 2) 查詢購物車 3)查詢訂單 4) 登出帳號\n");
        input = sc.nextInt();
        getALMenuInput(input);
        }while(input < 1 || input > 4);
    }
    
    public void getALMenuInput(int input)throws SQLException 
    {
        switch (input)
        {
            case 1:
                showCommodityMenu();
                break;
            case 2:
                showShopCartMenu();
                break;
            case 3:
                showOrderMenu();
                break;
            case 4:
                l.shopCart.clear();
                showBLMenu();
                break;
            default:
                System.out.print("輸入錯誤請重新輸入\n");
                break;
        }
    }
    
    public void showCommodityMenu()throws SQLException 
    {
        c.getAllCommodity();
        System.out.print("請輸入你想購買的產品編號:\n");
        String str1 = sc.next();
        if(Integer.valueOf(str1) > c.getCount() ||  Integer.valueOf(str1) <= 0)
        {
            System.out.print("你輸入的產品編號不存在，請按ENTER來重新輸入:\n");
            try
            {
                System.in.read();
            }
            catch(IOException e){}
            showCommodityMenu();
        }
        else
        {
            System.out.print("請輸入你想購買的產品數量:\n");
            String str2 = sc.next();
            l.shopCart.put(str1,str2);
            showALMenu();
        }
    }
    
    public void showShopCartMenu()throws SQLException 
    {
        int input;
        System.out.print("---------------以下是你在購物車的物品---------------\n");
        l.getShopCart(o, c);
        do {
        System.out.print("請輸入你所需要的服務:\n1) 繼續選購物品 2)結帳\n");
        input = sc.nextInt();
        getShopCartInput(input);
        }while(input < 1 || input > 2);
    }
    
    public void getShopCartInput(int input) throws SQLException
    {
        switch (input) {
            case 1:
                showCommodityMenu();
                break;
            case 2:
                showCusMenu();
                break;
            default:
                System.out.print("輸入錯誤請重新輸入\n");
                break;
        }
    }
    
    public void showCusMenu() throws SQLException
    {
        int input;
            do {
            System.out.print("請問需要以下服務嗎?:\n1)蛋糕 2)客製化訊息 3)不需要\n");
            input = sc.nextInt();
            getCusInput(input);
            }while(input != 1 && input != 2 && input != 3);
    }
    
    public void getCusInput(int input) throws SQLException
    {
        switch (input) {
            case 1:
                o.setCake(1);
                System.out.print("請問需要客製化訊息嗎? 1)需要 2)不需要\n");
                int a = sc.nextInt();
                switch (a)
                {
                    case 1:
                        System.out.print("請輸入客製化的訊息\n");
                        o.setMessage(sc.next());
                        if (o.judgeMoney(o.getMoney()) == 0)
                        {
                            System.out.print("訂單金金額(不含運費)不足400元，請繼續選購(按ENTER以繼續)...\n");
                            try
                            {
                                System.in.read();
                            }
                            catch(IOException e){}
                            showCommodityMenu();
                        }
                        else
                        {
                            o.setSerialNumber();
                            l.sendOrder(o,c);
                            showALMenu();
                        }
                        break;
                    case 2:
                        if (o.judgeMoney(o.getMoney()) == 0)
                        {
                            System.out.print("訂單金金額(不含運費)不足400元，請繼續選購(按ENTER以繼續)...\n");
                            try
                            {
                                System.in.read();
                            }
                            catch(IOException e){}
                            showCommodityMenu();
                        }
                        else
                        {
                            o.setSerialNumber();
                            l.sendOrder(o,c);
                            showALMenu();
                        }
                        break;
                    default:
                        System.out.print("輸入錯誤請重新輸入\n");
                        getCusInput(1);
                        break;
                }
                break;
            case 2:
                System.out.print("請輸入客製化的訊息\n");
                o.setMessage(sc.next());
                System.out.print("請問需要蛋糕嗎? 1)需要 2)不需要\n");
                int b = sc.nextInt();
                switch (b) {
                case 1:
                    o.setCake(1);
                    if (o.judgeMoney(o.getMoney()) == 0)
                    {
                        System.out.print("訂單金金額(不含運費)不足400元，請繼續選購(按ENTER以繼續)...\n");
                        try
                        {
                            System.in.read();
                        }
                        catch(IOException e){}
                        showCommodityMenu();
                    }
                    else
                    {
                        o.setSerialNumber();
                        l.sendOrder(o,c);
                        showALMenu();
                    }
                    break;
                case 2:
                    if (o.judgeMoney(o.getMoney()) == 0)
                    {
                        System.out.print("訂單金金額(不含運費)不足400元，請繼續選購(按ENTER以繼續)...\n");
                        try
                        {
                            System.in.read();
                        }
                        catch(IOException e){}
                        showCommodityMenu();
                    }
                    else
                    {
                        o.setSerialNumber();
                        l.sendOrder(o, c);
                        showALMenu();
                    }
                    break;
                default:
                    System.out.print("輸入錯誤請重新輸入\n");
                    getCusInput(2);
                    break;
                }
                break;
            case 3:
                if (o.judgeMoney(o.getMoney()) == 0)
                {
                    System.out.print("訂單金金額(不含運費)不足400元，請繼續選購(按ENTER以繼續)...\n");
                    try
                    {
                        System.in.read();
                    }
                    catch(IOException e){}
                    showCommodityMenu();
                }
                else
                {
                    o.setSerialNumber();
                    l.sendOrder(o,c);
                    showALMenu();
                }
                break;
            default:
                System.out.print("輸入錯誤請重新輸入\n");
                break;
        }
    }
    
    public void showOrderMenu()throws SQLException 
    {
        System.out.println("---------------以下是你的訂單--------------");
        l.getUOrder();
        System.out.print("按ENTER回到主選單...\n");
        try
        {
             System.in.read();
        }
        catch(IOException e){}
        showALMenu();
    }
    
    public void showLoginMenu() throws SQLException
    {
        System.out.print("請輸入你的帳號:\n");
        String strA = sc.next();
        System.out.print("請輸入你的密碼:\n");
        String strP = sc.next();
        if( !"".equals(strA) && !"".equals(strP))
        {
            if(l.login(strA, strP) == 1)
            {
                System.out.print("登入成功");
                l.setID(strA);
                l.setPassword(strP);
                showALMenu();
            }
            else
            {
                System.out.print("登入失敗，請按ENTER來重新輸入...\n");
                try
                {
                    System.in.read();
                }
                catch(IOException e){}
                showLoginMenu();
            }
        }
        else
        {
            System.out.println("你有欄位未填\n");
            showLoginMenu();
        }
    }
    
    public void showRegisterMenu()throws SQLException 
    {
        System.out.print("請輸入你的帳號:\n");
        String strI = sc.next();
        System.out.print("請輸入你的密碼:\n");
        String strP = sc.next();
        System.out.print("請輸入你的位址:\n");
        String strA = sc.next();
        if( !"".equals(strI) && !"".equals(strP) && !"".equals(strA))
        {
            if(l.register(strI, strP, strA, 0) == 1)
            {
                System.out.println("註冊成功");
                showBLMenu();
            }
            else
            {
                System.out.println("註冊失敗，請按ENTER來重新輸入...\n");
                try
                {
                    System.in.read();
                }
                catch(IOException e){}
                showRegisterMenu();
            }
        }
        else
            System.out.println("你有欄位未填");
    }
}
