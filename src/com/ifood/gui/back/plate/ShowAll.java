package com.ifood.gui.back.plate;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.ifood.entities.Plate;
import com.ifood.services.PlateService;

import java.util.ArrayList;

public class ShowAll extends Form {

    public static Plate currentPlate = null;
    Form previous;
    Button addBtn;

    TextField searchTF;
    ArrayList<Component> componentModels;
    Label categoryLabel, nameLabel, descriptionLabel, quantityLabel, priceLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public ShowAll(Form previous) {
        super("Plates", new BoxLayout(BoxLayout.Y_AXIS));
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


        ArrayList<Plate> listPlates = PlateService.getInstance().getAll();
        componentModels = new ArrayList<>();

        searchTF = new TextField("", "Chercher plate par Name");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Plate plate : listPlates) {
                if (plate.getName().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makePlateModel(plate);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);


        if (listPlates.size() > 0) {
            for (Plate plate : listPlates) {
                Component model = makePlateModel(plate);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentPlate = null;
            new Manage(this).show();
        });

    }

    private Container makeModelWithoutButtons(Plate plate) {
        Container plateModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        plateModel.setUIID("containerRounded");


        categoryLabel = new Label("Category : " + plate.getCategory());
        categoryLabel.setUIID("labelDefault");

        nameLabel = new Label("Name : " + plate.getName());
        nameLabel.setUIID("labelDefault");

        descriptionLabel = new Label("Description : " + plate.getDescription());
        descriptionLabel.setUIID("labelDefault");

        quantityLabel = new Label("Quantity : " + plate.getQuantity());
        quantityLabel.setUIID("labelDefault");

        priceLabel = new Label("Price : " + plate.getPrice());
        priceLabel.setUIID("labelDefault");

        categoryLabel = new Label("Category : " + plate.getCategory().getName());
        categoryLabel.setUIID("labelDefault");


        plateModel.addAll(

                categoryLabel, nameLabel, descriptionLabel, quantityLabel, priceLabel
        );

        return plateModel;
    }

    private Component makePlateModel(Plate plate) {

        Container plateModel = makeModelWithoutButtons(plate);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentPlate = plate;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce plate ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = PlateService.getInstance().delete(plate.getId());

                if (responseCode == 200) {
                    currentPlate = null;
                    dlg.dispose();
                    plateModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du plate. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        plateModel.add(btnsContainer);

        return plateModel;
    }

}