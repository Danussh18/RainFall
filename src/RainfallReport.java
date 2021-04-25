import java.beans.Statement;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RainfallReport {

	//Write the required business logic as expected in the question description
	public List<AnnualRainfall> generateRainfallReport(String filePath) {
	    
		//fill the code
		List<AnnualRainfall> li = new ArrayList<>();
		try {
			FileReader fr = new FileReader(new File(filePath));
			BufferedReader br = new BufferedReader(fr);
			String i;
			
			while( (i = br.readLine()) != null)
			{
				String[] details = i.split(",");
				String pincode = details[0];
				
				if(validate(pincode))
				{
					double[] rainfall = new double[12];
					for(int i1=0;i1<12;i1++)
						rainfall[i1] = Double.parseDouble(details[i1+2]);
					
					AnnualRainfall r = new AnnualRainfall();
					
					r.calculateAverageAnnualRainfall(rainfall);
					
					r.setCityName(details[1]);
					r.setCityPincode(Integer.parseInt(pincode));
					li.add(r);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCityPincodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return li;
		
	}
	
	public List<AnnualRainfall>  findMaximumRainfallCities() {
	
		//fill the code
		
		DBHandler db = new DBHandler();
		
		ArrayList<AnnualRainfall> l = new ArrayList<>();
		Connection con = db.establishConnection();
		Statement st = null;
		try {
			st = (Statement) con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query= "Select * from AnnualRainfall where average_annual_rainfall in (select max(average_annual_rainfall) "
				+ "from  AnnualRainfall);";
		ResultSet rs = null;
		try {
			 rs = ( ((java.sql.Statement) st).executeQuery(query) );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnnualRainfall ar  = null;
		try {
			while(rs.next())
			{
			ar	= new AnnualRainfall();
					ar.setCityName(rs.getString(2));
					ar.setCityPincode(rs.getInt(1));
					ar.setAverageAnnualRainfall(Double.parseDouble(rs.getString(3)));
					l.add(ar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
		
	}
	
	
	public boolean validate(String cityPincode) throws InvalidCityPincodeException {
	
		//fill the code
    		if(cityPincode.length()==5)
    			return true;
    		throw new InvalidCityPincodeException("Invalid City Pincode exception");
	}

}
