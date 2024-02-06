package org.iesalandalus.programacion.reservashotel.modelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public class Modelo {

    private Reservas reservas;
    private Habitaciones habitaciones;
    private Huespedes huespedes;



    //METODOS
    public Modelo()
    {

    }

    public void comenzar()
    {
        habitaciones= new Habitaciones();
        huespedes=new Huespedes();
        reservas =new Reservas();

    }
    public void terminar()
    {
        System.out.println("El modelo ha terminado.");
    }

    //Metodos INSERTAR
    public void insertar (Huesped huesped) throws OperationNotSupportedException
    {
        huespedes.insertar(huesped);
    }
    public void insertar (Habitacion habitacion) throws OperationNotSupportedException
    {
        habitaciones.insertar(habitacion);

    }
     public void insertar (Reserva reserva) throws OperationNotSupportedException
     {
        reservas.insertar(reserva);
     }

    //METODOS BUSCAR

    public Huesped buscar(Huesped huesped)
    {
        return new Huesped(huespedes.buscar(huesped));
    }

    public Habitacion buscar(Habitacion habitacion)
    {
        return new Habitacion(habitaciones.buscar(habitacion));
    }

    public Reserva buscar(Reserva reserva)
    {
        return new Reserva(reservas.buscar(reserva));
    }


    //METODOS BORRAR

    public void borrar(Huesped huesped) throws OperationNotSupportedException
    {
        huespedes.borrar(huesped);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException
    {
        habitaciones.borrar(habitacion);
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException
    {
        reservas.borrar(reserva);
    }


    //METODOS GET.

    public List<Huesped> getHuespedes()
    {
        return huespedes.get();
    }

    public List<Habitacion> getHabitaciones()
    {
        return habitaciones.get();
    }

    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion)
    {
        return habitaciones.get(tipoHabitacion);
    }
    public List<Reserva> getReservas()
    {
        return reservas.get();
    }
    public List<Reserva> getReservas(Huesped huesped)
    {
        return reservas.getReservas(huesped);
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion)
    {
        return reservas.getReservas(tipoHabitacion);
    }
    public List<Reserva> getReservasFuturas(Habitacion habitacion)
    {
        return reservas.getReservasFuturas(habitacion);
    }



    //METODOS CHECK IN/OUT
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) //
    {
        reservas.realizarCheckin(reserva, fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha)
    {
        reservas.realizarCheckout(reserva, fecha);
    }















}
