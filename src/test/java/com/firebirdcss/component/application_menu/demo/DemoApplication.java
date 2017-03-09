package com.firebirdcss.component.application_menu.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.firebirdcss.component.application_menu.Menu;

/**
 * This class makes up the DEMO for the {@link Menu} class.
 * <p>
 * The point of this demo is to allow others to see the Menu class in action as well as provide a 
 * coding example for how to use the Menu class.
 * 
 * @author Scott Griffis
 *
 */
public class DemoApplication {
	private static BufferedReader bReader = null;
	
	/**
	 * The main entry point of execution for this DEMO Application.
	 * 
	 * @param args - Arguments are not used for this application.
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			bReader = reader;
			
			Menu mainMenu = buildMenus();
			mainMenu.display(); // This is what causes the menu to actually begin running.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n~ Application Ended ~");
	}
	
	/**
	 * All building out of the {@link Menu} is done in this method so that it is all kept togather
	 * and easy for others to find.
	 * <p>
	 * This is example code for how to properally build out a menu system using the {@link Menu} class.
	 * 
	 * @return Returns the built menu for execution externally of this method, as {@link Menu}
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Menu buildMenus() throws NoSuchMethodException, SecurityException {
		Menu mainMenu = new Menu(bReader);
		
		Menu subMenuOne = new Menu(bReader);
		subMenuOne.addItem("Menu Item 'C'", DemoApplication.class.getMethod("menuItemC"));
		subMenuOne.addItem("Menu Item 'D'", DemoApplication.class.getMethod("menuItemD"));
		subMenuOne.addItem("Menu Item 'E'", DemoApplication.class.getMethod("menuItemE"));
		subMenuOne.addItem("Menu Item 'F'", DemoApplication.class.getMethod("menuItemF"));
		
		Menu subMenuTwo = new Menu(bReader);
		subMenuTwo.addItem("Menu Item 'G'", DemoApplication.class.getMethod("menuItemG"));
		
		mainMenu.addItem("A sub-menu of multiple options", subMenuOne);
		mainMenu.addItem("Menu Item 'A'", DemoApplication.class.getMethod("menuItemA"));
		mainMenu.addItem("Menu Item 'B'", DemoApplication.class.getMethod("menuItemB"));
		mainMenu.addItem("Another sub-menu", subMenuTwo);
		
		return mainMenu;
	}
	
	/* =======================================[vv Method menu-items below vv]======================================= */
	
	/**
	 * Method for use as a Menu-Item...
	 */
	public static void menuItemA() {
		System.out.println("Entered menuItemA()");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for use as a Menu-Item...
	 */
	public static void menuItemB() {
		System.out.println("Entered menuItemB()");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for use as a Menu-Item...
	 */
	public static void menuItemC() {
		System.out.println("Entered menuItemC()");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for use as a Menu-Item...
	 */
	public static void menuItemD() {
		System.out.println("Entered menuItemD()");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for use as a Menu-Item...
	 */
	public static void menuItemE() {
		System.out.println("Entered menuItemE()");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for use as a Menu-Item...
	 */
	public static void menuItemF() {
		System.out.println("Entered menuItemF()");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for use as a Menu-Item...
	 */
	public static void menuItemG() {
		System.out.println("Entered menuItemG()");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
