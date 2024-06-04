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

import java.sql.SQLException;

import java.util.ArrayList;

public class AvailableVehiclesWindow {

    /*public static void main(String[] args) {
        createVehiclesWindow();
    }*/

    public static void createVehiclesWindow() 
    {
        JFrame availablevehiclesFrame = new JFrame("Available Vehicles");
        availablevehiclesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        availablevehiclesFrame.setSize(500, 400);
        availablevehiclesFrame.setLayout(new GridLayout(2, 2));

        // Example image paths, replace with your actual image paths
        String[] imagePaths = {
                "src/media/car.jpg",
                "src/media/motorcycle.jpg",
                "src/media/scooter.jpg",
                "src/media/bicycle.jpg"
        };

        
        for (int i = 0; i < 4; i++) 
        {
            JButton button = new JButton(new ImageIcon(imagePaths[i]));
            scaleImageIcon(button, imagePaths[i], 200, 150); 		// Set the desired size (e.g., 100x100)
            button.addActionListener(new ButtonClickListener(i)); 	// Each button corresponds to a number
            availablevehiclesFrame.add(button);
        }
        
        availablevehiclesFrame.setLocationRelativeTo(null);
        availablevehiclesFrame.setVisible(true);
    }

    private static class ButtonClickListener implements ActionListener 
    {
    	private final int buttonNumber;
    	
    	ButtonClickListener(int index)
    	{
			this.buttonNumber = index;
    		
    	}
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            switch(this.buttonNumber)
            {
            case 0:
            	createDynamicButtonWindow("Car");
            	break;
            case 1:
            	createDynamicButtonWindow("Motorcycle");
            	break;
            case 2:
            	createDynamicButtonWindow("Scooter");
            	break;
            case 3:
            	createDynamicButtonWindow("Bicycle");
            	break;
            }
        }
    }
    
    private static void createDynamicButtonWindow(String vehicle_type) 
    {
        JFrame dynamicButtonWindow = new JFrame(vehicle_type + " list");
        dynamicButtonWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dynamicButtonWindow.setSize(400, 300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3));
        
        
        switch(vehicle_type)
        {
        case "Car":
        	try 
        	{
				ArrayList<Car> cars = EditCar.getAllCars();
				for(Car x : cars)
				{
					JButton vehicleButton = new JButton();
					 String buttonText = "<html>Brand: " + x.getBrand() +
                             "<br>Model: " + x.getModel() +
                             "<br>ID: " + x.getVehicle_id() + "</html>";
					vehicleButton.setText(buttonText);
					vehicleButton.setEnabled(false);	// Disable the button
		            buttonPanel.add(vehicleButton);
				}
			} 
        	catch (ClassNotFoundException | SQLException e) 
        	{
				e.printStackTrace();
			}
        	break;
        case "Motorcycle":
        	try 
        	{
				ArrayList<Motorcycle> motorcycles = EditMotorcycle.getAllMotorcycles();
				for(Motorcycle x : motorcycles)
				{
					JButton vehicleButton = new JButton();
					 String buttonText = "<html>Brand: " + x.getBrand() +
                             "<br>Model: " + x.getModel() +
                             "<br>ID: " + x.getVehicle_id() + "</html>";
					vehicleButton.setText(buttonText);
					vehicleButton.setEnabled(false);	// Disable the button
		            buttonPanel.add(vehicleButton);
				}
			} 
        	catch (ClassNotFoundException | SQLException e) 
        	{
				e.printStackTrace();
			}
        	break;
        case "Scooter":
        	try 
        	{
				ArrayList<Scooter> scooters = EditScooter.getAllScooters();
				for(Scooter x : scooters)
				{
					JButton vehicleButton = new JButton();
					 String buttonText = "<html>Brand: " + x.getBrand() +
                             "<br>Model: " + x.getModel() +
                             "<br>ID: " + x.getVehicle_id() + "</html>";
					vehicleButton.setText(buttonText);
					vehicleButton.setEnabled(false);	// Disable the button
		            buttonPanel.add(vehicleButton);
				}
			} 
        	catch (ClassNotFoundException | SQLException e) 
        	{
				e.printStackTrace();
			}
        	break;
        case "Bicycle":
        	try 
        	{
				ArrayList<Bicycle> bicycles = EditBicycle.getAllBicycles();
				for(Bicycle x : bicycles)
				{
					JButton vehicleButton = new JButton();
					 String buttonText = "<html>Brand: " + x.getBrand() +
                             "<br>Model: " + x.getModel() +
                             "<br>ID: " + x.getVehicle_id() + "</html>";
					vehicleButton.setText(buttonText);
					vehicleButton.setEnabled(false);	// Disable the button
		            buttonPanel.add(vehicleButton);
				}
			} 
        	catch (ClassNotFoundException | SQLException e) 
        	{
				e.printStackTrace();
			}
        	break;
        }

        
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        dynamicButtonWindow.add(scrollPane);

        dynamicButtonWindow.setLocationRelativeTo(null);
        dynamicButtonWindow.setVisible(true);
    }
    
    private static void scaleImageIcon(JButton button, String imagePath, int width, int height) 
    {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
    }
}
