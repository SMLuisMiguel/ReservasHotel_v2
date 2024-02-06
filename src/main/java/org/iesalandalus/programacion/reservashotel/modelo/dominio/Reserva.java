package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reserva {

    //Constantes.
    public static final int MAX_NUMERO_MESES_RESERVA=6;
    public static final int MAX_HORAS_POSTERIOR_CHECKOUT=12;
    public static final String FORMATO_FECHA_RESERVA="dd/MM/yyyy";
    public static final String FORMATO_FECHA_HORA_RESERVA="dd/MM/yyyy";

    //Atributos.
    private Huesped huesped;
    private Habitacion habitacion;
    private Regimen regimen;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double precio;
    private int numeroPersonas;

    //Constructor

    public Reserva(Huesped huesped,Habitacion habitacion,Regimen regimen,LocalDate fechaInicioReserva,LocalDate fechaFinReserva,int numeroPersonas)
    {
        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
        setPrecio();
    }

    //Constructor Copia.
    public Reserva(Reserva reserva)
    {
        if (reserva != null)
        {
            setHuesped(reserva.huesped);
            setHabitacion(reserva.habitacion);
            setRegimen(reserva.regimen);
            setFechaInicioReserva(reserva.fechaInicioReserva);
            setFechaFinReserva(reserva.fechaFinReserva);
            setNumeroPersonas(reserva.numeroPersonas);
            checkIn = reserva.checkIn;
            checkOut = reserva.checkOut;
            setPrecio();
        }
        else
        {
            throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");
        }

    }

    public Huesped getHuesped()
    {
        return huesped;
    }

    public void setHuesped(Huesped huesped)
    {
        if(huesped != null)
        {
            this.huesped = huesped;
        }
        else
        {
            throw new NullPointerException("ERROR: El hu�sped de una reserva no puede ser nulo.");
        }
    }

    public Habitacion getHabitacion()
    {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion)
    {
        if(habitacion != null)
        {
            this.habitacion = habitacion;
        }
        else
        {
            throw new NullPointerException("ERROR: La habitaci�n de una reserva no puede ser nula.");
        }
    }

    public Regimen getRegimen()
    {
        return regimen;
    }

    public void setRegimen(Regimen regimen)
    {
        if(regimen != null)
        {
            this.regimen = regimen;
        }
        else
        {
            throw new NullPointerException("ERROR: El r�gimen de una reserva no puede ser nulo.");
        }

    }

    public LocalDate getFechaInicioReserva()
    {
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioReserva)
    {
        if (fechaInicioReserva != null)
        {
            if(fechaInicioReserva.isBefore(LocalDate.now()))
            {
                throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser anterior al d�a de hoy.");
            }
            else
            {
                LocalDate fechaMaxima = LocalDate.now().plusMonths(MAX_NUMERO_MESES_RESERVA);
                if (fechaInicioReserva.isAfter(fechaMaxima))
                {
                    throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser posterior a seis meses.");
                }
                else
                {
                    this.fechaInicioReserva = fechaInicioReserva;
                }
            }
        }
        else
        {
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        }
    }

    public LocalDate getFechaFinReserva()
    {
        return fechaFinReserva;
    }

    public void setFechaFinReserva(LocalDate fechaFinReserva)
    {
        if (fechaFinReserva != null)
        {
            if (fechaFinReserva.isBefore(this.fechaInicioReserva) || fechaFinReserva.isEqual(this.fechaInicioReserva))
            {
                throw new IllegalArgumentException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");
            }
            else
            {
                this.fechaFinReserva = fechaFinReserva;
            }
        }
        else
        {
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");
        }
    }

    public LocalDateTime getCheckIn()
    {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn)
    {
        if (checkIn != null) {
            if (checkIn.isBefore(fechaInicioReserva.atStartOfDay()))
            {
                throw new IllegalArgumentException("ERROR: El checkin de una reserva no puede ser anterior a la fecha de inicio de la reserva.");
            }
            else
            {
                this.checkIn = checkIn;
            }
        }
        else
        {
            throw new NullPointerException("ERROR: El checkin de una reserva no puede ser nulo.");
        }
    }

    public LocalDateTime getCheckOut()
    {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut)
    {
        if(checkOut != null)
        {
            if (checkOut.isBefore(checkIn))
            {
                throw new IllegalArgumentException("ERROR: El checkout de una reserva no puede ser anterior al checkin.");
            }
            else
            {
                LocalDateTime maximaHora = fechaFinReserva.atTime(MAX_HORAS_POSTERIOR_CHECKOUT, 0);
                if (checkOut.isAfter(maximaHora))
                {
                    throw new IllegalArgumentException("ERROR: El checkout de una reserva puede ser como m�ximo 12 horas despu�s de la fecha de fin de la reserva.");
                }
                else
                {
                    this.checkOut = checkOut;
                }
            }
        }else
        {
            throw new NullPointerException("ERROR: El checkout de una reserva no puede ser nulo.");
        }
    }

    public double getPrecio()
    {
        return precio;
    }

    private void setPrecio()
    {
        Period period = Period.between(getFechaInicioReserva(), getFechaFinReserva());
        this.precio = ( habitacion.getPrecio() + regimen.getIncrementoPrecio() ) * period.getDays();
    }

    public int getNumeroPersonas()
    {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas)
    {
        if (numeroPersonas > 0)
        {
            if(numeroPersonas <= habitacion.getTipoHabitacion().getNumeroMaximoPersonas())
            {
                this.numeroPersonas = numeroPersonas;
            }
            else
            {
                throw new IllegalArgumentException("ERROR: El n�mero de personas de una reserva no puede superar al m�ximo de personas establacidas para el tipo de habitaci�n reservada.");
            }
        }
        else
        {
            throw new IllegalArgumentException("ERROR: El n�mero de personas de una reserva no puede ser menor o igual a 0.");
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return reserva.habitacion.equals(habitacion) && reserva.fechaInicioReserva == fechaInicioReserva;

    }

    @Override
    public int hashCode()
    {
        return Objects.hash(habitacion, fechaInicioReserva);
    }

    @Override
    public String toString()
    {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA);

        String textoCheckin;
        if (checkIn == null)
        {
            textoCheckin = "No registrado";
        }
        else
        {
            textoCheckin = checkIn.format(formateador);
        }

        String textoCheckOut;
        if (checkOut == null)
        {
            textoCheckOut = "No registrado";
        }
        else
        {
            textoCheckOut = checkOut.format(formateador);
        }

        String precioFormateado = new DecimalFormat("#,###.00").format(precio);

        return "Huesped: " + huesped.getNombre() + " " + huesped.getDni() + " " + "Habitaci�n:" +habitacion.getIdentificador() + " - " +
                habitacion.getTipoHabitacion() + " Fecha Inicio Reserva: " + getFechaInicioReserva().format(formateador) + " Fecha Fin Reserva: " + getFechaFinReserva().format(formateador) + " Checkin: "
                + textoCheckin + " Checkout: " + textoCheckOut + " Precio: " + precioFormateado + " Personas: " + numeroPersonas;
    }
}
