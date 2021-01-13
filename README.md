# ModyProg_Proyecto2
Integrantes: 
- Axel David García Beltrán
- Carlos Arturo Velázquez Nolasco 

Este programa fue hecho con maven por lo que es necesario que instale maven en su
ordenador.
En sistemas basados en Debian, solo necesita escribir en su terminal
```bash
sudo apt update
```
```bash
 sudo apt install maven
```
Para verificar que este correctamente instalado, escriba en su terminal
```bash
 mvn --version
```
Una instalado maven, abra la carpeta del programa en su terminal e ingrese a la
carpeta proyecto2, una vez dentro escriba el siguiente comando

```bash
 mvn clean package
```
Este comando compila el proyecto y genera el archivo .jar necesario para la ejecución,
una vez compilado exitosamente, escriba el siguiente comando para ejecutar el programa

```bash
 java -cp target/proyecto2-1.0.jar proyecto2/Main archivo.jpg
```
en la parte de archivo.jpg, escriba el nombre de la imagen que desea procesar, si desea
generar una copia en blanco y negro de la imagen ingresada, ejecute el comando con la
bandera -s o -S

```bash
 java -cp target/proyecto2-1.0.jar proyecto2/Main archivo.jpg -s
```
