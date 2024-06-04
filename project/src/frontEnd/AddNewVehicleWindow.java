package frontEnd;

import javax.swing.*;

import classes.Bicycle;
import classes.Car;
import classes.Motorcycle;
import classes.Scooter;
import database.EditBicycle;
import database.EditCar;
import database.EditMotorcycle;
import database.EditScooter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewVehicleWindow 
{
    private static JFrame newVehicleframe;
    private static JTextField vehicleIdTextField, rentCostTextField, vehicleRangeTextField,
            insuranceCostTextField, colorTextField, brandTextField, modelTextField,
            passengerNumberTextField, carTypeTextField;
    
    private static String licensePlateRegex = "[A-Za-z]{3}\\d{4}";
	private static String integerRegex = "^\\d+$"; // ^: Asserts the start of the string. , \\d+: Matches one or more digits (0-9)., $: Asserts the end of the string.
	

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	createNewVehicleWindow();
            }
        });
    }*/
    
    public static void createNewVehicleWindow() 
    {
        newVehicleframe = new JFrame("Add New Vehicle");
        newVehicleframe.setLayout(new GridBagLayout());
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Dropdown for the "Type" field
        newVehicleframe.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        String[] vehicleTypes = {"Car", "Motorcycle", "Bicycle", "Scooter"};
        JComboBox<String> typeComboBox = new JComboBox<>(vehicleTypes);
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTypeSelection(typeComboBox);
            }
        });
        newVehicleframe.add(typeComboBox, gbc);

        // Vehicle ID
        addLabelAndTextField("Vehicle ID:", gbc);
        vehicleIdTextField = new JTextField(15);
        newVehicleframe.add(vehicleIdTextField, gbc);

        // Rent Cost
        addLabelAndTextField("Rent Cost:", gbc);
        rentCostTextField = new JTextField(15);
        newVehicleframe.add(rentCostTextField, gbc);

        // Vehicle Range
        addLabelAndTextField("Vehicle Range:", gbc);
        vehicleRangeTextField = new JTextField(15);
        newVehicleframe.add(vehicleRangeTextField, gbc);

        // Insurance Cost
        addLabelAndTextField("Insurance Cost:", gbc);
        insuranceCostTextField = new JTextField(15);
        newVehicleframe.add(insuranceCostTextField, gbc);

        // Color
        addLabelAndTextField("Color:", gbc);
        colorTextField = new JTextField(15);
        newVehicleframe.add(colorTextField, gbc);

        // Brand
        addLabelAndTextField("Brand:", gbc);
        brandTextField = new JTextField(15);
        newVehicleframe.add(brandTextField, gbc);

        // Model
        addLabelAndTextField("Model:", gbc);
        modelTextField = new JTextField(15);
        newVehicleframe.add(modelTextField, gbc);

        // Additional fields for cars
        addLabelAndTextField("Passenger Number:", gbc);
        passengerNumberTextField = new JTextField(15);
        newVehicleframe.add(passengerNumberTextField, gbc);

        addLabelAndTextField("Car Type:", gbc);
        carTypeTextField = new JTextField(15);
        newVehicleframe.add(carTypeTextField, gbc);

        gbc.gridy++;
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit(typeComboBox.getSelectedItem());
            }
        });
        newVehicleframe.add(submitButton, gbc);

        newVehicleframe.setSize(400, 400);
        newVehicleframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newVehicleframe.setLocationRelativeTo(null);
        newVehicleframe.setVisible(true);
    }

    private static void handleTypeSelection(JComboBox<String> typeComboBox) 
    {
        // Enable or disable additional fields based on the selected type
        String selectedType = (String) typeComboBox.getSelectedItem();
        boolean isCar = selectedType.equals("Car");
        passengerNumberTextField.setEnabled(isCar);
        carTypeTextField.setEnabled(isCar);
    }
    private static void addLabelAndTextField(String label, GridBagConstraints gbc) 
    {
        gbc.gridy++;
        gbc.gridx = 0;
        newVehicleframe.add(new JLabel(label), gbc);
        gbc.gridx = 1;
    }
    private static void handleSubmit(Object selectedType) 
    {
        // Retrieve values from text fields and use them as needed
        String vehicleType = (String) selectedType;
        String vehicleId = vehicleIdTextField.getText();
        float rentCost = Float.parseFloat(rentCostTextField.getText());
        float vehicleRange = Float.parseFloat(vehicleRangeTextField.getText());
        float insuranceCost = Float.parseFloat(insuranceCostTextField.getText());
        String color = colorTextField.getText();
        String brand = brandTextField.getText();
        String model = modelTextField.getText();
        int rentCounter = 0;
        
     // Integrity check for vehicle_id, car,motorcycle : "XXX1234", bicycle,scooter : integer
        // Create a Pattern object
        Pattern licensePattern = Pattern.compile(licensePlateRegex);
        Pattern integerPattern = Pattern.compile(integerRegex);
        // Create a Matcher object
        Matcher licenseMatcher = licensePattern.matcher(vehicleId);
        Matcher integerMatcher = integerPattern.matcher(vehicleId);

        // Check if the input matches the pattern
        if (!licenseMatcher.matches() && (vehicleType.equals("Car") || vehicleType.equals("Motorcycle"))) 
        {
        	JOptionPane.showMessageDialog(newVehicleframe, "Car or motorcycle id format is XXX1111.", "Reservation Error",
                    JOptionPane.ERROR_MESSAGE);
			return;
        }
        else if (!integerMatcher.matches() && (vehicleType.equals("Bicycle") || vehicleType.equals("Scooter")))	// Check for bicycle and scooter
        {
        	JOptionPane.showMessageDialog(newVehicleframe, "Scooter or bicycle id format is a positive integer.", "Reservation Error",
                    JOptionPane.ERROR_MESSAGE);
			return;
        }
        
        switch(vehicleType)
        {
        case "Car":
        	int passengerNumber = Integer.parseInt(passengerNumberTextField.getText());
            String carType = carTypeTextField.getText();
        	try {
				EditCar.addNewCar(new Car(vehicleType, vehicleId, rentCost, vehicleRange, insuranceCost, color, brand, model,
						rentCounter, passengerNumber, carType));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	break;
        case "Motorcycle":
        	try {
				EditMotorcycle.addNewMotorcycle((new Motorcycle(vehicleType, vehicleId, rentCost, vehicleRange, insuranceCost, color, brand, model,
						rentCounter)));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	break;
        case "Scooter":
        	try {
				EditScooter.addNewScooter(new Scooter(vehicleType, vehicleId, rentCost, vehicleRange, insuranceCost, color, brand, model,
						rentCounter));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	break;
        case "Bicycle":
        	try {
				EditBicycle.addNewBicycle(new Bicycle(vehicleType, vehicleId, rentCost, vehicleRange, insuranceCost, color, brand, model,
						rentCounter));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	break;
        }

        // Additional fields for cars
        if (vehicleType.equals("Car")) 
        {
            int passengerNumber = Integer.parseInt(passengerNumberTextField.getText());
            String carType = carTypeTextField.getText();
            System.out.println("Passenger Number: " + passengerNumber);
            System.out.println("Car Type: " + carType);
            // Use additional car fields as needed...
        }

        // Now you can use these values as needed.
        System.out.println("Type: " + vehicleType);
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Rent Cost: " + rentCost);
        System.out.println("Vehicle Range: " + vehicleRange);
        System.out.println("Insurance Cost: " + insuranceCost);
        System.out.println("Color: " + color);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent Counter: " + rentCounter);

        JOptionPane.showMessageDialog(newVehicleframe, "New vehicle added!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
        
        // Close window after adding the vehicle
        newVehicleframe.dispose();
    }

}
