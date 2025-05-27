package banco.dao;

import banco.components.Reserva;

import java.time.LocalDate;
import java.util.*;

public interface ReservaDAO {
    boolean guardar(Reserva reserva);                   // ✅
    void buscarPorId(int id);                        // ✅
    List<Reserva> listarPorCliente(int clienteId);      // ✅
    List<Reserva> listarTodos();                        // ✅
    boolean eliminar(int id);                           // ✅

    List<Reserva> buscarPorFecha(LocalDate fecha);      // 🔹 útil para reportes

}
