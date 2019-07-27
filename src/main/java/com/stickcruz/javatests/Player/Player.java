package com.stickcruz.javatests.Player;

public class Player {
	
	private Dice dice;
	private int minNumberToWin;


	public Player(Dice dice, int minNumberToWin) {
		super();
		this.dice = dice;
		this.minNumberToWin = minNumberToWin;
	}

	public boolean play() {
		int diceNumber = dice.roll();
		return diceNumber > minNumberToWin;
	}
}
