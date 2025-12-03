package modelo;


// src/modelo/Audiolibro.java
public class Audiolibro extends RecursoDigital implements Prestable {
	private String fechaPrestamo;
	private int diasPrestamo;

	public Audiolibro(String titulo, String autor, String fechaPublicacion, String fechaPrestamo, int diasPrestamo) {
		super(titulo, autor, fechaPublicacion);
		this.fechaPrestamo = fechaPrestamo;
		this.diasPrestamo = diasPrestamo;
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("Audiolibro: " + titulo + ", Autor: " + autor + ", Publicado: " + fechaPublicacion);
	}

	@Override
	public String calcularFechaDevolucion() {
		return "Fecha de devolución calculada";
	}

	@Override
	public boolean tieneMulta() {
		return false;
	}

}
