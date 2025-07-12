package experiments;

import java.util.Date;

public class generateEmail {
	
	public static void main(String[]args) {
		
		Date date = new Date();
		//System.out.println(date);
		
		// we want to remove the spaces cause we want to make emails - date is in the date
		
		String dateString = date.toString();
		//regular expression
		String noStringDate = dateString.replaceAll(" ","");
		String proper =noStringDate.replaceAll("\\:","");
		//System.out.println(proper);
		
		String email = proper+"@gmail.com";
		
		System.out.print(email);
		
		
	}

}