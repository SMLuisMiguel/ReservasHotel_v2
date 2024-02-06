package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;

public class Huespedes {


    private int capacidad;
    private int tamano;
    private Huesped[] coleccionHuespedes;

    public Huespedes (int capacidad)
    {
        if (capacidad<=0)
        {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        else
        {
          this.capacidad=capacidad;
        }
        this.coleccionHuespedes=new Huesped [capacidad];
        this.tamano=0;
    }

    public Huesped [] get()
    {
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes()
    {
        Huesped[] copiaHuespedes = new Huesped[coleccionHuespedes.length];

        for (int i = 0; i < coleccionHuespedes.length; i++)
        {
            if (coleccionHuespedes[i] != null)
            {
                Huesped original = coleccionHuespedes[i];
                Huesped copia = new Huesped(original);
                copiaHuespedes[i] = copia;
            }
        }

        return copiaHuespedes;
    }


    //Getter


    public int getTamano()
    {

        return tamano;
    }

    public int getCapacidad()
    {
        return capacidad;
    }

    public void insertar( Huesped huesped) throws OperationNotSupportedException
    {
        if (huesped == null)
        {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }
        else
        {
            boolean insertado = false;
            for (int i = 0; i < coleccionHuespedes.length; i++)
            {
                Huesped original = coleccionHuespedes[i];
                if (original == null)
                {
                    coleccionHuespedes[i] = huesped;
                    tamano++;
                    insertado = true;
                    break;
                }
                else
                {
                    if (original.equals(huesped))
                    {
                        throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
                    }
                }
            }

            if(!insertado){
                throw new OperationNotSupportedException("ERROR: No se aceptan más huéspedes.");
            }
        }
    }

    private int buscarIndice(Huesped huesped)
    {
        for (int i = 0; i < coleccionHuespedes.length; i++)
        {
            Huesped original = coleccionHuespedes[i];
            if (huesped.equals(original))
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

    private Boolean capacidaSuperada(int indice)
    {
        return indice + 1 > capacidad;

    }

    public Huesped buscar (Huesped huesped)
     {
         if (huesped != null)
         {
             for (int i = 0; i < coleccionHuespedes.length; i++)
             {
                 Huesped original = coleccionHuespedes[i];
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
            int indiceABorrar = buscarIndice(huesped);

            if (indiceABorrar == -1)
            {
                throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
            }

            tamano--;
            for (int i = indiceABorrar; i < coleccionHuespedes.length; i++)
            {
                coleccionHuespedes[i] = null;
                if (i + 1 < coleccionHuespedes.length)
                {
                    desplazarUnaposicionHaciaIzquierda(i + 1);
                }
            }
        }
        else
        {
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }
    }

    private void desplazarUnaposicionHaciaIzquierda(int indice)
    {
        if(indice < 1)
        {
            throw new IllegalArgumentException("erro 5");
        }
        coleccionHuespedes[indice-1] = coleccionHuespedes[indice];
        coleccionHuespedes[indice] = null;
    }
}
