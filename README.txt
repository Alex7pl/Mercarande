# Mercarande


CAMBIOS REALIZADOS 

24 abril:
  -Martin: Prueba en clase
30/04/2023: 
  -Alex: Añadidos Main y Controller
  -Miguel: Añadida funcionalidad a Gerente
  -Dani: 
    + Añadida funcionalidad GEstionTrabajadores 
    + Clase trabajador (metodo solicitarLimpieza)
    + Clase directorRRHH
 01/05/2023:
  -Alex: Añadido model 100% y arreglado problemas con model
  -Dani:
    + Nuevos métodos en 'GestionTrabajadores' para el inicio de sesion
    + MainWiindow -> inicio sesion
02/05/2023:
  -Dani:
    + METODOS CONTROLLER
      - comprobarEsTrabajador(String user)
      - comprobarCredencialesCorrectas(String user, String password)
      - getTrabajadorConUsuario(String user)
      - getTrabajadorConDNI(String dni)
      - eliminarTrabajador(String DNI)
      - getTrabajadores()
      - modificarTrabajador(String tipoUsuario, String nombre, String dni, float salary, int entrada, int salida)
    + METODOS GESTIONMERCADO
      * modificados *
      - getTrabajador(String DNI) 
      - eliminarTrabajador(String DNI)
      * nuevos *
      - esTrabajador(String user)
      - credencialesCorrectas(String user, String password)
      - getTrabajadorConUsuario(String user)
      - getTrabajadores()
      - modificarTrabajador(String tipoUsuario, String nombre, String dni, float salary, int entrada, int salida)
    + NUEVAS CLASES:
      - RRHHModificarTrabajadores
      - RRHHMostrarTrabajadores
      - RRHHNuevoTrabajador
      - TopPanel
      - LoginPanel
