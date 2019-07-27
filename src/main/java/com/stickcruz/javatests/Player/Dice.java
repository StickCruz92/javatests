package com.stickcruz.javatests.Player;

import java.util.Random;

public class Dice {

	private int sides;
	
	public Dice(int sides) {
		super();
		this.sides = sides;
	}

	public int roll() {
		return new Random().nextInt(sides) +1;
	}
}
