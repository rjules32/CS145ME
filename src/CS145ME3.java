/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/

import java.util.Random;
import java.util.Scanner;

public class CS145ME3 {

	public static void main(String[] args) {

		int zombie = 100;
		boolean zombified = false;
		Random r = new Random();
		String attack;
		Scanner attackObj;
		int result;

		while (zombie > 0 && !zombified) {
			printMenu(zombie);
			attackObj = new Scanner(System.in);
			attack = attackObj.nextLine();

			switch (attack) {
			case "A":
				zombie -= 10;
				System.out.print("It hits! ");
				zombie = regen(zombie);
				break;
			case "B":
				result = tossGrenade(zombie, r);
				if (result == 1) {
					zombie -= 30;
					System.out.print("It hits! ");
					zombie = regen(zombie);
				} else {
					System.out.println("You missed! ");
					if(zombie < 100) zombie = regen(zombie);
				}
				break;
			case "C":
				result = decapitate(zombie, r);
				if (result == 1) {
					zombie = 0;
					System.out.print("It hits! ");
				} else {
					System.out.println("You missed! ");
					zombified = true;
				}
				break;
			default:
				System.out.println("Please input correct values: A, B, C");
			}
		}
		if (!zombified) {
			System.out.println("You have successfully killed the zombie!");
		}else {
			System.out.println("Oh no! You have turned into a zombie!");
			System.out.println("--Game over--");
		}
	}

	private static void printMenu(int zombie) {
		System.out.println("The zombie has " + zombie + "hp. What will you do?");
		System.out.println("A. Shoot it");
		System.out.println("B. Grenade it");
		System.out.println("C. Decapitate it");
	}

	private static int regen(int zombie) {
		if(zombie > 0) System.out.println("It has " + zombie + "hp left.");
		if (zombie <= 0)
			return 0;
		zombie += 20;
		if (zombie >= 100)
			zombie = 100;
		System.out.println("Oh no! It begins to regenerate. It has " + zombie + "hp now.");
		return zombie;
	}

	private static int tossGrenade(int zombie, Random r) {
		int chance = r.nextInt(100) + 1;
		if (chance <= 20)
			return 1;
		return 0;

	}

	private static int decapitate(int zombie, Random r) {
		int chance = r.nextInt(100) + 1;
		if (chance <= 50)
			return 1;
		return 0;
	}

}
