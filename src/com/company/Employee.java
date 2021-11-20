package com.company;

public class Employee implements Comparable{
    private String fullName;
    private String position;
    private Double salaryPerHour;

    public Employee(String name, String surname, String position, Double salaryPerHour) {
        this.fullName = name + " " + surname;
        this.position = position;
        this.salaryPerHour = salaryPerHour;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPosition() {
        return this.position;
    }

    public Double getSalary() {
        return this.salaryPerHour;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalaryPerHour(Double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public void printEmployeeData() {
        System.out.println(String.format(
                "%s%s is working as a %s with salary per hour: %.2f PLN%s",
                Main.ANSI_CYAN,
                this.fullName,
                this.position,
                this.salaryPerHour,
                Main.ANSI_RESET
        ));
    }

    @Override
    public String toString() {
        return this.getFullName();
    }

    @Override
    public int compareTo(Object o) {
        return this.fullName.compareTo(o.toString());
    }
}
