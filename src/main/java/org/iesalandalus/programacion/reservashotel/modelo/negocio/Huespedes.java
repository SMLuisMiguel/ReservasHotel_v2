package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Huespedes {

    private List<Huesped> coleccionHuespedes;

    public Huespedes ()
    {
        this.coleccionHuespedes=new ArrayList<>();
    }

    public List<Huesped>  get()
    {
        return copiaProfundaHuespedes();
    }

    private List<Huesped>  copiaProfundaHuespedes()
    {
        List<Huesped>  copiaHuespedes = new ArrayList<>();

        for (int i = 0; i < coleccionHuespedes.size(); i++)
        {
            if (coleccionHuespedes.get(i) != null)
            {
                Huesped original = coleccionHuespedes.get(i);
                Huesped copia = new Huesped(original);
                copiaHuespedes.add(copia);
            }
        }

        return copiaHuespedes;
    }


    //Getter


    public int getTamano()
    {

        return coleccionHuespedes.size();
    }

    public void insertar( Huesped huesped) throws OperationNotSupportedException
    {
        if (huesped == null)
        {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }
        else
        {
            for (int i = 0; i < coleccionHuespedes.size(); i++)
            {
                Huesped original = coleccionHuespedes.get(i);
                if (original.equals(huesped))
                {
                    throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
                }
            }

            coleccionHuespedes.add(huesped);
            System.out.println("Huesped insertado");
        }
    }

    public Huesped buscar (Huesped huesped)
     {
         if (huesped != null)
         {
             for (int i = 0; i < coleccionHuespedes.size(); i++)
             {
                 Huesped original = coleccionHuespedes.get(i);
                 if (huesped.equals(original))
                 {
                     return new Huesped(original);
                 }
             }

             return null;
         }
         else
         {
             throw new NullPointerException("ERROR56.");
         }
     }


    public void borrar (Huesped huesped) throws OperationNotSupportedException
    {
        if (huesped != null) {
            int indiceABorrar = coleccionHuespedes.indexOf(huesped);

            if (indiceABorrar == -1)
            {
                throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
            }

            coleccionHuespedes.remove(indiceABorrar);
        }
        else
        {
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }
    }
}
