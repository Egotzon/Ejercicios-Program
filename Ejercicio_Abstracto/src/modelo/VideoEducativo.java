package modelo;


// src/modelo/VideoEducativo.java
public class VideoEducativo extends RecursoDigital {
	public VideoEducativo(String titulo, String autor, String fechaPublicacion) {
		super(titulo, autor, fechaPublicacion);
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("Video Educativo: " + titulo + ", Autor: " + autor + ", Publicado: " + fechaPublicacion);
	}

}
