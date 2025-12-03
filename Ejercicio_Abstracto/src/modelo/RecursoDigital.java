package modelo;

public abstract class RecursoDigital {	
	
	protected String titulo;
	protected String autor;
	protected String fechaPublicacion;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}



	public RecursoDigital(String titulo, String autor, String fechaPublicacion) {
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
	}

	public abstract void mostrarInformacion();
}