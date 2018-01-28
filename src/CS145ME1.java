/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/

import java.util.Scanner;

public class CS145ME1 {

	public static void main(String[] args) {
		Scanner p1 = new Scanner(System.in);
		Scanner p2 = new Scanner(System.in);
		String player1;
		String player2;

		System.out.println("player 1: ");
		player1 = p1.nextLine();
		System.out.println("player 2: ");
		player2 = p2.nextLine();

		int winner = evaluateWinner(player1, player2);
		if (winner == -1) {
			System.out.println("Please input correct values. Lower case only.");
		} else if (winner == 0) {
			System.out.println("It is a draw!");
		} else {
			System.out.println("player " + winner + " wins");
		}

	}

	private static int evaluateWinner(String player1, String player2) {

		switch (player1) {
		case "scissors":
			switch (player2) {
			case "rock":
				return 2;
			case "paper":
				return 1;
			case "scissors":
				return 0;
			}
		case "rock":
			switch (player2) {
			case "rock":
				return 0;
			case "paper":
				return 2;
			case "scissors":
				return 1;
			}
		case "paper":
			switch (player2) {
			case "rock":
				return 1;
			case "paper":
				return 0;
			case "scissors":
				return 2;
			}
		}
		return -1;
	}

}
