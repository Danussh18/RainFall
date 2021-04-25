import java.beans.Statement;
import java.sql.Connection;
import java.util.*;
public class Main {

public static void main(String[] args) throws Exception { 
   
	   //fill the code
	   List<AnnualRainfall> al = new ArrayList<>();
	   
	   RainfallReport r = new RainfallReport();
	   
	   al = r.generateRainfallReport("AllCityMonthlyRainfall.txt");
	   
	   Connection con = null;
	   
	   Statement s = null;
	   
	   DBHandler db =new DBHandler();
	   
	   try {
	   con = db.establishConnection();
	   s =  (Statement) con.createStatement();
	   System.out.print(con);
	   for(int i=0;i<al.size();i++)
	   {
		   String sql = "insert into annualrainfall values ("+
	   al.get(i).getCityPincode()+","+al.get(i).getCityName()+","
	   +al.get(i).getAverageAnnualRainfall()+")"+"on duplicate key up"
	   		+ "update city_name ="+ al.get(i).getCityName() +", average_annual_rainfall ="
	   		+al.get(i).getAverageAnnualRainfall()+";";
		   ((java.sql.Statement) s).executeUpdate(sql);
	   }
		   
		   List<AnnualRainfall> fl =new ArrayList<>();
		   
		   fl = r.findMaximumRainfallCities();
		   
		   for(int ik= 0 ;ik<fl.size();ik++)
			   System.out.print(fl.get(ik).getCityName());
	   
	   }
	   
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	}
}
          