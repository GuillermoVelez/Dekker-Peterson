# ALGORITMOS PARA SOLUCIONAR PROBLEMAS DE CONCURRENCIA
## _Dekker y Peterson_

### Realizado por:
- Christian Galindo
- Guillermo Vélez

A continuación se presentan 6 algoritmos para solucionar problemas de concurrencia con 2 procesos (cinco de Dekker y uno de peterson), se detalla su funcionamiento y sus casos de error.


## Dekker primera versión (Alternancia Crítica)
La primer versión del algoritmo de Dekker es Alternancia Estricta, es llamado de esta manera ya que obliga a que cada proceso tenga un turno,o sea que hay un cambio de turno cada vez que un proceso sale de la sección critica,por lo tanto si un proceso es lento atrasará a otros procesos que son rápidos.
##### Características
* Garantiza la exclusión mutúa.
* Su sincronización es forzada
* Acopla fuertemente a los procesos (procesos lentos atrasan a procesos rápidos)
* No garantiza la progresión, ya que si un proceso por alguna razón es bloqueado dentro o fuera de la sección puede bloquear a los otros procesos.


### Código en Java
#### Proceso 1
```java
    // Hace tasks
    doTasks("initial", 2);
    // Espera a que la región crítica se desocupe
    while (Main.gui.getTurn() == 2) {
        waitcs();
    }
    // Accede a Sección Crítica
    criticalSection(3);
    // Hace final tasks   
    Main.gui.setTurn(2);
    doTasks("final", 3);.
```
#### Proceso 2
```java
    // Hace tasks
    doTasks("initial", 2);
    // Espera a que la Sección crítica se desocupe
    while (Main.gui.getTurn() == 1) {
        waitcs();
    }
    // Accede a Sección Crítica
    criticalSection(2);
    // Hace final tasks            
    Main.gui.setTurn(1);
    doTasks("final", 2);
```
### Prueba de escritorio
| Instruccion | Turno | Estado Proceso 1 | Estado Proceso 2 |
| :---: | :---: | :---: | :---: |
| Start() | undefined | Nuevo | Nuevo |
| setTurn(n) | 1 | Listo | Listo|
| mientras Turno==1 | 1 | En ejecución | Listo |
| mientras Turno==1 | 2 | Listo | En ejecución |
| mientras Turno==2 | 1 | En ejecución | Listo |
| mientras Turno==2 | 2 | Listo | En ejecución |
### Explicación
* Cuando un proceso es ejecutado verifica si es su turno, si no es su turno se queda en espera por medio de un ciclo while.
* De lo contrario si es su turno avanza a la sección crítica.
* Cuando el proceso sale de la sección crítica cambia de turno. 

## Dekker segunda versión (Problema Interbloqueo)
En esta version el proceso que haya completado sus tareas iniciales, se le permitira acceder a la sección crítica.
##### Características
* Garantiza la exclusión mutúa.
* No garantiza una espera limitada.
* En esta version no existe una alternancia.
* Si ambos procesos caen en el mismo estado para poder entrar a la sección crítica se produce interbloqueo.

### Código en Java
#### Proceso 1
```java
	doTasks("initial", 2);
    Main.gui.setP1qe(true);
    while (Main.gui.getP2qe()) {
        waitcs();
    }
    criticalSection(3);
    /*Aqui se rompe*/
    Main.gui.setP1qe(false);
    doTasks("final", 2);
```
#### Proceso 2
```java
	doTasks("initial", 3);
    Main.gui.setP2qe(false);
    Main.gui.setP2qe(true);
    while(Main.gui.getP1qe()){
        waitcs();
    }
    criticalSection(3);
    Main.gui.setP2qe(false);
    doTasks("final", 2);
```
### Prueba de escritorio
| Instruccion | Entrada Proceso 1 | Entrada Proceso 2 |Estado Proceso 1| Estado Proceso 2 |
| :---:  | :---: | :---: | :---: | :---: |
| Start() | Falso | Falso | Nuevo | Nuevo |
| setTurn(n) | Verdadero | Falso | Listo| Listo |
| Si Proceso 1 Puede Entrar | Falso | Verdadero | En ejecución | Listo |
| Si Proceso 2 Puede Entrar| Verdadero | Falso | Listo | En ejecución |
| Excepcion| Verdadero | Verdadero | Listo | Listo |

