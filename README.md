# Arquitectura Hexagonal Service

Este proyecto implementa una arquitectura hexagonal en Java con Spring Boot, organizada en módulos que separan la lógica de negocio de la infraestructura. Proporciona un servicio de precios con endpoints expuestos para su funcionalidad y está configurado para pruebas automáticas y análisis de calidad de código con SonarQube.

## Contenidos

- [Tecnologías](#tecnologías)
- [Instalación](#instalación)
- [Ejecución del Proyecto](#ejecución-del-proyecto)
- [Probar el Servicio con Postman](#probar-el-servicio-con-postman)
- [Pruebas y Reportes](#pruebas-y-reportes)
- [Análisis de Calidad de Código con SonarQube](#análisis-de-calidad-de-código-con-sonarqube)
- [Estructura del Proyecto](#estructura-del-proyecto)

---

## Tecnologías

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.3.4**: Framework para la configuración y el despliegue del proyecto.
- **Arquitectura Hexagonal**: Estructura modular que separa la lógica de negocio de la infraestructura.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **JUnit y Mockito**: Herramientas para pruebas unitarias e integración.
- **JaCoCo**: Para generar reportes de cobertura de código.
- **SonarQube**: Para análisis de calidad de código.
- **H2 Database**: Base de datos en memoria para pruebas locales.
- **Postman**: Utilizado para probar los endpoints del servicio REST.

## Instalación

1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/pacobravo89/arquitectura-hexagonal-java.git
   cd arquitectura-hexagonal-java


# Guía de Instalación y Ejecución

## 2. Instalación de Dependencias

Asegúrate de tener **Java 17** y **Maven** instalados en tu entorno antes de continuar.

## 3. Configurar SonarQube (Opcional para Análisis de Calidad)

1. Descarga SonarQube desde [https://www.sonarqube.org/downloads/](https://www.sonarqube.org/downloads/).
2. Descomprime y ejecuta SonarQube según tu sistema operativo.

## 4. Ejecución del Proyecto

Para levantar el proyecto, ejecuta los siguientes comandos en la terminal:

    ```bash
    mvn clean install
    mvn spring-boot:run

El servicio estará disponible en [http://localhost:8080](http://localhost:8080).

## Probar el Servicio con Postman

En la carpeta `/postman` del proyecto se encuentran los archivos necesarios para probar los endpoints:

1. Abre **Postman** y selecciona **Importar**.
2. Importa el archivo `Collection.json` ubicado en `/postman`.
3. Importa el archivo de entorno `Environment.json` en la misma carpeta.
4. Configura el entorno y ejecuta las solicitudes de la colección.

## Pruebas y Reportes

El proyecto incluye pruebas automatizadas con JUnit y Mockito, y genera reportes de cobertura de código con JaCoCo.

### Ejecutar Pruebas

Para ejecutar las pruebas, usa el siguiente comando:

    ```bash
    mvn test

### Reportes de Cobertura

Los reportes generados por JaCoCo estarán en `target/site/jacoco`. Abre `index.html` en un navegador para ver la cobertura de pruebas.

## Análisis de Calidad de Código con SonarQube

1. **Configura `application.properties`**: Descomenta y ajusta las variables de Sonar en `application.properties`:

   ```properties
   # SonarQube configuration
   sonar.host.url=http://localhost:9000
   sonar.projectKey=arquitectura-hexagonal-java
   sonar.login=YOUR_SONAR_TOKEN

2. **Ejecuta el análisis de SonarQube**:

   ```bash
   mvn sonar:sonar

Accede al análisis en [http://localhost:9000](http://localhost:9000).

## Estructura del Proyecto

El proyecto sigue la arquitectura hexagonal, dividiendo responsabilidades en módulos:

- **domain**: Contiene la lógica de negocio principal.
- **application**: Interactúa con el dominio y expone casos de uso.
- **infrastructure**: Implementa adaptadores de persistencia y configuración de infraestructura, tambien define los controladores REST.
- **web**: Configuración de la API.
