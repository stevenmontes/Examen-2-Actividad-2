package cr.ac.cenfotec.classes.encrypt;

public interface EncryptManager {
	public void createKey(String name) throws Exception;

	public void encryptMessage(String messageName, String message, String keyName) throws Exception;

	public void decryptMessage(String messageName, String keyName) throws Exception;
}
