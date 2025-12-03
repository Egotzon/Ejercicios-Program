package principal;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

import modelo.Alumno;
import modelo.Ciclo;
import utilidades.Util;

public class Principal {

    static ArrayList<Alumno> alumnos = new ArrayList<>();
    


    public static void main(String[] args) {
    	
 

        
        int opcion;

        do {
            menu();
            opcion = Util.leerInt();

            switch (opcion) {

            case 1: // Matricular alumno
                matricularAlumno();
                break;

            case 2: // Listado
                listarAlumnos();
                break;

            case 3: // Info completa por NIF
                infoAlumno();
                break;

            case 4: // Modificar datos
                modificarAlumno();
                break;

            case 5: // Modificar repetidor DAW por edad
                modificarRepetidoresDAW();
                break;

            case 6: // Dar de baja
                eliminarAlumno();
                break;

            case 7:
                System.out.println("Gracias por usar el programa.");
                break;
			case 8: // Ordenar con compareTo
				ordenarAlumnos();
				break;
            default:
                System.out.println("Introduce una opción válida entre 1 y 7.");
            }
        } while (opcion != 7);
    }

    private static void ordenarAlumnos() {
        System.out.println("Antes de ordenar: " + alumnos);

        Collections.sort(alumnos);  // usa compareTo de Alumno

        System.out.println("Después de ordenar: " + alumnos);
   Collections.sort(alumnos, Collections.reverseOrder());
   System.out.println("Después de ordenar en reverse: " + alumnos);

	}

	// ------------------------- MENÚ ------------------------------
    private static void menu() {
        System.out.println("*********************************************************************************");
        System.out.println("*                                     MENU                                      *");
        System.out.println("*********************************************************************************");
        System.out.println("1) Matricular Alumno                                                            *");
        System.out.println("2) Listado de Alumno/a/s                                                        *");
        System.out.println("3) Listado de toda la información disponible de un alumno/a                     *");
        System.out.println("4) Modificar los datos de un alumno                                             *");
        System.out.println("5) Modificar el campo repetidor de los alumnos de DAW con una edad determinada  *");
        System.out.println("6) Dar de baja a un alumno/a                                                    *");
        System.out.println("7) Salir del menú                                                               *");
        System.out.println("8) Ordenar con el compare to                                                    *");
        System.out.println("*********************************************************************************");
        System.out.println("*                   Introduce una opción                                        *");
        System.out.println("*********************************************************************************");
    }

