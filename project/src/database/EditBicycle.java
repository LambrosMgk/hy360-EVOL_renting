package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.StringTokenizer;

import classes.Bicycle;

public class EditBicycle {
	
	public static void createBicycleTable() throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();

        String query = "CREATE TABLE IF NOT EXISTS bicycles "
                + "(vehicle_id INTEGER not NULL AUTO_INCREMENT,"
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
	
	public static void dropBicycleTable() throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
		
		String query = "DROP TABLE bicycles";
		
		stmt.execute(query);
        stmt.close();
	}
	
	public static void addNewBicycle(Bicycle bicycle) throws ClassNotFoundException
	{
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
            		+ " bicycles (rent_cost,vehicle_range,insurance_cost,color,brand,model,rent_counter)"
            		+ " VALUES ("
            		+ "'" + bicycle.getRent_cost() + "',"
            		+ "'" + bicycle.getVehicle_range() + "',"
            		+ "'" + bicycle.getInsurance_cost() + "',"
            		+ "'" + bicycle.getColor() + "',"
            		+ "'" + bicycle.getBrand() + "',"
            		+ "'" + bicycle.getModel() + "',"
            		+ "'" + bicycle.getRent_counter() + "'"
            		+ ") ON DUPLICATE KEY UPDATE vehicle_id = vehicle_id;";

            
            stmt.executeUpdate(insertQuery);
            System.out.println("# Bicycle " + bicycle.getVehicle_id() + " was successfully added in the database.");
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
	
	public static void updateBicycle(Bicycle bicycle) throws SQLException, ClassNotFoundException
	{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        
        String update = "UPDATE bicycles SET rent_cost='" + bicycle.getRent_cost() + "'"
        		+ ", vehicle_range='" + bicycle.getVehicle_range() + "'"
        		+ ", insurance_cost='" + bicycle.getInsurance_cost() + "'"
        		+ ", color='" + bicycle.getColor() + "'"
        		+ ", brand='" + bicycle.getBrand() + "'"
        		+ ", model='" + bicycle.getModel() + "'"
        		+ ", rent_counter='" + bicycle.getRent_counter() + "'"
        		+ " WHERE vehicle_id = '" + bicycle.getVehicle_id() + "'";
        
        stmt.executeUpdate(update);
    }
	
	
	public static Bicycle getBicycle(String vehicle_id) throws SQLException, ClassNotFoundException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		Bicycle bicycle = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM bicycles WHERE vehicle_id = '" + vehicle_id + "'");
			rs.next();

			float rent_cost = rs.getFloat("rent_cost");
			float vehicle_range = rs.getFloat("vehicle_range");
			float insurance_cost = rs.getFloat("insurance_cost");
			String color = rs.getString("color");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			int rent_counter = rs.getInt("rent_counter");
			
			bicycle = new Bicycle("Bicycle", vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter);
			
		} catch (Exception e) {
			System.err.println("Got an exception while trying to get Bicycle " + vehicle_id + "!");
			System.err.println(e.getMessage());
		}

   		return bicycle;
	}

	public static ArrayList<Bicycle> getAllBicycles() throws SQLException, ClassNotFoundException
	{
		ArrayList<Bicycle> bicycleList = new ArrayList<>();
		
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM bicycles");
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
			
			Bicycle bicycle = new Bicycle("Bicycle", vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter);
			
			bicycleList.add(bicycle);
        }


        return bicycleList;
	}
	
	/* Returns the most rented Bicycle, otherwise null*/
	public static Bicycle getMostRented() throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
		
		ResultSet rs;
		Bicycle bicycle = null;
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM bicycles ORDER BY rent_counter DESC LIMIT 1");
			rs.next();

			String vehicle_id = rs.getString("vehicle_id");
			float rent_cost = rs.getFloat("rent_cost");
			float vehicle_range = rs.getFloat("vehicle_range");
			float insurance_cost = rs.getFloat("insurance_cost");
			String color = rs.getString("color");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			int rent_counter = rs.getInt("rent_counter");
			
			bicycle = new Bicycle("Bicycle", vehicle_id, rent_cost, vehicle_range, insurance_cost, color, brand, model, rent_counter);
			
		}
		catch (Exception e) 
		{
			System.err.println("Got an exception while trying to get most rented Bicycle!");
			System.err.println(e.getMessage());
		}

   		return bicycle;
	}
	
	public static int HowManyRented() throws SQLException, ClassNotFoundException
	{
		Connection con = DB_Connection.getConnection();
		Statement stmt = con.createStatement();
	
		ResultSet rs;
		int Count = 0;
		try {
			rs = stmt.executeQuery("SELECT COUNT(*) AS bicycle_count FROM bicycles WHERE vehicle_id IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Bicycle')");
			if(rs.next() == true)	// If there is a valid row
			{
				Count = rs.getInt("bicycle_count");
			}			
			
		} catch (Exception e)
		{
			System.err.println("Got an exception while trying to get the number of rented bicycles!");
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
			rs = stmt.executeQuery("SELECT COUNT(*) AS bicycle_count FROM bicycles WHERE vehicle_id NOT IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Bicycle')");
			if(rs.next() == true)	// If there is a valid row
			{
				Count = rs.getInt("bicycle_count");
			}			
			
		} catch (Exception e)
		{
			System.err.println("Got an exception while trying to get the number of rented bicycles!");
			System.err.println(e.getMessage());
		}

		return Count;
	}
	
	public static void deleteBicycle(String vehicle_id) throws ClassNotFoundException, SQLException
	{
		Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM bicycles WHERE `bicycles`.`vehicle_id` = '" + vehicle_id + "'";
        stmt.executeUpdate(delete);
	}
	
	public static void load_example_Bicycles(String path)
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
                	 Bicycle bicycle = new Bicycle(array.get(0), array.get(1), Float.parseFloat(array.get(2)), 
                			 Float.parseFloat(array.get(3)), Float.parseFloat(array.get(4)), array.get(5),
                			 array.get(6), array.get(7), Integer.parseInt(array.get(8)));
                	 
                     EditBicycle.addNewBicycle(bicycle);
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
