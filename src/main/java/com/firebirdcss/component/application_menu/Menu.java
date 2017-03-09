package com.firebirdcss.component.application_menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allows for the quick and simple setup of a menu driven application.
 * <p>
 * To use this object one must instantiate this class, from there it is as simple as calling the
 * {@link #addItem(String, Method)} method to add static {@link Method}s to the menu, and if one
 * wishes to have a menu option which leads to a new menu/sub-menu then you simply instantiate another
 * of these menu objects and add it to the parent menu using the {@link #addItem(String, Menu)} method.
 * <p>
 * When creating the menu system using this object one does not need to worry about an exit option for the
 * main menu, nor does one need to worry about "Back" options for the sub-menus as that is already taken care
 * of by this object.
 * <p>
 * Once the menu has been built out as desired one simply calls the {@link #display()} method on the main
 * menu to begin execution of the menu system. At that point the menu system handles everything from there.
 * 
 * @author Scott Griffis
 *
 */
public class Menu {
	private BufferedReader inputSource;
	private boolean menuActive = false;
	protected final List<Object/*MethodOrMenu*/> menuItems = new ArrayList<>();
	protected final List<String> menuItemsText = new ArrayList<>(); 
	private String promptText = "Please select an option from the above menu:";
	
	/**
	 * CONSTRUCTOR: Initial menu-item(s) added to the menu and inputSource stored for later use.
	 * 
	 * @param inputSource - The source from where the menu system will get user input as {@link BufferedReader}
	 */
	public Menu(BufferedReader inputSource) {
		this.inputSource = inputSource;
		this.menuItems.add("exit");
		this.menuItemsText.add("Exit Application");
		
	}
	
	/**
	 * Executes the desired menu item.
	 * This may mean displaying a new sub-menu or execution of a {@link Method}
	 * 
	 * @param menuSelection - The desired menu selection as {@link Integer}
	 */
	public void executeMenuSelection(Integer menuSelection) {
		Object selection = menuSelection != null ? this.menuItems.get(menuSelection - 1) : null;
		
		/* Take appropriate action on menu item */
		if (selection != null) {
			if (selection instanceof Menu) {
				Menu menu = (Menu) selection;
				menu.display();
			} else if (selection instanceof Method) {
				Method method = (Method) selection;
				try {
					method.invoke(null);
				} catch (Exception e) {
					System.err.println("\nERROR: Unable to execute menu option!");
				}
			} else if (selection instanceof String) {
				if (((String) selection).equals("exit")) this.menuActive = false;
			}
		} else {
			System.out.println("Invalid selection; Try again!");
		}
	}
	
	/**
	 * Changes the default menu choice prompt to a given one.
	 * 
	 * @param newPrompt - The new prompt text as {@link String}
	 */
	public void changeMenuChoicePrompt(String newPrompt) {
		this.promptText = newPrompt;
	}
	
	/**
	 * Used to add a method related menu-item to this menu.
	 * 
	 * @param menuItemText - The text to be displayed in the menu which represents this item
	 * @param method - The method to execute if this menu option is chosen as {@link Method}
	 */
	public void addItem(String menuItemText, Method method) {
		this.menuItems.add(0, method);
		this.menuItemsText.add(0, menuItemText);
	}
	
	/**
	 * Used to add a sub-menu related menu-item to this menu.
	 * 
	 * @param menuItemText - The text to be displayed in the menu which represents this item
	 * @param subMenu - The sub-menu to execute if this menu option is chosen as {@link Menu}
	 */
	public void addItem(String menuItemText, Menu subMenu) {
		this.menuItems.add(0, subMenu);
		this.menuItemsText.add(0, menuItemText);
		if (subMenu.menuItems.contains("exit")) {
			subMenu.menuItemsText.set(subMenu.menuItems.indexOf("exit"), "Previous Menu");
		} else {
			subMenu.menuItems.add("exit");
			subMenu.menuItemsText.add("Previous Menu");
		}
	}
	
	/**
	 * This method causes this menu to be executed such that it is displayed for the user and
	 * receives menu choices from the user.
	 */
	public void display() {
		this.menuActive = true;
		while (menuActive) {
			System.out.println(renderMenu());
			System.out.print(promptText + " ");
			getMenuSelection();
		}
	}
	
	/**
	 * PRIVATE METHOD: This private method generates the display of the menu based on available menu options.
	 * 
	 * @return Returns the rendered menu text as a {@link String}
	 */
	private String renderMenu() {
		StringBuilder result = new StringBuilder();
		result.append("\n\n\n")
			.append(renderSeparator('=', 100)).append('\n')
			.append(renderSeparator(' ', 48)).append("MENU\n")
			.append(renderSeparator('=', 100)).append("\n\n");
		for (int i = 1; i <= this.menuItemsText.size(); i++) {
			result.append(i).append(") ").append(this.menuItemsText.get(i - 1)).append('\n');
		}
			
		return result.toString();
	}
	
	/**
	 * PRIVATE METHOD: This private method renders a separator made from the specified repeating 
	 * of a single character.
	 * 
	 * @param character - The character to make the separator from as <code>char</code>
	 * @param length - The length of the separator in characters as <code>int</code>
	 * @return Returns the rendered separator text as a {@link String}
	 */
	private String renderSeparator(char character, int length) {
		StringBuilder result = new StringBuilder(length);
		for (int i = 1; i <= length; i++) result.append(character);
		
		return result.toString();
	}
	
	/**
	 * PRIVATE METHOD: Gets and executes the appropriate menu item.
	 */
	private void getMenuSelection() {
		String line = null;
		try {
			line = inputSource.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			this.executeMenuSelection(Integer.valueOf(line));
		} catch (NumberFormatException e) {
			this.executeMenuSelection(null);
		}
	}
} // END OF CLASS