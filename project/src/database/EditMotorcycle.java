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

import classes.Motorcycle;

public class EditMotorcycle {
	
	public static void createMotorcycleTable() throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();

        String query = "CREATE TABLE IF NOT EXISTS motorcycles "
                + "(vehicle_id VARCHAR(7) not NULL unique,"
                + "		rent_cost FLOAT not null,"
                + "		vehicle_range FLOAT not null,"
                + "		insurance_cost FLOAT not null,"
                + "		color VARCHAR(15) not null,"
                + "		brand VARCHAR(15) not null,"
                + "		model VARCHAR(15) not null,"
                + "		rent_counter INTEGER not null,"
                + " PRIMARY KEY (vehicle_id))";
        stmt.execute(query);
        stmt.close();
	}
	
	public static void dropMotorcycleTable() throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
		
		String query = "DROP TABLE motorcycles";
		
		stmt.execute(query);
        stmt.close();
	}
	
	public static void addNewMotorcycle(Motorcycle motorcycle) throws ClassNotFoundException
	{
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
        	con = DB_Connection.getConnection();
            String insertQuery = "INSERT INTO motorcycles (vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(insertQuery);

            // Set values for the parameters in the PreparedStatement
            pstmt.setString(1, motorcycle.getVehicle_id());  // vehicle_id
            pstmt.setFloat(2, motorcycle.getRent_cost());      // rent_cost
            pstmt.setFloat(3, motorcycle.getVehicle_range());       // vehicle_range
            pstmt.setFloat(4, motorcycle.getInsurance_cost());        // insurance_cost
            pstmt.setString(5, motorcycle.getColor());       			// color
            pstmt.setString(6, motorcycle.getBrand());    				// brand
            pstmt.setString(7, motorcycle.getModel());   				// model
            pstmt.setInt(8, motorcycle.getRent_counter());             // rent_counter

            // Execute the INSERT statement
            pstmt.executeUpdate();
            System.out.println("# Μotorcycle " + motorcycle.getVehicle_id() + " was successfully added in the database.");
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
	
	
	public static void updateMotorcycle(Motorcycle motorcycle) throws SQLException, ClassNotFoundException
	{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        
        String update = "UPDATE motorcycles SET rent_cost='" + motorcycle.getRent_cost() + "'"
        		+ ", vehicle_range='" + motorcycle.getVehicle_range() + "'"
        		+ ", insurance_cost='" + motorcycle.getInsurance_cost() + "'"
        		+ ", color='" + motorcycle.getColor() + "'"
        		+ ", brand='" + motorcycle.getBrand() + "'"
        		+ ", model='" + motorcycle.getModel() + "'"
        		+ ", rent_counter='" + motorcycle.getRent_counter() + "'"
        		+ " WHERE vehicle_id = '" + motorcycle.getVehicle_id() + "'";
        
        stmt.executeUpdate(update);
    }
	
	
	public static Motorcycle getMotorcycle(String vehicle_id) throws SQLException, ClassNotFoundException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		Motorcycle motorcycle = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM motorcycles WHERE vehicle_id = '" + vehicle_id + "'");
			rs.next();

			float rent_cost = rs.getFloat("rent_cost");
			float vehicle_range = rs.getFloat("vehicle_range");
			float insurance_cost = rs.getFloat("insurance_cost");
			String color = rs.getString("color");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			int rent_counter = rs.getInt("rent_counter");
			
			motorcycle = new Motorcycle("Motorcycle", vehicle_id, rent_cost, vehicle_range, insurance_cost, color,
					brand, model, rent_counter);
			
			
		} catch (Exception e) {
			System.err.println("Got an exception while trying to get motorcycle " + vehicle_id + "! ");
			System.err.println(e.getMessage());
		}

   		return motorcycle;
	}

	public static ArrayList<Motorcycle> getAllMotorcycles() throws SQLException, ClassNotFoundException
	{
		ArrayList<Motorcycle> motorcycleList = new ArrayList<>();
		
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM motorcycles");
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
			
			Motorcycle motorcycle = new Motorcycle("Motorcycle", vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter);
			
			motorcycleList.add(motorcycle);
        }


        return motorcycleList;
	}
	
	/* Returns the most rented Motorcycle, otherwise null*/
	public static Motorcycle getMostRented() throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
		
		ResultSet rs;
		Motorcycle motorcycle = null;
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM motorcycles ORDER BY rent_counter DESC LIMIT 1");
			rs.next();

			String vehicle_id = rs.getString("vehicle_id");
			float rent_cost = rs.getFloat("rent_cost");
			float vehicle_range = rs.getFloat("vehicle_range");
			float insurance_cost = rs.getFloat("insurance_cost");
			String color = rs.getString("color");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			int rent_counter = rs.getInt("rent_counter");
			
			motorcycle = new Motorcycle("Motorcycle", vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter);
			
		}
		catch (Exception e) 
		{
			System.err.println("Got an exception while trying to get most rented motorcycle!");
			System.err.println(e.getMessage());
		}

   		return motorcycle;
	}
	
	public static int HowManyRented() throws SQLException, ClassNotFoundException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		int Count = 0;
		try {
			rs = stmt.executeQuery("SELECT COUNT(*) AS motorcycle_count FROM motorcycles WHERE vehicle_id IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Motorcycle')");
			if(rs.next() == true)	// If there is a valid row
			{
				Count = rs.getInt("motorcycle_count");
			}			
			
		} catch (Exception e)
		{
			System.err.println("Got an exception while trying to get the number of rented motorcycles!");
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
			rs = stmt.executeQuery("SELECT COUNT(*) AS motorcycle_count FROM motorcycles WHERE vehicle_id NOT IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Motorcycle')");
			if(rs.next() == true)	// If there is a valid row
			{
				Count = rs.getInt("motorcycle_count");
			}			
			
		} catch (Exception e)
		{
			System.err.println("Got an exception while trying to get the number of rented motorcycles!");
			System.err.println(e.getMessage());
		}

		return Count;
	}
	
	public static void deleteMotorcycle(String vehicle_id) throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM motorcycles WHERE `motorcycles`.`vehicle_id` = '" + vehicle_id + "'";
        stmt.executeUpdate(delete);
	}
	
	public static void load_example_Motorcycle(String path)
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
                if(arg_count == 9)
                {
                	Motorcycle motorcycle = new Motorcycle(array.get(0), array.get(1), Float.parseFloat(array.get(2)), 
                			 Float.parseFloat(array.get(3)), Float.parseFloat(array.get(4)), array.get(5), array.get(6), array.get(7),
                			 Integer.parseInt(array.get(8)));
                     
                     EditMotorcycle.addNewMotorcycle(motorcycle);;
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
