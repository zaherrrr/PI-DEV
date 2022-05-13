package com.ifood.gui.back.command;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.ifood.entities.Commande;
import com.ifood.services.CommandService;
import com.ifood.utils.Statics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class ShowAll extends Form {

    public static Commande currentCommand = null;
    Form previous;
    Button addBtn;


    PickerComponent sortPicker;
    ArrayList<Component> componentModels;
    Label utilisateurLabel, dateCommandLabel, statusLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public ShowAll(Form previous) {
        super("Commands", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);


        ArrayList<Commande> listCommands = CommandService.getInstance().getAll();

        componentModels = new ArrayList<>();

        sortPicker = PickerComponent.createStrings("Utilisateur", "DateCommand", "Status").label("Trier par");
        sortPicker.getPicker().setSelectedString("");
        sortPicker.getPicker().addActionListener((l) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            Statics.compareVar = sortPicker.getPicker().getSelectedString();
            Collections.sort(listCommands);
            for (Commande command : listCommands) {
                Component model = makeCommandModel(command);
                this.add(model);
                componentModels.add(model);
            }
            this.revalidate();
        });
        this.add(sortPicker);

        if (listCommands.size() > 0) {
            for (Commande command : listCommands) {
                Component model = makeCommandModel(command);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentCommand = null;
            new Manage(this).show();
        });

    }

    private Container makeModelWithoutButtons(Commande command) {
        Container commandModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        commandModel.setUIID("containerRounded");


        utilisateurLabel = new Label("Utilisateur : " + command.getUtilisateur());
        utilisateurLabel.setUIID("labelDefault");

        dateCommandLabel = new Label("DateCommand : " + new SimpleDateFormat("dd-MM-yyyy").format(command.getDateCommand()));
        dateCommandLabel.setUIID("labelDefault");

        statusLabel = new Label("Status : " + (command.getStatus() == 1 ? "True" : "False"));
        statusLabel.setUIID("labelDefault");

        utilisateurLabel = new Label("Utilisateur : " + command.getUtilisateur().getEmail());
        utilisateurLabel.setUIID("labelDefault");


        commandModel.addAll(

                utilisateurLabel, dateCommandLabel, statusLabel
        );

        return commandModel;
    }

    private Component makeCommandModel(Commande command) {

        Container commandModel = makeModelWithoutButtons(command);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentCommand = command;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce command ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = CommandService.getInstance().delete(command.getId());

                if (responseCode == 200) {
                    currentCommand = null;
                    dlg.dispose();
                    commandModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du command. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        commandModel.add(btnsContainer);

        return commandModel;
    }

}