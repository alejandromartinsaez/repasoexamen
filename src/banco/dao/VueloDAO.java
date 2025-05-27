package banco.dao;

import banco.components.Vuelo;

import java.time.LocalDate;
import java.util.List;

public interface VueloDAO {
    boolean guardar(Vuelo vuelo);
    Vuelo buscarPorID(int id);
    List<Vuelo> listarTodos();
    // boolean actualizar(Vuelo vuelo);
    //Vuelo eliminar(int id);

    //List<Vuelo> buscarPorDestino(String destino);
    //List<Vuelo> buscarPorFecha(LocalDate fecha);
    //List<Vuelo> buscarDisponibles();
}

