package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Consola {


    private Consola()
    {

    }

    public static void mostrarMenu()
    {
        System.out.println("1: Insertar huesped. \n2: Buscar huesped. \n3: Borrar huesped.\n4: Mostrar huesped.\n5: Insertar habitación." +
                "\n6: Buscar habitación.\n7: Borrar habitación.\n8: Mostrar habitaciónes." +
                "\n9: Insertar reserva.\n10: Anular reserva.\n11: Mostrar reservas. \n12: Consultar disponibilidad." +
                "\n13: Realizar Checkin. \n14: Realizar Checkout.\n15: Salir.");

    }

    public static Opcion elegirOpcion()
    {
        int menu;

        System.out.println("Introduzca una opcion");
        menu= Entrada.entero();

        while (menu<1 || menu>15)
        {
            System.out.println("Introduzca un número correcto");
            menu=Entrada.entero();
        }

        switch (menu)
        {
            case 1:
                return Opcion.INSERTAR_HUESPED;

            case 2:
                return Opcion.BUSCAR_HUESPED;

            case 3:
                return Opcion.BORRAR_HUESPED;

            case 4:
                return Opcion.MOSTRAR_HUESPEDES;

            case 5:
                return Opcion.INSERTAR_HABITACION;

            case 6:
                return Opcion.BUSCAR_HABITACION;

            case 7:
                return Opcion.BORRAR_HABITACION;

            case 8:
                return Opcion.MOSTRAR_HABITACIONES;

            case 9:
                return Opcion.INSERTAR_RESERVA;

            case 10:
                return Opcion.ANULAR_RESERVA;

            case 11:
                return Opcion.MOSTRAR_RESERVAS;

            case 12:
                return Opcion.CONSULTAR_DISPONIBILIDAD;

            case 13:
                return Opcion.REALIZAR_CHECKIN;

            case 14:
                return Opcion.REALIZAR_CHECKOUT;

            default:
                return Opcion.SALIR;
        }
    }

    public static Huesped leerHuesped()
    {
        System.out.println("Introduce el nombre del nuevo huesped");
        String nombre = Entrada.cadena();

        System.out.println("Introduce el teléfono del nuevo huesped");
        String telefono = Entrada.cadena();

        System.out.println("Introduce el correo del nuevo huesped");
        String correo = Entrada.cadena();

        System.out.println("Introduce el dni del nuevo huesped");
        String dni = Entrada.cadena();

        LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento del nuevo huesped");

        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    public static Huesped getHuespedPorDni()
    {
        String pedirDni;
        System.out.println("Introduzca el D.N.I. del huesped");
        pedirDni=Entrada.cadena();

        while (pedirDni == null || pedirDni.isBlank())
        {
            System.out.println("Introduzca un D.N.I. válido");
            pedirDni=Entrada.cadena();
        }

        return new Huesped("Nombre", pedirDni, "test@gmail.com", "600000000", LocalDate.now());
    }

    public static LocalDate leerFecha(String mensaje)
    {
        LocalDate date = null;

        System.out.println(mensaje);
        String fecha = Entrada.cadena();

        while (date == null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                formatter = formatter.withLocale(Locale.FRANCE);
                date = LocalDate.parse(fecha, formatter);
            } catch (Exception e) {
                System.out.println("Introduzca una fecha válida");
                fecha = Entrada.cadena();
            }
        }
        return date;
    }

    public static Habitacion leerHabitacion()
    {

        System.out.println("Introduce la planta.");
        int planta    = Entrada.entero();

        System.out.println("Introduce la puerta.");
        int puerta = Entrada.entero();

        System.out.println("Introduce el precio.");
        double precio = Entrada.realDoble();

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        return new Habitacion( planta, puerta, precio, tipoHabitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador()
    {
        int pedirPlanta;
        System.out.println("Introduzca la planta");
        pedirPlanta=Entrada.entero();

        while (pedirPlanta < Habitacion.MIN_NUMERO_PLANTA || pedirPlanta > Habitacion.MAX_NUMERO_PLANTA)
        {
            System.out.println("Introduzca una planta válida");
            pedirPlanta=Entrada.entero();
        }

        int pedirPuerta;
        System.out.println("Introduzca la puerta");
        pedirPuerta=Entrada.entero();

        while (pedirPuerta < Habitacion.MIN_NUMERO_PUERTA || pedirPuerta > Habitacion.MAX_NUMERO_PUERTA)
        {
            System.out.println("Introduzca una puerta válida");
            pedirPuerta=Entrada.entero();
        }

        return new Habitacion(pedirPlanta, pedirPuerta, 150.0, TipoHabitacion.SUITE);
    }

    public static TipoHabitacion leerTipoHabitacion()
    {
        System.out.println("Introduce el tipo de habitación.");
        System.out.println("1: Suite. \n2: Simple. \n3: Doble.\n4: Triple.");
        System.out.println("Elige una opción");
        int menu= Entrada.entero();

        while (menu<1 || menu>4)
        {
            System.out.println("Introduzca una opción correcta");
            menu=Entrada.entero();
        }

        switch (menu)
        {
            case 1:
                return TipoHabitacion.SUITE;
            case 2:
                return TipoHabitacion.SIMPLE;
            case 3:
                return TipoHabitacion.DOBLE;
            default:
                return TipoHabitacion.TRIPLE;
        }
    }

    public static Regimen leerRegimen()
    {
        System.out.println("Introduce el regimen.");
        System.out.println("1: Solo alojamiento. \n2: Alojamiento con desayuno. \n3: Media pension.\n4: Pension completa.");
        System.out.println("Elige una opción");
        int menu= Entrada.entero();

        while (menu<1 || menu>4)
        {
            System.out.println("Introduzca una opción correcta");
            menu=Entrada.entero();
        }

        switch (menu)
        {
            case 1:
                return Regimen.SOLO_ALOJAMIENTO;
            case 2:
                return Regimen.ALOJAMIENTO_DESAYUNO;
            case 3:
                return Regimen.MEDIA_PENSION;
            default:
                return Regimen.PENSION_COMPLETA;
        }
    }

    public static Reserva leerReserva()
    {

        Huesped huesped = getHuespedPorDni();

        Habitacion habitacion = leerHabitacionPorIdentificador();

        Regimen regimen = leerRegimen();

        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de la reserva");

        LocalDate fechaFin = leerFecha("Introduce la fecha de fin de la reserva");

        System.out.println("Introduzca un número de personas válidas para esta habitación");
        int pedirNumeroPersonas=Entrada.entero();

        return new Reserva(huesped, habitacion, regimen, fechaInicio, fechaFin, pedirNumeroPersonas);
    }



    public static LocalDateTime leerFechaHora(String mensaje)
    {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime fechaFormateada = null;

        while (fechaFormateada == null)
        {
            try
            {
                System.out.println(mensaje);
                String entrada = Entrada.cadena();
                fechaFormateada = LocalDateTime.parse(entrada, formateador);
            }
            catch (DateTimeParseException e)
            {
                System.out.println("La fecha y hora introducida no es correcta.");
                System.out.println("El formato de fecha y hora debe ser: dd-mm-aaaa hh:mm:ss");
            }
        }

        return fechaFormateada;

    }



}
