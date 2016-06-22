package auxiliar;

import java.util.ArrayList;

public class Utilidades {

		public static ArrayList<Integer> stringToIntArray(String s){
			String[] vString = s.split(" ");
			ArrayList<Integer> vInt = new ArrayList<>();
			
			for (int i = 0; i < vString.length; i++) {
				if(!vString[i].isEmpty())
					vInt.add(Integer.parseInt(vString[i]));
			}
			return vInt;
		}
}
