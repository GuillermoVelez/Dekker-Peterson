# ALGORITMOS PARA SOLUCIONAR PROBLEMAS DE CONCURRENCIA
## _Dekker y Peterson_

### Realizado por:
- Christian Galindo
- Guillermo Vélez

A continuación se presentan 6 algoritmos para solucionar problemas de concurrencia con 2 procesos (cinco de Dekker y uno de peterson), se detalla su funcionamiento y sus casos de error.


## Dekker primera versión
### Código en Java
#### Proceso 1
```java
	// Hace tasks
    doTasks("initial", 2);
    // Espera a que la región crítica se desocupe
    while (Main.gui.getTurn() == 2) {
        waitcs();
    }
    // Accede a Región Crítica
    criticalSection(3);
    // Hace final tasks   
    Main.gui.setTurn(2);
    doTasks("final", 3);.
```
#### Proceso 2
```java
	// Hace tasks
    doTasks("initial", 2);
    // Espera a que la región crítica se desocupe
    while (Main.gui.getTurn() == 1) {
        waitcs();
    }
    // Accede a Región Crítica
    criticalSection(2);
    // Hace final tasks            
    Main.gui.setTurn(1);
    doTasks("final", 2);
```
### Prueba de escritorio
| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |
### Explicación

## Dekker segunda versión
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
| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |
### Explicación


## Dekker tercera versión
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
| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |
### Explicación


## Dekker cuarta versión
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
| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |
### Explicación


## Dekker quinta versión
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
| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |
### Explicación


## Solución de Peterson
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
| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |
### Explicación

## Referencias
- https://github.com/ejgarciaq/AlgoritmoDekker/tree/master/src/algoritmos
- https://es.slideshare.net/nerexi/algoritmos-de-dekker-55306714
