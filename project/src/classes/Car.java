package classes;

public class Car extends Vehicle {
	
	private int passanger_number;
	private Type car_type;
	
	public enum Type {
        SUV, CABRIO, SPORT, FAMILY, MINI, UNDEFINED
    }
	
	public Car(String _type, String _vehicle_id, float _rent_cost, float _vehicle_range, float _insurance_cost, String _color,
			String _brand, String _model, int _rent_counter, int _passanger_number, String _car_type)
	{
		super(_type, _vehicle_id, _rent_cost, _vehicle_range, _insurance_cost, _color, _brand, _model, _rent_counter);
		this.passanger_number = _passanger_number;
		
		switch(_car_type)
		{
		case "SUV":
			this.car_type = Type.SUV;
			break;
		case "CABRIO":
			this.car_type = Type.CABRIO;
			break;
		case "SPORT":
			this.car_type = Type.SPORT;
			break;
		case "FAMILY":
			this.car_type = Type.FAMILY;
			break;
		case "MINI":
			this.car_type = Type.MINI;
			break;
		default :
			this.car_type = Type.UNDEFINED;
			break;
		}
	}
	
	public String toString()
	{
		return super.toString() + ", {"
				+ passanger_number + ", "
				+ car_type
				+ "}";
	}
	
	public int getPassanger_number(){return passanger_number;}
	public void setPassanger_number(int passanger_number){this.passanger_number = passanger_number;}

	public Type getCar_type(){return car_type;}
	public void setCar_type(Type car_type){this.car_type = car_type;}
	
}