### Explicación


## Dekker tercera versión (Colisión región crítica no garantiza la exclusión mutua)
Esta version consiste en evaluar si existe un proceso alterno dentro de la seccion crítica, y si no existe un proceso adentro accedera la sección critica.
##### Características
* No garantiza la exclusión mutúa.
* El retardo puede ser tan grande que no se sabe en que momento el proceso podra acceder a la sección crítica.


### Código en Java
#### Proceso 1
```java
    doTasks("initial", 2);
    while (Main.gui.getP2qe()) {
        waitcs();
    }
    /*Aqui se rompe*/
    Main.gui.setP1qe(true);
    criticalSection(3);
    Main.gui.setP1qe(false);
    doTasks("final", 3);
```
#### Proceso 2
```java
	doTasks("initial", 3);
    while(Main.gui.getP1qe()){
        waitcs();
    }
    //Main.gui.setP2qe(true);
    criticalSection(4);
    Main.gui.setP2qe(false);
    doTasks("final", 2);
```
### Prueba de escritorio
| Instruccion | Proceso 1 Adentro | Proceso 2 Adentro |Estado Proceso 1| Estado Proceso 2 |
| :---:  | :---: | :---: | :---: | :---: |
| Start() | Falso | Falso | Nuevo | Nuevo |
| setTurn(n) | Verdadero | Falso | Listo| Listo |
| Si Proceso 2 Esta Adentro | Falso | Verdadero | En ejecución | Listo |
| Si Proceso 1 Esta Adentro| Verdadero | Falso | Listo | En ejecución |
| Excepcion| Verdadero | Verdadero | En ejecuion | En ejecución |
### Explicación


## Dekker cuarta versión (Postergación indefinida)
En esta version se coloca un retardo con un tiempo aleatorio, este retardo puede ser tan grande que el proceso se puede quedar esperando un evento que tal vez nunca suceda.
##### Características
* Garantiza la exclusión mutúa.
* Colision en la Sección crítica cuando ambos procesos pasan la fase de comprobación.

### Código en Java
#### Proceso 1
```java
	doTasks("initial", 2);
    Main.gui.setP1qe(true);
    while (Main.gui.getP2qe()) {
        Main.gui.setP1qe(false);
        try {
            waitcs();
            sleep(((int) ((Math.random() * 2) + 2)) * 1000);//Aumentar tiempo
        } catch (InterruptedException e) {
        }
        Main.gui.setP1qe(true);
    }
    criticalSection(3);
    Main.gui.setP1qe(false);
    doTasks("final", 3);
```
#### Proceso 2
```java
	doTasks("initial", 2);
    Main.gui.setP2qe(true);
    while(Main.gui.getP1qe()){
        Main.gui.setP2qe(false);
        try{
            waitcs();
            sleep(((int)((Math.random() * 2) + 2)) * 1000);
        }catch(InterruptedException e){}
        Main.gui.setP2qe(true);
    }
    criticalSection(4);
    Main.gui.setP2qe(false);
    doTasks("final", 3);
```
### Prueba de escritorio
| Instruccion | Entrada Proceso 1 | Entrada Proceso 2 |Estado Proceso 1| Estado Proceso 2 |
| :---:  | :---: | :---: | :---: | :---: |
| Start() | Falso | Falso | Nuevo | Nuevo |
| setTurn(n) | Verdadero | Falso | Listo| Listo |
| Si Proceso 1 Puede Entrar | Falso | Verdadero | En ejecución | Listo |
| Si Proceso 2 Puede Entrar| Verdadero | Falso | Listo | En ejecución |
| Excepcion| Verdadero | Falso | En ejecución | Listo |
### Explicación


## Dekker quinta versión (Algoritmo Optimo)
Esta version resulta de una combinación entre la primera y la cuarta versión.
El cual nos indica que al momento que un proceso accede a la seccion crítica, el otro proceso debe esperar hasta que el anterior termine para poder acceder tambien a la sección crítica.
##### Características
* Garantiza la exclusión mutúa.
* Garantiza el progreso.
* Garantiza una espera limitada.

