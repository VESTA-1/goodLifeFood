package goodlifefood;

import java.util.Scanner;
import java.io.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class interfaceShow
{
    Scanner sc = new Scanner(System.in);
    
    public void showMenu()throws FileNotFoundException,IOException 
    {
        int input;
        do {
        System.out.print("請輸入你所需要的服務:\n1) 登入系統 2) 註冊新帳號 3) 離開\n");
        input = sc.nextInt();
        getMenuInput(input);
        }while(input != 1 && input != 2 && input != 3);
    }
    
    public void getMenuInput(int input)throws FileNotFoundException,IOException 
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
    
    public void showRegister()throws FileNotFoundException,IOException 
    {
        System.out.print("請輸入你的帳號:\n");
        String strA = sc.next();
        System.out.print("請輸入你的密碼:\n");
        String strP = sc.next();
        System.out.print("請輸入你的位址:\n");
        String strAddress = sc.next();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("hello");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(2);
        cell.setCellValue("hello sheet");
        FileOutputStream fos = new FileOutputStream(new File("account.xlsx"));
        workbook.write(fos);
        workbook.close();
        fos.close();
}
       /* Workbook wb = WorkbookFactory.create("account.xlsx");
        FileOutputStream out = new FileOutputStream("account.xlsx");
        wb.write(out);
        out.close();
        System.out.println("createworkbook.xlsx written successfully");*/
}
