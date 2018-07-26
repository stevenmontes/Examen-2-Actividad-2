package cr.ac.cenfotec.controller;

import cr.ac.cenfotec.classes.EncryptionType;
import cr.ac.cenfotec.classes.encrypt.EncryptManager;
import cr.ac.cenfotec.classes.encrypt.EncryptManagerFactory;

public class Controlador {
	
	EncryptManager manager;

	public void createKey(String name, EncryptionType encrypt) throws Exception {
		manager = EncryptManagerFactory.create(encrypt);
		manager.createKey(name);
	}
	
	public void encryptMessage(String messageName, String message, String name, EncryptionType encrypt) throws Exception {
		manager = EncryptManagerFactory.create(encrypt);
		manager.encryptMessage(messageName, message, name);
	}
	
	public void decryptMessage(String messageName, String keyName, EncryptionType encrypt) throws Exception {
		manager = EncryptManagerFactory.create(encrypt);
		manager.decryptMessage(messageName, keyName);
	}
}
