package ua.nure.hrybeniuk.finalProject.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Util class - MD5hesh maker 
 * 
 * @author A.Hrybeniuk
 *
 */
public class HeshFactory {
	
	//make MD5 hesh from given String
	public static String makeHesh(String pass) {
		MessageDigest messageDigest = null;
		byte[] digest = new byte[0];
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(pass.getBytes());
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		BigInteger bigInt = new BigInteger(1, digest);
		String md5Hex = bigInt.toString(16);

		while (md5Hex.length() < 32) {
			md5Hex = "0" + md5Hex;
		}

		return md5Hex;
	}
}
