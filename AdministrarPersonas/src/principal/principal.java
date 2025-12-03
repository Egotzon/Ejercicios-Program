package principal;

import modelo.Persona;
import utilidades.Util;

public class Principal {

	public static void main(String[] args) {
		Persona[] personas = new Persona[10];
		int opcion;
		int contador = 0;

		do {
			menu();
			opcion = Util.leerInt();

			switch (opcion) {
			case 1:
				contador = introducirPersonas(personas, contador);
				break;
			case 2:
				mostrarMayorEdad(personas, contador);
				break;
			case 3:
				buscarPorDni(personas, contador);
				break;
			case 4:
				mostrarTodas(personas, contador);
				break;
			case 5:
				System.out.println("Gracias por usar el programa:");
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("introduce una opción valida entre 1-5");
				break;
			}
		} while (opcion != 5);
	}

	private static void mostrarTodas(Persona[] personas, int contador) {
		if (contador == 0) {
			System.out.println("No hay personas registradas.");
			return;
		}

		System.out.println("\n--- Lista de Personas ---");
		for (int i = 0; i < contador; i++) {
			System.out.println(personas[i]);
		}
	}

	private static void buscarPorDni(Persona[] personas, int contador) {
		if (contador == 0) {
			System.out.println("No hay personas registradas.");
			return;
		}

		System.out.print("Introduce el DNI a buscar: ");
		String dni = Util.introducirCadena();
		boolean encontrado = false;

		for (int i = 0; i < contador; i++) {
			if (personas[i].getDni().equalsIgnoreCase(dni)) {
				System.out.println("Persona encontrada:");
				System.out.println(personas[i]);
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			System.out.println("No se encontró ninguna persona con ese DNI.");
		}
	}

	private static void mostrarMayorEdad(Persona[] personas, int contador) {
		if (contador == 0) {
			System.out.println("No hay personas registradas.");
			return;
		}

		Persona mayor = personas[0];
		for (int i = 1; i < contador; i++) {
			if (personas[i].getEdad() > mayor.getEdad()) {
				mayor = personas[i];
			}
		}

		System.out.println("\nLa persona de mayor edad es:");
		System.out.println(mayor);
	}

	private static int introducirPersonas(Persona[] personas, int contador) {
		System.out.println("\n--- Introducir Personas ---");

		while (contador < personas.length) {
			System.out.println("Persona " + (contador + 1));
			Persona p = new Persona();

			String dni = leerDNI();
			p.setDni(dni);

			System.out.print("Introduce el nombre: ");
			p.setNombre(Util.introducirCadena());

			System.out.print("Introduce la edad: ");
			p.setEdad(Util.leerInt());

			personas[contador] = p;
			contador++;

			if (contador < personas.length) {
				System.out.print("¿Deseas introducir otra persona? (s/n): ");
				String respuesta = Util.introducirCadena();
				if (!respuesta.equalsIgnoreCase("s")) {
					break;
				}
			} else {
				System.out.println("Se ha alcanzado el número máximo de personas (10).");
			}
		}
		return contador;
	}

	private static String leerDNI() {
		int numero = -1;
		while (true) {
			System.out.print("Introduce los 8 números del DNI: ");
			String entrada = Util.introducirCadena();

			if (entrada.matches("\\d{8}")) {
				numero = Integer.parseInt(entrada);
				break;
			} else {
				System.out.println("❌ Error: El DNI debe tener exactamente 8 números.");
			}
		}

		char letra = calcularLetraDNI(numero);
		String dniCompleto = String.format("%08d%c", numero, letra);
		System.out.println("✅ DNI completo: " + dniCompleto);
		return dniCompleto;
	}

	private static char calcularLetraDNI(int numero) {
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int resto = numero % 23;
		return letras.charAt(resto);
	}

	private static void menu() {
		System.out.println("******************************************************************");
		System.out.println("*                         MENU                                   *");
		System.out.println("******************************************************************");
		System.out.println("1) Introducir personas:                                          *");
		System.out.println("2) Muestra la persona de mayor edad:                             *");
		System.out.println("3) Muestra la información de una persona tras introducir su DNI: *");
		System.out.println("4) Muestra información de todas las personas:                    *");
		System.out.println("5) Salir:                                                        *");
		System.out.println("******************************************************************");
		System.out.println("*                   Introdue una opción                          *");
		System.out.println("******************************************************************");
	}
}
