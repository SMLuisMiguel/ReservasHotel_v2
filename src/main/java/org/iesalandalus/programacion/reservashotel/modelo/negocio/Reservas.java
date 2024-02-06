package org.iesalandalus.programacion.reservashotel.modelo.negocio;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservas {

    private int capacidad;
    private int tamano;
    private Reserva [] coleccionReservas;


    //Constructor
    public Reservas(int capacidad)
    {
        if (capacidad<=0)
        {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        else
        {
            this.capacidad=capacidad;
        }
        this.coleccionReservas=new Reserva [capacidad];
        this.tamano=0;
    }

    public Reserva [] get()
    {
        return copiaProfundaReservas();
    }
    public int getCapacidad()
    {
        return capacidad;
    }

    public int getTamano()
    {
        return tamano;
    }

    private Reserva[] copiaProfundaReservas()
    {
        Reserva  [] copiaReservas= new Reserva [coleccionReservas.length];

        for (int i = 0; i < coleccionReservas.length; i++)
        {
            if (coleccionReservas[i] != null)
            {
                Reserva original =coleccionReservas[i];
                Reserva copia= new Reserva(original);
                copiaReservas[i] = copia;
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
            boolean insertado= false;
            for (int i=0; i < coleccionReservas.length; i++)
            {
                Reserva original= coleccionReservas[i];
                if(original == null)
                {
                    coleccionReservas[i]= reserva;
                    tamano++;
                    insertado= true;
                    break;
                }
                else
                {
                    if(original.equals(reserva))
                    {
                        throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
                    }

                }
            }
            if (!insertado)
            {
                throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
            }
        }
    }


    private int buscarIndice(Reserva reserva)
    {
        for (int i = 0; i < coleccionReservas.length; i++)
        {
            Reserva original = coleccionReservas[i];
            if (reserva.equals(original))
            {
                return i;
            }
        }
        return -1;
    }

    private Boolean tamanoSuperado(int indice)
    {
        return tamano >= capacidad;

    }

    private Boolean capacidadSuperada(int indice)
    {
        return indice + 1 > capacidad;
    }

    public Reserva buscar (Reserva reserva)
    {
        if (reserva != null)
        {
            for (int i = 0; i < coleccionReservas.length; i++)
            {
                Reserva original = coleccionReservas[i];
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
            int indiceABorrar = buscarIndice(reserva);

            if (indiceABorrar == -1)
            {
                throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
            }

            tamano--;
            for (int i = indiceABorrar; i < coleccionReservas.length; i++)
            {
                coleccionReservas[i] = null;
                if (i + 1 < coleccionReservas.length)
                {
                    desplazarUnaPosicionHaciaIzquierda(i + 1);
                }
            }
        }
        else
        {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice)
    {
        if(indice < 1)
        {
            throw new IllegalArgumentException("Error ");
        }
        coleccionReservas[indice-1] = coleccionReservas[indice];
        coleccionReservas[indice] = null;
    }

    public Reserva[] getReservas (Huesped huesped)
    {
        if(huesped != null) {
            Reserva[] copiaReservas = new Reserva[coleccionReservas.length];
            int contador = 0;

            for (int i = 0; i < coleccionReservas.length; i++) {
                if (coleccionReservas[i] != null && coleccionReservas[i].getHuesped().equals(huesped)) {
                    Reserva original = coleccionReservas[i];
                    Reserva copia = new Reserva(original);
                    copiaReservas[contador] = copia;
                    contador++;
                }
            }

            return copiaReservas;
        }
        else
        {
            throw new  NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }
    }

    public Reserva[] getReservas (TipoHabitacion tipoHabitacion)
    {
        if(tipoHabitacion != null)
        {
            Reserva[] copiaReservas = new Reserva[coleccionReservas.length];
            int contador = 0;

            for (int i = 0; i < coleccionReservas.length; i++) {
                if (coleccionReservas[i] != null && coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                    Reserva original = coleccionReservas[i];
                    Reserva copia = new Reserva(original);
                    copiaReservas[contador] = copia;
                    contador++;
                }
            }

            return copiaReservas;
        }
        else
        {
            throw new  NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
    }

    public Reserva[] getReservasFuturas (Habitacion habitacion)
    {
        if(habitacion != null) {
            Reserva[] copiaReservas = new Reserva[coleccionReservas.length];
            int contador = 0;

            for (int i = 0; i < coleccionReservas.length; i++) {
                if (coleccionReservas[i] != null && coleccionReservas[i].getHabitacion().equals(habitacion) &&
                        coleccionReservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                    Reserva original = coleccionReservas[i];
                    Reserva copia = new Reserva(original);
                    copiaReservas[contador] = copia;
                    contador++;
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
        for (int i = 0; i < coleccionReservas.length; i++)
        {
            if( coleccionReservas[i] != null && coleccionReservas[i].equals(reserva) )
            {
                coleccionReservas[i].setCheckIn(fecha);
            }
        }
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha)
    {
        for (int i = 0; i < coleccionReservas.length; i++)
        {
            if( coleccionReservas[i] != null && coleccionReservas[i].equals(reserva) )
            {
                coleccionReservas[i].setCheckOut(fecha);
            }
        }
    }

}


