package cr.ac.cenfotec.classes.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import cr.ac.cenfotec.classes.encrypt.EncryptManagerToDES;

public class EncryptManagerToDESTest {

	EncryptManagerToDES DES;

	@Before
	public void initObjects() {
		DES = new EncryptManagerToDES();
	}

	@Test
	public void testCrearLlave() throws Exception {
		DES.createKey("test");
		assertTrue(DES.validatePassword("test"));
	}
	
	@Test
	public void testCrearLlave1() throws Exception {
		DES.createKey("test");
		assertFalse(DES.validatePassword("tests"));
	}

	private void eliminarArchivo(File archivo) {
		archivo.delete();
	}

	@Test
	public void testGuardarEncriptacion() throws Exception {
		DES.createKey("test");
		DES.encryptMessage("test", "test is the best", "test");
		File encript = new File("C:/encrypt/symetric/test.encript");
		assertTrue(encript.exists());
		eliminarArchivo(encript);
	}
	
	@Test(expected = Exception.class)
	public void testGuardarDatoConClaveIncorrecta() throws Exception {
		DES.createKey("test");
		DES.encryptMessage("test", "test is the best", "test11111");
		File encript = new File("C:/encrypt/symetric/test.encript");
		assertTrue(encript.exists());
		eliminarArchivo(encript);
	}

	@Test
	public void testDesencriptar() throws Exception {
		DES.createKey("test");
		DES.encryptMessage("test", "test is the best", "test");
		File encript = new File("C:/encrypt/symetric/test.encript");
		String resultadoEsperado = "El mensaje era: \ntest is the best";
		assertTrue(encript.exists());
		assertEquals(resultadoEsperado, DES.decryptMessage("test", "test"));
		eliminarArchivo(encript);
	}	

	@Test(expected = Exception.class)
	public void testDesencriptarConError() throws Exception {
		DES.createKey("test");
		DES.encryptMessage("test", "test is the best", "test");
		File encript = new File("C:/encrypt/symetric/test.encript");
		String resultadoEsperado = "El mensaje era: \ntest is the best";
		assertTrue(encript.exists());
		assertEquals(resultadoEsperado, DES.decryptMessage("test", "test1111"));
		eliminarArchivo(encript);
	}
}
