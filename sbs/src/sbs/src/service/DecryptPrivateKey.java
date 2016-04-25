package sbs.src.service;

public class DecryptPrivateKey {

	public static String encryptedData(String userkey , String encryptedString)
	{   
		
		byte [] databyte = encryptedString.getBytes() ;
		byte [] output = Pki.priDecrypt(databyte, Pki.bytesToPri((Pki.hexToBytes(userkey) ) ));
	//	byte[] output = Pki.priDecrypt(databyte, Pki.bytesToPri((Pki.hexToBytes(userkey))) );
		System.out.println(new String(output));
		
		return (new String(output)) ;
	}
}
