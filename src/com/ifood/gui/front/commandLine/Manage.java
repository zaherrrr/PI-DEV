package com.ifood.gui.front.commandLine;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.ifood.entities.CommandLine;
import com.ifood.entities.Commande;
import com.ifood.entities.Plate;
import com.ifood.services.CommandLineService;
import com.ifood.services.CommandService;
import com.ifood.services.PlateService;
import com.ifood.utils.AlertUtils;

import java.util.ArrayList;

public class Manage extends Form {


    CommandLine currentCommandLine;

    TextField quantityTF;
    Label quantityLabel;


    ArrayList<Plate> listPlates;
    PickerComponent platePC;
    Plate selectedPlate = null;
    ArrayList<Commande> listCommands;
    PickerComponent commandPC;
    Commande selectedCommand = null;


    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentCommandLine == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentCommandLine = ShowAll.currentCommandLine;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        String[] plateStrings;
        int plateIndex;
        platePC = PickerComponent.createStrings("").label("Plate");
        listPlates = PlateService.getInstance().getAll();
        plateStrings = new String[listPlates.size()];
        plateIndex = 0;
        for (Plate plate : listPlates) {
            plateStrings[plateIndex] = plate.getName();
            plateIndex++;
        }
        if (listPlates.size() > 0) {
            platePC.getPicker().setStrings(plateStrings);
            platePC.getPicker().addActionListener(l -> selectedPlate = listPlates.get(platePC.getPicker().getSelectedStringIndex()));
        } else {
            platePC.getPicker().setStrings("");
        }

        String[] commandStrings;
        int commandIndex;
        commandPC = PickerComponent.createStrings("").label("Commande");
        listCommands = CommandService.getInstance().getAll();
        commandStrings = new String[listCommands.size()];
        commandIndex = 0;
        for (Commande command : listCommands) {
            commandStrings[commandIndex] = String.valueOf(command.getDateCommand());
            commandIndex++;
        }
        if (listCommands.size() > 0) {
            commandPC.getPicker().setStrings(commandStrings);
            commandPC.getPicker().addActionListener(l -> selectedCommand = listCommands.get(commandPC.getPicker().getSelectedStringIndex()));
        } else {
            commandPC.getPicker().setStrings("");
        }


        quantityLabel = new Label("Quantity : ");
        quantityLabel.setUIID("labelDefault");
        quantityTF = new TextField();
        quantityTF.setHint("Tapez le quantity");


        if (currentCommandLine == null) {


            manageButton = new Button("Ajouter");
        } else {


            quantityTF.setText(String.valueOf(currentCommandLine.getQuantity()));

            platePC.getPicker().setSelectedString(currentCommandLine.getPlate().getName());
            selectedPlate = currentCommandLine.getPlate();
            commandPC.getPicker().setSelectedString(String.valueOf(currentCommandLine.getCommand().getDateCommand()));
            selectedCommand = currentCommandLine.getCommand();


            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                quantityLabel, quantityTF,
                platePC, commandPC,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        if (currentCommandLine == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = CommandLineService.getInstance().add(
                            new CommandLine(


                                    selectedPlate,
                                    selectedCommand,
                                    (int) Float.parseFloat(quantityTF.getText())
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("CommandLine ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de commandLine. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = CommandLineService.getInstance().edit(
                            new CommandLine(
                                    currentCommandLine.getId(),


                                    selectedPlate,
                                    selectedCommand,
                                    (int) Float.parseFloat(quantityTF.getText())

                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("CommandLine modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de commandLine. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((ShowAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (quantityTF.getText().equals("")) {
            Dialog.show("Avertissement", "Quantity vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(quantityTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", quantityTF.getText() + " n'est pas un nombre valide (quantity)", new Command("Ok"));
            return false;
        }


        if (selectedPlate == null) {
            Dialog.show("Avertissement", "Veuillez choisir un plate", new Command("Ok"));
            return false;
        }

        if (selectedCommand == null) {
            Dialog.show("Avertissement", "Veuillez choisir un command", new Command("Ok"));
            return false;
        }


        return true;
    }
}