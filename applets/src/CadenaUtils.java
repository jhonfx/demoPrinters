/**
 * Proyecto: Host To Host Corporativo
 *
 * Archivo: CadenaUtils.java
 *
 * Creado: 10/08/2007
 *
 * (c) Grupo Altec Mexico. Todos los derechos reservados - 2007
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Utilerias sobre cadenas.
 *
 * @author Rafael Antonio Guti�rrez Turullols, Oswaldo Herrera
 */
public class CadenaUtils {

    /**
     * Cadena para el signo de pesos
     */
    public static final String SIGNOPESOS = "$";

    /**
     * Direccion de relleno para una cadena.
     */
    public static enum DIRECCION_RELLENO {
        DERECHA, IZQUIERDA
    }

    ;

    /**
     * Caracteres usados por CECOBAN en Unicode.
     */
    public static final char[] CARACTERES_ESPECIALES_UNICODE_CECOBAN = {
        '\u00F1', // � minuscula
        '\u00D1', // � mayuscula
        '\u00C1', // A con acento
        '\u00C9', // E con acento
        '\u00CD', // I con acento
        '\u00D3', // O con acento
        '\u00DA', // U con acento
        '\u00E1', // a con acento
        '\u00E9', // e con acento
        '\u00ED', // i con acento
        '\u00F3', // o con acento
        '\u00FA', // u con acento
        '\u00BF', // �
        '\u00A1' // �
    };

    /**
     * Caracteres que su representacion Unicode es igual que en ASCII
     */
    private static final String CARACTERES_IGUALES_ASCII_UNICODE = " !\"#$%&'()*+,-./:;?@_";

    /**
     * Vocales A
     */
    public static final String VOCALES_A = "a����";

    /**
     * Vocales E
     */
    public static final String VOCALES_E = "e����";

    /**
     * Vocales I
     */
    public static final String VOCALES_I = "i����";

    /**
     * Vocales O
     */
    public static final String VOCALES_O = "o����";

    /**
     * Vocales U
     */
    public static final String VOCALES_U = "u����";

    /**
     * Vocales posibles
     */
    public static final String VOCALES = VOCALES_A + VOCALES_E + VOCALES_I + VOCALES_O + VOCALES_U;

    /**
     * Consonantes posibles
     */
    public static final String CONSONANTES = "bcdfghjklmn�pqrstvwxyz";

    /**
     * Valida si la cadena es alfanumerica segun las reglas para CECOBAN.
     *
     * @param cadena Cadena a validar
     * @return true si cumple, false de lo contrario
     */
    public static boolean isAlfanumericaSegunCECOBAN(String cadena) {
        char[] caracteres = cadena.toCharArray();
        for (char caracter : caracteres) {
            // validar si se trata de una letra o un numero o alguno de los otros
            // caracteres validos definidos por CECOBAN
            //LOGGER.debug("caracter = [" + caracter + ']');
            //LOGGER.debug("isLetterOrDigit? = [" + Character.isLetterOrDigit(caracter) + ']');
            //LOGGER.debug("isOtroCaracterValidoSegunCECOBAN? =
            // [" + isOtroCaracterValidoSegunCECOBAN(caracter) + ']');
            if (!isCaracterValidoSegunCECOBAN(caracter)) {
                return false;
            }
            //if (!Character.isLetterOrDigit(caracter) &&
            //    !isOtroCaracterValidoSegunCECOBAN(caracter)) {
            //    return false;
            //}
        }
        return true;
    }

    /**
     * Valida si es alguno de los otros caracteres validos para una cadena Alfanumerica en CECOBAN.
     *
     * @param caracter Caracter a validar
     * @return true si lo es, false de lo contrario
     */
    public static boolean isOtroCaracterValidoSegunCECOBAN(char caracter) {
        String losDemasCaracteres = CARACTERES_IGUALES_ASCII_UNICODE;
        return isCaracterEnArreglo(caracter, losDemasCaracteres.toCharArray()) ||
            isCaracterEnArreglo(caracter, CARACTERES_ESPECIALES_UNICODE_CECOBAN);
    }

