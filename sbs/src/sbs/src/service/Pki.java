package sbs.src.service;





import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import java.util.Properties;





public class Pki {
    public static byte[] hexToBytes(String hex){
        return DatatypeConverter.parseHexBinary(hex);
    }

    public  static  PrivateKey priv1 = null;
    public static PublicKey pub2 = null;
    
	/* 
	 * This function is used purely for debugging purposes, to view the ciphers as hex streams
	 */
    public static String bytesToHex(byte[] bytes) {
	    return DatatypeConverter.printHexBinary(bytes);
	}
    

    
    /* 
	 * This function is used to create a random pair of RSA public and private keys.
	 * Currently, it stores it to files on the hard disk.
	 * Modify to store public key in db and email private key to user
	 */
    public static void generateKeys() {
		try {
			/* Generate RSA key pair */
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			KeyPair pair = keyGen.generateKeyPair();
			PrivateKey priv = pair.getPrivate();
			PublicKey pub = pair.getPublic();

        	// save the public key in a file
	    	byte[] key1 = pub.getEncoded();
	    	FileOutputStream keyfos = new FileOutputStream("kpub");
	    	keyfos.write(key1);
	    //	System.out.println("Pub Key: " + bytesToHex(key1));   
	    	System.out.println("Pub Key: " + key1);
	    	keyfos.close();
	    	
	    	

	    	

        	// save the private key in a file
	    	byte[] key2 = priv.getEncoded();
	    	keyfos = new FileOutputStream("kpri");
	    	keyfos.write(key2);
	    	System.out.println("Pri Key: " + key2);   
	    	keyfos.close();
	    	
	    	priv1 = priv ;
	    	pub2 = pub;
	    	
		}
		catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
	}

    
	/* 
	 * Given a byte stream, this function converts it into type PublicKey, and returns
	 */
	public static PublicKey bytesToPub(byte[] pubKeyBytes) {
		PublicKey pub = null;
		
		try {
			pub = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubKeyBytes));
		} 
		catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}
		return pub;
    }

	
	/* 
	 * Given a byte stream, this function converts it into type PrivateKey, and returns
	 */
	public static PrivateKey bytesToPri(byte[] priKeyBytes) {
		PrivateKey pri = null;
		try {
			pri = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(priKeyBytes));
		} 
		catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}
		return pri;
    }

	
	/* 
	 * Given a public key, this function converts it into a byte stream and returns
	 */
    public static byte[] pubToBytes(PublicKey key){
        return key.getEncoded(); 
    }
    
    
	/* 
	 * Given a private key, this function converts it into a byte stream and returns
	 */
    public static byte[] priToBytes(PrivateKey key){
        return key.getEncoded(); 
    }

    
    /* 
	 * Given a byte stream and public key, this function creates cipher text byte stream
	 */
    public static byte[] pubEncrypt (byte[] plainBytes, PublicKey key) {
        Cipher cipher = null;
        byte[] cipherBytes = null;
        try {
        	cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        	cipher.init(Cipher.ENCRYPT_MODE, key);
        	cipherBytes = cipher.doFinal(plainBytes);
        } 
        catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		
        }
        return cipherBytes;
    }

	/* 
	 * Given a byte stream and private key, this function creates cipher text byte stream
	 */
    public static byte[] priEncrypt (byte[] plainBytes, PrivateKey key) {
        Cipher cipher = null;
        byte[] cipherBytes = null;
        try {
        	cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        	cipher.init(Cipher.ENCRYPT_MODE, key);
        	cipherBytes = cipher.doFinal(plainBytes);
        } 
        catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}
        return cipherBytes;
    }
    
    
    
    /* 
	 * Given a byte stream and private key, this function creates plain text byte stream
	 */
    
    public static byte[] priDecrypt (byte[] cipherBytes, PrivateKey key) {
        Cipher cipher = null;
        byte[] plainBytes = null;
        try {
        	cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        	cipher.init(Cipher.DECRYPT_MODE, key);
        	plainBytes = cipher.doFinal(cipherBytes);
    
        
        }
        catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}
        return plainBytes;
    }
    
    
    /* 
	 * Given a byte stream and public key, this function creates plain text byte stream
	 */
    public static byte[] pubDecrypt (byte[] cipherBytes, PublicKey key) {
        Cipher cipher = null;
        byte[] plainBytes = null;
        try {
        	cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        	cipher.init(Cipher.DECRYPT_MODE, key);
        	plainBytes = cipher.doFinal(cipherBytes);
        }
        catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}
        return plainBytes;
    }
    
    /*public static void main(String[] args) throws Exception {
    	try{
    		
    		long timeSeed = System.nanoTime(); 

	        double randSeed = Math.random() * 1000; 

	        long midSeed = (long) (timeSeed * randSeed);
	                                                     
	        String s = midSeed + "";
	        String subStr = s.substring(0,6);

	        int timeseed = Integer.parseInt(subStr);  		     

        	generateKeys();
        	PublicKey pubKey = null;
        	PrivateKey priKey = null;
        	//String u = null;
        	byte[] encKey = null;
        	FileInputStream keyfis = null;
        	
        	// Test code - Uses file kpub as public key to encode
        	keyfis = new FileInputStream("kpub");
        	encKey = new byte[keyfis.available()];  
			keyfis.read(encKey);
			keyfis.close();
			pubKey = bytesToPub(encKey);
        	
        	keyfis = new FileInputStream("kpri");
        	encKey = new byte[keyfis.available()];  
			keyfis.read(encKey);
			keyfis.close();
			priKey = bytesToPri(encKey);
		// System.out.println(priKey );
			System.out.println("before scannner");
//			String plainText = "Hey PKI really works!";
		//	Scanner scan = new Scanner(System.in);
	        System.out.println("Enter text to encrypt: ");
	     //   String plainText = scan.nextLine();
           
	       
	        
	        System.out.println(""+timeseed);
	        String str= Integer.toString(timeseed);
	        String plainText = ""+str ;   //   // 
	        byte[] cipherText = pubEncrypt(plainText.getBytes("UTF-8"),pubKey); 
	        System.out.println("cipherText: "+ bytesToHex(cipherText));
	        System.out.println("plainText:");
	        System.out.println(new String(priDecrypt(cipherText,priKey),"UTF-8"));
	        String e= bytesToHex(cipherText);
	        System.out.println("This is ct:" + e);
	       // System.out.println(""+bytesToHex(cipherText));
	       
	        System.out.println("Enter 1 to encrypt using Public key, and 2 to encrypt using private key: ");
	        int num = scan.nextInt();

	        if (num == 1) {
	        	byte[] cipherText = pubEncrypt(plainText.getBytes("UTF-8"),pubKey);
		        System.out.println("cipherText: "+ bytesToHex(cipherText));
		        System.out.println("plainText:");
		        System.out.println(new String(priDecrypt(cipherText,priKey),"UTF-8"));
	        } else if (num == 2) {
	        	byte[] cipherText = priEncrypt(plainText.getBytes("UTF-8"),priKey);
		        System.out.println("cipherText: "+ bytesToHex(cipherText));
		        System.out.println("plainText:");
		        System.out.println(new String(pubDecrypt(cipherText,pubKey),"UTF-8"));
	        } else {
	        	System.out.println("Can't follow simple instructions can you? Bad boy!");
	        }
	        
	        	Properties props = new Properties();
	        	props.put("mail.smtp.host", "smtp.gmail.com");
	        	props.put("mail.smtp.socketFactory.port", "465");
	        	props.put("mail.smtp.socketFactory.class",
	        			"javax.net.ssl.SSLSocketFactory");
	        	props.put("mail.smtp.auth", "true");
	        	props.put("mail.smtp.port", "465");

	        	Session session = Session.getDefaultInstance(props,
	        		new javax.mail.Authenticator() {
	        			protected PasswordAuthentication getPasswordAuthentication() {
	        				return new PasswordAuthentication("breakingaaronpaul@gmail.com","breaking_bad");
	        			}
	        		});

	   	     //   double randSeed = Math.random() * 1000; 
	   //
	   	//        long midSeed = (long) (timeSeed * randSeed);
	   	  //                                                   
	   	    //    String s = midSeed + "";
	   	      //  String subStr = s.substring(0,6);

	   	        //int timeseed = Integer.parseInt(subStr);   		     

	   		Message message = new MimeMessage(session);
	   		message.setFrom(new InternetAddress("breakingaaronpaul@gmail.com"));
	   		message.setRecipients(Message.RecipientType.TO,
	   				InternetAddress.parse("pritam09@gmail.com"));
	   		message.setSubject("Testing SS"+"cipherText: ");
	   		message.setText("OTP," +
	   				"\n\n " + "cipherText: "+e); 

	   		Transport.send(message);

	   		System.out.println("Done");
	        
    	}        
        
    	catch (Exception e) {
    		throw new RuntimeException(e);
          //  System.err.println("Caught exception " + e.toString());
        }
    

    }*/
    }    