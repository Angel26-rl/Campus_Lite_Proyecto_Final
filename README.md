#  Campus Lite

### Programación I – Proyecto Final  
### Universidad Mariano Gálvez de Guatemala, Campus Jutiapa

---

## Descripción del Proyecto

**Campus Lite** es una aplicación de escritorio desarrollada en Java orientada a la gestión académica básica de una institución educativa.

El sistema permite administrar información relacionada con estudiantes, cursos y reportes académicos mediante una interfaz gráfica moderna e intuitiva, facilitando operaciones de registro, consulta, edición, eliminación y visualización estadística de datos.

El proyecto fue desarrollado como parte del curso **Programación I**, aplicando conceptos fundamentales de programación orientada a objetos, estructuras de datos, persistencia de información y trabajo colaborativo con control de versiones.

---

## Integrantes del Equipo

| Nombre | Carnet |
|------|--------|
| Angel Geovanni Ramos López | 0905-24-6447 |
| Carlin Alondra Argueta Espino | 0905-25-13150 |
| Esaú Cardona Godoy | 0905-25-400 |

---

##  Tecnologías Utilizadas

- **Java**
- **Java Swing (GUI)**
- **Eclipse IDE 2025**
- **CSV (persistencia de datos)**
- **Git**
- **GitHub**
- **Trello**
- **Google Meet**
- **WhatsApp**

---

##  Repositorio del Proyecto

Repositorio oficial del proyecto:

```bash
https://github.com/Angel26-rl/Campus_Lite_Proyecto_Final
```

---

##  Organización del Equipo de Trabajo

El desarrollo del proyecto se realizó de forma colaborativa utilizando distintas herramientas de comunicación y coordinación.

Durante la **primera semana**, el equipo realizó reuniones virtuales mediante **Google Meet**, con el objetivo de definir la idea principal del sistema, establecer funcionalidades y distribuir responsabilidades.

También se creó un grupo de **WhatsApp**, utilizado para:

- Resolver dudas rápidas
- Compartir avances
- Coordinar tareas
- Discutir cambios o mejoras

Cuando surgían dudas complejas o era necesario revisar decisiones importantes, se realizaban nuevamente reuniones por Google Meet.

En esta etapa inicial se trabajó en:

- Definición del concepto del sistema
- Planificación de módulos
- Creación del repositorio GitHub
- Configuración inicial del proyecto
- Preparación de estructura base del sistema

Una vez construida la base inicial del proyecto, los integrantes sincronizaban los avances utilizando Git mediante:

```bash
git pull
git add .
git commit
git push
```

Posteriormente, cada integrante desarrolló módulos específicos mientras el resto aportaba ideas, mejoras visuales o apoyo lógico.

Durante la **segunda semana**, el trabajo se enfocó en:

- Integración de módulos
- Corrección de errores
- Mejoras visuales
- Validaciones
- Persistencia de datos
- Desarrollo del módulo de reportes

Gracias a una estructura organizada y comunicación constante, el proyecto logró completarse satisfactoriamente.

---

##  Funcionalidades Implementadas

###  Gestión de Estudiantes

- Registro de estudiantes
- Validación de campos obligatorios
- Selección de carrera mediante ComboBox
- Edición de estudiantes
- Eliminación con confirmación
- Visualización de listado completo
- Persistencia mediante CSV

Carreras disponibles:

- Ingeniería en Sistemas
- Ingeniería Civil
- Ingeniería Industrial
- Ingeniería Electrónica
- Arquitectura
- Administración de Empresas
- Contaduría Pública
- Derecho

---

###  Gestión de Cursos

- Registro inteligente de cursos
- Selección automática por carrera
- Carga automática de:
  - Nombre del curso
  - Créditos
  - Horario
- Edición de cursos
- Eliminación con confirmación
- Visualización de listado completo
- Persistencia mediante CSV

---

###  Módulo de Reportes

- Total de estudiantes registrados
- Total de cursos registrados
- Total de créditos acumulados
- Última actualización
- Reportes dinámicos mediante selector
- Listado de estudiantes
- Listado de cursos
- Estudiantes por carrera
- Cursos por carrera
- Curso con mayor cantidad de créditos
- Últimos estudiantes registrados
- Botón de acceso al listado completo
- Actualización dinámica de reportes

---

##  Persistencia de Datos

El sistema utiliza archivos CSV para almacenar información permanentemente.

Archivos utilizados:

```bash
students.csv
courses.csv
```

Esto permite que la información permanezca guardada incluso después de cerrar y volver a ejecutar la aplicación.

---

##  Conceptos Aplicados de Programación Orientada a Objetos

Durante el desarrollo del proyecto se aplicaron conceptos fundamentales de Programación Orientada a Objetos:

### Encapsulamiento
Uso de atributos privados con acceso controlado mediante getters y setters en clases del dominio como:

- Student
- Course

---

### Herencia
Uso de herencia mediante interfaces gráficas que extienden de clases base de Swing:

```java
extends JFrame
```

---

### Polimorfismo
Aplicación de polimorfismo mediante sobrescritura de métodos como:

```java
paintComponent()
```

permitiendo personalizar el comportamiento visual de componentes.

---

### Abstracción
Separación clara de responsabilidades mediante estructura modular:

- `domain` → entidades del sistema
- `service` → manejo de datos
- `ui` → interfaz gráfica

---

##  Problemas Encontrados y Soluciones

### 1. Conflictos con GitHub
Durante el trabajo colaborativo surgieron conflictos al realizar sincronización del repositorio.

**Problemas:**
- cambios locales sin guardar
- conflictos entre versiones
- errores al hacer push o pull

**Solución:**
uso ordenado de Git:

```bash
git status
git add .
git commit
git pull
git push
```

---

### 2. Errores de compilación
Se presentaron errores relacionados con:

- llaves mal ubicadas
- bloques duplicados
- métodos mal insertados
- errores sintácticos

**Solución:**
revisión y reorganización del código.

---

### 3. Persistencia incorrecta
Inicialmente algunos cambios no se guardaban correctamente después de editar o eliminar.

**Solución:**
corrección de métodos de guardado y carga CSV.

---

### 4. Problemas visuales
Las primeras versiones contenían imágenes de fondo con baja calidad y elementos duplicados.

**Solución:**
rediseño visual completo utilizando fondos mejorados.

---

### 5. Integración entre módulos
Integrar componentes desarrollados por distintos integrantes generó ajustes necesarios.

**Solución:**
comunicación constante mediante WhatsApp y reuniones virtuales.

---

##  Gestión del Proyecto

Tablero Trello utilizado para organización:

https://trello.com/b/2kUCdBZY/programacion-1-pf

---

---

##  Instalación y Ejecución

### Clonar repositorio

```bash
git clone https://github.com/Angel26-rl/Campus_Lite_Proyecto_Final.git
```

---

### Abrir proyecto

Abrir el proyecto en:

```bash
Eclipse IDE 2025
```

---

### Ejecutar

Ejecutar la clase principal:

```bash
Main.java
```

---

##  Conclusión

El proyecto **Campus Lite** permitió aplicar de forma práctica conocimientos fundamentales de programación, diseño de interfaces gráficas, persistencia
de datos, control de versiones y trabajo colaborativo.

A pesar de los retos técnicos encontrados durante el desarrollo, la organización del equipo, la comunicación constante y la resolución progresiva de
problemas permitieron completar satisfactoriamente un sistema funcional, visualmente atractivo y adecuado para la gestión académica básica.

---
