package principal;

import java.util.HashMap;
import java.util.Iterator;

import modelo.Socio;

public class Principal {

    private BibliotecaApp app;

    public Principal() {
        app = new BibliotecaApp();
    }

    public void iniciar() {
        app.menu();
    }

    public static void main(String[] args) {
    	@SuppressWarnings("unused")
		HashMap<String, Socio> socio = new HashMap<String, Socio>();
    	
    	@SuppressWarnings("unused")
		Socio s1;
    	@SuppressWarnings("unused")
		Socio s2;
    	
    	s1 = new Socio("12345678A", "Ana Garcia", 5, 2020, 5);
    	s2 = new Socio("87654321B", "Luis Perez", 3, 2019, 7);
    	
    	//Añadir los socios al HasMap
    	socio.put(s1.getDni(), s1);
    	socio.put(s2.getDni(), s2);
    	
    	// Opción 4 iterator con claves
 
    	Iterator<HashMap.Entry<String, Socio>> it = socio.entrySet().iterator();
    	while (it.hasNext()) {
			HashMap.Entry<String, Socio> entry = it.next();
			System.out.println("DNI: " + entry.getKey() + ", Socio: " + entry.getValue());
    	}

        new Principal().iniciar();
        
    }
}
