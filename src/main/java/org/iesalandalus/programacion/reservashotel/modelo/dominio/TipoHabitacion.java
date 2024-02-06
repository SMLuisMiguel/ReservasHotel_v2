package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {


    SUITE("Suite",4),
    SIMPLE("Simple",1),
    DOBLE("Doble",2),
    TRIPLE("Triple",3);


    private String cadenaAMostrar;

    private int numeroMaximoPersonas;


    TipoHabitacion(String cadenaAMostrar,int numeroMaximoPersonas)
    {
        this.cadenaAMostrar=cadenaAMostrar;
        this.numeroMaximoPersonas=numeroMaximoPersonas;
    }

    public int getNumeroMaximoPersonas()
    {
        return numeroMaximoPersonas;
    }

    @Override
    public String toString()
    {
        return "TipoHabitacion{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                ", numeroMaximoPersonas=" + numeroMaximoPersonas +
                '}';
    }
}