    /**
     * Identifica si es un caracter Unicode valido segun CECOBAN
     *
     * @param caracter es el caracter a validar
     * @return true si es valido
     */
    public static boolean isCaracterValidoSegunCECOBAN(char caracter) {
        if (Character.isLetterOrDigit(caracter) || isOtroCaracterValidoSegunCECOBAN(caracter) ||
            isCarriageReturn(caracter) || isLineFeed(caracter)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica si existe el caracter en el arreglo de caracteres
     *
     * @param caracter Caracter a validar
     * @param arreglo  Arreglo de caracteres
     * @return true si existe, false de lo contrario
     */
    public static boolean isCaracterEnArreglo(char caracter, char[] arreglo) {
        for (char crt : arreglo) {
            //LOGGER.debug("crt = [" + crt + ']');
            //LOGGER.debug("caracter = [" + caracter + ']');
            if (crt == caracter) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si la cadena es numerica segun las reglas de CECOBAN.
     *
     * @param cadena Cadena a validar
     * @return true si cumple, false de lo contrario
     */
    public static boolean isNumericaSegunCECOBAN(String cadena) {
        return isEntero(cadena);
    }


    /**
     * Valida si la cadena es numerica segun las reglas de CECOBAN.
     *
     * @param cadena Cadena a validar
     * @return true si cumple, false de lo contrario
     */
    public static boolean isEntero(String cadena) {
        char[] caracteres = cadena.toCharArray();
        for (char caracter : caracteres) {
            // validar si se trata de un numero
            if (!Character.isDigit(caracter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valida si la cadena es vacia o nula.
     *
     * @param cadena Cadena a validar
     * @return true si el valor es vacio, false de lo contrario
     */
    public static boolean isValorVacio(String cadena) {
        return (cadena == null || "".equalsIgnoreCase(cadena));
    }

    /**
     * Valida si el parametro es vacio o no.
     * En caso de que el parametro no sea nulo se regresa
     * con su valor tipo cadena, de lo contrario regresa nulo.
     *
     * @param objeto Object
     * @return nulo o el objeto pasado como parametro
     */
    public static String valorOVacio(Object objeto) {
        return objeto == null ? null : objeto.toString();
    }

    /**
     * Crea una nueva cadena a partir de la original rellenando los espacios faltantes para lograr
     * el tama�o especificado con el caracter de relleno.
     *
     * @param cadenaOriginal   Cadena Original
     * @param caracterRelleno  Caracter de relleno
     * @param totalTamanho     Tama�o total que debe tener la cadena
     * @param direccionRelleno Direccion de relleno de la cadena
     * @return Una nueva cadena
     */
    public static String rellenarCadena(String cadenaOriginal, char caracterRelleno,
                                        int totalTamanho, DIRECCION_RELLENO direccionRelleno) {
        int cadenaOriginalSize =
            (cadenaOriginal == null || "".equalsIgnoreCase(cadenaOriginal)) ?
                0 : cadenaOriginal.length();
        if (cadenaOriginalSize == 0) {
            StringBuffer buf = new StringBuffer(totalTamanho);
            for (int i = 0; i < totalTamanho; i++) {
                buf.append(caracterRelleno);
            }
            return buf.toString();
        } else {
            if (totalTamanho <= cadenaOriginalSize) {
                return cadenaOriginal;
            } else {
                int tamanhoRestante = totalTamanho - cadenaOriginalSize;
                char[] arregloFinal = new char[totalTamanho];
                char[] arregloOriginal = cadenaOriginal.toCharArray();
                rellenarArreglo(arregloFinal, caracterRelleno);

                if (DIRECCION_RELLENO.DERECHA.equals(direccionRelleno)) {
                    // se recorre el arreglo final del inicio al fin
                    for (int count = 0; count < arregloOriginal.length; ++count) {
                        arregloFinal[count] = arregloOriginal[count];
                    }
                } else if (DIRECCION_RELLENO.IZQUIERDA.equals(direccionRelleno)) {
                    int indice = 0;
                    for (int count = 0; count < arregloFinal.length; ++count) {
                        indice = count - (arregloFinal.length - arregloOriginal.length);
                        if (indice >= 0) {
                            arregloFinal[count] = arregloOriginal[indice];
                        }
                    }
                }
                return String.valueOf(arregloFinal);
            }
        }
    }

    /**
     * Ajusta una Cadena exactamente al largo indicado
     *
     * @param cadenaOriginal
     * @param largoCadena
     * @return cadena ajustada
     */
    public static String ajustarCadenaAFijo(String cadenaOriginal, char caracter, int largoCadena, DIRECCION_RELLENO direccionRelleno) {

        String cadenaInicial = null;
        if (direccionRelleno == DIRECCION_RELLENO.DERECHA) {
            cadenaInicial =
                (cadenaOriginal.length() >= largoCadena) ? cadenaOriginal.substring(cadenaOriginal.length() - largoCadena, cadenaOriginal.length()) :
                    cadenaOriginal;
        } else {
            cadenaInicial =
                (cadenaOriginal.length() >= largoCadena) ? cadenaOriginal.substring(0, largoCadena) :
                    cadenaOriginal;
        }
        if (cadenaInicial.length() < largoCadena) {
            return CadenaUtils.rellenarCadena(cadenaInicial, caracter, largoCadena, direccionRelleno);
        }
        return cadenaInicial;
    }

    /**
     * Ajusta una Cadena exactamente al largo indicado
     *
     * @param cadenaOriginal
     * @param largoCadena
     * @return cadena ajustada
     */
    public static String ajustarCadenaAFijo(String cadenaOriginal, int largoCadena) {
        return ajustarCadenaAFijo(cadenaOriginal, ' ', largoCadena, DIRECCION_RELLENO.DERECHA);
    }

    /**
     * Ajusta una Cadena al largo indicado
     *
     * @param cadenaOriginal
     * @param largoCadena
     * @return cadena ajustada
     */
    public static String ajustarCadena(String cadenaOriginal, int largoCadena) {
        return cadenaOriginal.substring(0, largoCadena);
    }

    /**
     * Rellena un arreglo con el caracter indicado
     *
     * @param arreglo  Arreglo
     * @param caracter Caracter
     */
    public static void rellenarArreglo(char[] arreglo, char caracter) {
        for (int count = 0; count < arreglo.length; ++count) {
            arreglo[count] = caracter;
        }
    }

    /**
     * Devuelve un salto de linea segun CECOBAN
     *
     * @return un arreglo de bytes con los codigos Unicode que representan el salto de linea
     */
    public static byte[] getSaltoDeLineaSegunCECOBAN() {
        byte[] saltoDeLinea = {'\r', '\n'};
        return saltoDeLinea;
    }

    /**
     * Identifica si 'c' corresponde al codigo de un salto de linea
     *
     * @param c como el caracter a validar
     * @return true si es salto de linea
     */
    public static boolean isLineFeed(char c) {
        return c == '\n';
    }

    /**
     * Identifica si 'c' corresponde al codigo de un retorno de carro
     *
     * @param c como el caracter a validar
     * @return true si es retorno de carro
     */
    public static boolean isCarriageReturn(char c) {
        return c == '\r';
    }

    /**
     * Convierte un String a un entero primitivo
     *
     * @param cadena la cadena a convertir
     * @return el entero primitivo
     */
    public static int convertirStringAint(String cadena) {
        return new Integer(cadena).intValue();
    }

    /**
     * Convierte un String a un entero primitivo
     *
     * @param cadena la cadena a convertir
     * @return el entero primitivo
     */
    public static BigDecimal convertirStringABigDecimal(String cadena) {
        return new BigDecimal(cadena);
    }

    /**
     * Representa un BigDecimal con el numero de decimales indicados.
     * Ej. si BigDecimal = 23.5467 y se envia numDecimales = 2, el resultado es: "2354"
     *
     * @param bigDecimal   es el numero a convertir
     * @param numDecimales es el numero de decimales a representar como decimales
     * @return la cadena con los decimales en una sola cadena sin punto
     */
    public static String convertirBigDecimalAString(BigDecimal bigDecimal, int numDecimales) {
        String cadena = null;
        if (bigDecimal != null) {
            cadena = bigDecimal.movePointRight(numDecimales).toString();
            if (cadena.indexOf('.') != -1) {
                cadena = cadena.substring(0, cadena.indexOf('.'));
            }
        }
        return cadena;
    }

    /**
     * Convierte un String a un BigDecimal tomando el cuenta el numero de decimales enviados
     *
     * @param cantidad     la cadena a convertir
     * @param numDecimales numero de decimaless
     * @return el entero primitivo
     */
    public static BigDecimal convertirStringABigDecimal(String cantidad, int numDecimales) {
        int tamCadenaCantidad = cantidad.length();
        BigDecimal bigDecimal = null;
        if (tamCadenaCantidad > 0) {
            String cantidadSinDecimales = cantidad.substring(0, tamCadenaCantidad - numDecimales);
            String decimales = cantidad.substring(tamCadenaCantidad - numDecimales);
            String cantidadConDecimales = cantidadSinDecimales + '.' + decimales;
            bigDecimal = convertirStringABigDecimal(cantidadConDecimales);
        }
        return bigDecimal;
    }

    /**
     * Representa un BigInteger completando con '0' hacia la izquierda hasta alcanzar el tamano (size)
     * Si el valor es mayor a size se trunca la cadena a size.
     *
     * @param bigInteger es el numero a convertir
     * @param size       es el numero de caracteres que debe tener la cadena
     * @return la cadena con el tamano correcto
     */
    public static String convertirBigIntegerAString(BigInteger bigInteger,
                                                    int size) {
        String cadena = null;
        if (bigInteger != null) {
            String numeroCadena = bigInteger.toString();
            if (numeroCadena.length() < size) {
                cadena = rellenarCadena(numeroCadena, '0', size, DIRECCION_RELLENO.IZQUIERDA);
            }
        }
        return cadena;
    }

    /**
     * Toma el string y valida si tiene punto, elimina el punto y toma hasta dos caracteres
     * a la derecha del punto
     *
     * @param cantidad
     * @return
     */
    public static String eliminarPuntoEnMoneda(String cantidad) {
        StringBuffer valor = new StringBuffer();
        if (cantidad.indexOf('.') >= 0) {            
            valor.append(cantidad.substring(0, cantidad.indexOf('.')));
            if (cantidad.length() > cantidad.indexOf('.')) {
                String decimales = cantidad.substring(cantidad.indexOf('.') + 1);
                if (decimales.length() > 2) {
                    valor.append(decimales.substring(0, 2));
                } else {
                    valor.append(decimales);
                }
            }
        } else {
            valor.append(cantidad);
        }
        return valor.toString();
    }

    /**
     * Obtiene el nombre completo de una persona, considerando el formato:
     * => APaterno AMaterno Nombre(s)
     *
     * @param paterno el apellido paterno
     * @param materno el apellido materno
     * @param nombres los nombres
     * @return el nombre formateado
     */
    public static String obtenerPaMaNombre(String paterno, String materno, String nombres) {
        return new StringBuilder().append(paterno)
            .append(' ').append(materno)
            .append(' ').append(nombres).toString();
    }

    /**
     * Obtiene el nombre completo de una persona, considerando el formato:
     * => Nombre(s) APaterno AMaterno
     *
     * @param nombres los nombres
     * @param paterno el apellido paterno
     * @param materno el apellido materno
     * @return el nombre formateado
     */
    public static String obtenerNombrePaMa(String nombres, String paterno, String materno) {
        return new StringBuilder().append(nombres)
            .append(' ').append(paterno)
            .append(' ').append(materno).toString();
    }

    /**
     * Valida si el valor recibido es segun la expresion regular indicada.
     *
     * @param valor            Valor a comparar
     * @param expresionRegular Expresion regular a usar
     * @return true si cumple la condicion, false de lo contrario
     */
    public static boolean isSegunExpresionRegular(String valor, String expresionRegular) {
        /*if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Intentando validar [" + valor +
                "] con expresion [" + expresionRegular + ']');
        }*/
        Pattern p = Pattern.compile(expresionRegular);
        boolean resultado = p.matcher(valor).matches();
        /*if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("resultado de la validacion [" + resultado + ']');
        }*/
        return resultado;
    }


    /**
     * Cambia vocales con acentos a vocales normales (considera a����)
     *
     * @param cadena la cadena original
     * @return la misma cadena si no se encontro nada que reemplazar y en caso contrario una nueva
     *         cadena sin acentos
     */
    public static String sustituirAcentosXNormales(String cadena) {
        String temporal = cadena;
        temporal = temporal.replaceAll('[' + VOCALES_A + ']', "a");
        temporal = temporal.replaceAll('[' + VOCALES_A.toUpperCase() + ']', "A");
        temporal = temporal.replaceAll('[' + VOCALES_E + ']', "e");
        temporal = temporal.replaceAll('[' + VOCALES_E.toUpperCase() + ']', "E");
        temporal = temporal.replaceAll('[' + VOCALES_I + ']', "i");
        temporal = temporal.replaceAll('[' + VOCALES_I.toUpperCase() + ']', "I");
        temporal = temporal.replaceAll('[' + VOCALES_O + ']', "o");
        temporal = temporal.replaceAll('[' + VOCALES_O.toUpperCase() + ']', "O");
        temporal = temporal.replaceAll('[' + VOCALES_U + ']', "u");
        temporal = temporal.replaceAll('[' + VOCALES_U.toUpperCase() + ']', "U");
        return temporal;
    }

    /**
     * Recupera la primera vocal de la cadena
     *
     * @param cadena la cadena a evaluar
     * @return el caracter o '0' si no se encontro
     */
    public static Character obtenerPrimeraVocal(String cadena) {
        Character c = obtenerPrimeraLetra(cadena.toLowerCase(), CadenaUtils.VOCALES);
        return null != c ? c : '0';
    }

    /**
     * Recupera la primera consonante de la cadena
     *
     * @param cadena la cadena a evaluar
     * @return el caracter o '0' si no se encontro
     */
    public static Character obtenerPrimeraConsonante(String cadena) {
        Character c = obtenerPrimeraLetra(cadena.toLowerCase(),
            CadenaUtils.CONSONANTES);
        return null != c ? c : '0';
    }

    /**
     * Recupera la primera letra de la cadena si se encuentra cualquiera de los
     * caracteres incluidos en letrasABuscar
     *
     * @param cadena        la cadena a evaluar
     * @param letrasABuscar las letras a buscar en la cadena
     * @return el caracter o nulo si no se encontro
     */
    public static Character obtenerPrimeraLetra(String cadena, String letrasABuscar) {
        boolean encontrada = false;
        Character letra = null;
        for (int index = 0, result; index < cadena.length(); index++) {
            letra = cadena.charAt(index);
            result = letrasABuscar.indexOf(letra);
            if (result >= 0) {
                encontrada = true;
                break;
            }
        }
        return encontrada ? letra : null;
    }

    /**
     * Elimina el primer y ultimo caracter de una cadena
     * @param cadena
     * @return
     */
    public static String eliminarPrimeroYUtimo(String cadena) {
        return cadena.substring(1,cadena.length()-1);
    }

}
