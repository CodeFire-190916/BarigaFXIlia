package com.ilia.barigafx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.io.IOUtils;
import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static final String API_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";


    @FXML
    private Button btnBuy;
    @FXML
    private Button btnSale;
    @FXML
    private TextField resultField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField margField;
    @FXML
    private ComboBox comboBox;

    private List<Currency> currencies;

    private double a;
    private double b;

    @FXML
    private void initialize() {
        currencies = new ArrayList<>();

        try {
            String resp_json = IOUtils.toString(new URL(API_URL), "UTF-8");
            JsonArray data = (JsonArray) Jsoner.deserialize(resp_json);

            for (Object obj : data) {
                JsonObject currency = (JsonObject) obj;

                String currencyName = currency.getString("ccy");
                double buy = currency.getDouble("sale");
                double sale = currency.getDouble("buy");


                Currency curren = new Currency(currencyName, buy, sale);

                currencies.add(curren);
            }

        } catch (DeserializationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<String> currItems = FXCollections.observableArrayList();

        for (Currency c : currencies) {
            currItems.add(c.getName());
        }

        comboBox.setItems(currItems);
    }

    public void onBuyClick() {
        double amount = Double.parseDouble(amountField.getText());
        double result = .0;
        double percentage = .0;

        Currency currency = currencies.get(comboBox.getSelectionModel().getSelectedIndex());

        double buy = currency.getBuy();

        result = amount * buy * 1.02;

        resultField.setText(Double.toString(result));

        a = 0.02;
        percentage = result * a;
        margField.setText(Double.toString(percentage));
    }

    public void clickOnSale() {
        double amount = Double.parseDouble(amountField.getText());
        double result = .0;
        double percentage = .0;

        Currency currency = currencies.get(comboBox.getSelectionModel().getSelectedIndex());

        double sale = currency.getSale();

        result = amount * sale * 1.015;
        resultField.setText(Double.toString(result));

        b = 0.015;
        percentage = result * b;
        margField.setText(Double.toString(percentage));
    }
}
