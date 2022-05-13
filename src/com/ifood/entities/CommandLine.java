package com.ifood.entities;

import com.ifood.utils.Statics;

public class CommandLine implements Comparable<CommandLine> {

    private int id;
    private Plate plate;
    private Commande command;
    private int quantity;

    public CommandLine() {
    }

    public CommandLine(int id, Plate plate, Commande command, int quantity) {
        this.id = id;
        this.plate = plate;
        this.command = command;
        this.quantity = quantity;
    }

    public CommandLine(Plate plate, Commande command, int quantity) {
        this.plate = plate;
        this.command = command;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public Commande getCommand() {
        return command;
    }

    public void setCommand(Commande command) {
        this.command = command;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public int compareTo(CommandLine commandLine) {
        switch (Statics.compareVar) {
            case "Plate":
                return this.getPlate().getName().compareTo(commandLine.getPlate().getName());
            case "Quantity":
                return Integer.compare(this.getQuantity(), commandLine.getQuantity());

            default:
                return 0;
        }
    }

}