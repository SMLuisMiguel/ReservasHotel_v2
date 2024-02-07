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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
            } catch (IllegalArgumentException | NullPointerException e) {
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
            } catch (IllegalArgumentException | NullPointerException e) {
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
        } catch (IllegalArgumentException | NullPointerException e) {
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
            } catch (IllegalArgumentException | NullPointerException e) {
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
        if (controlador.getHuespedes() != null && !controlador.getHuespedes().isEmpty())
        {
            controlador.getHuespedes().sort(new Comparator<Huesped>() {
                @Override
                public int compare(Huesped h1, Huesped h2) {
                    return h1.getNombre().compareTo(h2.getNombre());
                }
            });


            System.out.println("Mostrando listado de huespedes");
            for (int i = 0; i < controlador.getHuespedes().size(); i++)
            {
                System.out.println(controlador.getHuespedes().get(i));
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
            } catch (IllegalArgumentException | NullPointerException e) {
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
            } catch (IllegalArgumentException | NullPointerException e) {
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
        } catch (IllegalArgumentException | NullPointerException e) {
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
            } catch (IllegalArgumentException | NullPointerException e) {
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
        if (controlador.getHabitaciones() != null && !controlador.getHabitaciones().isEmpty())
        {
            controlador.getHabitaciones().sort(new Comparator<Habitacion>() {
                @Override
                public int compare(Habitacion h1, Habitacion h2) {
                    if (h1.getPlanta() < h2.getPlanta())
                    {
                        return -1;
                    }
                    else
                    {
                        if (h1.getPlanta() > h2.getPlanta())
                        {
                            return 1;
                        }
                        else
                        {
                            if (h1.getPuerta() < h2.getPuerta())
                            {
                                return -1;
                            }
                            else
                            {
                                if(h1.getPuerta() > h2.getPuerta())
                                {
                                    return 1;
                                }
                                else
                                {
                                    return 0;
                                }
                            }
                        }
                    }
                }
            });


            System.out.println("Mostrando listado de habitaciones");
            for (int i=0; i<controlador.getHabitaciones().size(); i++)
            {
                System.out.println(controlador.getHabitaciones().get(i));
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

                reserva.setHuesped(controlador.buscar(reserva.getHuesped()));
                reserva.setHabitacion(controlador.buscar(reserva.getHabitacion()));
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try {
            Habitacion habitacionLibre = consultarDisponibilidad(
                    reserva.getHabitacion().getTipoHabitacion(),
                    reserva.getFechaInicioReserva(),
                    reserva.getFechaFinReserva()
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
        List<Reserva> listaReservas = controlador.getReservas(tipoHabitacion);
        if (listaReservas != null && !listaReservas.isEmpty())
        {
            controlador.getReservas().sort(new Comparator<Reserva>() {
                @Override
                public int compare(Reserva r1, Reserva r2) {
                    if (r1.getFechaInicioReserva().isAfter(r2.getFechaInicioReserva()))
                    {
                        return -1;
                    }
                    else
                    {
                        if (r1.getFechaInicioReserva().isBefore(r2.getFechaInicioReserva()))
                        {
                            return 1;
                        }
                        else
                        {
                            return r1.getHuesped().getNombre().compareTo(r2.getHuesped().getNombre());
                        }
                    }
                }
            });

            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<listaReservas.size(); i++)
            {
                System.out.println(listaReservas.get(i));
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
        List<Reserva> listaReservas = controlador.getReservas(huesped);
        if (listaReservas != null && !listaReservas.isEmpty())
        {
            controlador.getReservas().sort(new Comparator<Reserva>() {
                @Override
                public int compare(Reserva r1, Reserva r2) {
                    if (r1.getFechaInicioReserva().isAfter(r2.getFechaInicioReserva()))
                    {
                        return -1;
                    }
                    else
                    {
                        if (r1.getFechaInicioReserva().isBefore(r2.getFechaInicioReserva()))
                        {
                            return 1;
                        }
                        else
                        {
                            if (r1.getHabitacion().getPlanta() < r2.getHabitacion().getPlanta())
                            {
                                return -1;
                            }
                            else
                            {
                                if (r1.getHabitacion().getPlanta() > r2.getHabitacion().getPlanta())
                                {
                                    return 1;
                                }
                                else
                                {
                                    if (r1.getHabitacion().getPuerta() < r2.getHabitacion().getPuerta())
                                    {
                                        return -1;
                                    }
                                    else
                                    {
                                        if(r1.getHabitacion().getPuerta() > r2.getHabitacion().getPuerta())
                                        {
                                            return 1;
                                        }
                                        else
                                        {
                                            return 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });

            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<listaReservas.size(); i++)
            {
                System.out.println(listaReservas.get(i));
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
        if (controlador.getReservas() != null && !controlador.getReservas().isEmpty())
        {
            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<controlador.getReservas().size(); i++)
            {
                boolean fechaEntreReserva = fecha.isAfter(controlador.getReservas().get(i).getFechaInicioReserva()) &&
                        fecha.isBefore(controlador.getReservas().get(i).getFechaFinReserva());
                if (fechaEntreReserva ||
                        fecha.isEqual(controlador.getReservas().get(i).getFechaInicioReserva())  ||
                        fecha.isEqual(controlador.getReservas().get(i).getFechaFinReserva())
                )
                {
                    System.out.println(controlador.getReservas().get(i));
                }
            }
        }
        else
        {
            System.out.println("No hay reservas registradas");
        }
        System.out.println("Volviendo al menu...");
    }

    private List<Reserva> getReservasAnulables(List<Reserva> reservasAAnular)
    {
        List<Reserva> reservasAnulables = new ArrayList<>();

        for (int i = 0; i < reservasAAnular.size(); i++)
        {
            if (reservasAAnular.get(i) != null && reservasAAnular.get(i).getFechaInicioReserva().isAfter(LocalDate.now()))
            {
                reservasAnulables.add(new Reserva(reservasAAnular.get(i)));
            }
        }

        List<Reserva> reservasAnulablesLimpio = new ArrayList<>();
        for (int i = 0; i < reservasAnulables.size(); i++)
        {
            reservasAnulablesLimpio.add(new Reserva(reservasAnulables.get(i)));
        }

        return reservasAnulablesLimpio;
    }

    private void anularReserva()
    {
        Huesped huesped = null;
        List<Reserva> reservasAnulables = new ArrayList<>();
        while (huesped == null)
        {
            try {
                huesped = Consola.getHuespedPorDni();
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
                System.out.println("Intentelo de nuevo");
            }
        }
        try
        {
            List<Reserva> reservasTemporal = getReservasAnulables(controlador.getReservas());
            for (int i = 0; i < reservasTemporal.size(); i++)
            {
                if (reservasTemporal.get(i) != null && reservasTemporal.get(i).getHuesped().equals(huesped))
                {
                    reservasAnulables.add(reservasTemporal.get(i));
                }
            }

            if (reservasAnulables.isEmpty())
            {
                System.out.println("No existen reservas anulables");
            }
            else
            {
                if (reservasAnulables.size() == 1)
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
                        controlador.borrar(reservasAnulables.get(0));
                        System.out.println("Reserva borrada");
                    }
                }
                else {
                    System.out.println("Selecciona la reserva que desea anular");
                    for (int i = 0; i < reservasAnulables.size(); i++)
                    {
                        System.out.println((i + 1) + " - " + reservasAnulables.get(i));
                    }

                    System.out.println("Introduzca una opcion");
                    int menu = Entrada.entero();

                    while (menu < 1 || menu > reservasAnulables.size())
                    {
                        System.out.println("Introduzca un número correcto");
                        menu = Entrada.entero();
                    }

                    controlador.borrar(reservasAnulables.get(menu - 1));
                    System.out.println("Reserva borrada");
                }
            }
            System.out.println("Volviendo al menu...");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menu...");
        }
    }

    private void mostrarReservas()
    {
        if (controlador.getReservas() != null && !controlador.getReservas().isEmpty())
        {
            controlador.getReservas().sort(new Comparator<Reserva>() {
                @Override
                public int compare(Reserva r1, Reserva r2) {
                    if (r1.getFechaInicioReserva().isAfter(r2.getFechaInicioReserva()))
                    {
                        return -1;
                    }
                    else
                    {
                        if (r1.getFechaInicioReserva().isBefore(r2.getFechaInicioReserva()))
                        {
                            return 1;
                        }
                        else
                        {
                            if (r1.getHabitacion().getPlanta() < r2.getHabitacion().getPlanta())
                            {
                                return -1;
                            }
                            else
                            {
                                if (r1.getHabitacion().getPlanta() > r2.getHabitacion().getPlanta())
                                {
                                    return 1;
                                }
                                else
                                {
                                    if (r1.getHabitacion().getPuerta() < r2.getHabitacion().getPuerta())
                                    {
                                        return -1;
                                    }
                                    else
                                    {
                                        if(r1.getHabitacion().getPuerta() > r2.getHabitacion().getPuerta())
                                        {
                                            return 1;
                                        }
                                        else
                                        {
                                            return 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });

            System.out.println("Mostrando listado de reservas");
            for (int i=0; i<controlador.getReservas().size(); i++)
            {
                System.out.println(controlador.getReservas().get(i));
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

        for (int i = 0; i < controlador.getHabitaciones().size(); i++)
        {
            if (controlador.getHabitaciones().get(i) != null && controlador.getHabitaciones().get(i).getTipoHabitacion().equals(tipohabitacion))
            {
                if (controlador.getReservas().isEmpty())
                {
                    return controlador.getHabitaciones().get(i);
                }
                boolean habitacionValida = true;
                for (int j = 0; j < controlador.getReservas().size(); j++)
                {
                    if (controlador.getReservas().get(j) != null && controlador.getReservas().get(j).getHabitacion().equals(controlador.getHabitaciones().get(i)))
                    {
                        // fechas nuevas entre la reserva
                        boolean isFechaInicioNuevaEntreLaReserva =
                                fechaInicioReserva.isAfter(controlador.getReservas().get(j).getFechaInicioReserva()) &&
                                        fechaInicioReserva.isBefore(controlador.getReservas().get(j).getFechaFinReserva());

                        boolean isFechaFinNuevaEntreLaReserva =
                                fechaFinReserva.isAfter(controlador.getReservas().get(j).getFechaInicioReserva()) &&
                                        fechaFinReserva.isBefore(controlador.getReservas().get(j).getFechaFinReserva());

                        // fechas antiguas entre las nuevas
                        boolean isFechaInicioAntiguaEntreLaReservaNueva =
                                controlador.getReservas().get(j).getFechaInicioReserva().isAfter(fechaInicioReserva) &&
                                        controlador.getReservas().get(j).getFechaInicioReserva().isBefore(fechaFinReserva);

                        boolean isFechaFinAntiguaEntreLaReservaNueva =
                                controlador.getReservas().get(j).getFechaFinReserva().isAfter(fechaInicioReserva) &&
                                        controlador.getReservas().get(j).getFechaFinReserva().isBefore(fechaFinReserva);



                        if (isFechaInicioNuevaEntreLaReserva || isFechaFinNuevaEntreLaReserva ||
                                isFechaInicioAntiguaEntreLaReservaNueva || isFechaFinAntiguaEntreLaReservaNueva )
                        {
                            habitacionValida = false;
                        }
                    }
                }

                if (habitacionValida)
                {
                    habitacion = controlador.getHabitaciones().get(i);
                    return habitacion;
                }
            }
        }


        return habitacion;
    }

    private void realizarCheckin()
    {
        Huesped huesped = Consola.getHuespedPorDni();
        List<Reserva> reservas = controlador.getReservas(huesped);
        LocalDateTime fechaCheckin = Consola.leerFechaHora("Introduce la fecha y hora del checkin");

        int checkInRealizados = 0;
        for (int i = 0; i < reservas.size(); i++)
        {
            if (reservas.get(i) != null && reservas.get(i).getFechaInicioReserva().equals(fechaCheckin.toLocalDate()))
            {
                controlador.realizarCheckin(reservas.get(i), fechaCheckin);
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
        List<Reserva> reservas = controlador.getReservas(huesped);
        LocalDateTime fechaCheckout = Consola.leerFechaHora("Introduce la fecha y hora del checkout");

        int checkOutRealizados = 0;
        for (int i = 0; i < reservas.size(); i++)
        {
            if (reservas.get(i) != null && reservas.get(i).getFechaFinReserva().equals(fechaCheckout.toLocalDate()))
            {
                controlador.realizarCheckout(reservas.get(i), fechaCheckout);
                checkOutRealizados++;
            }
        }

        if(checkOutRealizados == 0)
        {
            System.out.println("No existe reserva para la fecha indicado");
        }
    }

}
