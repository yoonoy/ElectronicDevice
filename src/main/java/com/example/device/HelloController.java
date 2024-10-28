package com.example.device;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class HelloController {

    // FXML elements
    @FXML private RadioButton rbSmartphone;
    @FXML private RadioButton rbLaptop;
    @FXML private RadioButton rbTablet;
    @FXML private ToggleGroup device;
    @FXML private TextField textName;
    @FXML private TextField textPrice;
    @FXML private TextField textWeight;
    @FXML private ListView<Device> listView;
    @FXML private Label label;

    private ObservableList<Device> deviceList = FXCollections.observableArrayList();

    // Initialize method
    @FXML
    public void initialize() {
        listView.setItems(deviceList);
        label.setText("Select a device to add or remove");
    }

    // Event handler for Add button
    @FXML
    public void onAddClick() {
        try {
            // Collect common attributes
            String name = textName.getText();
            double price = Double.parseDouble(textPrice.getText());
            double weight = Double.parseDouble(textWeight.getText());

            Device newDevice = null;

            // Check which device type is selected and create the appropriate object
            if (rbSmartphone.isSelected()) {
                newDevice = new Smartphone(name, price, weight, 6.5, 12); // Example values for screenSize and cameraResolution
            } else if (rbLaptop.isSelected()) {
                newDevice = new Laptop(name, price, weight, 16, "Intel i7"); // Example values for ramSize and processorType
            } else if (rbTablet.isSelected()) {
                newDevice = new Tablet(name, price, weight, 10.0, true); // Example values for batteryLife and hasStylus
            }

            // Add new device to the list
            if (newDevice != null) {
                deviceList.add(newDevice);
                label.setText(name + " added.");
            }

            // Clear input fields after adding
            textName.clear();
            textPrice.clear();
            textWeight.clear();

        } catch (NumberFormatException e) {
            label.setText("Please enter valid numeric values for price and weight.");
        }
    }

    // Event handler for Remove button
    @FXML
    public void onRemoveClick() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Device removedDevice = deviceList.remove(selectedIndex);
            label.setText(removedDevice.getName() + " removed.");
        } else {
            label.setText("Please select a device to remove.");
        }
    }

    // Event handler for selecting from ListView
    @FXML
    public void onListClicked(MouseEvent event) {
        Device selectedDevice = listView.getSelectionModel().getSelectedItem();
        if (selectedDevice != null) {
            label.setText("Selected: " + selectedDevice);
        }
    }
}
