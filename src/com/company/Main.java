package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[97m";
    public static final String ANSI_BOLD_WHITE = "\u001B[1;97m";

    public static String getInputFromConsole(String message) {
        System.out.println(message);
        return scanner.nextLine().strip();
    }

    public static Integer stringToInt(String numberText) {
        Integer wholeNumber = null;
        try {
            wholeNumber = Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "The number you put is weird... I don't want it!" + ANSI_RESET);
            System.exit(1);
        }
        return wholeNumber;
    }

    public static Double stringToDouble(String numberText) {
        Double rationalNumber = null;
        try {
            rationalNumber = Double.parseDouble(numberText);
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "The number you put is weird... I don't want it!" + ANSI_RESET);
            System.exit(1);
        }
        return rationalNumber;
    }

    public static Restaurant programInit() {
        System.out.println(ANSI_BLACK + "####################################" + ANSI_RESET);
        System.out.println(String.format(
                "%s############%s  WELCOME!  %s############%s",
                ANSI_BLACK, ANSI_WHITE, ANSI_BLACK, ANSI_RESET
        ));
        System.out.println(ANSI_BLACK + "####################################" + ANSI_RESET);
        System.out.println("This is a Restaurant Console Program!\n");
        System.out.println("Create a new Restaurant.");

        String input = getInputFromConsole("Enter number of rooms (positive whole numbers):");
        Integer roomsNumber = stringToInt(input);
        input = getInputFromConsole("Now enter number of bathrooms (positive whole numbers):");
        Integer bathroomsNumber = stringToInt(input);
        input = getInputFromConsole("Do you have a kitchen? (Y/n)").toLowerCase(Locale.ROOT);
        Boolean isKitchen = true;
        if (input.equals("n")) {
            isKitchen = false;
        }

        Restaurant restaurant = new Restaurant(roomsNumber, isKitchen, bathroomsNumber);
        System.out.println("\n");
        restaurant.printAttrs();
        restaurant.getMenu().printMenu();
        restaurant.printEmployeeList();
        System.out.println(String.format(
                "%sGreat job! You've created %sThe Restaurant%s!%s\n\n",
                ANSI_BLUE, ANSI_YELLOW, ANSI_BLUE, ANSI_RESET
        ));
        return restaurant;
    }

    public static void printOptions() {
        System.out.println("D\t- print this menu");
        System.out.println(ANSI_PURPLE + "--------------------------------------------" + ANSI_RESET);
        System.out.println("B\t- print building status");
        System.out.println(ANSI_GREEN + "--------------------------------------------" + ANSI_RESET);
        System.out.println("M\t- print menu");
        System.out.println("MA\t- add a dish to menu");
        System.out.println("MDP\t- print a price of a dish");
        System.out.println(ANSI_CYAN + "--------------------------------------------" + ANSI_RESET);
        System.out.println("E\t- print employee list");
        System.out.println("EA\t- add employee");
        System.out.println("EG\t- print employee's all information");
        System.out.println("EGP\t- print employee's position");
        System.out.println("EGS\t- print employee's salary per hour");
        System.out.println("ESP\t- change employee's position");
        System.out.println("ESS\t- change employee's salary per hour");
        System.out.println(ANSI_BLACK + "--------------------------------------------" + ANSI_RESET);
        System.out.println("XXX\t- about the program");
        System.out.println("Q\t- Quit the program");
        System.out.println("\n");
    }

    public static void programLoop(Restaurant restaurant){
        while(true) {
            System.out.println(ANSI_BOLD_WHITE + "\nChose one of the options:" + ANSI_RESET);
            printOptions();
            String input = getInputFromConsole("Type option and press Enter:").toUpperCase(Locale.ROOT);

            switch (input) {
                case "D": break;
                case "B": restaurant.printAttrs(); break;
                case "M": restaurant.getMenu().printMenu(); break;
                case "MA": inputMenuItemData(restaurant); break;
                case "MDP": getDishPrice(restaurant); break;
                case "E": restaurant.printEmployeeList(); break;
                case "EA": addNewEmployee(restaurant); break;
                case "EG": employeeInfo(restaurant); break;
                case "EGP": employeePosition(restaurant); break;
                case "EGS": employeeSalary(restaurant); break;
                case "ESP": setEmployeePosition(restaurant); break;
                case "ESS": setEmployeeSalary(restaurant); break;
                case "XXX": about(); break;
                case "Q": System.out.println("Good bye Sir!"); System.exit(0);
                default: System.out.println("Sorry, this option is not supported yet.");
            }
        }
    }

    public static void inputMenuItemData(Restaurant restaurant) {
        System.out.println("Create a new dish.");
        String itemName = getInputFromConsole("Please enter the dish name:");
        String itemDescription = getInputFromConsole("Please enter the dish description:");
        String input = getInputFromConsole("Please enter the dish price (with 2 decimal places):");
        Double itemPrice = stringToDouble(input);
        restaurant.getMenu().addMenuItem(itemName, itemDescription, itemPrice);
    }

    public static void getDishPrice(Restaurant restaurant) {
        String input = getInputFromConsole("You wanted to get the dish price, enter the dish name:");
        restaurant.getMenu().printMenuItemPrice(input);
    }

    public static void addNewEmployee(Restaurant restaurant) {
        System.out.println("Add new employee.");
        String name = getInputFromConsole("Please enter the Name of Employee:");
        String surname = getInputFromConsole("Please enter the Surname:");
        String position = getInputFromConsole("Please enter the position:");
        String input = getInputFromConsole("Please enter the salary per hour:");
        Double salary = stringToDouble(input);
        restaurant.addEmployee(name, surname, position, salary);
    }

    public static void employeeInfo(Restaurant restaurant) {
        System.out.println("You wanted to get all information about employee.");
        String fullName = getInputFromConsole("Please enter employee full name:");
        restaurant.getEmployeeByFullName(fullName).printEmployeeData();
    }

    public static void employeePosition(Restaurant restaurant) {
        System.out.println("You wanted to get position of an employee.");
        String fullName = getInputFromConsole("Please enter employee full name:");
        restaurant.printEmployeePosition(fullName);
    }

    public static void employeeSalary(Restaurant restaurant) {
        System.out.println("You wanted to get salary of an employee.");
        String fullName = getInputFromConsole("Please enter employee full name:");
        restaurant.printEmployeeSalary(fullName);
    }

    public static void setEmployeePosition(Restaurant restaurant) {
        System.out.println("You wanted to set new position of an employee.");
        String fullName = getInputFromConsole("Please enter employee full name:");
        String position = getInputFromConsole("Please enter new position:");
        restaurant.setEmployeePosition(fullName, position);
    }
    public static void setEmployeeSalary(Restaurant restaurant) {
        System.out.println("You wanted to set a new salary of an employee.");
        String fullName = getInputFromConsole("Please enter employee full name:");
        String input = getInputFromConsole("Please enter new salary per hour (2 decimal places):");
        Double salary = stringToDouble(input);
        restaurant.setEmployeeSalary(fullName, salary);
    }

    public static void about() {
        System.out.println(ANSI_BOLD_WHITE + "About the program and author:" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "This is a simple console program to create and manage the restaurant.");
        System.out.println("Author: Edwin Pająk\nPosition: QA Engineer\nCompany: Intive Gmbh" + ANSI_RESET);
    }

    public static void main(String[] args) {
	// write your code here
        Restaurant restaurant = programInit();
        programLoop(restaurant);
    }
}
