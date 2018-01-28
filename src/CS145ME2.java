/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/

import java.util.Scanner;

public class CS145ME2 {

	public static void main(String[] args) {
		Scanner monthObj = new Scanner(System.in);
		Scanner dayObj = new Scanner(System.in);

		String month;
		String dayString;

		String[] signs = { "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius",
				"Capricorn", "Aquarius", "Pisces" };

		System.out.print("Enter month: ");
		month = monthObj.nextLine();
		System.out.print("Enter day: ");
		dayString = dayObj.nextLine();
		
		int day = Integer.parseInt(dayString);
		int userSign = getSign(month, day);

		if (userSign == -1) {
			System.out.println("Please input correct values.");
			System.out.println("Month: Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sept, Oct, Nov, Dec");
		} else {
			System.out.println(signs[userSign]);
		}

	}

	private static int getSign(String month, int day) {
		if ((month.equals("Mar") && day >= 21 && day <= 31) || (month.equals("Apr") && day <= 19 && day >= 0)) {
			return 0;
		} else if ((month.equals("Apr") && day >= 20 && day <= 30) || (month.equals("May") && day <= 20 && day >= 0)) {
			return 1;
		} else if ((month.equals("May") && day >= 21 && day <= 31) || (month.equals("Jun") && day <= 20 && day >= 0)) {
			return 2;
		} else if ((month.equals("Jun") && day >= 21 && day <= 30) || (month.equals("Jul") && day <= 22 && day >= 0)) {
			return 3;
		} else if ((month.equals("Jul") && day >= 23 && day <= 31) || (month.equals("Aug") && day <= 22 && day >= 0)) {
			return 4;
		} else if ((month.equals("Aug") && day >= 23 && day <= 31) || (month.equals("Sept") && day <= 22 && day >= 0)) {
			return 5;
		} else if ((month.equals("Sept") && day >= 23 && day <= 30) || (month.equals("Oct") && day <= 22 && day >= 0)) {
			return 6;
		} else if ((month.equals("Oct") && day >= 23 && day <= 31) || (month.equals("Nov") && day <= 21 && day >= 0)) {
			return 7;
		} else if ((month.equals("Nov") && day >= 22 && day <= 30) || (month.equals("Dec") && day <= 21 && day >= 0)) {
			return 8;
		} else if ((month.equals("Dec") && day >= 22 && day <= 31) || (month.equals("Jan") && day <= 19 && day >= 0)) {
			return 9;
		} else if ((month.equals("Jan") && day >= 20 && day <= 31) || (month.equals("Feb") && day <= 18 && day >= 0)) {
			return 10;
		} else if ((month.equals("Feb") && day >= 19 && day <= 29) || (month.equals("Mar") && day <= 20 && day >= 0)) {
			return 11;
		} else {
			return -1;
		}

	}

}
