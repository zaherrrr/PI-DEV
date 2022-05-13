package com.ifood.gui.back.plate;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.ifood.entities.Category;
import com.ifood.entities.Plate;
import com.ifood.services.CategoryService;
import com.ifood.services.PlateService;

import java.util.ArrayList;

public class Manage extends Form {


    Plate currentPlate;

    TextField nameTF;
    TextField descriptionTF;
    TextField quantityTF;
    TextField priceTF;
    Label nameLabel;
    Label descriptionLabel;
    Label quantityLabel;
    Label priceLabel;


    ArrayList<Category> listCategorys;
    PickerComponent categoryPC;
    Category selectedCategory = null;


    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentPlate == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentPlate = ShowAll.currentPlate;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        String[] categoryStrings;
        int categoryIndex;
        categoryPC = PickerComponent.createStrings("").label("Category");
        listCategorys = CategoryService.getInstance().getAll();
        categoryStrings = new String[listCategorys.size()];
        categoryIndex = 0;
        for (Category category : listCategorys) {
            categoryStrings[categoryIndex] = category.getName();
            categoryIndex++;
        }
        if (listCategorys.size() > 0) {
            categoryPC.getPicker().setStrings(categoryStrings);
            categoryPC.getPicker().addActionListener(l -> selectedCategory = listCategorys.get(categoryPC.getPicker().getSelectedStringIndex()));
        } else {
            categoryPC.getPicker().setStrings("");
        }


        nameLabel = new Label("Name : ");
        nameLabel.setUIID("labelDefault");
        nameTF = new TextField();
        nameTF.setHint("Tapez le name");


        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez le description");


        quantityLabel = new Label("Quantity : ");
        quantityLabel.setUIID("labelDefault");
        quantityTF = new TextField();
        quantityTF.setHint("Tapez le quantity");


        priceLabel = new Label("Price : ");
        priceLabel.setUIID("labelDefault");
        priceTF = new TextField();
        priceTF.setHint("Tapez le price");


        if (currentPlate == null) {


            manageButton = new Button("Ajouter");
        } else {

            nameTF.setText(currentPlate.getName());
            descriptionTF.setText(currentPlate.getDescription());
            quantityTF.setText(String.valueOf(currentPlate.getQuantity()));
            priceTF.setText(String.valueOf(currentPlate.getPrice()));

            categoryPC.getPicker().setSelectedString(currentPlate.getCategory().getName());
            selectedCategory = currentPlate.getCategory();


            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                nameLabel, nameTF,
                descriptionLabel, descriptionTF,
                quantityLabel, quantityTF,
                priceLabel, priceTF,
                categoryPC,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        if (currentPlate == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = PlateService.getInstance().add(
                            new Plate(


                                    selectedCategory,
                                    nameTF.getText(),
                                    descriptionTF.getText(),
                                    (int) Float.parseFloat(quantityTF.getText()),
                                    (int) Float.parseFloat(priceTF.getText())
                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Plate ajouté avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de plate. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = PlateService.getInstance().edit(
                            new Plate(
                                    currentPlate.getId(),


                                    selectedCategory,
                                    nameTF.getText(),
                                    descriptionTF.getText(),
                                    (int) Float.parseFloat(quantityTF.getText()),
                                    (int) Float.parseFloat(priceTF.getText())

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Plate modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de plate. Code d'erreur : " + responseCode, new Command("Ok"));
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


        if (nameTF.getText().equals("")) {
            Dialog.show("Avertissement", "Name vide", new Command("Ok"));
            return false;
        }


        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Description vide", new Command("Ok"));
            return false;
        }


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


        if (priceTF.getText().equals("")) {
            Dialog.show("Avertissement", "Price vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(priceTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", priceTF.getText() + " n'est pas un nombre valide (price)", new Command("Ok"));
            return false;
        }


        if (selectedCategory == null) {
            Dialog.show("Avertissement", "Veuillez choisir un category", new Command("Ok"));
            return false;
        }


        return true;
    }
}