package frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class UpcomingVehicleWindow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createMainWindow();
        });
    }

    public static void createMainWindow() {
        JFrame mainWindow = new JFrame("Upcoming Categories");
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setLayout(new GridLayout(4, 1));

        JButton carButton = createVehicleButton("Cars");
        JButton scooterButton = createVehicleButton("Scooters");
        JButton bicycleButton = createVehicleButton("Bicycles");
        JButton motorcycleButton = createVehicleButton("Motorcycles");

        carButton.addActionListener(e -> openCarModelsWindow());
        scooterButton.addActionListener(e -> openScooterModelsWindow());
        bicycleButton.addActionListener(e -> openBicycleModelsWindow());
        motorcycleButton.addActionListener(e -> openMotorcycleModelsWindow());

        mainWindow.add(carButton);
        mainWindow.add(scooterButton);
        mainWindow.add(bicycleButton);
        mainWindow.add(motorcycleButton);

        mainWindow.setSize(300, 400);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    private static JButton createVehicleButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(200, 150)); // Set an initial size

        return button;
    }

    private static void openCarModelsWindow() {
        JFrame upcomingCarsFrame = new JFrame("Upcoming Car Models");
        upcomingCarsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        upcomingCarsFrame.setLayout(new GridLayout(2, 2));

        // Actual image paths
        String[] imagePaths = {
                "src/media/UpcomingCar1.jpg",
                "src/media/UpcomingCar2.jpg",
                "src/media/UpcomingCar3.jpg",
                "src/media/UpcomingCar5.jpg"
        };
        String[] details = {
                "G - Wagon\n\nThe G-Wagon, or Mercedes-Benz G-Class,\n is an iconic luxury SUV.\n\n" +
                        "Renowned for its robust and boxy design,\n combining off-road prowess with luxury.\n\n" +
                        "Offers powerful engine options, advanced technology,\n and a high-end interior.\n\n" +
                        "Features include three locking differentials\n for exceptional off-road capability.\n\n" +
                        "A symbol of status and performance, the G-Wagon is favored\n for its distinctive style and versatility.",
                "BMW i-8\n\nThe BMW i8 is a plug-in hybrid sports car.\n\n" +
                        "It features a futuristic design\n with distinctive scissor doors.\n\n" +
                        "Equipped with a combination of a turbocharged\n three-cylinder engine and electric motor.\n\n" +
                        "Offers impressive acceleration, advanced technology,\n and efficient fuel economy.\n\n" +
                        "Known for its high-performance attributes\n and commitment to sustainable driving\n with a focus on electrification.",
                "Ford Mustang\n\n The Ford Mustang is an iconic American muscle car.\n\n" +
                        "Known for its powerful engine options\n and classic pony car design.\n\n" +
                        "Offers a thrilling driving experience\n with various performance packages.\n\n" +
                        "Features a muscular and aerodynamic exterior design.\n\n" +
                        "Combines heritage with modern technology,\n making it a symbol of American automotive culture.",
                "Supra mk4\n\nThe Toyota Supra MK4 is a legendary\n sports car produced from 1993 to 2002.\n\n" +
                        "Powered by the 2JZ-GTE engine,\n known for its robustness and tuning potential.\n\n" +
                        "Recognized for its sleek and aerodynamic design,\n featuring pop-up headlights.\n\n" +
                        "Achieved fame through its role\n in \"The Fast and the Furious\" movie.\n\n" +
                        "Considered a classic among enthusiasts\n for its performance and timeless styling."
        };
        createVehicleButtons(upcomingCarsFrame, details, imagePaths);

        upcomingCarsFrame.setSize(800, 600);
        upcomingCarsFrame.setLocationRelativeTo(null);
        upcomingCarsFrame.setVisible(true);
    }

    private static void openMotorcycleModelsWindow() {
        JFrame motorcycleModelsFrame = new JFrame("Upcoming Motorcycle Models");
        motorcycleModelsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        motorcycleModelsFrame.setLayout(new GridLayout(2, 2));

        // Actual image paths for motorcycles
        String[] imagePaths = {
                "src/media/UpcomingMoto1.jpg",
                "src/media/UpcomingMoto2.jpg",
                "src/media/UpcomingMoto3.jpg",
                "src/media/UpcomingMoto4.jpg"
        };
        String[] details = {
                "KAWASAKI Ninja ZX-10R (2014-2015)\n\n Kawasaki Ninja ZX-10R (2014-2015) has a 998cc \ninline-four engine with 200 hp.\n\n" +
                        "It features advanced suspension with\n a 43 mm inverted Showa fork at the front.\n\n" +
                        "Braking is handled by dual 310 mm petal discs at the front.\n\n" +
                        "The motorcycle offers optional ABS for enhanced safety.\n\n" +
                        "With a weight of around 198 kg (without ABS),\n it's a high-performance sportbike.",
                "Suzuki Hayabusa 2022\n\nSuzuki Hayabusa 2022 is powered \nby a 1,340cc inline-four engine.\n\n" +
                        "The engine delivers impressive power\n and torque for high-speed performance.\n\n" +
                        "Equipped with advanced electronics,\n including multiple riding modes and quick shifter.\n\n" +
                        "Brembo brakes ensure effective stopping power,\n and it features an anti-lock braking system (ABS).\n\n" +
                        "The iconic Hayabusa design is complemented\n by aerodynamic improvements for stability.",
                "2007 Big Dog Motorcycle\n\nThe 2007 Big Dog Motorcycle is known \nfor its powerful V-twin engine.\n\n" +
                        "It boasts a distinctive and customizable\n chopper-style design.\n\n" +
                        "Features a rigid frame and a \nunique suspension setup for a custom riding experience.\n\n" +
                        "Braking is handled by robust disc\n brakes for effective stopping power.\n\n" +
                        "Known for its presence in the custom motorcycle market,\n offering a unique and personalized riding experience.",
                "suzuki dl 1000 v-strom\n\nThe Suzuki DL1000 V-Strom\n is an adventure-touring motorcycle.\n\n" +
                        "Powered by a 1,037cc V-twin engine,\n it offers a good balance of power and torque.\n\n" +
                        "Equipped with advanced features,\n including traction control and ABS.\n\n" +
                        "Known for its comfortable upright riding position\n and versatile on-road/off-road capabilities.\n\n" +
                        "Popular choice among adventure riders\n for its reliability and long-distance touring capabilities."
        };
        createVehicleButtons(motorcycleModelsFrame, details, imagePaths);

        motorcycleModelsFrame.setSize(800, 600);
        motorcycleModelsFrame.setLocationRelativeTo(null);
        motorcycleModelsFrame.setVisible(true);
    }
    private static void openScooterModelsWindow() {
        JFrame upcomingCarsFrame = new JFrame("Upcoming Scooter Models");
        upcomingCarsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        upcomingCarsFrame.setLayout(new GridLayout(2, 2));

        // Actual image paths
        String[] imagePaths = {
                "src/media/UpcomingScooter1.jpg",
                "src/media/UpcomingScooter2.jpg",
                "src/media/UpcomingScooter3.jpg",
                "src/media/UpcomingScooter4.jpg"
        };
        String[] details = {
                "LaScoota Foldable Adult Scooter\n" +
                        "Also Great as a Scooter for Kids Ages 8-12 & Teenagers 11-15\n" +
                        " Big 200mm Wheels - Kick Scooters With Shock Absorption\n" +
                        "Great Gift - Up to 105kg ",
                "Mongoose Trace Youth/Adult Folding Commuter Kick Scooter,\n" +
                        " Ages 8 Years and Up, Lightweight, Multiple Colours\n " +
                        "Whether it’s cruising down the street, the beach promenade or on the school run,\n" +
                        " do it in style with the Trace 200 Scooter by MONGOOSE\n" +
                        "HEAVY-DUTY construction, LIGHTWEIGHT alloy deck supports riders aged 8+, up to 99kg max weight\n" +
                        "Easy folding kick scooter for compact storage and transport\n" +
                        "Full coverage Max Grip on the 546 x 110mm alloy deck and steel brake for non-slip contact\n" +
                        "Kickstand to park your scooter upright to protect grips and frame from damage",
                "M.Y X-Skate Stunt Scooter - Trick Kick Scooter With ABEC 7 Chrome Bearings\n" +
                        "HIGH SPEC STUNT SCOOTER: The M.Y X-Skate Sunt Scooter is assembled \n" +
                        "to a pro level spec and is made from top of the range parts,\n" +
                        "finished with a premium painted finish.\nPREMIUM and UPGRADED PARTS :" +
                        "The Stunt Scooter features a double layer aluminium deck, aluminium clamp," +
                        "a CNC machined front fork, reinforced steel handlebars and CNC machined aluminium " +
                        "core wheels with super-smooth ABEC 7 Chrome bearings.",
                "TENBOOM Kick Scooter Handbrake for Kids 3 to 12 Years Old \n" +
                        "Foldable Adjustable Handlebars Light Up Wheels Light Weight Kids Scooter"
        };
        createVehicleButtons(upcomingCarsFrame, details, imagePaths);

        upcomingCarsFrame.setSize(800, 600);
        upcomingCarsFrame.setLocationRelativeTo(null);
        upcomingCarsFrame.setVisible(true);
    }
    private static void openBicycleModelsWindow() {
        JFrame upcomingCarsFrame = new JFrame("Upcoming Bicycle Models");
        upcomingCarsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        upcomingCarsFrame.setLayout(new GridLayout(2, 2));

        // Actual image paths
        String[] imagePaths = {
                "src/media/UpcomingBicycle1.jpg",
                "src/media/UpcomingBicycle2.jpg",
                "src/media/UpcomingBicycle3.jpg",
                "src/media/UpcomingBicycle4.jpg"
        };
        String[] details = {
                "Vivi 500W Electric Bike, Adult Electric Bikes 26 Mountain Bike Bicycle\n\n" +
                        "★Strong Power★ This electric bicycle equipped with 500W\n" +
                        "Brushless Motor and 36V/10.5Ah removable battery, brushless motor\n has have a longer" +
                        " life span and faster starting speed.\n Speed up to 20 Mph, recharge mileage up to 35 mile.\n\n" +
                        "★21-Speed Gears★ 3-Speed Chainwheel and 7-Speed Freewheel,3*7=21 speeds\n, multiple selection of"+
                        "speed gears ensure that you ride more smoothly on uphill,\n downhill and flat roads,it can be mountain bikes,\n" +
                        " also suitable for urban cycling and commuting.\n\n★Adjustable Height★ Electric bikes for adults,the handlebar\n"+
                        "and saddle are adjustable,suitable for different heights people.\n Handlebar height:97-104cm. Saddle Height:84-108cm.\n\n" +
                        "★Removable Waterproof Cable Port★ Brake power off cables, meter,\n turn handle connected to the controller by removable cable ports.\n" +
                        "It can make it easier for you to change these accessories,\nNo need to remove the controller, directly replace the accessories.",
                "Schwinn Axum Mountain Bike, Schwinn Axum Mountain Bike, 8 speeds, 29-inch wheels\n\n " +
                        "This bike has 7 speeds. You need a ride that can quench your thirst for\n" +
                        " trail riding that knows no limits. Introducing the Schwinn Boundary,\n " +
                        "a big trail-ready mountain bike that's itching to hit the trails.\n\n" +
                        "The hydroformed aluminum mountain-style frame is light and sturdy,\n" +
                        "and the front suspension fork is built for soaking up huge bumps and rocky paths.\n" +
                        "7-speed trigger shifter with Pro Rush 1x7 wide range drivetrain, with 14-38 freewheel\n " +
                        "and 30t chainring provide superior performance. Reliable front and rear disc brakes\n " +
                        "deliver crisp, all-condition stopping power. Plus, the Boundary comes with a limited\n " +
                        "lifetime warranty for as long as you own the bike. Go tear up the trail with the Boundary.\n\n" +
                        " Enjoy the freedom of riding a Schwinn.",
                "ECOTRIC Fat Tire Electric Bike Beach Snow Bicycle 26″ \n\n" +
                        "4.0 inch Fat Tire ebike 500W 36V/12AH Electric Mountain Bicycle \n" +
                        "with Shimano 7 Speeds Lithium Battery Black/Orange/Blue ",
                "The Schwinn Capitol Women’s Hybrid Bicycle 700c combines the best of a road and mountain bike,\n" +
                        "making it ideal for town, leisure and bike path riding.\n\nThe Schwinn Capitol Women’s Bike\n" +
                        "features an aluminum step-through 16” hybrid frame with an all-new Schwinn comfort fit geometry.\n\n" +
                        "The Schwinn Capitol’s step-through frame design makes it easy to mount \n" +
                        "and dismount the bike’s comfort padded seat, while the seat post and \n" +
                        "handlebar stem can be adjusted for a custom fit to give you the most comfortable riding position.\n\n"+
                        "The Schwinn Capitol Hybrid Bike features swept-back style handlebars which allow \n" +
                        "for a more upright riding position that’s easier on yo+ur back compared \n" +
                        "to traditional road or mountain bicycles.\n "
        };
        createVehicleButtons(upcomingCarsFrame, details, imagePaths);

        upcomingCarsFrame.setSize(800, 600);
        upcomingCarsFrame.setLocationRelativeTo(null);
        upcomingCarsFrame.setVisible(true);
    }
    private static void createVehicleButtons(JFrame frame, String[] details, String[] imagePaths) {
        for (int i = 0; i < 4; i++) {
            JButton vehicleButton = new JButton(new ImageIcon(imagePaths[i]));
            int finalI = i;
            vehicleButton.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    scaleImageIcon(vehicleButton, imagePaths[finalI]);
                }
            });
            vehicleButton.addActionListener(new VehicleButtonClickListener(details[i]));

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(vehicleButton, BorderLayout.CENTER);
            String label = null;
            switch (imagePaths[i]){
                case "src/media/UpcomingCar1.jpg":
                    label = "G-Wagon";
                    break;
                case "src/media/UpcomingCar2.jpg":
                    label = "BMW i-8";
                    break;
                case "src/media/UpcomingCar3.jpg":
                    label = "Ford Mustang";
                    break;
                case "src/media/UpcomingCar5.jpg":
                    label = "Supra mk4";
                    break;
                case "src/media/UpcomingMoto1.jpg":
                    label = "KAWASAKI Ninja ZX-10R (2014-2015)";
                    break;
                case "src/media/UpcomingMoto2.jpg":
                    label = "Suzuki Hayabusa 2022";
                    break;
                case "src/media/UpcomingMoto3.jpg":
                    label = "2007 Big Dog Motorcycle";
                    break;
                case "src/media/UpcomingMoto4.jpg":
                    label = "suzuki dl 1000 v-strom";
                    break;
                case "src/media/UpcomingScooter1.jpg":
                    label = "LaScoota Foldable Adult Scooter";
                    break;
                case "src/media/UpcomingScooter2.jpg":
                    label = "Mongoose Trace Kick Scooter";
                    break;
                case "src/media/UpcomingScooter3.jpg":
                    label = "M.Y X-Skate Stunt Scooter";
                    break;
                case "src/media/UpcomingScooter4.jpg":
                    label = "TENBOOM Kick Scooter";
                    break;
                case "src/media/UpcomingBicycle1.jpg":
                    label = "Vivi 500W Electric Bike";
                    break;
                case "src/media/UpcomingBicycle2.jpg":
                    label = "Schwinn Axum Mountain Bike";
                    break;
                case "src/media/UpcomingBicycle3.jpg":
                    label = "ECOTRIC Fat Tire Electric Bike";
                    break;
                case "src/media/UpcomingBicycle4.jpg":
                    label = "Schwinn Capitol Women Hybrid Bicycle";
                    break;

            }
            JLabel vehicleLabel = new JLabel(label, SwingConstants.CENTER);
            vehicleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(vehicleLabel, BorderLayout.SOUTH);

            frame.add(panel);
        }
    }

    private static class VehicleButtonClickListener implements ActionListener {
        private final String vehicleDetails;

        public VehicleButtonClickListener(String vehicleDetails) {
            this.vehicleDetails = vehicleDetails;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            showVehicleDetailsWindow(vehicleDetails);
        }
    }

    private static void showVehicleDetailsWindow(String details) {
        JFrame vehicleDetailsWindow = new JFrame("Vehicle Details");
        vehicleDetailsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vehicleDetailsWindow.setSize(400, 300);

        JTextArea detailsTextArea = new JTextArea(details);
        detailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);

        vehicleDetailsWindow.add(scrollPane);
        vehicleDetailsWindow.setLocationRelativeTo(null);
        vehicleDetailsWindow.setVisible(true);
    }

    private static void scaleImageIcon(JButton button, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image originalImage = icon.getImage();

        int width = button.getWidth();
        int height = button.getHeight();

        if (width > 0 && height > 0) {
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } else {
            // If the button size is not available yet, use a default size
            Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        }
    }
}





