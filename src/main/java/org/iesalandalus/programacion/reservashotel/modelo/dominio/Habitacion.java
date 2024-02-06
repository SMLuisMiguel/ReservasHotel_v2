package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.util.Objects;

public class Habitacion {

   public static final double MIN_PRECIO_HABITACION=40;
   public static final double MAX_PRECIO_HABITACION=150;
   public static final double MIN_NUMERO_PUERTA=0;
   public static final double MAX_NUMERO_PUERTA=14;
   public static final double MIN_NUMERO_PLANTA=1;
   public static final double MAX_NUMERO_PLANTA=3;

   private String identificador;
   private int planta;
   private int puerta;
   private double precio;
   private TipoHabitacion tipoHabitacion;

   //Constructor.
   public Habitacion(int planta,int puerta,double precio/*,String descripcion*/)
   {
       setPlanta(planta);
       setPuerta(puerta);
       setPrecio(precio);
       setIdentificador();
   }

   //Constructor //
   public Habitacion(int planta, int puerta, double precio, TipoHabitacion tipoHabitacion/*,String descripcion*/)
   {
       setPlanta(planta);
       setPuerta(puerta);
       setPrecio(precio);
       setTipoHabitacion(tipoHabitacion);
       setIdentificador();

   }

   //Constructor copia.
   public Habitacion (Habitacion habitacion)
   {
       if (habitacion != null) {
           setPlanta(habitacion.planta);
           setPuerta(habitacion.puerta);
           setPrecio(habitacion.precio);
           setTipoHabitacion(habitacion.tipoHabitacion);
           setIdentificador();
       }
        else
       {
           throw  new NullPointerException("ERROR: No es posible copiar una habitaci�n nula.");
       }
   }

   //Getter.
    public String getIdentificador()
    {
        return identificador;
    }

    public int getPlanta()
    {
        return planta;
    }

    public int getPuerta()
    {
        return puerta;
    }

    public double getPrecio()
    {
        return precio;
    }

    public TipoHabitacion getTipoHabitacion()
    {
        return tipoHabitacion;
    }

    //Setter

    private void setIdentificador()
    {
        if (planta >= MIN_NUMERO_PLANTA && planta <=MAX_NUMERO_PLANTA && puerta >= MIN_NUMERO_PUERTA && puerta <= MAX_NUMERO_PUERTA)
        {
            this.identificador = String.valueOf(planta) + String.valueOf(puerta);
        }
        else
        {
            throw new NullPointerException("ERROR 1");
        }
    }

    private void setIdentificador(String identificador)
    {
        if (identificador != null) {
            this.identificador = identificador;
        }
        else
        {
            throw new NullPointerException("ERROR 2");
        }
    }

    private void setPlanta(int planta)
    {
        if (planta >= MIN_NUMERO_PLANTA && planta <= MAX_NUMERO_PLANTA)
        {
            this.planta = planta;
        }
        else
        {
            throw new IllegalArgumentException("ERROR: No se puede establecer como planta de una habitaci�n un valor menor que 1.0 ni mayor que 3.0.");
        }
    }

    private void setPuerta(int puerta)
    {
        if (puerta >= MIN_NUMERO_PUERTA && puerta <= MAX_NUMERO_PUERTA)
        {
            this.puerta = puerta;
        }
        else
        {
            throw new IllegalArgumentException("ERROR: No se puede establecer como puerta de una habitaci�n un valor menor que 0.0 ni mayor que 14.0.");
        }
    }

    public void setPrecio(double precio) {
        if (precio >= MIN_PRECIO_HABITACION && precio<= MAX_PRECIO_HABITACION)
        {
            this.precio = precio;
        }
        else
        {
            throw new IllegalArgumentException("ERROR: No se puede establecer como precio de una habitaci�n un valor menor que 40.0 ni mayor que 150.0.");
        }
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion != null)
        {
            this.tipoHabitacion = tipoHabitacion;
        }
        else
        {
            throw new NullPointerException("ERROR: No se puede establecer un tipo de habitaci�n nula.");
        }
    }

    //Metodos Equals y HasCode.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return identificador.equals(that.identificador);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(identificador);
    }

    @Override
    public String toString()
    {
        return "identificador=" + identificador  +
                " (" + planta + "-" + puerta + ")" +
                ", precio habitaci�n=" + precio +
                ", tipo habitaci�n=" + tipoHabitacion;
    }
}
