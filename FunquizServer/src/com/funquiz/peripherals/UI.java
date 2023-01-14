package com.funquiz.peripherals;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

/**
 * Common UI class implemented to reduce complexity and to avoid different values been set for the identical components
 * 
 * @author Oshadee Amarasinghe
 *
 */
public class UI {

	/**
	 * Dark grey color used as the primary background color
	 */
	public static Color COLOR_DARK_GREY = Color.decode("#263238");
	
	/**
	 * White color used as the secondary font and background color
	 */
	public static Color COLOR_WHITE = Color.WHITE;
	
	/**
	 * Red color used as the primary font and selected button color
	 */
	public static Color COLOR_RED = Color.decode("#b71c1c");

	private static String APPLICATION_THEME_FONT = "Roboto";
	
	/**
	 * Roboto Plain 13 font 
	 */
	public static Font APPLICATION_THEME_FONT_13_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 13);
	
	/**
	 * Roboto Plain 14 font 
	 */
	public static Font APPLICATION_THEME_FONT_14_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 14);

	/**
	 * Hand cursor to be used at button hovers
	 */
	public static int APPPLICATION_THEME_SELECT_CURSOR = Cursor.HAND_CURSOR;
}
