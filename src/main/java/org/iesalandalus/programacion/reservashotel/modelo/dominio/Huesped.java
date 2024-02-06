package org.iesalandalus.programacion.reservashotel.modelo.dominio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {


    //Constantes
    private static final String ER_TELEFONO = "[0-9]{9}";
    private static final String ER_CORREO = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$";
    private static final String ER_DNI = "[0-9]{8}[A-Za-z]";
    public static final String FORMATO_FECHA="dd/MM/yyyy";

    //Atributos

    private  String nombre;
    private  String telefono;
    private  String correo;
    private  String dni;
    private LocalDate fechaNacimiento;


    private String formateaNombre(String nombre)
    {
        String borraEspacio = nombre.trim();
        String[] palabras = borraEspacio.split(" ");
        String limpio = "";
        for (int i = 0; i < palabras.length; i++)
        {
            if (palabras[i] != null && !palabras[i].isEmpty())
            {
                String palabraTemporal = palabras[i].trim();
                palabraTemporal = palabraTemporal.toLowerCase();
                if (palabraTemporal.length() == 1)
                {
                    palabraTemporal = palabraTemporal.toUpperCase();
                }
                else
                {
                    palabraTemporal = palabraTemporal.substring(0, 1).toUpperCase() + palabraTemporal.substring(1);
                }

                limpio += palabraTemporal + " ";
            }
        }

        limpio = limpio.trim();

        return limpio;

    }

    private boolean comprobarLetraDni(String dni)
    {
        Pattern patron = Pattern.compile(ER_DNI);
        Matcher mat = patron.matcher(dni);
        if (mat.matches())
        {
            String cadenaNumeros = dni.substring(0, 8);
            int numeros = Integer.parseInt(cadenaNumeros);
            int resto = numeros % 23;

            String letra = dni.substring(8, 9);


            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            String letraValida = String.valueOf(letras.charAt(resto));

            if (letra.equals(letraValida))
            {
                return true;
            }
            else
            {
                throw new IllegalArgumentException("ERROR: La letra del dni del hu�sped no es correcta.");
            }


        }
        else
        {
            throw new IllegalArgumentException("ERROR: El dni del hu�sped no tiene un formato v�lido.");
        }
    }


    //Constructor.
    public Huesped(String nombre, String dni,String correo,String telefono, LocalDate fechaNacimiento)
    {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);

    }

    //Constructor copia.
    public Huesped(Huesped huesped) {
        if (huesped != null) {
            setNombre(huesped.nombre);
            setDni(huesped.dni);
            setCorreo(huesped.correo);
            setTelefono(huesped.telefono);
            setFechaNacimiento(huesped.fechaNacimiento);
        }
        else
        {
            throw new NullPointerException("ERROR: No es posible copiar un hu�sped nulo.");
        }
    }

    //Getter.
    public String getTelefono()
    {
        return telefono;
    }

    public String getCorreo()
    {
        return correo;
    }

    public String getDni()
    {
        return dni;
    }

    public LocalDate getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public String getNombre()
    {
        return nombre;
    }

    //Setter.

    public void setNombre(String nombre)
    {
        if (nombre != null) {
            if (!nombre.trim().isEmpty()) {
                this.nombre = formateaNombre(nombre);
            }
            else
            {
                throw new IllegalArgumentException("ERROR: El nombre de un hu�sped no puede estar vac�o.");
            }
        }
        else
        {
            throw new NullPointerException("ERROR: El nombre de un hu�sped no puede ser nulo.");
        }
    }

    public void setTelefono(String telefono)
    {
        if (telefono != null) {
            Pattern patron = Pattern.compile(ER_TELEFONO);
            Matcher mat = patron.matcher(telefono);

            if (mat.matches()) {
                this.telefono = telefono;
            } else {
                throw new IllegalArgumentException("ERROR: El tel�fono del hu�sped no tiene un formato v�lido.");
            }
        }
        else
        {
            throw new NullPointerException("ERROR: El tel�fono de un hu�sped no puede ser nulo.");
        }
    }

    public void setCorreo(String correo)
    {
        if(correo != null) {
            Pattern patron = Pattern.compile(ER_CORREO);
            Matcher mat = patron.matcher(correo);

            if (mat.matches()) {
                this.correo = correo;
            } else {
                throw new IllegalArgumentException("ERROR: El correo del hu�sped no tiene un formato v�lido.");
            }
        }
        else
        {
            throw new NullPointerException("ERROR: El correo de un hu�sped no puede ser nulo.");
        }

    }

    private void setFechaNacimiento(LocalDate fechaNacimiento)
    {
        if (fechaNacimiento != null)
        {
            this.fechaNacimiento = fechaNacimiento;
        }
        else
        {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un hu�sped no puede ser nula.");
        }
    }

    private void setDni(String dni)
    {
        if (dni != null) {
            if (comprobarLetraDni(dni)) {
                this.dni = dni;
            }
        }
        else
        {
            throw new NullPointerException("ERROR: El dni de un hu�sped no puede ser nulo.");
        }

    }

    //Metodo get iniciales.

    private String getIniciales()
    {
        String borraEspacio = nombre.trim();
        String[] palabras = borraEspacio.split(" ");
        String iniciales = "";
        for (int i = 0; i < palabras.length; i++)
        {
            if (palabras[i] != null && !palabras[i].isEmpty())
            {
                iniciales += palabras[i].trim().substring(0,1).toUpperCase();
            }
        }

        return iniciales;
    }


    //Equals and hash code.
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huesped huesped = (Huesped) o;
        return dni.equals(huesped.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, telefono, correo, dni, fechaNacimiento);
    }


    @Override
    public String toString()
    {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(FORMATO_FECHA);

        return "nombre=" + nombre +
                " (" + getIniciales() + ")" +
                ", DNI=" + dni +
                ", correo=" + correo +
                ", tel�fono=" + telefono +
                ", fecha nacimiento=" + fechaNacimiento.format(formateador);
    }



}
