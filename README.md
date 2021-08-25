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
Su nombre se debe a que si en cada ráfaga de CPU, cada proceso queda en el mismo estado,en el estado donde se le asigna que puede entrar a la sección critica. Entonces estando los dos procesos con opción a entrar, a la siguiente ráfaga de CPU ambos procesos verificaran si el proceso alterno puede entrar, viendo que el proceso alterno tiene la opción de entrar, los procesos quedan bloqueados ya que se quedaran en espera circular bloqueándose mutuamente ya que no podrán entrar nunca a la sección critica.

##### Características
* Garantiza la exclusión mutúa.
* No garantiza una espera limitada.

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
* El proceso que es ejecutado después de realizar sus tareas iniciales,a este procesose le permite entrar.
* Cuando ya puede entrar verifica si otro proceso tiene la opción de poder entrar, si otro proceso también tiene la opción de poder entrar se da un interbloqueo.De lo contrario el proceso avanza a la sección crítica.
* Al salir de la sección crítica el proceso cambia su opción. Y permite al otro proceso avanzar a la sección crítica.


## Dekker tercera versión (Colisión región crítica no garantiza la exclusión mutua)
La Tercera versión del algoritmo de Dekker es llamado Colisión región crítica no garantiza la exclusión mutua, como su nombre lo indica se da una colisión en la región crítica por la forma en que son colocados por así decirlo los permisos, ya que primero se comprueba si otro proceso está dentro y luego se indica que el proceso en el que se está actualmente cambia diciendo que está dentro. Y el problema se da cuando los procesos después de haber tenido sus ráfagas de CPU pasan de la fase de comprobación y se tiene libre el camino para entrar a la región crítica, generando esto una colisión. 

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
* Al ejecutarse el proceso y después de realizar sus tareas iniciales, verifica si otro proceso está dentro de la sección critica.
* Si el otro proceso está dentro entonces espera a que salga de la sección crítica. De lo contrario pasa la fase de comprobación y cambia su estado a que está dentro.
* Luego de pasar la sección crítica cambia su estado, termina sus tareas finales.

## Dekker cuarta versión (Postergación indefinida)
Su nombre se debe a que en una parte del código es colocado un retardo con un tiempo aleatorio, y el retardo puede ser tan grande que no se sabe hasta cuándo entrara a la sección critica.

##### Características
* Garantiza la exclusiónmutua.
* Un proceso o varios se quedan esperando a que suceda un evento que tal vez nunca suceda.

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
* Luego de realizar sus tareas iniciales el proceso solicita poder entrar en la sección crítica, si el otro proceso no puede entrar ya que su estado es falso entonces el proceso entra sin problema a la sección critica. 
* De lo contrario si el otro proceso también puede entrar entonces se entra al ciclo donde el proceso actual se niega el paso así mismo y con un retardo de x tiempo siendo este aleatorio se pausa el proceso, para darle vía libre a los otros procesos. 
* Luego de terminar su pausa entonces el proceso actual nuevamente puede entrar y nuevamente si el otro proceso puede entrar se repite el ciclo y si no hay otro proceso, entonces el proceso puede entrar en la sección critica. 
* Cambia su estado y luego realiza sus tareas finales.


## Dekker quinta versión (Algoritmo Optimo)
Esta version resulta de una combinación entre la primera y la cuarta versión.
El cual nos indica que al momento que un proceso accede a la seccion crítica, el otro proceso debe esperar hasta que el anterior termine para poder acceder tambien a la sección crítica.

##### Características
* Garantiza la exclusión mutúa.
* Progreso.
* Espera limitada.

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
* Se realizalastareas iniciales, luego se verifica si hay otro procesos que puede entrar, si lo hay se entra al ciclo y si es el turno de algún otro proceso cambia su estado a ya no poder entrar a la sección crítica y nuevamente verifica si es el turno de algún otro proceso si lo es se queda en ciclado hasta que se da un cambio de turno, luego nuevamente retoma su estado de poder entrar a la sección critica, regresa al ciclo y verifica si hay otro proceso que puede entrar entonces nuevamente se encicla, de lo contrario entra a la sección critica. 
* Al salir de la sección critica el proceso cambia su turno, cambia su estado y realiza sus tareas finales.


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

