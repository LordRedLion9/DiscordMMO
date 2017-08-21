package mmoServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class BotInfo implements java.io.Serializable{

	public static String botToken;
	
	public void readInfo(){
		try{
			Scanner s = new Scanner(new BufferedReader(new FileReader("token.dat")));
			botToken = s.nextLine();
		} catch(Exception e){
			System.out.println("Bot Token file read error");
			e.printStackTrace();
		}
	}
	
}
