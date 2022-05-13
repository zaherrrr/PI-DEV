package com.ifood.gui.front.command;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.ifood.entities.Commande;
import com.ifood.entities.Utilisateur;
import com.ifood.services.CommandService;
import com.ifood.services.UtilisateurService;
import com.ifood.utils.AlertUtils;

import java.util.ArrayList;

public class Manage extends Form {


    Commande currentCommand;


    PickerComponent dateCommandTF;
    CheckBox statusCB;
    ArrayList<Utilisateur> listUtilisateurs;
    PickerComponent utilisateurPC;
    Utilisateur selectedUtilisateur = null;


    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentCommand == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentCommand = ShowAll.currentCommand;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        String[] utilisateurStrings;
        int utilisateurIndex;
        utilisateurPC = PickerComponent.createStrings("").label("Utilisateur");
        listUtilisateurs = UtilisateurService.getInstance().getAll();
        utilisateurStrings = new String[listUtilisateurs.size()];
        utilisateurIndex = 0;
        for (Utilisateur utilisateur : listUtilisateurs) {
            utilisateurStrings[utilisateurIndex] = utilisateur.getEmail();
            utilisateurIndex++;
        }
        if (listUtilisateurs.size() > 0) {
            utilisateurPC.getPicker().setStrings(utilisateurStrings);
            utilisateurPC.getPicker().addActionListener(l -> selectedUtilisateur = listUtilisateurs.get(utilisateurPC.getPicker().getSelectedStringIndex()));
        } else {
            utilisateurPC.getPicker().setStrings("");
        }


        dateCommandTF = PickerComponent.createDate(null).label("DateCommand");


        statusCB = new CheckBox("Status : ");


        if (currentCommand == null) {


            manageButton = new Button("Ajouter");
        } else {

            dateCommandTF.getPicker().setDate(currentCommand.getDateCommand());
            if (currentCommand.getStatus() == 1) {
                statusCB.setSelected(true);
            }

            utilisateurPC.getPicker().setSelectedString(currentCommand.getUtilisateur().getEmail());
            selectedUtilisateur = currentCommand.getUtilisateur();


            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                dateCommandTF,
                statusCB,
                utilisateurPC,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        if (currentCommand == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = CommandService.getInstance().add(
                            new Commande(


                                    selectedUtilisateur,
                                    dateCommandTF.getPicker().getDate(),
                                    statusCB.isSelected() ? 1 : 0
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Commande ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de command. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = CommandService.getInstance().edit(
                            new Commande(
                                    currentCommand.getId(),


                                    selectedUtilisateur,
                                    dateCommandTF.getPicker().getDate(),
                                    statusCB.isSelected() ? 1 : 0

                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Commande modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de command. Code d'erreur : " + responseCode, new Command("Ok"));
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


        if (dateCommandTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la dateCommand", new Command("Ok"));
            return false;
        }


        if (selectedUtilisateur == null) {
            Dialog.show("Avertissement", "Veuillez choisir un utilisateur", new Command("Ok"));
            return false;
        }


        return true;
    }
}