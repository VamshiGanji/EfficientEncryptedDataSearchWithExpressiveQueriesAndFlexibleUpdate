package in.nareshit.cva.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.nareshit.cva.model.UploadFile;

public class FileEncryption {

	private static Logger logger = LoggerFactory.getLogger(FileEncryption.class);

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";

	public static UploadFile encrypt(String key, InputStream inputFile)
			throws Exception {

		return doCrypto(Cipher.ENCRYPT_MODE, key, inputFile);
	}

	public static UploadFile decrypt(String key, InputStream inputFile)
			throws Exception {
		return doCrypto(Cipher.DECRYPT_MODE, key, inputFile);
	}

	private static UploadFile doCrypto(int cipherMode, String key, InputStream inputFile) {
		logger.info("==================>> FileEncryption::doCrypto::started <<==================");
		
		UploadFile uploadFile=new UploadFile();
		Cipher cipher;
		byte[] outputBytes = null;
		InputStream inputFileReader = null;
		try {
			if(cipherMode==Cipher.ENCRYPT_MODE) {
				cipher = Cipher.getInstance(TRANSFORMATION);
				System.out.println("-----------------> cipher::"+cipher);

				/* Custom Key Generator */
				/*		KeyGenerator keygen = KeyGenerator.getInstance("AES");
				keygen.init(128);
				byte[] key2 = keygen.generateKey().getEncoded();
				System.out.println("byte::"+key2);
				SecretKey secretKey =new SecretKeySpec(key2, ALGORITHM);
				 */
				SecretKey secretKey =new SecretKeySpec(key.getBytes(), ALGORITHM);

				cipher.init(cipherMode, secretKey);

				String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
				System.out.println(encodedSecretKey);
				logger.info("-----------------> Encoded secretKey::"+encodedSecretKey+ " <-----------------");
				uploadFile.setFileKey(encodedSecretKey);
			}else{
				cipher = Cipher.getInstance(ALGORITHM);
				String decodedSecretKey=new String(Base64.getDecoder().decode(key));
				uploadFile.setFileKey(decodedSecretKey);
				logger.info("-----------------> Decoded secretKey::"+decodedSecretKey+ " <-----------------");
				//SecretKey secretKey =new SecretKeySpec(decode,0,decode.length, ALGORITHM);
				Key key1=new SecretKeySpec(decodedSecretKey.getBytes(), ALGORITHM);
				cipher.init(cipherMode, key1); 
			}

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] inputBytesReader = new byte[(int) inputFile.available()];
			while ((nRead = inputFile.read(inputBytesReader, 0, inputBytesReader.length)) != -1) {
				buffer.write(inputBytesReader, 0, nRead);
				logger.info("-----------------> ::"+nRead+ " <-----------------");
			}
			buffer.flush();
			byte[] byteArray = buffer.toByteArray();

			outputBytes = cipher.doFinal(byteArray);
			inputFileReader= new ByteArrayInputStream(outputBytes);

			int count=0;
			for(int i=0; i< (int) inputFileReader.available() ; i++) {
				String collectByte="";
				collectByte=collectByte+outputBytes[i];
				if(count==25) {
					count=0;
					System.out.println(Base64.getEncoder().encodeToString(collectByte.getBytes()));
				}else System.out.print(Base64.getEncoder().encodeToString(collectByte.getBytes()));
				count++;
			}

			uploadFile.setFile(inputFileReader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(new String(Base64.getEncoder().encodeToString(outputBytes)));
		//System.out.println(Base64.getEncoder().encodeToString(outputBytes));
		logger.info("==================>> FileEncryption::doCrypto::Ended <<==================");

		return uploadFile;
	}

}
