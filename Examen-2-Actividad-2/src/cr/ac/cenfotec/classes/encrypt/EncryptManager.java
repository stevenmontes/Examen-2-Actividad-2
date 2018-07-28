package cr.ac.cenfotec.classes.encrypt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class EncryptManager {
	protected final String KEY_EXTENSION = ".key";
	protected final String MESSAGE_ENCRYPT_EXTENSION = ".encript"; 
	
	public abstract void createKey(String name) throws Exception;

	public abstract void encryptMessage(String messageName, String message, String keyName) throws Exception;

	public abstract void decryptMessage(String messageName, String keyName) throws Exception;

	public abstract String getPATH();
	
	public Cipher initializeCipher(String instance) throws Exception, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance(instance);
		return cipher;
	}

	public void writeBytesFile(String name, byte[] content, String type)
			throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(getPATH() + name + type);
		fos.write(content);
		fos.close();
	}
	
	public byte[] readMessageFile(String messageName) throws Exception {
		File file = new File(getPATH() + messageName + MESSAGE_ENCRYPT_EXTENSION);
		int length = (int) file.length();
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[length];
		reader.read(bytes, 0, length);
		Decoder oneDecoder = Base64.getDecoder();
		reader.close();
		return oneDecoder.decode(bytes);
	}
	
	public void saveEncryptMessage(String messageName, String message, Cipher cipher)
			throws IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException {
		byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
		Encoder oneEncoder = Base64.getEncoder();
		encryptedData = oneEncoder.encode(encryptedData);
		writeBytesFile(messageName, encryptedData, MESSAGE_ENCRYPT_EXTENSION);
	}
	
	public void showMessage(Cipher cipher, byte[] encryptedMessage)
			throws IllegalBlockSizeException, BadPaddingException {
		byte[] decryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(decryptedData, StandardCharsets.UTF_8);
		System.out.println("El mensaje era: \n" + message);
	}

}
