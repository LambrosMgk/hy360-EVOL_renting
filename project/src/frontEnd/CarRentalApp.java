package frontEnd;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;

public class CarRentalApp {
	
	public static JTextField loggedUserTextField;

	private static ActionListener listener;
	
    private static JButton customerLoginButton;
    private static JButton customerRegistrationButton;
    private static JButton newReservationButton;
    private static JButton upcomingModelsButton;
    private static JButton addNewVehicleButton;
    private static JButton availableVehiclesButton;
    private static JButton returnVehicleButton;
    private static JButton reportAccidentButton;
    private static JButton repairVehicleButton;
    private static JButton customerSupportButton;
    private static JButton LogoutButton;

    
    /*public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new CarRentalApp());
	}*/
    
    
    public static void StartCarRentalApp() 
    {
        JFrame appFrame = new JFrame("Car Rental Management System");
        appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        appFrame.setLayout(new BorderLayout());

        // Create buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(11, 1));

        listener = new ButtonClickListener();
        customerLoginButton = createButton("Customer Login", listener);
        customerRegistrationButton = createButton("Customer Registration", listener);
        newReservationButton = createButton("New Reservation", listener);
        upcomingModelsButton = createButton("Upcoming Models", listener);
        addNewVehicleButton = createButton("Add New Vehicle", listener);
        availableVehiclesButton = createButton("Available Vehicles", listener);
        returnVehicleButton = createButton("Return Vehicle", listener);
        reportAccidentButton = createButton("Report Accident", listener);
        repairVehicleButton = createButton("Repair Vehicle", listener);
        customerSupportButton = createButton("Customer Support", listener);
        LogoutButton = createButton("Logout", listener);

        buttonsPanel.add(customerLoginButton);
        buttonsPanel.add(customerRegistrationButton);
        buttonsPanel.add(newReservationButton);
        buttonsPanel.add(upcomingModelsButton);
        buttonsPanel.add(addNewVehicleButton);
        buttonsPanel.add(availableVehiclesButton);
        buttonsPanel.add(returnVehicleButton);
        buttonsPanel.add(reportAccidentButton);
        buttonsPanel.add(repairVehicleButton);
        buttonsPanel.add(customerSupportButton);
        buttonsPanel.add(LogoutButton);

        appFrame.add(buttonsPanel, BorderLayout.WEST);

        // Add an image to the free space
        ImageIcon carImage = new ImageIcon("src/media/car.jpg");  // Replace "car_image.jpg" with your image file path
        JLabel imageLabel = new JLabel(carImage);
        appFrame.add(imageLabel, BorderLayout.CENTER);

        // Add an editable JTextField to the bottom right corner
        JPanel bottomPanel = new JPanel(new BorderLayout());
        loggedUserTextField = new JTextField(25); // Adjust the size as needed
        loggedUserTextField.setEditable(false);
        bottomPanel.add(loggedUserTextField, BorderLayout.LINE_END);
        appFrame.add(bottomPanel, BorderLayout.SOUTH);
        
        appFrame.setSize(730, 500);
        appFrame.setLocationRelativeTo(null);
        appFrame.setVisible(true);
        
        appFrame.addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                // This method will be called just before the window is closed
                // Perform any actions you want here
            	
            	mainBack.latch.countDown();	//Signal back end code that the app is closing
            	
                // Dispose the window
            	appFrame.dispose();
            }
        });
        
    }

    private static JButton createButton(String buttonText, ActionListener listener) 
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(listener);
        return button;
    }


    private static class ButtonClickListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            JButton sourceButton = (JButton) e.getSource();
            String buttonText = sourceButton.getText();
            

            switch (buttonText) 
            {
            	case "Customer Login":
            		LoginWindow.createLogin();
            		break;
                case "Customer Registration":
                	RegistrationWindow.CustomerRegistrationWindow();
                    break;
                case "New Reservation":
                	if (mainBack.user != null) 
                	{                		
                		NewReservationWindow.openNewReservationWindow();
                	}
                	else
                	{
                		System.out.println("User not logged in!");
                        JOptionPane.showMessageDialog(sourceButton.getRootPane().getParent(), "User not logged in!", "Registration Error",
                                JOptionPane.ERROR_MESSAGE);
                	}
                    break;
                case "Upcoming Models":
                	UpcomingVehicleWindow.createMainWindow();
                    break;
                case "Add New Vehicle":
                    if (mainBack.user != null && mainBack.user.getLicence_id() == 1)	// User id 1 will be treated as "Admin" 
                	{               		
                    	AddNewVehicleWindow.createNewVehicleWindow();
                	}
                	else
                	{
                		System.out.println("Admin not logged in!");
                        JOptionPane.showMessageDialog(sourceButton.getRootPane().getParent(), "Admin not logged in!", "Registration Error",
                                JOptionPane.ERROR_MESSAGE);
                	}
                    break;
                case "Available Vehicles":
                	AvailableVehiclesWindow.createVehiclesWindow();
                    break;
                case "Return Vehicle":
                	if (mainBack.user != null) 
                	{               		
                		ReturnVehicleWindow.createReturnVehicleWindow();
                	}
                	else
                	{
                		System.out.println("User not logged in!");
                        JOptionPane.showMessageDialog(sourceButton.getRootPane().getParent(), "User not logged in!", "Registration Error",
                                JOptionPane.ERROR_MESSAGE);
                	}
                    break;
                case "Report Accident":
                	if (mainBack.user != null) 
                	{                		
                		ReportAccidentWindow.openReportAccidentWindow();
                	}
                	else
                	{
                		System.out.println("User not logged in!");
                        JOptionPane.showMessageDialog(sourceButton.getRootPane().getParent(), "User not logged in!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                	}
                    break;
                case "Repair Vehicle":
                    if (mainBack.user != null && mainBack.user.getLicence_id() == 1)	// User id 1 will be treated as "Admin" 
                	{               		
                    	RepairVehicleWindow.createRepairVehicleWindow();
                	}
                	else
                	{
                		System.out.println("Admin not logged in!");
                        JOptionPane.showMessageDialog(sourceButton.getRootPane().getParent(), "Admin not logged in!", "Registration Error",
                                JOptionPane.ERROR_MESSAGE);
                	}
                    break;
                case "Customer Support":
                	CustomerSupportWindow.openCustomerSupportWindow();
                    break;
                case "Logout":
                	if(mainBack.user == null)
                	{
                		System.out.println("User wansn't logged in!");
                        JOptionPane.showMessageDialog(sourceButton.getRootPane().getParent(), "User wasn't logged in!", "Logout?",
                                JOptionPane.QUESTION_MESSAGE);
                	}
                	else
                	{
                		mainBack.user = null;
                		loggedUserTextField.setText("");
                    	System.out.println("User log out!");
                        JOptionPane.showMessageDialog(sourceButton.getRootPane().getParent(), "User logged out!", "Logout Success",
                                JOptionPane.INFORMATION_MESSAGE);
                	}
                	break;
            }
        }     
    }
    
    
    
    
    public static JTextField createLabeledTextField(JPanel panel, String labelText) 
    {
    	
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField(20); // Adjust the size as needed

        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.add(label);
        fieldPanel.add(textField);

        panel.add(fieldPanel);

        return textField;
    }

    public static JPasswordField addPasswordField(JPanel panel, String labelText) 
    {
        JLabel label = new JLabel(labelText);
        JPasswordField passwordField = new JPasswordField(30); // Adjust the size as needed

        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.add(label);
        fieldPanel.add(passwordField);

        panel.add(fieldPanel);

        return passwordField;
    }
    
    public static String getTextFromLabeledTextField(JPanel parentPanel, String labelText) {
        for (Component component : parentPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component innerComponent : panel.getComponents()) {
                    if (innerComponent instanceof JTextField) {
                        JTextField textField = (JTextField) innerComponent;
                        Component labelOrCheckBox = panel.getComponent(0); // Assuming label or checkbox is the first component
                        if (labelOrCheckBox instanceof JLabel) {
                            JLabel label = (JLabel) labelOrCheckBox;
                            if (label.getText().equals(labelText)) {
                                return textField.getText();
                            }
                        } else if (labelOrCheckBox instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) labelOrCheckBox;
                            if (checkBox.getText().equals(labelText)) {
                                return textField.getText();
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    
    public static String getPasswordFromLabeledPasswordField(JPanel parentPanel, String labelText) {
        for (Component component : parentPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component innerComponent : panel.getComponents()) {
                    if (innerComponent instanceof JPasswordField) {
                        JPasswordField passwordField = (JPasswordField) innerComponent;
                        JLabel label = (JLabel) panel.getComponent(0); // Assuming label is the first component
                        if (label.getText().equals(labelText)) {
                            // Password fields have a different method for retrieving text
                            char[] passwordChars = passwordField.getPassword();
                            return new String(passwordChars);
                        }
                    }
                }
            }
        }
        return "";
    }

    /*Returns 0 if the call fails*/
    public static int parseIntegerField(JPanel panel, String label) 
    {
        try 
        {
            String input = CarRentalApp.getTextFromLabeledTextField(panel, label).trim();
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            // Handle the case where the input is not a valid integer
            JOptionPane.showMessageDialog(null, label + " must be a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return 0; // or return a default value, depending on your requirements
        }
    }


    public static Date getDateFromComboBoxes(JComboBox<String> dayComboBox, JComboBox<String> monthComboBox, JComboBox<String> yearComboBox) 
    {
        // Extract selected day, month, and year
        int day = Integer.parseInt((String) dayComboBox.getSelectedItem());
        String month = (String) monthComboBox.getSelectedItem();
        int year = Integer.parseInt((String) yearComboBox.getSelectedItem());

        // Convert month string to a numerical value (assuming January is 1)
        int monthValue;
        switch (month) {
            case "January": monthValue = 1; break;
            case "February": monthValue = 2; break;
            case "March": monthValue = 3; break;
            case "April": monthValue = 4; break;
            case "May": monthValue = 5; break;
            case "June": monthValue = 6; break;
            case "July": monthValue = 7; break;
            case "August": monthValue = 8; break;
            case "September": monthValue = 9; break;
            case "October": monthValue = 10; break;
            case "November": monthValue = 11; break;
            case "December": monthValue = 12; break;
            default: monthValue = 1; // Default to January if month is not recognized
        }

        // Construct a java.sql.Date object
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthValue - 1, day); // Note: month is 0-indexed in Calendar
        java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());

        return sqlDate;
    }

    public static JComboBox<String>[] addDatePicker(JPanel panel, String label_text) 
    {
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());

        // Day ComboBox
        String[] daysArray = new String[31];
        for (int i = 1; i <= 31; i++) 
        {
            daysArray[i - 1] = String.valueOf(i);
        }
        JComboBox<String> dayComboBox = new JComboBox<>(daysArray);

        // Month ComboBox
        String[] monthsArray = new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        JComboBox<String> monthComboBox = new JComboBox<>(monthsArray);

        // Year ComboBox
        String[] yearsArray = new String[100];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 100; i++) 
        {
            yearsArray[i] = String.valueOf(currentYear - i);
        }
        JComboBox<String> yearComboBox = new JComboBox<>(yearsArray);

        datePanel.add(new JLabel(label_text));
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);

        panel.add(datePanel);

        // Return the ComboBoxes for later use
        return new JComboBox[]{dayComboBox, monthComboBox, yearComboBox};
    }

    public static JComboBox<String> addVehicleTypeComboBox(JPanel panel, String label_text)
    {
        JPanel typePanel = new JPanel();

        // Vehicle Type ComboBox
        String[] vehicleTypes = {"Car", "Motorcycle", "Scooter", "Bicycle"};
        JComboBox<String> typeComboBox = new JComboBox<>(vehicleTypes);
        typeComboBox.setName("VehicleTypeComboBox"); // Set a name for easy retrieval

        typePanel.add(new JLabel(label_text));
        typePanel.add(typeComboBox);

        panel.add(typePanel);
        
		return typeComboBox;
    }

}

