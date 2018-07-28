package cr.ac.cenfotec.classes.encrypt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

public class EncryptManagerToRSA extends EncryptManager {

	private final String PUBLIC = "public";
	private final String PRIVATE = "private";
	private final String PATH = "C:/encrypt/asymetric/";

	@Override
	public void createKey(String name) throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		KeyFactory fact = KeyFactory.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.genKeyPair();
		RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(), RSAPublicKeySpec.class);
		RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(), RSAPrivateKeySpec.class);

		saveToFile(PATH + name + "public.key", pub.getModulus(), pub.getPublicExponent());
		saveToFile(PATH + name + "private.key", priv.getModulus(), priv.getPrivateExponent());
	}
	
	@Override
	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		PublicKey pubKey = (PublicKey) readKeyFromFile(keyName, PUBLIC);
		Cipher cipher = initializeCipher("RSA", Cipher.ENCRYPT_MODE, pubKey);
		saveEncryptMessage(messageName, message, cipher);
	}
	
	@Override
	public String decryptMessage(String messageName, String keyName) throws Exception {
		PrivateKey privKey = (PrivateKey) readKeyFromFile(keyName, PRIVATE);
		Cipher cipher = initializeCipher("RSA", Cipher.DECRYPT_MODE, privKey);
		byte[] encryptedMessage = readMessageFile(messageName);
		return showMessage(cipher, encryptedMessage);
	}

	private void saveToFile(String fileName, BigInteger mod, BigInteger exp) throws IOException {
		ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		try {
			oout.writeObject(mod);
			oout.writeObject(exp);
		} catch (Exception e) {
			throw new IOException("Unexpected error", e);
		} finally {
			oout.close();
		}
	}

	private Key readKeyFromFile(String keyFileName, String type) throws IOException {
		InputStream in = new FileInputStream(PATH + keyFileName + type + KEY_EXTENSION);
		ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));
		try {
			BigInteger m = (BigInteger) oin.readObject();
			BigInteger e = (BigInteger) oin.readObject();
			if (type.equals("public")) {
				RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
				KeyFactory fact = KeyFactory.getInstance("RSA");
				PublicKey pubKey = fact.generatePublic(keySpec);
				return pubKey;
			} else {
				RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
				KeyFactory fact = KeyFactory.getInstance("RSA");
				PrivateKey pubKey = fact.generatePrivate(keySpec);
				return pubKey;
			}
		} catch (Exception e) {
			throw new RuntimeException("Spurious serialisation error", e);
		} finally {
			oin.close();
		}
	}

	@Override
	public String getPATH() {
		return PATH;
	}
}
