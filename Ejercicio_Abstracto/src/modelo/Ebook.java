package modelo;


// src/modelo/Ebook.java
public class Ebook extends RecursoDigital implements Prestable {
	private String fechaPrestamo;
	private int diasPrestamo;

	public Ebook(String titulo, String autor, String fechaPublicacion, String fechaPrestamo, int diasPrestamo) {
		super(titulo, autor, fechaPublicacion);
		this.fechaPrestamo = fechaPrestamo;
		this.diasPrestamo = diasPrestamo;
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("Ebook: " + titulo + ", Autor: " + autor + ", Publicado: " + fechaPublicacion);
	}

	@Override
	public String calcularFechaDevolucion() {
		// Lógica para calcular la fecha de devolución
		return "Fecha de devolución calculada";
	}

	@Override
	public boolean tieneMulta() {
		// Lógica para determinar si hay multa
		return false;
	}}

