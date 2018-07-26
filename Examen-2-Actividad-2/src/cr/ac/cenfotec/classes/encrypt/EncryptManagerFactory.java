package cr.ac.cenfotec.classes.encrypt;

import cr.ac.cenfotec.classes.EncryptionType;

public class EncryptManagerFactory {

	public static EncryptManager create (EncryptionType encrypt) {
		switch(encrypt) {
		case AES:
			return new EncryptManagerToAES();
		case RSA:
			return new EncryptManagerToRSA();
		default:
			return null;
		}
	}
}
