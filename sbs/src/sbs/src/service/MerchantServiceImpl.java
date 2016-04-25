package sbs.src.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import sbs.src.dao.MerchantDAO;
import sbs.src.form.Externaluseraccount;
import sbs.src.form.Login;
import sbs.src.form.Merchanttransaction;
import sbs.src.form.Registeruser;

@Transactional
@Service("merchantService")
public class MerchantServiceImpl  implements MerchantService{

	 private MerchantDAO merchantDAO;
	 
	 @Autowired
	    public void setmerchantDAO(MerchantDAO merchantDAO) {
			this.merchantDAO = merchantDAO;
		}
	 
	@Override
	public List<Merchanttransaction> listRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Registeruser> listUserRequest() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//merchant trnasfer
	@Override
	public int merchanttransfer(Merchanttransaction merchanttransaction) {
		
		System.out.println("in service before validation" + merchanttransaction.getCustomeraccountnumber());
		if(merchantDAO.validateExternaluseraccountnumber(merchanttransaction.getCustomeraccountnumber()))
		{   
			
			merchanttransaction.setAccountnumber(merchantDAO.getMerchantaccountinfo(merchanttransaction.getUsername()).getAccountnumber());
			System.out.println("in service after validation customer  accnt" + merchanttransaction.getCustomeraccountnumber() + "merch accnt number " + merchanttransaction.getAccountnumber());
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			 Date todaydate = new Date();
			 String date = new String();
			 date = dateFormat.format(todaydate);
	         merchanttransaction.setTransactionstatus("pending");
	        merchanttransaction.setDate(date);
	        System.out.println("in merchant transfer " + merchanttransaction);
			//call below mentioned method to transfer the key to user mail id 
	        //1.send a mail with OTP (in ecrypted format ) and save the OTP in merchant transaction table
			
	
	        //send the user OTP and ask him to encrypt and give it back to merchant
	        merchanttranferSendUserKey(merchanttransaction);
	        
	        
			
	        
	        //save merchant transaction
			merchantDAO.savemerchanttransaction(merchanttransaction);
			
			//3.Instead of implement, we can show user pending request from merchant transaction table 
			//with pending status 
			//userDAO.senduserrequest(merchanttransaction);
			 
			
	         
	         return 0;
		}
		else
		{
			System.out.println("in service rejecting validation");
			return -1;  // customer account is not present 

		}
	}		

@Override
public int merchanttranferSendUserKey(Merchanttransaction merchanttransaction) {
	
	
	
	Externaluseraccount externaluseraccount = merchantDAO.getExternaluserinfo(merchanttransaction.getCustomeraccountnumber());
	System.out.println("in merchat OTP transfer external user account " + externaluseraccount);
	Login login  = merchantDAO.getLoginuserinfo(externaluseraccount.getUsername());
    SendMailSSL.customerMail(merchanttransaction , login.getEmail());
	
	return 0;
}

@Override
public int merchantusertransfer(int merchantTransactionId,	String encryptedkey) {
	// TODO Auto-generated method stub
	
	if (merchantDAO.getMerchantTransaction(merchantTransactionId) != null)
	{	
		Merchanttransaction merchantTransaction = merchantDAO.getMerchantTransaction(merchantTransactionId);
		System.out.println("in merchant dao and tranasaction status is " +merchantTransaction.getTransactionstatus() );
	     if(!(merchantTransaction.getTransactionstatus().equalsIgnoreCase("pending"))  )
	     { 
	    	 System.out.println("in merchant  service and tranasaction status is not pending");
	    	 return -3 ; // transaction status has already been changed
	     }
	
	//CREATE ENCRYPTION KEY DECRYPTION method AND FOLLOW THE RESULT
	//get customer username to send information to match in encrypt key
	 String externalusername = merchantDAO.getExternaluserinfo(merchantTransaction.getCustomeraccountnumber()).getUsername();
	 
	 System.out.println("in merchant service external username is " + externalusername);
	 Login externaluser = merchantDAO.getLoginuserinfo(externalusername);
	 
	 System.out.println("in merchant servicee and external user is " + externaluser.getPrivatekey());
	 
	 String encryptedString = DecryptPrivateKey.encryptedData( externaluser.getPrivatekey() ,encryptedkey);
   //  String yeprivate = "30820276020100300D06092A864886F70D0101010500048202603082025C02010002818100895B1BFDEFBA81998F0BDF0DF869F1431B92EEB741BEC327BF37728BC42CBC8AD1A9AEBDDB282285918076C671C320F1E1AB1C2DE7992B97FFC3C7515446B05A282DA2C89A3327FABC1F5CA976BF9F293FADE3B96BC9195E31ADFC49088F9899839FD240D0139CDCFDE64B8EBA24944B151374B08D2A52B4ECA1D1F873F6745B02030100010281804D0CC1C009E94A67F479494F91E904008C083946AD67B01BACC512C95A6B69FC492E712A6934B360CFC50B5BB8572834E74D500A641F64F7E7EE4FF1CC5FB6EE99B3369F40FEBE4643FE5E852D6D697CB97AE9BC02913A6C5A5F3AECCB15F49C81BD61D739A24B6FA951358F6D71FD2B206860F25A61E82AC5680C9D79A02109024100BE032303DD34E78D97927714501866F74B438597FC9C40016BAC39F093ACCE5BA4B03EBD48C05D2948754B488C619D8229FAAD83159DB9B2D6FD4663A9D64915024100B90E9BA21E47D38D353B05E913635364C9E7DCE19569055C60C2991293DABC3DE570C7C552D9F6F4516B13727E9CB587633893F6777FA7D7BA787913527243AF02407A87DF4EA0997B30ADABBFAA93B408C23400C8E7A7F8C2B5A7CF9A556F85A2552AB094E4CF436D502FEFA3AA970C63DC0A6222FE43344109F8E5BF3C89CB2BB902404463DDA8804053327065ABBD8368C5BD0DD11E944FBF4C37E39A872F926CD7FC97FAA2198E90F6EF3BD7B28C31B6806FD47F684F41C26FA90E44CF9A10722209024100B0F1279C83767CC9ED3B9925F4A54D0AAF6AAC0B8E8B6208E5AAB963BFCC5465F4C7A8D75B320660236DC77DBCCA8795EAAEBA86255412B98E7DD464A3EE4061";
	
     
     System.out.println("in merchant service-------------- " + encryptedString);
	
     /* String data = "amit123" ;
	 byte[] plainBytes = data.getBytes();
	  String privatekey = "30820276020100300D06092A864886F70D0101010500048202603082025C02010002818100983C55BC470900855B98A4B65554C9D585ED48419FA43E926F5F3FEA52444BA0565609C04BB99E8538233E28DCC6A992F51A78F6D48B6B2224A552FFD6E74F75891795DB7BF1973378A7E09D41CDC74D1BECA08554C18E7F6D899D163BDA127E3F8A7FCD2EC0B8894CC948B6D0B459D068DE27F3B5A88C3A7D08F5B02FDAE6410203010001028181009350D77A7197217CEB4D441EF9662A4648EA7C209CD6788285E9BB550A34F2A5FEC6157B930BD57D7EFC83BB5121A199073C8DCFF58FD0A562A6E531940994965BF2BF64E6D2BB01E6FD5AA35B6736EAC92C7C87E5D84D836619BEFC559DBEA053DECAEB21D60070CE4C97DAA5E7D307C21041FCF98B444EE3038EF05CD99F6D024100E216224FE7E7C032E3BC354E679C5F16DC8FE0D8150D0F739817CEE1B84EB7FC8C9CBE4721B3D17086AF86690B9DB20B04A88BC38CD9AD669C168BF1BB3AE9CF024100AC60C57447234683151A6C503455404E3DAF9D3B02A13B6192DC08931F4F62587353AD9539C8B1C4F960633731A2D1EE74B2F4AFC4896EC1F56F65571E4902EF02404B28E6913FDC286602957FF727B4B15BED491B8B5A3C3445CE6B23C8A037F32C4F26908F1F22235F50372BECE202B3BCBBF8B835F32E2E5A2D4BC58C401D0EBB02406F8F197B0D67090560ACFF185126333DEFB22E01B8CBFC1043E95F2B48022A55B01DA1A3880818F4AFA7EFCE12B39B45C604E352B5D173FAD70AC6FD40BE63C5024010833257404BB09181E05F22256503A02D47657E2FD70E27C5210A9878050205C701FAF741FCEDBA689D3DF965FE817C5D3124E81121F16BB13459AD6289DEF7";
	   String publickey = "30819F300D06092A864886F70D010101050003818D0030818902818100983C55BC470900855B98A4B65554C9D585ED48419FA43E926F5F3FEA52444BA0565609C04BB99E8538233E28DCC6A992F51A78F6D48B6B2224A552FFD6E74F75891795DB7BF1973378A7E09D41CDC74D1BECA08554C18E7F6D899D163BDA127E3F8A7FCD2EC0B8894CC948B6D0B459D068DE27F3B5A88C3A7D08F5B02FDAE6410203010001" ;
	   
	  
	  
	 byte[] dataencrypt =  Pki.priEncrypt(plainBytes, Pki.bytesToPri(privatekey.getBytes()));
	//  Pki.pubDecrypt(Pki.priEncrypt(plainBytes, Pki.bytesToPri(privatekey.getBytes())), Pki.bytesToPub(publickey.getBytes()));
	// System.out.println("output is------- " + output);
	 */
	  
	  
	  /* //store public key from users table for externalusername
	  String privatekey = "30820276020100300D06092A864886F70D0101010500048202603082025C02010002818100983C55BC470900855B98A4B65554C9D585ED48419FA43E926F5F3FEA52444BA0565609C04BB99E8538233E28DCC6A992F51A78F6D48B6B2224A552FFD6E74F75891795DB7BF1973378A7E09D41CDC74D1BECA08554C18E7F6D899D163BDA127E3F8A7FCD2EC0B8894CC948B6D0B459D068DE27F3B5A88C3A7D08F5B02FDAE6410203010001028181009350D77A7197217CEB4D441EF9662A4648EA7C209CD6788285E9BB550A34F2A5FEC6157B930BD57D7EFC83BB5121A199073C8DCFF58FD0A562A6E531940994965BF2BF64E6D2BB01E6FD5AA35B6736EAC92C7C87E5D84D836619BEFC559DBEA053DECAEB21D60070CE4C97DAA5E7D307C21041FCF98B444EE3038EF05CD99F6D024100E216224FE7E7C032E3BC354E679C5F16DC8FE0D8150D0F739817CEE1B84EB7FC8C9CBE4721B3D17086AF86690B9DB20B04A88BC38CD9AD669C168BF1BB3AE9CF024100AC60C57447234683151A6C503455404E3DAF9D3B02A13B6192DC08931F4F62587353AD9539C8B1C4F960633731A2D1EE74B2F4AFC4896EC1F56F65571E4902EF02404B28E6913FDC286602957FF727B4B15BED491B8B5A3C3445CE6B23C8A037F32C4F26908F1F22235F50372BECE202B3BCBBF8B835F32E2E5A2D4BC58C401D0EBB02406F8F197B0D67090560ACFF185126333DEFB22E01B8CBFC1043E95F2B48022A55B01DA1A3880818F4AFA7EFCE12B39B45C604E352B5D173FAD70AC6FD40BE63C5024010833257404BB09181E05F22256503A02D47657E2FD70E27C5210A9878050205C701FAF741FCEDBA689D3DF965FE817C5D3124E81121F16BB13459AD6289DEF7";
      byte[] dataencrypt =  Pki.priEncrypt(plainBytes, Pki.bytesToPri(privatekey.getBytes()));
	  String publickey = "30819F300D06092A864886F70D010101050003818D0030818902818100983C55BC470900855B98A4B65554C9D585ED48419FA43E926F5F3FEA52444BA0565609C04BB99E8538233E28DCC6A992F51A78F6D48B6B2224A552FFD6E74F75891795DB7BF1973378A7E09D41CDC74D1BECA08554C18E7F6D899D163BDA127E3F8A7FCD2EC0B8894CC948B6D0B459D068DE27F3B5A88C3A7D08F5B02FDAE6410203010001" ;
      byte []outputbyte =  Pki.pubDecrypt(dataencrypt, Pki.bytesToPub(publickey.getBytes()));
	  String output = outputbyte.toString();
	
	  System.out.println(" in merchant service encrypted data and decrypted in next line  " + dataencrypt.toString() + "\n" + output );
	 
	 */
	 
	 
	 
	 
	 
	 
	 
	 
	    boolean status = true ; // remove it use above status notation 
	 if(status)
	     {
	     	int presentstatus =  merchantDAO.completeMoneyTransaction(merchantTransaction.getAccountnumber() , merchantTransaction.getCustomeraccountnumber() , merchantTransaction.getAmount()); 
	     	if ( presentstatus == 0)
	     	{
	     	    merchantTransaction.setTransactionstatus("success");
	     		int result = merchantDAO.updateMerchantTransaction(merchantTransaction.getTransactionid());
	     		result = merchantDAO.createExternalUserTransaction(merchantTransaction , externalusername);
	     		return 0;
	     	}
	     	// for successful implementation
	     }
	    else 
	     {
		  
	       return -2;   // for wrong key entered
	     }
	 
	
     }
	return -1; //for wrong  merchant transaction id entry
	
}

}