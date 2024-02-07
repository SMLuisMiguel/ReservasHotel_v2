package org.iesalandalus.programacion.reservashotel.modelo.negocio;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Habitaciones {
    private List<Habitacion> coleccionHabitaciones;

    //constructor
    public Habitaciones ()
    {
        this.coleccionHabitaciones = new ArrayList<>();
    }

    public List<Habitacion> get()
    {
        return copiaProfundaHabitaciones();
    }

    public List<Habitacion> get(TipoHabitacion tipoHabitacion)
    {
        List<Habitacion> copiaHabitaciones = new ArrayList<>();

        for (int i = 0; i < coleccionHabitaciones.size(); i++)
        {
            if (coleccionHabitaciones.get(i).getTipoHabitacion().equals(tipoHabitacion))
            {
                Habitacion original = coleccionHabitaciones.get(i);
                Habitacion copia= new Habitacion(original);
                copiaHabitaciones.add(copia);
            }
        }

        return copiaHabitaciones;
    }


    private List<Habitacion> copiaProfundaHabitaciones()
    {
        List<Habitacion> copiaHabitaciones= new ArrayList<>();

        for (int i = 0; i < coleccionHabitaciones.size(); i++)
        {
            if (coleccionHabitaciones.get(i) != null)
            {
                Habitacion original = coleccionHabitaciones.get(i);
                Habitacion copia= new Habitacion(original);
                copiaHabitaciones.add(copia);
            }
        }

        return copiaHabitaciones;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException
    {
        if (habitacion == null)
        {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }
        else
        {
           for (int i=0; i < coleccionHabitaciones.size(); i++)
           {
               Habitacion original= coleccionHabitaciones.get(i);
               if(original.equals(habitacion))
               {
                   throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
               }
           }
           coleccionHabitaciones.add(habitacion);
           System.out.println("Habitacion insertada.");
        }
    }

    public Habitacion buscar (Habitacion habitacion)
    {
        if (habitacion != null)
        {
            for (int i = 0; i < coleccionHabitaciones.size(); i++)
            {
                Habitacion original = coleccionHabitaciones.get(i);
                if (habitacion.equals(original))
                {
                    return new Habitacion(original);
                }
            }

            return null;
        }
        else
        {
            throw new NullPointerException("ERROR 4.");
        }
    }

    public void borrar (Habitacion habitacion) throws OperationNotSupportedException
    {
        if (habitacion != null) {
            int indiceABorrar = coleccionHabitaciones.indexOf(habitacion);

            if (indiceABorrar == -1) {
                throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
            }

            coleccionHabitaciones.remove(indiceABorrar);
        } else {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }
    }

    public int getTamano()
    {
        return coleccionHabitaciones.size();
    }
}
