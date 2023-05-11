package service;

public abstract class ValidateField {
	
	public static boolean validateInteger(char key) {
		String str = String.valueOf(key);

		try {
	        Integer.parseInt(str);
	        
	        return true;
		} catch (Exception e) {
			
			return false;
		}
	}
}
