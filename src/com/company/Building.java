package com.company;

public class Building {
    private Integer roomsNumber;
    private Boolean kitchen;
    private Integer bathroomNumber;

    public Building (Integer roomsNumber, Boolean kitchen, Integer bathroomNumber) {
        this.roomsNumber = roomsNumber;
        this.kitchen = kitchen;
        this.bathroomNumber = bathroomNumber;
    }

    public void printAttrs() {
        System.out.println(Main.ANSI_PURPLE + " *** Building status ***");
        System.out.println(
                String.format("Building has %s room(s) and %s. Also with %s bathroom(s).",
                        this.roomsNumber,
                        this.kitchen ? "kitchen" : "no kitchen",
                        this.bathroomNumber
                ));
        System.out.println(" *** End of building status ***\n" + Main.ANSI_RESET);
    }
}
