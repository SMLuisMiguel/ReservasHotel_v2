package org.iesalandalus.programacion.reservashotel.modelo.negocio;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservas {
    private List<Reserva> coleccionReservas;


    //Constructor
    public Reservas()
    {
        this.coleccionReservas= new ArrayList<>();
    }

    public List<Reserva>  get()
    {
        return copiaProfundaReservas();
    }

    public int getTamano()
    {
        return coleccionReservas.size();
    }

    private List<Reserva> copiaProfundaReservas()
    {
        List<Reserva> copiaReservas= new ArrayList<>();

        for (int i = 0; i < coleccionReservas.size(); i++)
        {
            if (coleccionReservas.get(i) != null)
            {
                Reserva original =coleccionReservas.get(i);
                Reserva copia= new Reserva(original);
                copiaReservas.add(copia);
            }
        }
        return copiaReservas;
    }


    public void insertar(Reserva reserva) throws OperationNotSupportedException
    {
        if (reserva == null)
        {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
        else
        {
            for (int i=0; i < coleccionReservas.size(); i++)
            {
                Reserva original= coleccionReservas.get(i);
                if(original.equals(reserva))
                {
                    throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
                }
            }
            coleccionReservas.add(reserva);
            System.out.println("Reserva insertada");
        }
    }


    private int buscarIndice(Reserva reserva)
    {
        return coleccionReservas.indexOf(reserva);
    }

    public Reserva buscar (Reserva reserva)
    {
        if (reserva != null)
        {
            for (int i = 0; i < coleccionReservas.size(); i++)
            {
                Reserva original = coleccionReservas.get(i);
                if (reserva.equals(original))
                {
                    return new Reserva(original);
                }
            }

            return null;
        }
        else
        {
            throw new NullPointerException("ERROR .");
        }
    }

    public void borrar (Reserva reserva) throws OperationNotSupportedException
    {
        if (reserva != null) {
            int indiceABorrar = coleccionReservas.indexOf(reserva);

            if (indiceABorrar == -1)
            {
                throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
            }

            coleccionReservas.remove(indiceABorrar);
        }
        else
        {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }
    }

    public List<Reserva> getReservas (Huesped huesped)
    {
        if(huesped != null) {
            List<Reserva> copiaReservas = new ArrayList<>();

            for (int i = 0; i < coleccionReservas.size(); i++) {
                if (coleccionReservas.get(i) != null && coleccionReservas.get(i).getHuesped().equals(huesped)) {
                    Reserva original = coleccionReservas.get(i);
                    Reserva copia = new Reserva(original);
                    copiaReservas.add(copia);
                }
            }

            return copiaReservas;
        }
        else
        {
            throw new  NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }
    }

    public List<Reserva> getReservas (TipoHabitacion tipoHabitacion)
    {
        if(tipoHabitacion != null)
        {
            List<Reserva> copiaReservas = new ArrayList<>();

            for (int i = 0; i < coleccionReservas.size(); i++) {
                if (coleccionReservas.get(i)  != null && coleccionReservas.get(i) .getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                    Reserva original = coleccionReservas.get(i);
                    Reserva copia = new Reserva(original);
                    copiaReservas.add(copia);
                }
            }

            return copiaReservas;
        }
        else
        {
            throw new  NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
    }

    public List<Reserva> getReservasFuturas (Habitacion habitacion)
    {
        if(habitacion != null) {
            List<Reserva> copiaReservas = new ArrayList<>();

            for (int i = 0; i < coleccionReservas.size(); i++) {
                if (coleccionReservas.get(i) != null && coleccionReservas.get(i).getHabitacion().equals(habitacion) &&
                        coleccionReservas.get(i).getFechaInicioReserva().isAfter(LocalDate.now())) {
                    Reserva original = coleccionReservas.get(i);
                    Reserva copia = new Reserva(original);
                    copiaReservas.add(copia);
                }
            }

            return copiaReservas;
        }
        else
        {
            throw new  NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
    }

    //Tarea 5 metodos checkIn y checkout.
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha)
    {
        for (int i = 0; i < coleccionReservas.size(); i++)
        {
            if( coleccionReservas.get(i) != null && coleccionReservas.get(i).equals(reserva) )
            {
                coleccionReservas.get(i).setCheckIn(fecha);
            }
        }
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha)
    {
        for (int i = 0; i < coleccionReservas.size(); i++)
        {
            if( coleccionReservas.get(i) != null && coleccionReservas.get(i).equals(reserva) )
            {
                coleccionReservas.get(i).setCheckOut(fecha);
            }
        }
    }

}


