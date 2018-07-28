package cr.ac.cenfotec.classes.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import cr.ac.cenfotec.classes.encrypt.EncryptManagerToAES;

public class EncryptManagerToAESTest {

	EncryptManagerToAES AES;
	
	@Before
	public void initObjects() {
		AES = new EncryptManagerToAES();
	}
	
	@Test
	public void testCrearLlave() throws Exception {
		File prueba = crearArchivoLlave();
		assertTrue(prueba.exists());
		eliminarArchivo(prueba);
	}

	private File crearArchivoLlave() throws Exception {
		AES.createKey("test");
		File prueba = new File("C:/encrypt/symetric/test.key");
		return prueba;
	}
	
	@Test
	public void testGuardarEncriptacion() throws Exception {
		AES.createKey("test");
		AES.encryptMessage("test", "test is the best", "test");
		File encript = new File("C:/encrypt/symetric/test.encript");
		File key = new File("C:/encrypt/symetric/test.key");
		assertTrue(encript.exists());
		eliminarArchivo(encript);
		eliminarArchivo(key);
	}
	
	@Test(expected = Exception.class)
	public void testGuardarEncriptacion1() throws Exception {
		AES.createKey("test");
		AES.encryptMessage("test", "test is the best", "test2");
		File encript = new File("C:/encrypt/symetric/test.encript");
		File key = new File("C:/encrypt/symetric/test.key");
		assertTrue(encript.exists());
		eliminarArchivo(encript);
		eliminarArchivo(key);
	}

	private void eliminarArchivo(File key) {
		key.delete();
	}
	
	@Test
	public void testDesencriptar() throws Exception {
		AES.createKey("test");
		AES.encryptMessage("test", "test is the best", "test");
		File encript = new File("C:/encrypt/symetric/test.encript");
		File key = new File("C:/encrypt/symetric/test.key");
		String resultadoEsperado = "El mensaje era: \ntest is the best";
		assertTrue(encript.exists());
		assertEquals(resultadoEsperado, AES.decryptMessage("test", "test"));
		eliminarArchivo(encript);
		eliminarArchivo(key);
	}
	
	@Test(expected = Exception.class)
	public void testDesencriptar1() throws Exception {
		AES.createKey("test");
		AES.encryptMessage("test", "test is the best", "test");
		File encript = new File("C:/encrypt/symetric/test.encript");
		File key = new File("C:/encrypt/symetric/test.key");
		String resultadoEsperado = "El mensaje era: \ntest is the best";
		assertTrue(encript.exists());
		assertEquals(resultadoEsperado, AES.decryptMessage("test", "test1"));
		eliminarArchivo(encript);
		eliminarArchivo(key);
	}

}
