package cr.ac.cenfotec.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cr.ac.cenfotec.classes.EncryptionType;
import cr.ac.cenfotec.controller.Controlador;

public class Main {

	private static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
	private static Controlador controller = new Controlador();

	public static void main(String[] args) throws Exception {
		boolean salir = false;
		int opcion;

		controller.initializeEncryptMethod(fijarMetodoEncriptacion());

		while (!salir) {
			mostrarMenuPrincipal();
			opcion = solicitarOpcion();
			salir = leerOpcion(opcion);
		}
	}

	private static EncryptionType fijarMetodoEncriptacion() throws IOException {
		int opcion;
		EncryptionType encrypt;

		do {
			mostrarMetodosEncriptacion();
			opcion = solicitarOpcion();
			encrypt = validarTipoEncription(opcion);
		} while (opcion != 1 && opcion != 2 && opcion != 3);

		return encrypt;
	}

	private static EncryptionType validarTipoEncription(int opcion) {
		if (opcion == 1) {
			return EncryptionType.AES;
		} else if(opcion == 2){
			return EncryptionType.RSA;
		} else {
			return EncryptionType.DES;
		}
	}

	private static void mostrarMenuPrincipal() {
		System.out.println("1.Create key");
		System.out.println("2.Encript Message");
		System.out.println("3.Decrypt Message");
		System.out.println("4.Exit ");
	}

	private static boolean leerOpcion(int opcion) throws Exception {
		String keyName;
		String messageName;
		String message;
		
		switch (opcion) {
		case 1:
			keyName = solicitarInformacion("Key name: ");
			controller.createKey(keyName);
			break;
		case 2:
			keyName = solicitarInformacion("Key name: ");
			messageName = solicitarInformacion("Message name: ");
			message = solicitarInformacion("Message: ");
			controller.encryptMessage(messageName, message, keyName);
			break;
		case 3:
			keyName = solicitarInformacion("Key name: ");
			messageName = solicitarInformacion("Message name: ");
			controller.decryptMessage(messageName, keyName);
			break;
		case 4:
			return true;
		default:
			break;
		}
		return false;
	}

	private static String solicitarInformacion(String message) throws IOException {
		System.out.println(message);
		return leer.readLine();
	}

	private static int solicitarOpcion() throws NumberFormatException, IOException {
		System.out.println("Choose your option");
		return Integer.parseInt(leer.readLine());
	}

	private static void mostrarMetodosEncriptacion() {
		System.out.println("1. AES.");
		System.out.println("2. RSA.");
		System.out.println("3. DES.");
	}

}
