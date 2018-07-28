package cr.ac.cenfotec.classes.encrypt;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptManagerToDES extends EncryptManager {
	private String PASSWORD = "CENFOTEC";
	private final String PATH = "C:/encrypt/symetric/";

	@Override
	public void createKey(String name) throws Exception {
		PASSWORD = name;
	}

	@Override
	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		SecretKey key = readKeyFile(keyName);
		Cipher cifrar = initializeCipher("DESede", Cipher.ENCRYPT_MODE, key);
		saveEncryptMessage(messageName, message, cifrar);
	}

	@Override
	public String decryptMessage(String messageName, String keyName) throws Exception {
		byte[] encryptedMessage = readMessageFile(messageName);
		SecretKey key = readKeyFile(keyName);
		Cipher descifrar = initializeCipher("DESede", Cipher.DECRYPT_MODE, key);
		return showMessage(descifrar, encryptedMessage);
	}

	@Override
	public String getPATH() {
		return PATH;
	}

	private SecretKey readKeyFile(String keyName) throws Exception {
		if (validatePassword(keyName)) {
			String secretKey = keyName;
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] password = md.digest(secretKey.getBytes("utf-8"));
			byte[] codeKey = Arrays.copyOf(password, 24);
			SecretKey key = new SecretKeySpec(codeKey, "DESede");
			return key;
		}
		throw new Exception("Clave incorrecta");
	}

	public boolean validatePassword(String keyName) {
		return PASSWORD.equals(keyName);
	}

}
