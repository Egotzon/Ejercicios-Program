package modelo;

import java.time.LocalDate;

public class Alumno implements Comparable<Alumno> {

	
    private String nif;
    private String nombre;
    private LocalDate fechaNacimiento;
    private Ciclo ciclo;
    private boolean repetidor;

    public Alumno(String nif, String nombre, LocalDate fechaNacimiento, Ciclo ciclo, boolean repetidor) {
        this.nif = nif;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.ciclo = ciclo;
        this.repetidor = repetidor;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public boolean isRepetidor() {
        return repetidor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setRepetidor(boolean repetidor) {
        this.repetidor = repetidor;
    }

    @Override
    public String toString() {
        return "NIF: " + nif +
               " | Nombre: " + nombre +
               " | Nacimiento: " + fechaNacimiento +
               " | Ciclo: " + ciclo +
               " | Repetidor: " + (repetidor ? "Sí" : "No");
    }

	@Override
    public int compareTo(Alumno otro) {
        // Ordena por nombre alfabéticamente
        return this.nif.compareToIgnoreCase(otro.nif);
    }

}
