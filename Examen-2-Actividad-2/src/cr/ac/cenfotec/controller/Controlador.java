package cr.ac.cenfotec.controller;

import cr.ac.cenfotec.classes.EncryptionType;
import cr.ac.cenfotec.classes.encrypt.EncryptManager;
import cr.ac.cenfotec.classes.encrypt.EncryptManagerFactory;

public class Controlador {
	
	EncryptManager manager;

	public void initializeEncryptMethod (EncryptionType encrypt) {
		manager = EncryptManagerFactory.create(encrypt);
	}
	
	public void createKey(String name) throws Exception {
		manager.createKey(name);
	}
	
	public void encryptMessage(String messageName, String message, String name) throws Exception {
		manager.encryptMessage(messageName, message, name);
	}
	
	public void decryptMessage(String messageName, String keyName) throws Exception {
		manager.decryptMessage(messageName, keyName);
	}
}
