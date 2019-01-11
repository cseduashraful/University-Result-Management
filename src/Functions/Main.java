package Functions;
import java.util.ArrayList;

import stermip.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> columns = markDistributionSystem.getAvailableSectors();
		for(int i = 0;i<columns.size();i++){
			System.out.println(columns.get(i));
		}
		
		
	}
}
