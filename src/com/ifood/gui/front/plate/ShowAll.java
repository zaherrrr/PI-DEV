package com.ifood.gui.front.plate;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.ifood.entities.Plate;
import com.ifood.services.PlateService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ShowAll extends Form {

    public static Plate currentPlate = null;
    Form previous;
    TextField searchTF;
    ArrayList<Component> componentModels;
    Label categoryLabel, nameLabel, descriptionLabel, quantityLabel, priceLabel;
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


        Button btnAfficherScreenshot = new Button("Partager");
        btnAfficherScreenshot.addActionListener(listener -> share(plate));

        btnsContainer.add(BorderLayout.CENTER, btnAfficherScreenshot);

        plateModel.add(btnsContainer);

        return plateModel;
    }

    private void share(Plate plate) {
        Form form = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        form.add(makeModelWithoutButtons(plate));
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        Image screenshot = Image.createImage(
                com.codename1.ui.Display.getInstance().getDisplayWidth(),
                com.codename1.ui.Display.getInstance().getDisplayHeight()
        );
        form.revalidate();
        form.setVisible(true);
        form.paintComponent(screenshot.getGraphics(), true);
        form.removeAll();
        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch (IOException err) {
            Log.e(err);
        }
        Form screenShotForm = new Form("Partager plate", new BoxLayout(BoxLayout.Y_AXIS));
        ImageViewer screenshotViewer = new ImageViewer(screenshot.fill(1000, 2000));
        screenshotViewer.setFocusable(false);
        screenshotViewer.setUIID("screenshot");
        ShareButton btnPartager = new ShareButton();
        btnPartager.setText("Partager ");
        btnPartager.setTextPosition(LEFT);
        btnPartager.setImageToShare(imageFile, "image/png");
        btnPartager.setTextToShare(plate.toString());
        screenShotForm.addAll(screenshotViewer, btnPartager);
        screenShotForm.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        screenShotForm.show();
    }

}