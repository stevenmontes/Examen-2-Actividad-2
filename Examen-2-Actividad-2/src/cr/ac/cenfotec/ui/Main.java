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

		mostrarMetodosEncriptacion();
		opcion = solicitarOpcion();
		EncryptionType encrypt = validarTipoEncription(opcion);

		while (!salir) {
			mostrarMenuPrincipal();
			opcion = solicitarOpcion();
			salir = leerOpcion(opcion, encrypt);
		}
	}

	private static void mostrarMenuPrincipal() {
		System.out.println("1.Create key");
		System.out.println("2.Encript Message");
		System.out.println("3.Decrypt Message");
		System.out.println("4.Exit ");
	}

	private static EncryptionType validarTipoEncription(int opcion) {
		if (opcion == 1) {
			return EncryptionType.AES;
		} else {
			return EncryptionType.RSA;
		}
	}

	private static boolean leerOpcion(int opcion, EncryptionType encrypt) throws Exception {
		switch (opcion) {
		case 1:
			System.out.println("Key name: ");
			String name = leer.readLine();
			controller.createKey(name, encrypt);
			break;
		case 2:
			System.out.println("Key name: ");
			String name1 = leer.readLine();
			System.out.println("Message name: ");
			String messageName = leer.readLine();
			System.out.println("Message: ");
			String message = leer.readLine();
			controller.encryptMessage(messageName,message,name1, encrypt);
			break;
		case 3:
			System.out.println("Key name: ");
			String keyName = leer.readLine();
			System.out.println("Message name: ");
			String messageName1 = leer.readLine();
			controller.decryptMessage(messageName1, keyName, encrypt);
			break;
		case 4:
			return true;
		default:
			break;
		}
		return false;
	}

	private static int solicitarOpcion() throws NumberFormatException, IOException {
		System.out.println("Choose your option");
		return Integer.parseInt(leer.readLine());
	}

	private static void mostrarMetodosEncriptacion() {
		System.out.println("1. AES.");
		System.out.println("2. RSA.");
		System.out.println("3. Exit.");
	}

}
