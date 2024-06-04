package frontEnd;

import javax.swing.*;

import classes.Bicycle;
import classes.Car;
import classes.Motorcycle;
import classes.RentalForm;
import classes.Scooter;
import classes.Vehicle;
import database.EditBicycle;
import database.EditCar;
import database.EditMotorcycle;
import database.EditRentalForm;
import database.EditScooter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerSupportWindow 
{
	
    public static void openCustomerSupportWindow() 
    {
        JFrame customerSupportWindow = new JFrame("Customer Support");
        customerSupportWindow.setSize(600, 300);
        customerSupportWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new GridLayout(0, 1));

        
        // Add buttons for predefined questions
        String[] predefinedQuestions = {
                "Status of available or rented vehicles by category",
                "Rental status by time period",
                "Maximum, minimum, and average rental duration by vehicle category",
                "Revenue from rentals by time period and vehicle category",
                "Total maintenance and repair expenses by time period",
                "Most popular vehicle by category"
        };
        for (String question : predefinedQuestions) 
        {
            JButton questionButton = new JButton(question);
            PredefinedQueryListener listener = new PredefinedQueryListener(question);
            questionButton.addActionListener(listener);
            queryPanel.add(questionButton);
        }
        // Add space for additional customer questions
        JTextArea customerQuestionArea = new JTextArea();
        customerQuestionArea.setRows(5);
        customerQuestionArea.setEditable(true);

        
        //	Custom customer questions
        JScrollPane customerQuestionScrollPane = new JScrollPane(customerQuestionArea);
        JLabel customerQuestionLabel = new JLabel("Questions from Customer:");
        JButton submitQuestionButton = new JButton("Submit Question");
        submitQuestionButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String customerQuestion = customerQuestionArea.getText();
                // Perform actions with the customer question, e.g., database storage, processing, etc.
                JOptionPane.showMessageDialog(null, "Customer Question:\n\n" + customerQuestion, "Customer Question", JOptionPane.INFORMATION_MESSAGE);
                // Provide a sample answer for illustration purposes
                String sampleAnswer = "Sample answer to customer question.";
                JOptionPane.showMessageDialog(null, "Sample Answer:\n\n" + sampleAnswer, "Sample Answer", JOptionPane.INFORMATION_MESSAGE);
                // Clear the text area after submission
                customerQuestionArea.setText("");
            }
        });

        mainPanel.add(queryPanel, BorderLayout.WEST);

        
        
        // Create a new JPanel for the customer question section
        JPanel customerQuestionPanel = new JPanel(new BorderLayout());
        customerQuestionPanel.add(customerQuestionLabel, BorderLayout.NORTH);
        customerQuestionPanel.add(customerQuestionScrollPane, BorderLayout.CENTER);
        customerQuestionPanel.add(submitQuestionButton, BorderLayout.SOUTH);

        // Add the customer question panel below the buttons
        mainPanel.add(customerQuestionPanel, BorderLayout.CENTER);

        customerSupportWindow.setContentPane(mainPanel);
        customerSupportWindow.setLocationRelativeTo(null);
        customerSupportWindow.setVisible(true);
    }
    
    private static class PredefinedQueryListener implements ActionListener 
    {
    	private final String query;
    	
    	PredefinedQueryListener(String query)
    	{
			this.query = query;
    	}
    	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			switch (this.query)
	        {
	            case "Status of available or rented vehicles by category":
	            	StatusOfVehicles();
	                break;
	            case "Rental status by time period":
	            	RentalsByTimePeriod();
	                break;
	            case "Maximum, minimum, and average rental duration by vehicle category":
	                break;
	            case "Revenue from rentals by time period and vehicle category":
	                break;
	            case "Total maintenance and repair expenses by time period":
	                break;
	            case "Most popular vehicle by category":
	            	MostPopularVehicles();
	                break;
	        }
		}
    }
    
    private static void StatusOfVehicles()
    {
    	try 
		{			
			JFrame mostRentedWindow = new JFrame("Available and rented vehicles");
	        mostRentedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        mostRentedWindow.setSize(400, 300);
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new GridLayout(2, 2));
	        
	        // Car button
	        JButton carRentButton = new JButton();
			String carButtonText = "<html>Cars: " +
                    "<br>Available: " + EditCar.HowManyAvailable() +
                    "<br>Rented: " + EditCar.HowManyRented() + "</html>";
			carRentButton.setText(carButtonText);
			carRentButton.setEnabled(false);	// Disable the button
			buttonPanel.add(carRentButton);
			
			// Motorcycle button
	        JButton motorcycleRentButton = new JButton();
			String motorcycleButtonText = "<html>Motorcycles: " +
                    "<br>Available: " + EditMotorcycle.HowManyAvailable() +
                    "<br>Rented: " + EditMotorcycle.HowManyRented() + "</html>";
			motorcycleRentButton.setText(motorcycleButtonText);
			motorcycleRentButton.setEnabled(false);	// Disable the button
			buttonPanel.add(motorcycleRentButton);
			
			// Scooter button
	        JButton scooterRentButton = new JButton();
			String scooterButtonText = "<html>Scooters: " +
                    "<br>Available: " + EditScooter.HowManyAvailable() +
                    "<br>Rented: " + EditScooter.HowManyRented() + "</html>";
			scooterRentButton.setText(scooterButtonText);
			scooterRentButton.setEnabled(false);	// Disable the button
			buttonPanel.add(scooterRentButton);
			
			// Bicycle button
	        JButton bicycleRentButton = new JButton();
			String bicycleButtonText = "<html>Bicycles: " +
                    "<br>Available: " + EditBicycle.HowManyAvailable() +
                    "<br>Rented: " + EditBicycle.HowManyRented() + "</html>";
			bicycleRentButton.setText(bicycleButtonText);
			bicycleRentButton.setEnabled(false);	// Disable the button
			buttonPanel.add(bicycleRentButton);
           
			
	        JScrollPane scrollPane = new JScrollPane(buttonPanel);
	        mostRentedWindow.add(scrollPane);

	        mostRentedWindow.setLocationRelativeTo(null);
	        mostRentedWindow.setVisible(true);
		} 
		catch (ClassNotFoundException | SQLException e1) 
		{
			e1.printStackTrace();
		}
    }
    
    private static void RentalsByTimePeriod()
    {
    	try 
    	{
			ArrayList<RentalForm> rentalForms = EditRentalForm.getRentalFormsAscending();
			
			
			JFrame mostRentedWindow = new JFrame("Rental forms by time period");
	        mostRentedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        mostRentedWindow.setSize(400, 300);
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new GridLayout(0, 1));
	        
	        System.out.println("Printing all rental forms by time period");
			for(RentalForm x : rentalForms)
			{
				JButton vehicleButton = new JButton();
				 String buttonText = "<html>Date: " + x.getRental_date() +
						 "<br>rental form id: " + x.getForm_id() +
                        "<br>ID: " + x.getVehicle_id() +
                        "</html>";
				vehicleButton.setText(buttonText);
				vehicleButton.setEnabled(false);	// Disable the button
	            buttonPanel.add(vehicleButton);
				System.out.println("Date: " + x.getRental_date() + ", rental form id : " + x.getForm_id() + ", vehicle id: " + x.getVehicle_id());
			}
	        
	        JScrollPane scrollPane = new JScrollPane(buttonPanel);
	        mostRentedWindow.add(scrollPane);

	        mostRentedWindow.setLocationRelativeTo(null);
	        mostRentedWindow.setVisible(true);
		} 
    	catch (ClassNotFoundException | SQLException e) 
    	{
			e.printStackTrace();
		}
    }
    
    private static void MostPopularVehicles()
	{
		try 
		{
			Car car = EditCar.getMostRented();
			Motorcycle motorcycle = EditMotorcycle.getMostRented();
			Scooter scooter = EditScooter.getMostRented();
			Bicycle bicycle = EditBicycle.getMostRented();
			
			ArrayList<Vehicle> vehicles = new ArrayList<>();
			vehicles.add(car);
			vehicles.add(motorcycle);
			vehicles.add(scooter);
			vehicles.add(bicycle);
			
			JFrame mostRentedWindow = new JFrame("Most rented");
	        mostRentedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        mostRentedWindow.setSize(400, 300);
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new GridLayout(2, 2));
	        
	        for(Vehicle x : vehicles)
			{
				JButton vehicleButton = new JButton();
				 String buttonText = "<html>Type: " + x.getType() +
						 "<br>Brand: " + x.getBrand() +
                         "<br>Model: " + x.getModel() +
                         "<br>ID: " + x.getVehicle_id() +
                         "<br>Rent count: " + x.getRent_counter() + "</html>";
				vehicleButton.setText(buttonText);
				vehicleButton.setEnabled(false);	// Disable the button
	            buttonPanel.add(vehicleButton);
			}
	        
	        JScrollPane scrollPane = new JScrollPane(buttonPanel);
	        mostRentedWindow.add(scrollPane);

	        mostRentedWindow.setLocationRelativeTo(null);
	        mostRentedWindow.setVisible(true);
		} 
		catch (ClassNotFoundException | SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
    
}
