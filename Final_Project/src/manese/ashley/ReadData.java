package manese.ashley;

import java.io.File;
import java.util.Scanner;

public class ReadData {

	public static void main(String[] args) throws Exception{
		java.io.File file = new java.io.File("SavedGameData.txt");
		
		Scanner input = new Scanner(file);
		
		while(input.hasNext()) {
			String row = input.nextLine();
			
			System.out.println(row);
		}
		
		input.close();

	}

}
