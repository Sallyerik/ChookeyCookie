package ChookyCookiePackage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


public class ChookyCookie {

	// Create a HashMap object called ChookyCookieHMap
	static HashMap<String, String> ChookyCookieHMap = new HashMap<String, String>();
	static HashMap<String, String> ChookyCookieHMapUS = new HashMap<String, String>();

public static boolean validateChookyCookie(String CookieValue, String ipAddress, String userAgent) throws NoSuchAlgorithmException {
		//Create hash with user info
		String hash256 = CreateHash(ipAddress, userAgent);
		// get value of key keyToBeChecked
	      String HashinMap = (String)ChookyCookieHMap.get(CookieValue);
		//Compare hash just created with stored for the key keyToBeChecked
	     boolean Coparehash = hash256.equals(HashinMap);
		 return Coparehash;
	}
public static boolean validateChookyCookieUS(String state, String user) throws NoSuchAlgorithmException {
	//Create hash with user info
    String HashStateinmap = (String)ChookyCookieHMapUS.get(user);
	String hashState = CreateHashUserState(state);
    
	//Compare hash just created with stored for the key keyToBeChecked
     boolean Coparehash = hashState.equals(HashStateinmap);
	 return Coparehash;
}


public static String StoreCookie(String Cookie, String ipAddress, String userAgent) throws NoSuchAlgorithmException{
		
			String hash256 = CreateHash(ipAddress, userAgent);

		    // Add keys and values (Cookie, Hash)
		    ChookyCookieHMap.put(Cookie, hash256);
		    return hash256 ;
		  }

public static String StoreUserState(String User, String State) throws NoSuchAlgorithmException{
	
	String hashUserState = CreateHashUserState(State);

    // Add keys and values (User state)
	ChookyCookieHMapUS.put(User, hashUserState);
    return hashUserState ;
  }
private static String CreateHashUserState(String state) throws NoSuchAlgorithmException {
	
	String hashUserState = state;
	/* SHA-256 */
	MessageDigest objSHA256US = MessageDigest.getInstance("SHA-256");
	// String input = null;
	byte[] bytSHA256US = objSHA256US.digest(hashUserState.getBytes());
	BigInteger intNumSHA256 = new BigInteger(1, bytSHA256US);
	String hcSHA256US = intNumSHA256.toString(16);
	while (hcSHA256US.length() < 64) {
		hcSHA256US = "0" + hcSHA256US;
	}

	// Object hash user info;
	String Hash256US = String.format("%032x", intNumSHA256);

	return hcSHA256US;
	
}

public static String CreateHash(String ipAddress, String userAgent) throws NoSuchAlgorithmException {

		String hashui = ipAddress + userAgent;
		/* SHA-256 */
		MessageDigest objSHA256 = MessageDigest.getInstance("SHA-256");
		// String input = null;
		byte[] bytSHA256 = objSHA256.digest(hashui.getBytes());
		BigInteger intNumSHA256 = new BigInteger(1, bytSHA256);
		String hcSHA256 = intNumSHA256.toString(16);
		while (hcSHA256.length() < 64) {
			hcSHA256 = "0" + hcSHA256;
		}
		// Object hash user info;
		String HashUI256 = String.format("%032x", intNumSHA256);
		return HashUI256;
	}
public static String Removehashmapentry(String User){
	
	ChookyCookieHMap.remove(User);
    return "Done" ;
  }
public static String RemovehashmaUSpentry(String User){
	
	ChookyCookieHMapUS.remove(User);
    return "Done" ;
  }
}
