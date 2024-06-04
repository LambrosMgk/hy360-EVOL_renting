package frontEnd;

import javax.swing.*;

import classes.Bicycle;
import classes.Car;
import classes.Motorcycle;
import classes.RentalForm;
import classes.Scooter;
import database.EditBicycle;
import database.EditCar;
import database.EditMotorcycle;
import database.EditRentalForm;
import database.EditScooter;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnVehicleWindow {
	
	/*public static void main(String[] args) {
    	createReturnVehicleWindow();
	}*/
	
	public static void createReturnVehicleWindow() 
	{
        JFrame returnVehicleFrame = new JFrame("Return Car Details");
        returnVehicleFrame.setSize(400, 300);
        returnVehicleFrame.setLocationRelativeTo(null);
        returnVehicleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel returnVehiclePanel = new JPanel();
        returnVehiclePanel.setLayout(new BoxLayout(returnVehiclePanel, BoxLayout.Y_AXIS));

        JTextField customerName = CarRentalApp.createLabeledTextField(returnVehiclePanel,"Customer Name:");
        JTextField customerSurname = CarRentalApp.createLabeledTextField(returnVehiclePanel,"Customer Surname:");
        JTextField customerLicenseId = CarRentalApp.createLabeledTextField(returnVehiclePanel,"Customer license id:");
        
        // Assume user is logged in
        customerName.setEditable(false);
        customerName.setText(mainBack.user.getFname());
        
        customerSurname.setEditable(false);
        customerSurname.setText(mainBack.user.getLname());
        
        customerLicenseId.setEditable(false);
        customerLicenseId.setText(String.valueOf(mainBack.user.getLicence_id()));
        
        
        JComboBox<String>[] returnDateComboBoxes = CarRentalApp.addDatePicker(returnVehiclePanel, "Return Date:");
        CarRentalApp.createLabeledTextField(returnVehiclePanel,"Vehicle id:");
        

        JButton submitButton = new JButton("Submit Return");
        submitButton.addActionListener(e -> handleSubmission(returnVehiclePanel, returnVehicleFrame, returnDateComboBoxes));

        returnVehiclePanel.add(submitButton);

        returnVehicleFrame.add(returnVehiclePanel);
        returnVehicleFrame.setVisible(true);
    }
	
	// Handle submission logic here
	private static void handleSubmission(JPanel returnPanel, JFrame returnVehicleFrame, JComboBox<String>[] returnDateBoxes)
	{
        Date returnDate = CarRentalApp.getDateFromComboBoxes(returnDateBoxes[0], returnDateBoxes[1], returnDateBoxes[2]);
        String vehicle_id = CarRentalApp.getTextFromLabeledTextField(returnPanel, "Vehicle id:");
        
        try 
        {
			RentalForm rentalForm = EditRentalForm.getRentalForm(vehicle_id);
			if(rentalForm == null)
			{
				System.out.println("Could not find rental form for vehicle id : " + vehicle_id);
				JOptionPane.showMessageDialog(returnVehicleFrame, "Could not find rental form for vehicle id: " + vehicle_id, "Submit error.",
                        JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String vehicle_type = rentalForm.getVehicle_type();
			float extra_cost = 0;
			
			if(rentalForm.getRent_duration().compareTo(returnDate) <= 0) // A value less than 0 if this Date is before the Date argument
			{
				// Convert java.sql.Date to java.time.LocalDate
		        LocalDate localDate1 = rentalForm.getRent_duration().toLocalDate();
		        LocalDate localDate2 = returnDate.toLocalDate();

		        // Calculate the difference in days
		        long daysDifference = ChronoUnit.DAYS.between(localDate1, localDate2);
		        
		        switch(vehicle_type)
		        {
		        case "Car":
		    		Car car = EditCar.getCar(vehicle_id);
		    		extra_cost = car.getRent_cost() * daysDifference;
		    		break;
		    	case "Motorcycle":
		    		Motorcycle motorcycle = EditMotorcycle.getMotorcycle(vehicle_id);
		    		extra_cost = motorcycle.getRent_cost() * daysDifference;
		    		break;
		    	case "Scooter":
		    		Scooter scooter = EditScooter.getScooter(vehicle_id);
		    		extra_cost = scooter.getRent_cost() * daysDifference;
		    		break;
		    	case "Bicycle":
		    		Bicycle bicycle = EditBicycle.getBicycle(vehicle_id);
		    		extra_cost = bicycle.getRent_cost() * daysDifference;
		    		break;
		        }

				JOptionPane.showMessageDialog(returnVehicleFrame, "Vehicle return is late by " + daysDifference + " days"
						+ " and you have an extra cost of " + extra_cost + "!", "You are late!",
                        JOptionPane.ERROR_MESSAGE);
			}
			
			
			EditRentalForm.removeRentalForm(rentalForm.getForm_id());	// Remove rental form from database
			JOptionPane.showMessageDialog(returnVehicleFrame, "Thanks for choosing EVOL services!", "Reservation ended.",
                    JOptionPane.INFORMATION_MESSAGE);
		} 
        catch (ClassNotFoundException e)
        {
			e.printStackTrace();
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        // Close the window after submission
        returnVehicleFrame.dispose();
    }

}
