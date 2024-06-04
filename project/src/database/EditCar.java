package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.StringTokenizer;

import classes.Car;

public class EditCar {
	
	public static void createCarTable() throws SQLException, ClassNotFoundException 
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();

        String query = "CREATE TABLE IF NOT EXISTS cars "
                + "(vehicle_id VARCHAR(7) not NULL unique,"
                + "		rent_cost FLOAT not null,"
                + "		vehicle_range FLOAT not null,"
                + "		insurance_cost FLOAT not null,"
                + "		color VARCHAR(16) not null,"
                + "		brand VARCHAR(16) not null,"
                + "		model VARCHAR(16) not null,"
                + "		rent_counter INTEGER not null,"
                + "		passanger_number INTEGER not null,"
                + "		car_type VARCHAR(16) not null,"
                + " PRIMARY KEY (vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }
	
	public static void dropCarTable() throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
		
		String query = "DROP TABLE cars";
		
		stmt.execute(query);
        stmt.close();
	}
	
	/* Inserts a new entry in the cars table*/
	public static void addNewCar (Car car) throws ClassNotFoundException
	{
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
        	con = DB_Connection.getConnection();
            String insertQuery = "INSERT INTO cars (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter, passanger_number, car_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(insertQuery);

            // Set values for the parameters in the PreparedStatement
            pstmt.setString(1, car.getVehicle_id());  // vehicle_id
            pstmt.setFloat(2, car.getRent_cost());      // rent_cost
            pstmt.setFloat(3, car.getVehicle_range());       // vehicle_range
            pstmt.setFloat(4, car.getInsurance_cost());        // insurance_cost
            pstmt.setString(5, car.getColor());       			// color
            pstmt.setString(6, car.getBrand());    				// brand
            pstmt.setString(7, car.getModel());   				// model
            pstmt.setInt(8, car.getRent_counter());             // rent_counter
            pstmt.setInt(9, car.getPassanger_number());        	// passanger_number
            pstmt.setString(10, car.getCar_type().toString());  // car_type

            // Execute the INSERT statement
            pstmt.executeUpdate();
            System.out.println("# Car " + car.getVehicle_id() + " was successfully added in the database.");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } finally 
        {
            // Close resources in the reverse order of their creation
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                if (con != null) 
                {
                    con.close();
                }
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }
	
	
	public static void updateCar(Car car) throws SQLException, ClassNotFoundException
	{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        
        String update = "UPDATE cars SET rent_cost='" + car.getRent_cost() + "'"
        		+ ", vehicle_range='" + car.getVehicle_range() + "'"
        		+ ", insurance_cost='" + car.getInsurance_cost() + "'"
        		+ ", color='" + car.getColor() + "'"
        		+ ", brand='" + car.getBrand() + "'"
        		+ ", model='" + car.getModel() + "'"
        		+ ", rent_counter='" + car.getRent_counter() + "'"
        		+ ", passanger_number='" + car.getPassanger_number() + "'"
        		+ ", car_type='" + car.getCar_type() + "'"
        		+ " WHERE vehicle_id = '" + car.getVehicle_id() + "'";
        
        stmt.executeUpdate(update);
    }
	
	
	public static Car getCar(String vehicle_id) throws SQLException, ClassNotFoundException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		Car car = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM cars WHERE vehicle_id = '" + vehicle_id + "'");
			rs.next();

			float rent_cost = rs.getFloat("rent_cost");
			float vehicle_range = rs.getFloat("vehicle_range");
			float insurance_cost = rs.getFloat("insurance_cost");
			String color = rs.getString("color");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			int rent_counter = rs.getInt("rent_counter");
			
			// Car specific fields
			int passanger_number = rs.getInt("passanger_number");
			String car_type = rs.getString("car_type");
			
			car = new Car("Car", vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter,
					passanger_number, car_type);
			
		} catch (Exception e) {
			System.err.println("Got an exception while trying to get car with license plate : " + vehicle_id + "! ");
			System.err.println(e.getMessage());
		}

   		return car;
	}
	
	public static ArrayList<Car> getAllCars() throws SQLException, ClassNotFoundException
	{
		ArrayList<Car> carList = new ArrayList<>();
		
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM cars");
		while (rs.next()) 
        {
			String vehicle_id = rs.getString("vehicle_id");
			float rent_cost = rs.getFloat("rent_cost");
			float vehicle_range = rs.getFloat("vehicle_range");
			float insurance_cost = rs.getFloat("insurance_cost");
			String color = rs.getString("color");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			int rent_counter = rs.getInt("rent_counter");
			
			// Car specific fields
			int passanger_number = rs.getInt("passanger_number");
			String car_type = rs.getString("car_type");
			
			Car car = new Car("Car", vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter,
					passanger_number, car_type);
			
            carList.add(car);
        }


        return carList;
	}

	
	/* Returns the most rented Car, otherwise null*/
	public static Car getMostRented() throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
		
		ResultSet rs;
		Car car = null;
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM cars ORDER BY rent_counter DESC LIMIT 1");
			rs.next();

			String vehicle_id = rs.getString("vehicle_id");
			float rent_cost = rs.getFloat("rent_cost");
			float vehicle_range = rs.getFloat("vehicle_range");
			float insurance_cost = rs.getFloat("insurance_cost");
			String color = rs.getString("color");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			int rent_counter = rs.getInt("rent_counter");
			
			// Car specific fields
			int passanger_number = rs.getInt("passanger_number");
			String car_type = rs.getString("car_type");
			
			car = new Car("Car", vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter,
					passanger_number, car_type);
			
		}
		catch (Exception e) 
		{
			System.err.println("Got an exception while trying to get most rented car!");
			System.err.println(e.getMessage());
		}

   		return car;
	}
	
	public static int HowManyRented() throws SQLException, ClassNotFoundException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		int Count = 0;
		try {
			rs = stmt.executeQuery("SELECT COUNT(*) AS car_count FROM cars WHERE vehicle_id IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Car')");
			if(rs.next() == true)	// If there is a valid row
			{
				Count = rs.getInt("car_count");
			}			
			
		} catch (Exception e)
		{
			System.err.println("Got an exception while trying to get the number of rented cars!");
			System.err.println(e.getMessage());
		}

		return Count;
	}
	
	public static int HowManyAvailable() throws SQLException, ClassNotFoundException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		int Count = 0;
		try {
			rs = stmt.executeQuery("SELECT COUNT(*) AS car_count FROM cars WHERE vehicle_id NOT IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Car')");
			if(rs.next() == true)	// If there is a valid row
			{
				Count = rs.getInt("car_count");
			}			
			
		} catch (Exception e)
		{
			System.err.println("Got an exception while trying to get the number of rented cars!");
			System.err.println(e.getMessage());
		}

		return Count;
	}
	
	public static void deleteCar(String vehicle_id) throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM cars WHERE `cars`.`vehicle_id` = '" + vehicle_id + "'";
        stmt.executeUpdate(delete);
	}
	
	public static void load_example_Cars(String path)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			ArrayList<String> array = new ArrayList<String>();
            String line;
            int arg_count = 0;
            
            while ((line = br.readLine()) != null) 
            {
            	// Check for comments
            	if(line.charAt(0) == '#')
            	{
            		continue;
            	}
            	
                // Tokenize the line using StringTokenizer with space as delimiter
                StringTokenizer tokenizer = new StringTokenizer(line);

                // Process each word
                arg_count = 0;
                while (tokenizer.hasMoreTokens())
                {
                    array.add(tokenizer.nextToken());
                    arg_count++;
                }
                
                // Checking if there is a mistake in the examples
                if(arg_count == 11)
                {
                	 Car car = new Car(array.get(0), array.get(1), Float.parseFloat(array.get(2)), 
                			 Float.parseFloat(array.get(3)), Float.parseFloat(array.get(4)), array.get(5),
                			 array.get(6), array.get(7), Integer.parseInt(array.get(8)), Integer.parseInt(array.get(9)), array.get(10));
                	
                	 
                     EditCar.addNewCar(car);;
                }
                
                array.clear();
            }
            br.close();
        } 
		catch (IOException | ClassNotFoundException e) 
		{
            e.printStackTrace();
        }
	}
	
}
