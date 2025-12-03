package principal;

import modelo.*;
import util.Util;

import java.util.ArrayList;

public class BibliotecaApp {

    private ArrayList<Socio> socios;

    public BibliotecaApp() {
        socios = new ArrayList<>();
    }

    // ---------------------- MENÚ ----------------------
    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- BIBLIOTECA LECTURA VIVA ---");
            System.out.println("0. Añadir socio/bibliotecario");
            System.out.println("1. Mostrar todos los socios");
            System.out.println("2. Mostrar bibliotecarios");
            System.out.println("3. Bibliotecarios por sección");
            System.out.println("4. Buscar por nombre");
            System.out.println("5. Buscar por cuota mínima");
            System.out.println("6. Bibliotecarios por antigüedad");
            System.out.println("7. Dar de baja por DNI");
            System.out.println("8. Salir");
            
            opcion = Util.leerInt(0, 8, "Elige opción: ");

            switch (opcion) {
                case 0 -> anadirPersona();
                case 1 -> mostrarTodos();
                case 2 -> mostrarBibliotecarios();
                case 3 -> bibliotecariosPorSeccion();
                case 4 -> buscarPorNombre();
                case 5 -> buscarPorCuota();
                case 6 -> bibliotecariosPorAntiguedad();
                case 7 -> eliminarPorDni();
                case 8 -> System.out.println("Saliendo...");
            }

        } while (opcion != 8);
    }

    // ---------------------- OPCIÓN 0 ----------------------
    public void anadirPersona() {
        String dni = Util.leerString(15, "Introduce DNI:");

        if (buscarPorDni(dni) != null) {
            System.out.println("ERROR: Esa persona ya existe.");
            return;
        }

        String nombre = Util.leerString(30, "Nombre completo:");
        int mes = Util.leerInt(1, 12, "Mes de alta (1-12):");
        int anio = Util.leerInt(1900, 2025, "Año de alta:");
        int libros = Util.leerInt(1, 20, "Libros permitidos:");

        boolean esBiblio = Util.esBoolean("¿Es bibliotecario? (s/n)");

        if (esBiblio) {
            Seccion seccion = null;
            boolean seccionValida = false;
            
            // Bucle para reintentar hasta introducir una sección válida
            while (!seccionValida) {
                System.out.println("Secciones disponibles:");
                for (Seccion s : Seccion.values()) {
                    System.out.println("- " + s);
                }

                String secStr = Util.leerString(20, "Elige sección:");

                try {
                    seccion = Seccion.valueOf(secStr.toUpperCase());
                    seccionValida = true; // Si llega aquí, la sección es válida
                } catch (IllegalArgumentException e) {
                    System.out.println("Sección no válida. Inténtalo de nuevo.");
                }
            }

            socios.add(new Bibliotecario(dni, nombre, mes, anio, libros, seccion));

        } else {
            socios.add(new Socio(dni, nombre, mes, anio, libros));
        }

        System.out.println("Persona registrada correctamente.");
    }

    // ---------------------- OPCIÓN 1 ----------------------
    public void mostrarTodos() {
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados.");
            return;
        }

        socios.forEach(System.out::println);
    }

    // ---------------------- OPCIÓN 2 ----------------------
    public void mostrarBibliotecarios() {
        boolean encontrado = false;

        for (Socio s : socios) {
            if (s.esBibliotecario()) {
                System.out.println(s);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay bibliotecarios registrados.");
        }
    }

    // ---------------------- OPCIÓN 3 ----------------------
    public void bibliotecariosPorSeccion() {
        Seccion sec = null;
        boolean seccionValida = false;
        
        // Bucle para reintentar hasta introducir una sección válida
        while (!seccionValida) {
            System.out.println("Secciones disponibles:");
            for (Seccion s : Seccion.values()) {
                System.out.println("- " + s);
            }

            String secStr = Util.leerString(20, "Introduce sección:");

            try {
                sec = Seccion.valueOf(secStr.toUpperCase());
                seccionValida = true; // Si llega aquí, la sección es válida
            } catch (Exception e) {
                System.out.println("Sección no válida. Inténtalo de nuevo.");
            }
        }

        boolean alguno = false;

        System.out.println("Bibliotecarios de la sección " + sec + ":");

        for (Socio s : socios) {
            if (s.esBibliotecario()) {
                Bibliotecario b = (Bibliotecario) s;
                if (b.getSeccion() == sec) {
                    System.out.println("- " + b.getNombre());
                    alguno = true;
                }
            }
        }

        if (!alguno) {
            System.out.println("No hay bibliotecarios en esa sección.");
        }
    }

    // ---------------------- OPCIÓN 4 ----------------------
    public void buscarPorNombre() {
        String texto = Util.leerString(30, "Introduce nombre o parte:");

        boolean encontrado = false;

        for (Socio s : socios) {
            if (s.getNombre().toLowerCase().contains(texto.toLowerCase())) {

                System.out.println("DNI: " + s.getDni());
                System.out.println("Años en la biblioteca: " + s.getAntiguedad());
                System.out.println("¿Es bibliotecario? " + (s.esBibliotecario() ? "Sí" : "No"));
                System.out.println();

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay coincidencias.");
        }
    }

    // ---------------------- OPCIÓN 5 ----------------------
    public void buscarPorCuota() {
        double cuota = Util.leerDouble("Cuota mínima: ");

        boolean hallado = false;

        for (Socio s : socios) {
            if (s.getCuotaFinal() >= cuota) {
                System.out.print(s.getNombre());
                if (s.esBibliotecario())
                    System.out.print(" (Bibliotecario)");
                System.out.println(" → Cuota: " + s.getCuotaFinal());
                hallado = true;
            }
        }

        if (!hallado) {
            System.out.println("Ningún socio cumple esa cuota.");
        }
    }

    // ---------------------- OPCIÓN 6 ----------------------
    public void bibliotecariosPorAntiguedad() {
        int años = Util.leerInt("Introduce años mínimos:");

        boolean existe = false;

        for (Socio s : socios) {
            if (s.esBibliotecario() && s.getAntiguedad() >= años) {
                System.out.println(s.getNombre() + " → " + s.getAntiguedad() + " años");
                existe = true;
            }
        }

        if (!existe) {
            System.out.println("Ningún bibliotecario cumple ese tiempo.");
        }
    }

    // ---------------------- OPCIÓN 7 ----------------------
    public void eliminarPorDni() {
        String dni = Util.leerString(15, "Introduce DNI:");

        Socio s = buscarPorDni(dni);
        if (s == null) {
            System.out.println("No existe ese socio.");
            return;
        }

        socios.remove(s);
        System.out.println("Eliminado correctamente.");
    }

    private Socio buscarPorDni(String dni) {
        for (Socio s : socios) {
            if (s.getDni().equalsIgnoreCase(dni))
                return s;
        }
        return null;
    }
}