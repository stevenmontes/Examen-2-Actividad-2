package cr.ac.cenfotec.classes.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import cr.ac.cenfotec.classes.encrypt.EncryptManagerToRSA;

public class EncryptManagerToRSATest {

	EncryptManagerToRSA RSA;

	@Before
	public void initObjects() {
		RSA = new EncryptManagerToRSA();
	}

	@Test
	public void testCrearLlave() throws Exception {
		RSA.createKey("test");
		File keyPrivate = new File("C:/encrypt/asymetric/testpublic.key");
		File keyPublic = new File("C:/encrypt/asymetric/testprivate.key");
		assertTrue(keyPrivate.exists());
		assertTrue(keyPublic.exists());
		eliminarArchivo(keyPrivate);
		eliminarArchivo(keyPublic);
	}

	private void eliminarArchivo(File archivo) {
		archivo.delete();
	}

	@Test
	public void testGuardarEncriptacion() throws Exception {
		RSA.createKey("test");
		RSA.encryptMessage("test", "test is the best", "test");
		File encript = new File("C:/encrypt/asymetric/test.encript");
		File keyPrivate = new File("C:/encrypt/asymetric/testpublic.key");
		File keyPublic = new File("C:/encrypt/asymetric/testprivate.key");
		assertTrue(encript.exists());
		eliminarArchivo(encript);
		eliminarArchivo(keyPrivate);
		eliminarArchivo(keyPublic);
	}

	@Test
	public void testDesencriptar() throws Exception {
		RSA.createKey("test");
		RSA.encryptMessage("test", "test is the best", "test");
		File encript = new File("C:/encrypt/asymetric/test.encript");
		File keyPrivate = new File("C:/encrypt/asymetric/testpublic.key");
		File keyPublic = new File("C:/encrypt/asymetric/testprivate.key");
		String resultadoEsperado = "El mensaje era: \ntest is the best";
		assertTrue(encript.exists());
		assertEquals(resultadoEsperado, RSA.decryptMessage("test", "test"));
		eliminarArchivo(encript);
		eliminarArchivo(keyPrivate);
		eliminarArchivo(keyPublic);
	}
}
