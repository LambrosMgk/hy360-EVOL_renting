package frontEnd;

import javax.swing.*;

import classes.RentalForm;
import database.EditBicycle;
import database.EditCar;
import database.EditMotorcycle;
import database.EditRentalForm;
import database.EditScooter;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportAccidentWindow {
	
    private static String enteredVehicleId = "";
    private static String licensePlateRegex = "[A-Za-z]{3}\\d{4}";
	private static String integerRegex = "^\\d+$"; // ^: Asserts the start of the string. , \\d+: Matches one or more digits (0-9)., $: Asserts the end of the string.
	
    
    public static void openReportAccidentWindow() 
    {
        JFrame reportAccidentWindow = new JFrame("Report Accident");
        reportAccidentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reportAccidentWindow.setSize(400, 300);
        reportAccidentWindow.setLocationRelativeTo(null);

        JPanel accidentPanel = new JPanel();
        accidentPanel.setLayout(new BoxLayout(accidentPanel, BoxLayout.Y_AXIS));

        // Radio buttons for accident type
        JRadioButton accidentRadioButton = new JRadioButton("Accident");
        accidentRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        JRadioButton machineFaultRadioButton = new JRadioButton("BrokenDownVehicle");
        machineFaultRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));

        ButtonGroup accidentButtonGroup = new ButtonGroup();
        accidentButtonGroup.add(accidentRadioButton);
        accidentButtonGroup.add(machineFaultRadioButton);

        // Dropdown for vehicle type
        String[] vehicleTypes = {"Car", "Motorcycle", "Scooter", "Bicycle"};
        JComboBox<String> vehicleTypeDropdown = new JComboBox<>(vehicleTypes);

        // Radio buttons for insurance choice
        ButtonGroup insuranceButtonGroup = new ButtonGroup();
        JRadioButton yesRadioButton = new JRadioButton("Yes");
        yesRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        JRadioButton noRadioButton = new JRadioButton("No");
        noRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));

        insuranceButtonGroup.add(yesRadioButton);
        insuranceButtonGroup.add(noRadioButton);

        JLabel insuranceLabel = new JLabel("Have you paid the insurance money?");
        insuranceLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        accidentPanel.add(accidentRadioButton);
        accidentPanel.add(machineFaultRadioButton);
        accidentPanel.add(new JLabel("Vehicle Type:"));
        accidentPanel.add(vehicleTypeDropdown);

        accidentPanel.add(insuranceLabel);
        accidentPanel.add(yesRadioButton);
        accidentPanel.add(noRadioButton);

        JLabel reservationIdLabel = new JLabel("Enter Vehicle ID:");
        reservationIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField reservationIdTextField = new JTextField(10);

        accidentPanel.add(reservationIdLabel);
        accidentPanel.add(reservationIdTextField);

        JButton submitButton = new JButton("Submit Report");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 16));

        submitButton.addActionListener(e -> HandleSubmit(reportAccidentWindow, reservationIdTextField, vehicleTypeDropdown,
        		accidentRadioButton, yesRadioButton, noRadioButton, machineFaultRadioButton));

        // Add item listener to enable/disable insurance components based on accident selection
        ItemListener insuranceItemListener = new ItemListener() 
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                boolean selected = accidentRadioButton.isSelected() && e.getStateChange() == ItemEvent.SELECTED;
                insuranceLabel.setEnabled(selected);
                yesRadioButton.setEnabled(selected);
                noRadioButton.setEnabled(selected);
            }
        };

        accidentRadioButton.addItemListener(insuranceItemListener);
        machineFaultRadioButton.addItemListener(insuranceItemListener);

        accidentPanel.add(submitButton);

        reportAccidentWindow.add(accidentPanel);
        reportAccidentWindow.setVisible(true);
    }

    private static void displayMessage(String message) 
    {
        JOptionPane.showMessageDialog(null, message);
    }

    private static void HandleSubmit(JFrame reportAccidentWindow, JTextField reservationIdTextField, JComboBox<String> vehicleTypeDropdown,
    		JRadioButton accidentRadioButton, JRadioButton yesRadioButton, JRadioButton noRadioButton, JRadioButton machineFaultRadioButton)
    {
        enteredVehicleId = reservationIdTextField.getText();
        String vehicleType = (String) vehicleTypeDropdown.getSelectedItem();
        
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Vehicle ID: " + enteredVehicleId);
        
        // Integrity check for vehicle_id, car,motorcycle : "XXX1234", bicycle,scooter : integer
        // Create a Pattern object
        Pattern licensePattern = Pattern.compile(licensePlateRegex);
        Pattern integerPattern = Pattern.compile(integerRegex);
        // Create a Matcher object
        Matcher licenseMatcher = licensePattern.matcher(enteredVehicleId);
        Matcher integerMatcher = integerPattern.matcher(enteredVehicleId);

        // Check if the input matches the pattern
        if (!licenseMatcher.matches() && (vehicleType.equals("Car") || vehicleType.equals("Motorcycle"))) 
        {
        	JOptionPane.showMessageDialog(reportAccidentWindow, "Wrong vehicle id format", "Reservation Error",
                    JOptionPane.ERROR_MESSAGE);
			return;
        }
        else if (!integerMatcher.matches() && (vehicleType.equals("Bicycle") || vehicleType.equals("Scooter")))	// Check for bicycle and scooter
        {
        	JOptionPane.showMessageDialog(reportAccidentWindow, "Wrong vehicle id format, not a positive integer.", "Reservation Error",
                    JOptionPane.ERROR_MESSAGE);
			return;
        }
        
        if (accidentRadioButton.isSelected()) 
        {
            if (yesRadioButton.isSelected()) 
            {
                String reservationId = reservationIdTextField.getText();
                if (!reservationId.isEmpty()) 
                {
                    displayMessage("The car will be changed without extra cost.\nReservation ID: " + reservationId);
                    System.out.println("Removing vehicle id " + enteredVehicleId);
                    removeVehicle(vehicleType, enteredVehicleId);
                } 
                else
                {
                    displayMessage("Please enter a valid Reservation ID.");
                }
            } 
            else if (noRadioButton.isSelected()) 
            {
            	try 
            	{
					RentalForm rentalForm = EditRentalForm.getRentalForm(enteredVehicleId);
					if(rentalForm == null)
					{
						JOptionPane.showMessageDialog(reportAccidentWindow, "There is no rental form with vehicle id: " + enteredVehicleId, "Error",
			                    JOptionPane.ERROR_MESSAGE);
						return;
					}
					rentalForm.setTotal_cost(rentalForm.getTotal_cost()* 3);
					EditRentalForm.updateRentalForm(rentalForm);
					displayMessage("You have been charged 3 times the cost for not paying the insurance cost.");
				} 
            	catch (ClassNotFoundException | SQLException e) 
            	{
					e.printStackTrace();
					return;
				}
            } 
            else 
            {
                displayMessage("Please select whether you have paid the insurance money.");
            }
        } 
        else if (machineFaultRadioButton.isSelected()) 
        {
            displayMessage("MachineFault selected. Will remove vehicle with id " + enteredVehicleId);
            removeVehicle(vehicleType, enteredVehicleId);
            // Additional logic for MachineFault case
            // ...
        } 
        else 
        {
            displayMessage("Please select Accident or MachineFault.");
        }

        // Close the window after submission
        reportAccidentWindow.dispose();
    }
    
    private static void removeVehicle(String vehicleType, String enteredVehicleId)
    {
    	try 
        {
        	switch(vehicleType)
            {
            case "Car":
            	EditCar.deleteCar(enteredVehicleId);
            	break;
            case "Motorcycle":
            	EditMotorcycle.deleteMotorcycle(enteredVehicleId);
            	break;
            case "Scooter":
            	EditScooter.deleteScooter(enteredVehicleId);
            	break;
            case "Bicycle":
            	EditBicycle.deleteBicycle(enteredVehicleId);
            	break;
            }
		} 
        catch (ClassNotFoundException | SQLException e) 
        {
			e.printStackTrace();
		}
    }
}

