import java.util.Scanner;


public class NumToWord {
	static String[] base = new String[] {
			"zero", "one", "two", "three", "four", "five",
			"six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen",
			"fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
			};
	static String[] base10 = new String[] {
			"twenty", "thirty", "forty", "fifty",
			"sixty", "seventy", "eighty", "ninety"
			};
	static String[] baseMill = new String[] {
			"m", "b", "tr", "quadr", "quint",
			"sext", "sept", "oct", "non", "dec",
			"undec", "duodec", "tredec", "quattuordec", "quindec",
			"sexdec", "septendec", "octodec", "novemdec", "vigint",
			"unvigint", "dovigint", "trevigint", "quattuorvigint",
			"quinvigint", "sexvigint", "septenvigint", "octovigint",
			"novemvigint", "trigint", "untrigint", "dotrigint", "tretrigint",
			"quattuortrigint", "quintrigint", "sextrigint", "septentrigint",
			"octotrigint", "novemtrigint"
			};
	
	public static void main(String[] args) {
		while (true) {
			Scanner scan = new Scanner(System.in);
			String n = scan.next();
			if (n.length() > 66)
				System.out.println("Sorry That number is too big");
			else
				System.out.println(parseLarge(n));
		}
	}
	
	private static String parse(int i) {
		//NEGATIVES
		if (i < 0) {
			return "negative " + parse(Math.abs(i));
		}
		//0-19
		if (i<20) {
			return base[i];
		}
		//TENS
		if (i<100) {
			if (i % 10 == 0)
				return base10[(i/10 - 2)];
			else
				return base10[(i/10 - 2)] + "-" + parse(i%10);
		}
		//HUNDREDS
		if (i < 1000) {
			if (i % 100 == 0)
				return parse(i/100) + " hundred";
			else
				return parse(i/100) + " hundred and " + parse(i%100);
		}
		//THOUSANDS
		if (i < 1000000) {
			if (i % 1000 == 0)
				return parse(i/1000) + " thousand";
			else
				return parse(i/1000) + " thousand " + (i%1000<100 ? "and " : "") + parse(i%1000);
		}
		
		return null;
	}
	
	private static String parseLarge(String s) {
		int l = s.length();	
		if (l < 7) {
			return (parse(Integer.parseInt(s)));
		}	
		int e = l%3;
		String rest = s.substring(0, e==0 ? 3 : e);
		return parse(Integer.parseInt(rest)) + " " +
				baseMill[(l-7)/3] + "illion" + (allZeroes(s.substring(rest.length())) ? "" : " "
		       + parseLarge(s.substring(rest.length())));
	}
	
	private static boolean allZeroes(String s) {
		if (s.length() == 0)
			return true;
		if (s.length() > 0 && s.substring(0, 1).equals("0"))
			return allZeroes(s.substring(1));
		else
			return false;
	}
}