    // -----------------------------------------------------------
    // Auxiliar: leer fecha con validación
    // -----------------------------------------------------------
    
    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.print(mensaje);
            String entrada = Util.introducirCadena();
            try {
                fecha = LocalDate.parse(entrada); // espera AAAA-MM-DD
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Debe ser AAAA-MM-DD. Inténtalo de nuevo.");
            } catch (Exception e) {
                System.out.println("Error al leer la fecha. Inténtalo de nuevo.");
            }
        }

        return fecha;
    }

    // opcion 1 del Menu (Matricular)
    private static void matricularAlumno() {

        boolean seguir = true;

        while (seguir) {

            System.out.print("Introduce NIF: ");
            String nif = Util.introducirCadena();

            long registros = alumnos.stream()
                    .filter(a -> a.getNif().equalsIgnoreCase(nif))
                    .count();

            if (registros == 2) {
                System.out.println("Este alumno ya está matriculado en DAM y DAW.");
                return;
            }

            System.out.print("Introduce nombre: ");
            String nombre = Util.introducirCadena();

            // VALIDACIÓN DE FECHA: usa el nuevo método
            LocalDate fecha = leerFecha("Fecha nacimiento (AAAA-MM-DD): ");

            // Ciclo
            Ciclo ciclo = null;
            while (ciclo == null) {
                System.out.print("Ciclo (DAM/DAW): ");
                try {
                    ciclo = Ciclo.valueOf(Util.introducirCadena().toUpperCase());
                } catch (Exception e) {
                    System.out.println("Ciclo no válido. Introduce DAM o DAW.");
                }
            }

         // Ya matriculado en ese ciclo
            final Ciclo cicloFinal = ciclo;
            boolean yaEsta = alumnos.stream()
                    .anyMatch(a -> a.getNif().equalsIgnoreCase(nif) && a.getCiclo() == cicloFinal);

            if (yaEsta) {
                System.out.println("Este alumno ya está matriculado en ese ciclo.");
            } else {
                System.out.print("¿Es repetidor? (S/N): ");
                boolean rep = Util.introducirCadena().equalsIgnoreCase("S");

                alumnos.add(new Alumno(nif, nombre, fecha, ciclo, rep));
                System.out.println("Alumno matriculado correctamente.");
            }

            System.out.print("¿Deseas matricular otro alumno? (S/N): ");
            seguir = Util.introducirCadena().equalsIgnoreCase("S");
        }
    }

    // Opcion 2 del Menu (Listado)
    private static void listarAlumnos() {

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }
        Collections.sort(alumnos);
        System.out.println("----- LISTADO DE ALUMNOS -----");
        for (Alumno a : alumnos) {
           System.out.println(a);
        }
    }

    // Opcion 3 del Menu (Info completa por NIF)
    private static void infoAlumno() {

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        System.out.print("Introduce NIF: ");
        String nif = Util.introducirCadena();

        boolean encontrado = false;

        for (Alumno a : alumnos) {
            if (a.getNif().equalsIgnoreCase(nif)) {
                System.out.println(a);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No existe ningún alumno con ese NIF.");
        }
    }

    // Opcion 4 del Menu (Modificar datos)
    private static void modificarAlumno() {

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        System.out.print("Introduce NIF del alumno a modificar: ");
        String nif = Util.introducirCadena();

        boolean modificado = false;

        for (Alumno a : alumnos) {

            if (a.getNif().equalsIgnoreCase(nif)) {

                System.out.print("Nuevo nombre: ");
                a.setNombre(Util.introducirCadena());

                // VALIDACIÓN DE FECHA al modificar
                a.setFechaNacimiento(leerFecha("Nueva fecha de nacimiento (AAAA-MM-DD): "));

                System.out.print("¿Es repetidor? (S/N): ");
                a.setRepetidor(Util.introducirCadena().equalsIgnoreCase("S"));

                modificado = true;
            }
        }

        if (!modificado)
            System.out.println("No se encontró un alumno con ese NIF.");
        else
            System.out.println("Alumno modificado correctamente.");
    }

    // Opcion 5 (Modificar repetidores DAW por edad)
    private static void modificarRepetidoresDAW() {

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        System.out.print("Introduce la edad: ");
        int edad = Util.leerInt();

        int anioActual = LocalDate.now().getYear();
        boolean cambiado = false;

        for (Alumno a : alumnos) {
            int edadAlumno = anioActual - a.getFechaNacimiento().getYear();

            if (a.getCiclo() == Ciclo.DAW && edadAlumno == edad) {
                a.setRepetidor(!a.isRepetidor());
                cambiado = true;
            }
        }

        if (!cambiado)
            System.out.println("No existen alumnos de DAW con esa edad.");
        else
            System.out.println("Repetidores modificados.");
    }

    // Opcion 6 del Menu (Eliminar)
    private static void eliminarAlumno() {

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        System.out.print("Introduce NIF: ");
        String nif = Util.introducirCadena();

        // Recoger todos los registros de ese NIF
        ArrayList<Alumno> encontrados = new ArrayList<>();

        for (Alumno a : alumnos) {
            if (a.getNif().equalsIgnoreCase(nif)) {
                encontrados.add(a);
            }
        }

        if (encontrados.isEmpty()) {
            System.out.println("No existe ningún alumno con ese NIF.");
            return;
        }

        System.out.println("Registros encontrados:");
        encontrados.forEach(a -> System.out.println(a));

        System.out.print("¿Confirmar borrado? (S/N): ");
        if (Util.introducirCadena().equalsIgnoreCase("S")) {

            alumnos.removeIf(a -> a.getNif().equalsIgnoreCase(nif));
            System.out.println("Alumno eliminado correctamente.");

        } else {
            System.out.println("Operación cancelada.");
        }
    }
}