### Código en Java
#### Proceso 1
```java
	doTasks("initial", 3);
    Main.gui.setP1qe(true);
    while (Main.gui.getP2qe()) {
        if (Main.gui.getTurn() == 2) {
            Main.gui.setP1qe(false);
            try {
                waitcs();
                sleep(((int) ((Math.random() * 2) + 2)) * 1000);//Aumentar tiempo
            } catch (InterruptedException e) {
            }
            Main.gui.setP1qe(true);
        }
    }
    criticalSection(4);
    Main.gui.setTurn(2);
    Main.gui.setP1qe(false);
    doTasks("final", 3);
```
#### Proceso 2
```java
	doTasks("initial", 3);
    Main.gui.setP2qe(true);
    while(Main.gui.getP1qe()){
        if(Main.gui.getTurn() == 1){
            Main.gui.setP2qe(false);
            try{
                waitcs();
                sleep(((int)((Math.random() * 3) + 2)) * 1000);
            }catch(InterruptedException e){}
            Main.gui.setP2qe(true);
        }
    }
    criticalSection(4);
    Main.gui.setTurn(1);
    Main.gui.setP2qe(false);
    doTasks("final", 3);
```
### Prueba de escritorio
| Instruccion | Entrada Proceso 1 | Entrada Proceso 2 |Estado Proceso 1| Estado Proceso 2 |
| :---:  | :---: | :---: | :---: | :---: |
| Start() | Falso | Falso | Nuevo | Nuevo |
| setTurn(n) | Verdadero | Falso | Listo| Listo |
| Si Proceso 1 Puede Entrar | Falso | Verdadero | En ejecución | Listo |
| Si Proceso 2 Puede Entrar| Verdadero | Falso | Listo | En ejecución |
| Excepcion| Verdadero | Verdadero | Listo | Listo |
### Explicación


## Solución de Peterson
En esta solucion Peterson permite a n procesos compartir un recurso sin conflictos, utilizando sólo memoria compartida para la comunicación.
##### Características
* Garantiza la exclusión mutúa.
* Garantiza el progreso.
* Garantiza una espera limitada.
* Funcional para n procesos.

### Código en Java
#### Proceso 1
```java
	doTasks("initial", 3);
    int other;
    other = 1;
    Main.gui.setInterested(0, true);
    Main.gui.setTurn(0);
    while (Main.gui.getTurn() == 0 && Main.gui.getInterested()[1]) {
        try {
            waitcs();
            sleep(((int) ((Math.random() * 2) + 2)) * 1000);
        } catch (InterruptedException e) {}
    }
    criticalSection(4);
    Main.gui.setInterested(0, false);
    doTasks("final", 3);
```
#### Proceso 2
```java
	doTasks("initial", 3);
    int other;
    other = 0;
    Main.gui.setInterested(1, true);
    Main.gui.setTurn(1);
    while (Main.gui.getTurn() == 1 && Main.gui.getInterested()[0]) {
        try {
            waitcs();
            sleep(((int) ((Math.random() * 2) + 2)) * 1000);
        } catch (InterruptedException e) {}
    }
    criticalSection(4);
    Main.gui.setInterested(1, false);
    doTasks("final", 3);
```
### Prueba de escritorio
| Instruccion | Turno|Proceso |Otro|Interesado[Otro]  |Interesado[Proceso]|Estado Proceso 1|Estado Proceso 2|
| :---:  | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| Start() | undefined | undefined | undefined | Falso |Falso|Nuevo|Nuevo|
| setTurn(n) | 1 | 1 | 2| Falso |Verdadero|Listo|Listo|
| mientras_Turno==Proceso_e_Interesado[Otro]==True | 1 | 1 | 2 | Falso |Verdadero|En ejecución|Listo|
| mientras_Turno==Proceso_e_Interesado[Otro]==True| 2 | 2 | 1 | Falso |Verdadero|Listo|En ejecución|



## Referencias
- https://github.com/ejgarciaq/AlgoritmoDekker/tree/master/src/algoritmos
- https://es.slideshare.net/nerexi/algoritmos-de-dekker-55306714
- http://cidecame.uaeh.edu.mx/lcc/mapa/PROYECTO/libro26/soluciones_software_para_la_exclusin_mutua.html

