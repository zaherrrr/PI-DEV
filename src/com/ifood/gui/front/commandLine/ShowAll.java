package com.ifood.gui.front.commandLine;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.ifood.entities.CommandLine;
import com.ifood.services.CommandLineService;
import com.ifood.utils.Statics;

import java.util.ArrayList;
import java.util.Collections;

public class ShowAll extends Form {

    public static CommandLine currentCommandLine = null;
    Form previous;
    Button addBtn;


    PickerComponent sortPicker;
    ArrayList<Component> componentModels;
    Label plateLabel, commandLabel, quantityLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public ShowAll(Form previous) {
        super("CommandLines", new BoxLayout(BoxLayout.Y_AXIS));
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


        ArrayList<CommandLine> listCommandLines = CommandLineService.getInstance().getAll();

        componentModels = new ArrayList<>();

        sortPicker = PickerComponent.createStrings("Plate", "Commande", "Quantity").label("Trier par");
        sortPicker.getPicker().setSelectedString("");
        sortPicker.getPicker().addActionListener((l) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            Statics.compareVar = sortPicker.getPicker().getSelectedString();
            Collections.sort(listCommandLines);
            for (CommandLine commandLine : listCommandLines) {
                Component model = makeCommandLineModel(commandLine);
                this.add(model);
                componentModels.add(model);
            }
            this.revalidate();
        });
        this.add(sortPicker);

        if (listCommandLines.size() > 0) {
            for (CommandLine commandLine : listCommandLines) {
                Component model = makeCommandLineModel(commandLine);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentCommandLine = null;
            new Manage(this).show();
        });

    }

    private Container makeModelWithoutButtons(CommandLine commandLine) {
        Container commandLineModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        commandLineModel.setUIID("containerRounded");


        plateLabel = new Label("Plate : " + commandLine.getPlate());
        plateLabel.setUIID("labelDefault");

        commandLabel = new Label("Commande : " + commandLine.getCommand());
        commandLabel.setUIID("labelDefault");

        quantityLabel = new Label("Quantity : " + commandLine.getQuantity());
        quantityLabel.setUIID("labelDefault");

        plateLabel = new Label("Plate : " + commandLine.getPlate().getName());
        plateLabel.setUIID("labelDefault");

        commandLabel = new Label("Commande : " + commandLine.getCommand().getDateCommand());
        commandLabel.setUIID("labelDefault");


        commandLineModel.addAll(

                plateLabel, commandLabel, quantityLabel
        );

        return commandLineModel;
    }

    private Component makeCommandLineModel(CommandLine commandLine) {

        Container commandLineModel = makeModelWithoutButtons(commandLine);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentCommandLine = commandLine;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce commandLine ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = CommandLineService.getInstance().delete(commandLine.getId());

                if (responseCode == 200) {
                    currentCommandLine = null;
                    dlg.dispose();
                    commandLineModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du commandLine. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        commandLineModel.add(btnsContainer);

        return commandLineModel;
    }

}