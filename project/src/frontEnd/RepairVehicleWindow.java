package frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepairVehicleWindow {
    private static JFrame frame;
    private static JTextField vehicleIdTextField;

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	createRepairVehicleWindow();
            }
        });
    }*/
    
    public static void createRepairVehicleWindow() 
    {
        frame = new JFrame("Repair Vehicle");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Drop down for the "Type" field
        frame.add(new JLabel("Vehicle type:"), gbc);
        gbc.gridx = 1;
        String[] vehicleTypes = {"Car", "Motorcycle", "Bicycle", "Scooter"};
        JComboBox<String> typeComboBox = new JComboBox<>(vehicleTypes);
        
        frame.add(typeComboBox, gbc);

        // Vehicle ID
        addLabelAndTextField("Vehicle ID:", gbc);
        vehicleIdTextField = new JTextField(15);
        frame.add(vehicleIdTextField, gbc);

        gbc.gridy++;
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit(typeComboBox.getSelectedItem());
            }
        });
        frame.add(submitButton, gbc);

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static void addLabelAndTextField(String label, GridBagConstraints gbc) 
    {
        gbc.gridy++;
        gbc.gridx = 0;
        frame.add(new JLabel(label), gbc);
        gbc.gridx = 1;
    }
    
    private static void handleSubmit(Object selectedType) 
    {
        // Retrieve values from text fields and use them as needed
        String type = (String) selectedType;
        String vehicleId = vehicleIdTextField.getText();

        // Now you can use these values as needed.
        System.out.println("Type: " + type);
        System.out.println("Vehicle ID: " + vehicleId);

        JOptionPane.showMessageDialog(frame, "Vehicle repaired!", "Repair success",
                JOptionPane.INFORMATION_MESSAGE);

        // Close the windows
        frame.dispose();
    }
}
