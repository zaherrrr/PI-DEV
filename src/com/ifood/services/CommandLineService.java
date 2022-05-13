package com.ifood.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ifood.entities.CommandLine;
import com.ifood.entities.Commande;
import com.ifood.entities.Plate;
import com.ifood.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandLineService {

    public static CommandLineService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<CommandLine> listCommandLines;


    private CommandLineService() {
        cr = new ConnectionRequest();
    }

    public static CommandLineService getInstance() {
        if (instance == null) {
            instance = new CommandLineService();
        }
        return instance;
    }

    public ArrayList<CommandLine> getAll() {
        listCommandLines = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/commandLine");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listCommandLines = getList();
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

        return listCommandLines;
    }

    private ArrayList<CommandLine> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                CommandLine commandLine = new CommandLine(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        makePlate((Map<String, Object>) obj.get("plate")),
                        makeCommand((Map<String, Object>) obj.get("command")),
                        (int) Float.parseFloat(obj.get("quantity").toString())

                );

                listCommandLines.add(commandLine);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listCommandLines;
    }

    public Plate makePlate(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Plate plate = new Plate();
        plate.setId((int) Float.parseFloat(obj.get("id").toString()));
        plate.setName((String) obj.get("name"));
        return plate;
    }

    public Commande makeCommand(Map<String, Object> obj) throws ParseException {
        if (obj == null) {
            return null;
        }

        return new Commande(
                (int) Float.parseFloat(obj.get("id").toString()),
                null,
                new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("dateCommand")),
                (int) Float.parseFloat(obj.get("status").toString())

        );
    }

    public int add(CommandLine commandLine) {
        return manage(commandLine, false);
    }

    public int edit(CommandLine commandLine) {
        return manage(commandLine, true);
    }

    public int manage(CommandLine commandLine, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/commandLine/edit");
            cr.addArgument("id", String.valueOf(commandLine.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/commandLine/add");
        }

        cr.addArgument("plate", String.valueOf(commandLine.getPlate().getId()));
        cr.addArgument("command", String.valueOf(commandLine.getCommand().getId()));
        cr.addArgument("quantity", String.valueOf(commandLine.getQuantity()));


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

    public int delete(int commandLineId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/commandLine/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(commandLineId));

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
