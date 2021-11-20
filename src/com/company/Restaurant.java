package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class Restaurant extends Building {
    private TreeSet<Employee> employees = new TreeSet();
    private Menu menu = new Menu();

    public Menu getMenu() {
        return this.menu;
    }

    public Restaurant(Integer roomsNumber, Boolean kitchen, Integer bathroomNumber) {
        super(roomsNumber, kitchen, bathroomNumber);
    }

    public void addEmployee(String name, String surname, String position, Double salaryPerHour) {
        Employee employee = new Employee(name, surname, position, salaryPerHour);
        Boolean result = employees.add(employee);

        if (result) {
            System.out.println(String.format(
                    "%s%s was added to Employees list.%s",
                    Main.ANSI_CYAN,
                    employee.getFullName(),
                    Main.ANSI_RESET
            ));
        } else {
            System.out.println(String.format(
                    "%s%s is already hired. I can't hire him again...%s",
                    Main.ANSI_RED,
                    employee.getFullName(),
                    Main.ANSI_RESET
            ));
        }
    }

    public Employee getEmployeeByFullName(String fullName) {
        Employee employee = null;
        try {
            employee = employees.stream().filter(e -> e.getFullName().equals(fullName)).findFirst().get();
        } catch (NoSuchElementException e) {
            System.out.println(Main.ANSI_RED + fullName + " doesn't work in our restaurant." + Main.ANSI_RESET);
        }
        return employee;
    }

    public void printEmployeeList() {
        System.out.println(Main.ANSI_CYAN + " *** Employee list ***");
        Iterator<Employee> itr = employees.iterator();
        while(itr.hasNext()) {
            itr.next().printEmployeeData();
        }
        System.out.println(Main.ANSI_CYAN + " *** End of Employee list ***\n" + Main.ANSI_RESET);
    }

    public void printEmployeePosition(String fullName) {
        Employee employee = getEmployeeByFullName(fullName);
        if (employee == null) { return; }
        System.out.println(String.format("%s%s is a %s.%s",
                Main.ANSI_CYAN,
                fullName,
                employee.getPosition(),
                Main.ANSI_RESET
        ));
    }

    public void printEmployeeSalary(String fullName) {
        Employee employee = getEmployeeByFullName(fullName);
        if (employee == null) { return; }
        System.out.println(String.format(
                "%s%s earns %.2f PLN per hour.%s",
                Main.ANSI_CYAN,
                fullName,
                employee.getSalary(),
                Main.ANSI_RESET
        ));
    }

    public void printEmployeePosition(Employee employee) {
        System.out.println(String.format(
                "\"%s%s is a %s.%s\"",
                Main.ANSI_CYAN,
                employee.getFullName(),
                employee.getPosition(),
                Main.ANSI_RESET
        ));
    }

    public void printEmployeeSalary(Employee employee) {
        System.out.println(String.format(
                "%s%s earns %.2f PLN per hour.%s",
                Main.ANSI_CYAN,
                employee.getFullName(),
                employee.getSalary(),
                Main.ANSI_RESET
        ));
    }

    public void setEmployeePosition(String fullName, String position) {
        Employee employee = getEmployeeByFullName(fullName);
        if (employee == null) { return; }
        employee.setPosition(position);
        this.printEmployeePosition(employee);
    }

    public void setEmployeeSalary(String fullName, Double salary) {
        Employee employee = getEmployeeByFullName(fullName);
        if (employee == null) { return; }
        employee.setSalaryPerHour(salary);
        this.printEmployeeSalary(employee);
    }
}
