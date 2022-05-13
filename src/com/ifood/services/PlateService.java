package com.ifood.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ifood.entities.Category;
import com.ifood.entities.Plate;
import com.ifood.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlateService {

    public static PlateService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Plate> listPlates;


    private PlateService() {
        cr = new ConnectionRequest();
    }

    public static PlateService getInstance() {
        if (instance == null) {
            instance = new PlateService();
        }
        return instance;
    }

    public ArrayList<Plate> getAll() {
        listPlates = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/plate");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listPlates = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listPlates;
    }

    private ArrayList<Plate> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Plate plate = new Plate(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        makeCategory((Map<String, Object>) obj.get("category")),
                        (String) obj.get("name"),
                        (String) obj.get("description"),
                        (int) Float.parseFloat(obj.get("quantity").toString()),
                        (int) Float.parseFloat(obj.get("price").toString())

                );

                listPlates.add(plate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPlates;
    }

    public Category makeCategory(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Category category = new Category();
        category.setId((int) Float.parseFloat(obj.get("id").toString()));
        category.setName((String) obj.get("name"));
        return category;
    }

    public int add(Plate plate) {
        return manage(plate, false);
    }

    public int edit(Plate plate) {
        return manage(plate, true);
    }

    public int manage(Plate plate, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/plate/edit");
            cr.addArgument("id", String.valueOf(plate.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/plate/add");
        }

        cr.addArgument("category", String.valueOf(plate.getCategory().getId()));
        cr.addArgument("name", plate.getName());
        cr.addArgument("description", plate.getDescription());
        cr.addArgument("quantity", String.valueOf(plate.getQuantity()));
        cr.addArgument("price", String.valueOf(plate.getPrice()));


        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int plateId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/plate/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(plateId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
