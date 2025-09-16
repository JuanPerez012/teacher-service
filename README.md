# teacher-service

Microservicio responsable de la gestión de profesores y de los servicios centrados en la carga, validación y análisis de calificaciones. Expondrá endpoints para carga de archivos (Excel/PDF), validación, procesamiento y consulta de estadísticas académicas.

---

## Descripción del repositorio

Este repositorio contiene únicamente la implementación del **Teacher Service**. Su responsabilidad principal es:

* Gestionar la carga de **archivos de calificaciones** (Excel y PDF).
* Validar formato y encabezados de los archivos (materia, estudiante, nota).
* Convertir documentos (Excel/PDF) a estructuras de datos procesables.
* Detectar errores en los archivos (faltantes o inconsistencias).
* Generar y exponer vistas previas de los datos procesados.
* Almacenar calificaciones en base de datos relacional.
* Mantener un historial de archivos subidos.
* Exponer endpoints de estadísticas y reportes (por materia, estudiante, cortes, distribuciones).
* Generar visualizaciones (barras, circulares, líneas).
* Integrarse con otros microservicios (Auth, Student, University, Notification).

Tecnologías principales:

* Java 21  
* Spring Boot 3.5.5  
* Build: Maven  
* Tests: JUnit 5  
* Base de datos: PostgreSQL  
* Contenerización prevista (Docker)  

---

## Estructura del repositorio

Ejemplo (alto nivel):

```
/
├─ src/
│  ├─ main/
│  │  ├─ java/         → código fuente
│  │  └─ resources/    → application.properties
│  └─ test/            → pruebas unitarias con JUnit 5 y Testcontainers
├─ docs/                → diagramas, contratos API (OpenAPI), ADRs
├─ Dockerfile
├─ pom.xml
├─ .gitignore
└─ README.md
```

---

## Políticas de rama y flujo de trabajo

Ramas principales:

* **main** → solo código listo para producción.  
* **release/** → estabilización previa a producción.  
* **qa** → fuente para despliegues en entorno QA.  
* **develop** → integración diaria.  

Flujo típico:  
`feature/*` → PR → `develop` → merge a `qa` → validación → `release/*` → `main`.

---

## Perfiles / properties (con Maven)

* **application.properties** → Configuración base.  
* **application-dev.properties** → Desarrollo local (PostgreSQL local, logging debug).  
* **application-qa.properties** → QA (DB QA, logging info).  
* **application-prod.properties** → Producción (pool optimizado, logging reducido, seguridad).  

---

## API (versionado)

Versionado base: `/api/v1/...`.

## Endpoints principales previstos

### Teacher
* `GET  /api/v1/teachers` → Listar docentes (con filtros por specialty, status).  
* `GET  /api/v1/teachers/{id}` → Obtener docente por ID.  
* `POST /api/v1/teachers` → Crear docente.  
* `PUT  /api/v1/teachers/{id}` → Actualizar datos del docente.  
* `PATCH /api/v1/teachers/{id}/status` → Cambiar estado (ACTIVE/INACTIVE).  
* `DELETE /api/v1/teachers/{id}` → Baja lógica o eliminación del docente.  

### TeacherAssignment

* `GET  /api/v1/teachers/{teacherId}/assignments` → Listar asignaciones del docente.  
* `POST /api/v1/teachers/{teacherId}/assignments` → Crear asignación docente–materia–grupo–periodo.  
* `PUT  /api/v1/teachers/{teacherId}/assignments/{assignmentId}` → Actualizar asignación.  
* `DELETE /api/v1/teachers/{teacherId}/assignments/{assignmentId}` → Eliminar asignación.  

### TeacherAccount (opcional, integración con Security)

* `GET  /api/v1/teachers/{teacherId}/account` → Consultar cuenta asociada.  
* `POST /api/v1/teachers/{teacherId}/account` → Asociar docente con cuenta.  
* `PUT  /api/v1/teachers/{teacherId}/account` → Actualizar rol o estado.  
* `DELETE /api/v1/teachers/{teacherId}/account` → Desasociar cuenta.  

---

## Dockerización (concepto)

* Imagen base: JDK 21.  
* Multi-stage build.  
* Variables de entorno para perfiles.  
* Healthcheck en `/actuator/health`.  
* Tagging por versión (`vX.Y.Z`) y commit (`sha`).  

---

## Enlaces relevantes

* Base repo [university-academic-tracker](https://github.com/IAndresPH/university-academic-tracker.git)

---

## Colaboración y convención de commits

* Convencional commits: `feat(teacher): add file upload endpoint`.  
* PRs deben:  
  * Referenciar HU o issue en Jira.  
  * Incluir descripción clara.  
  * Ser revisadas antes de merge.