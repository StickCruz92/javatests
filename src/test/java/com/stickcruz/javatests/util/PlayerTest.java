package com.stickcruz.javatests.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.stickcruz.javatests.Player.Dice;
import com.stickcruz.javatests.Player.Player;

public class PlayerTest {

	@Test
	public void looses_when_dice_number_is_too_low() {
		
		Dice dice = Mockito.mock(Dice.class);
		Mockito.when(dice.roll()).thenReturn(2);
		
		Player player = new Player(dice, 3);
		assertFalse(player.play());
	}
	
	@Test
	public void looses_when_dice_number_is_too_big() {
		
		Dice dice = Mockito.mock(Dice.class);
		Mockito.when(dice.roll()).thenReturn(4);
		
		Player player = new Player(dice, 3);
		assertTrue(player.play());
	}
	
}
