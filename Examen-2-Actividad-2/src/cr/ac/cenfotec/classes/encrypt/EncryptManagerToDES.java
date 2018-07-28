package cr.ac.cenfotec.classes.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptManagerToDES extends EncryptManager {
	private final int KEYSIZE = 8;
	private final String PATH = "C:/encrypt/symetric/";

	@Override
	public void createKey(String name) throws Exception {
		byte[] key = generatedSequenceOfBytes();
		writeBytesFile(name, key, KEY_EXTENSION);

	}

	@Override
	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		SecretKey key = readKeyFile();
		Cipher cifrar = initializeCipher("DESede");
		cifrar.init(Cipher.ENCRYPT_MODE, key);
		saveEncryptMessage(messageName, message, cifrar);
	}

	@Override
	public void decryptMessage(String messageName, String keyName) throws Exception {
		byte[] encryptedMessage = readMessageFile(messageName);
		SecretKey key = readKeyFile();
		Cipher descifrar = initializeCipher("DESede");
		descifrar.init(Cipher.DECRYPT_MODE, key);
		showMessage(descifrar, encryptedMessage);
	}

	@Override
	public String getPATH() {
		return PATH;
	}

	private byte[] generatedSequenceOfBytes() throws Exception {
		StringBuilder randomkey = new StringBuilder();
		for (int i = 0; i < KEYSIZE; i++) {
			randomkey.append(Integer.parseInt(Double.toString((Math.random() + 0.1) * 1000).substring(0, 2)));
		}
		return randomkey.toString().getBytes("UTF-8");
	}
	
	private SecretKey readKeyFile() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String secretKey = "cenfotec";
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] password = md.digest(secretKey.getBytes("utf-8"));
		byte[] codeKey = Arrays.copyOf(password, 24);
		SecretKey key = new SecretKeySpec(codeKey, "DESede");
		return key;
	}

}
