package com.ifood.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ifood.entities.Commande;
import com.ifood.entities.Utilisateur;
import com.ifood.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandService {

    public static CommandService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Commande> listCommands;


    private CommandService() {
        cr = new ConnectionRequest();
    }

    public static CommandService getInstance() {
        if (instance == null) {
            instance = new CommandService();
        }
        return instance;
    }

    public ArrayList<Commande> getAll() {
        listCommands = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/command");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listCommands = getList();
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

        return listCommands;
    }

    private ArrayList<Commande> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Commande command = new Commande(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        makeUtilisateur((Map<String, Object>) obj.get("utilisateur")),
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("dateCommand")),
                        (int) Float.parseFloat(obj.get("status").toString())

                );

                listCommands.add(command);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listCommands;
    }

    public Utilisateur makeUtilisateur(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId((int) Float.parseFloat(obj.get("id").toString()));
        utilisateur.setEmail((String) obj.get("email"));
        return utilisateur;
    }

    public int add(Commande command) {
        return manage(command, false);
    }

    public int edit(Commande command) {
        return manage(command, true);
    }

    public int manage(Commande command, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/command/edit");
            cr.addArgument("id", String.valueOf(command.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/command/add");
        }

        cr.addArgument("utilisateur", String.valueOf(command.getUtilisateur().getId()));
        cr.addArgument("dateCommand", new SimpleDateFormat("dd-MM-yyyy").format(command.getDateCommand()));
        cr.addArgument("status", String.valueOf(command.getStatus()));


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

    public int delete(int commandId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/command/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(commandId));

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
