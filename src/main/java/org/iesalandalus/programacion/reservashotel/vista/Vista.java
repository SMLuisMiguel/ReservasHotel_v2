package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vista {

    private Controlador controlador;

    public Vista (){}

    public void setControlador(Controlador controlador)
    {
        if (controlador == null)
        {
            throw new NullPointerException("Controlador no puede ser nulo");
        }
        else {
            this.controlador = controlador;
        }
    }

    public void comenzar()
    {
        System.out.println("--------------------------------------------");
        System.out.println("Bienvenido al hotel IES Al-Ándalus ");

        Opcion opcion = null;

        while (opcion != Opcion.SALIR) {
            System.out.println("--------------------------------------------");
            Consola.mostrarMenu();
            System.out.println("--------------------------------------------");
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }

        controlador.terminar();
    }

    public void terminar()
    {
        System.out.println("Adios.");
    }

    private void ejecutarOpcion(Opcion opcion)
    {
        switch (opcion)
        {
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;

            case BUSCAR_HUESPED:
                buscarHuesped();
                break;

            case BORRAR_HUESPED:
                borrarHuesped();
                break;

            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;

            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;

            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;

            case BORRAR_HABITACION:
                borrarHabitacion();
                break;

            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;

            case INSERTAR_RESERVA:
                insertarReserva();
                break;

            case ANULAR_RESERVA:
                anularReserva();
                break;

            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;

            case CONSULTAR_DISPONIBILIDAD:
                TipoHabitacion tipo = Consola.leerTipoHabitacion();
                LocalDate fechaInicioReserva = Consola.leerFecha("Introduce la fecha de inicio de reserva");
                LocalDate fechaFinReserva = Consola.leerFecha("Introduce la fecha de fin de reserva");
                Habitacion habitacion = consultarDisponibilidad(tipo, fechaInicioReserva, fechaFinReserva);

                if (habitacion != null)
                {
                    System.out.println("Hay disponibilidad");
                }
                else
                {
                    System.out.println("No hay disponibilidad");
                }
                break;

            case REALIZAR_CHECKIN:
                realizarCheckin();
                break;

            case REALIZAR_CHECKOUT:
                realizarCheckout();
                break;


        }
    }

    private void insertarHuesped() {
        Huesped huesped = null;
        while (huesped == null) {
            try {
                huesped = Consola.leerHuesped();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            controlador.insertar(huesped);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }


    private void buscarHuesped()
    {
        Huesped huesped = null;
        while (huesped == null) {
            try {
                huesped = Consola.getHuespedPorDni();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            Huesped huespedEncontrado = controlador.buscar(huesped);
            if (huespedEncontrado != null)
            {
                System.out.println("Mostrando datos del huesped encontrado");
                System.out.println(huespedEncontrado);
            }
            else
            {
                System.out.println("Huesped no encontrado");
            }
            System.out.println("Volviendo al menu...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private void borrarHuesped()
    {
        Huesped huesped = null;
        while (huesped == null) {
            try {
                huesped = Consola.getHuespedPorDni();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            controlador.borrar(huesped);
            System.out.println("Se ha borrado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private void mostrarHuespedes()
    {
        if (controlador.getHuespedes() != null && getNumElementosNoNulos(controlador.getHuespedes()) > 0)
        {
            System.out.println("Mostrando listado de huespedes");
            for (int i = 0; i < getNumElementosNoNulos(controlador.getHuespedes()); i++)
            {
                System.out.println(controlador.getHuespedes()[i]);
            }
            System.out.println("Volviendo al menu...");
        }
        else
        {
            System.out.println("No hay huespedes registrados");
        }
    }

    private void insertarHabitacion()
    {
        Habitacion habitacion = null;
        while (habitacion == null) {
            try {
                habitacion = Consola.leerHabitacion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            controlador.insertar(habitacion);
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private void buscarHabitacion()
    {
        Habitacion habitacion = null;
        while (habitacion == null) {
            try {
                habitacion = Consola.leerHabitacionPorIdentificador();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            Habitacion habitacionEncontrado = controlador.buscar(habitacion);
            if (habitacionEncontrado != null)
            {
                System.out.println("Mostrando datos de la habitación encontrada");
                System.out.println(habitacionEncontrado);
            }
            else
            {
                System.out.println("Habitación no encontrada");
            }
            System.out.println("Volviendo al menu...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private void borrarHabitacion()
    {
        Habitacion habitacion = null;
        while (habitacion == null) {
            try {
                habitacion = Consola.leerHabitacionPorIdentificador();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            controlador.borrar(habitacion);
            System.out.println("Se ha borrado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private void mostrarHabitaciones()
    {
        if (controlador.getHabitaciones() != null && getNumElementosNoNulos(controlador.getHabitaciones()) > 0)
        {
            System.out.println("Mostrando listado de habitaciones");
            for (int i=0; i<getNumElementosNoNulos(controlador.getHabitaciones()); i++)
            {
                System.out.println(controlador.getHabitaciones()[i]);
            }
            System.out.println("Volviendo al menu...");
        }
        else
        {
            System.out.println("No hay habitaciones registradas");
        }
    }

    private void insertarReserva()
    {
        Reserva reserva = null;
        while (reserva == null) {
            try {
                reserva = Consola.leerReserva();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            Habitacion habitacionLibre = consultarDisponibilidad(
                    reserva.getHabitacion().getTipoHabitacion(),
                    reserva.getFechaInicioReserva(),
                    reserva.getFechaInicioReserva()
            );

            if (habitacionLibre != null) {
                controlador.insertar(reserva);
                System.out.println("Reserva confirmada");
            }
            else
            {
                System.out.println("No se ha podido hacer la reserva. No hay disponibilidad.");
            }
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private void listarReserva(TipoHabitacion tipoHabitacion)
    {
        Reserva[] listaReservas = controlador.getReservas(tipoHabitacion);
        if (listaReservas != null && listaReservas.length > 0)
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<listaReservas.length; i++)
            {
                System.out.println(listaReservas[i]);
            }
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
        System.out.println("Volviendo al menu...");
    }

    private void listarReserva(Huesped huesped)
    {
        Reserva[] listaReservas = controlador.getReservas(huesped);
        if (listaReservas != null && listaReservas.length > 0)
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<listaReservas.length; i++)
            {
                System.out.println(listaReservas[i]);
            }
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
        System.out.println("Volviendo al menu...");
    }

    private void listarReserva(LocalDate fecha)
    {
        if (controlador.getReservas() != null && getNumElementosNoNulos(controlador.getReservas()) > 0)
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<getNumElementosNoNulos(controlador.getReservas()); i++)
            {
                boolean fechaEntreReserva = fecha.isAfter(controlador.getReservas()[i].getFechaInicioReserva()) &&
                        fecha.isBefore(controlador.getReservas()[i].getFechaFinReserva());
                if (fechaEntreReserva ||
                        fecha.isEqual(controlador.getReservas()[i].getFechaInicioReserva())  ||
                        fecha.isEqual(controlador.getReservas()[i].getFechaFinReserva())
                )
                {
                    System.out.println(controlador.getReservas()[i]);
                }
            }
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
        System.out.println("Volviendo al menu...");
    }

    private Reserva[] getReservasAnulables(Reserva[] reservasAAnular)
    {
        Reserva[] reservasAnulables = new Reserva[reservasAAnular.length];
        int contador = 0;

        for (int i = 0; i < reservasAAnular.length; i++)
        {
            if (reservasAAnular[i] != null && reservasAAnular[i].getFechaInicioReserva().isAfter(LocalDate.now()))
            {
                reservasAnulables[contador] = new Reserva(reservasAAnular[i]);
                contador++;
            }
        }

        Reserva[] reservasAnulablesLimpio = new Reserva[contador];
        for (int i = 0; i < reservasAnulablesLimpio.length; i++)
        {
            reservasAnulablesLimpio[i] = new Reserva(reservasAnulables[i]);
        }

        return reservasAnulablesLimpio;
    }

    private void anularReserva()
    {
        Huesped huesped = null;
        Reserva[] reservasAnulables = null;
        while (huesped == null)
        {
            try {
                huesped = Consola.getHuespedPorDni();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try
        {
            reservasAnulables = getReservasAnulables(controlador.getReservas());
            if (reservasAnulables.length == 0)
            {
                System.out.println("No existen reservas anulables");
            }
            else
            {
                if (reservasAnulables.length == 1)
                {
                    System.out.println("1: Confirmar. \n2: Cancelar.");
                    System.out.println("Introduzca una opcion");
                    int menu= Entrada.entero();

                    while (menu<1 || menu>2)
                    {
                        System.out.println("Introduzca un número correcto");
                        menu=Entrada.entero();
                    }
                    if (menu == 1){
                        controlador.borrar(reservasAnulables[0]);
                        System.out.println("Reserva borrada");
                    }
                }
                else {
                    System.out.println("Selecciona la reserva que desea anular");
                    for (int i = 0; i < reservasAnulables.length; i++)
                    {
                        System.out.println((i + 1) + " - " + reservasAnulables[i]);
                        System.out.println("Introduzca una opcion");
                        int menu = Entrada.entero();

                        while (menu < 1 || menu > reservasAnulables.length)
                        {
                            System.out.println("Introduzca un número correcto");
                            menu = Entrada.entero();
                        }

                        controlador.borrar(reservasAnulables[menu - 1]);
                        System.out.println("Reserva borrada");
                    }
                }
            }
            System.out.println("Volviendo al menu...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private void mostrarReservas()
    {
        if (controlador.getReservas() != null && getNumElementosNoNulos(controlador.getReservas()) > 0)
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<getNumElementosNoNulos(controlador.getReservas()); i++)
            {
                System.out.println(controlador.getReservas()[i]);
            }
            System.out.println("Volviendo al menu...");
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
    }

    private int getNumElementosNoNulos(Reserva[] array)
    {
        int contador = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                contador++;
            }
        }
        return contador;
    }

    private int getNumElementosNoNulos(Habitacion[] array)
    {
        int contador = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                contador++;
            }
        }
        return contador;
    }

    private int getNumElementosNoNulos(Huesped[] array)
    {
        int contador = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                contador++;
            }
        }
        return contador;
    }

    private Habitacion consultarDisponibilidad(TipoHabitacion tipohabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva)
    {
        Habitacion habitacion = null;

        for (int i = 0; i < controlador.getHabitaciones().length; i++)
        {
            if (controlador.getHabitaciones()[i] != null && controlador.getHabitaciones()[i].getTipoHabitacion().equals(tipohabitacion))
            {
                if (getNumElementosNoNulos(controlador.getReservas()) == 0)
                {
                    return controlador.getHabitaciones()[i];
                }
                for (int j = 0; j < controlador.getReservas().length; j++)
                {
                    if (controlador.getReservas()[j] != null && controlador.getReservas()[j].getHabitacion().equals(controlador.getHabitaciones()[i]))
                    {
                        // fechas nuevas entre la reserva
                        boolean isFechaInicioNuevaEntreLaReserva =
                                fechaInicioReserva.isAfter(controlador.getReservas()[j].getFechaInicioReserva()) &&
                                        fechaInicioReserva.isBefore(controlador.getReservas()[j].getFechaFinReserva());

                        boolean isFechaFinNuevaEntreLaReserva =
                                fechaFinReserva.isAfter(controlador.getReservas()[j].getFechaInicioReserva()) &&
                                        fechaFinReserva.isBefore(controlador.getReservas()[j].getFechaFinReserva());

                        // fechas antiguas entre las nuevas
                        boolean isFechaInicioAntiguaEntreLaReservaNueva =
                                controlador.getReservas()[j].getFechaInicioReserva().isAfter(fechaInicioReserva) &&
                                        controlador.getReservas()[j].getFechaInicioReserva().isBefore(fechaFinReserva);

                        boolean isFechaFinAntiguaEntreLaReservaNueva =
                                controlador.getReservas()[j].getFechaFinReserva().isAfter(fechaInicioReserva) &&
                                        controlador.getReservas()[j].getFechaFinReserva().isBefore(fechaFinReserva);



                        if (!isFechaInicioNuevaEntreLaReserva && !isFechaFinNuevaEntreLaReserva &&
                                !isFechaInicioAntiguaEntreLaReservaNueva && !isFechaFinAntiguaEntreLaReservaNueva )
                        {
                            habitacion = controlador.getHabitaciones()[i];
                            return habitacion;
                        }
                    }
                }
            }
        }


        return habitacion;
    }

    private void realizarCheckin()
    {
        Huesped huesped = Consola.getHuespedPorDni();
        Reserva[] reservas = controlador.getReservas(huesped);
        LocalDateTime fechaCheckin = Consola.leerFechaHora("Introduce la fecha y hora del checkin");

        int checkInRealizados = 0;
        for (int i = 0; i < reservas.length; i++)
        {
            if (reservas[i] != null && reservas[i].getFechaInicioReserva().equals(fechaCheckin.toLocalDate()))
            {
                controlador.realizarCheckin(reservas[i], fechaCheckin);
                checkInRealizados++;
            }
        }

        if(checkInRealizados == 0)
        {
            System.out.println("No existe reserva para la fecha indicado");
        }
    }

    private void realizarCheckout()
    {
        Huesped huesped = Consola.getHuespedPorDni();
        Reserva[] reservas = controlador.getReservas(huesped);
        LocalDateTime fechaCheckout = Consola.leerFechaHora("Introduce la fecha y hora del checkout");

        int checkOutRealizados = 0;
        for (int i = 0; i < reservas.length; i++)
        {
            if (reservas[i] != null && reservas[i].getFechaFinReserva().equals(fechaCheckout.toLocalDate()))
            {
                controlador.realizarCheckout(reservas[i], fechaCheckout);
                checkOutRealizados++;
            }
        }

        if(checkOutRealizados == 0)
        {
            System.out.println("No existe reserva para la fecha indicado");
        }
    }

}
