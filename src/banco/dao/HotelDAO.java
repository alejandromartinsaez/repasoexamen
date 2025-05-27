package banco.dao;

import banco.components.Hotel;

import java.util.List;

public interface HotelDAO {
    void guardar(Hotel hotel);                       // ✅
    void buscarPorId(int id);                          // ✅
    List<Hotel> listarTodos();                          // ✅
    void actualizar(Hotel hotel);                    // ✅
    void eliminar(int id);                           // ✅

    List<Hotel> buscarPorCiudad(String ciudad);         // 🔹
    List<Hotel> buscarDisponibles();                    // 🔹 habitaciones > 0

}
