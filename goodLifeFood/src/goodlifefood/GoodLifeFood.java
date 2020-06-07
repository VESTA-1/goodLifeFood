package goodlifefood;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GoodLifeFood
{
    public static void main(String[] args)throws FileNotFoundException,IOException 
    {
        Scanner sc = new Scanner(System.in);
        interfaceShow menu = new interfaceShow();
        menu.showMenu();
    }
    
}
