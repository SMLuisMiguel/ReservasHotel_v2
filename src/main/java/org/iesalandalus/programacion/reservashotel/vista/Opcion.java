package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {

    SALIR("Salir"),
    INSERTAR_HUESPED("Insertar huesped"),
    BUSCAR_HUESPED("Buscar huesped"),
    BORRAR_HUESPED("Borrar Huesped"),
    MOSTRAR_HUESPEDES("Mostrar Huesped"),
    INSERTAR_HABITACION("Insertar Habitaci�n"),
    BUSCAR_HABITACION("Buscar habitaci�n"),
    BORRAR_HABITACION("Borrar Habitaci�n"),
    MOSTRAR_HABITACIONES("Mostrar Habitaciones"),
    INSERTAR_RESERVA("Insertar Reserva"),
    ANULAR_RESERVA("Anular Reserva"),
    MOSTRAR_RESERVAS("Mostrar Reserva"),
    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad"),


    REALIZAR_CHECKIN("Realizar checkin"),
    REALIZAR_CHECKOUT("Realizar checkout");


    private String mensajeAMostrar;
    Opcion(String mensajeAMostrar)
    {
        this.mensajeAMostrar=mensajeAMostrar;
    }

    @Override
    public String toString()
    {
        return "Opcion{" +
                "mensajeAMostrar='" + mensajeAMostrar + '\'' +
                '}';
    }
}
