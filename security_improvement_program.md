# Programa de Mejora de Seguridad de la Aplicación

Este documento define un **Programa de Mejora de Seguridad** diseñado para evaluar, fortalecer y mantener la seguridad de la aplicación Android desarrollada, cuyo alcance actual incluye:

- Menú principal.  
- Integración con **Google Maps (API Key protegida)**.  
- Descarga de una imagen desde Internet.  

Aunque la aplicación aún no cuenta con base de datos, autenticación de usuarios o manejo avanzado de datos, este programa establece una **base sólida para un ciclo continuo de mejora y evaluación de seguridad**, adaptable a su futura evolución.

---

## Objetivo del Programa

El propósito del **Programa de Mejora de Seguridad** es garantizar que la aplicación:

- Mantenga un **nivel de seguridad coherente y medible** durante su desarrollo y mantenimiento.  
- **Identifique vulnerabilidades a tiempo**, reduciendo el riesgo de exposición de datos o fallas de configuración.  
- Esté alineada con las **buenas prácticas y estándares de seguridad móvil**, como Android Security Best Practices.

---

## Estructura del Programa

El programa se divide en **tres fases principales** que se aplican de manera continua en el ciclo de desarrollo.

### Fase 1: Evaluación Inicial

**Objetivo:** Detectar vulnerabilidades actuales y definir el punto de partida del nivel de seguridad.

**Acciones:**
- Ejecutar análisis estático y dinámico con **MobSF** para identificar vulnerabilidades en permisos, tráfico de red y configuraciones inseguras.  
- Documentar los hallazgos y clasificarlos por nivel de severidad (alto, medio, bajo).  

**Resultado esperado:**  
Un diagnóstico claro del estado de seguridad actual de la aplicación.

---

### Fase 2: Implementación de Mejoras

**Objetivo:** Corregir vulnerabilidades y aplicar buenas prácticas según el contexto de la app.

**Acciones principales:**
- Asegurar el código fuente (desactivación de debug).  
- Forzar el uso de **HTTPS** para todas las comunicaciones.  
- Proteger la **API Key de Google Maps** mediante restricciones en Google Cloud.  

**Resultado esperado:**  
La aplicación reforzada que cumple con los estándares básicos de seguridad móvil.

---

### Fase 3: Monitoreo y Mejora Continua

**Objetivo:** Mantener la seguridad en el tiempo mediante revisiones planificadas y métricas medibles.

**Acciones:**
- Establecer revisiones **cuatrimestrales** del estado de seguridad.  
- Repetir análisis con MobSF tras cada actualización o cambio significativo en el código.  
- Revisar la configuración de la API Key y las políticas de red periódicamente.  


**Resultado esperado:**  
Un proceso que garantiza la mejora continua y la detección temprana de riesgos.

---

## Métricas Clave de Seguridad

Para evaluar la efectividad del programa y el nivel de seguridad alcanzado, se establecen las siguientes **métricas**:

| Métrica                                                 | Descripción                                                      | Frecuencia               | Objetivo                    |
|---------------------------------------------------------|------------------------------------------------------------------|--------------------------|-----------------------------|
| **Puntaje de MobSF**                                    | Resultado global del análisis estático/dinámico.                 | Cada revisión trimestral | Mantener sobre 70/100       |
| **Número de vulnerabilidades críticas**                 | Cantidad de fallas clasificadas como “altas”.                    | Por versión              | 0 vulnerabilidades críticas |
| **Uso de HTTP**                                         | Verificación de conexiones inseguras.                            | En cada revisión         | 0 conexiones HTTP           |
| **Exposición de API Keys**                              | Validación de que las claves no estén embebidas sin restricción. | Cada actualización       | 0 claves expuestas          |
| **Cumplimiento de buenas prácticas Android**            | Revisión de configuraciones: debuggable, backup, permisos.       | Semestral                | 100% cumplidas               |

---

## Proceso de Revisión Periódica

| Etapa                              | Frecuencia                                    | Actividades                                   | Herramientas                          |
|------------------------------------|-----------------------------------------------|-----------------------------------------------|---------------------------------------|
| **Revisión técnica**               | Cuatrimestral                                 | Análisis estático con MobSF y revisión de red | MobSF, Android Studio                 |
| **Validación de infraestructura**  | Cuatrimestral                                 | Revisión de API Keys, HTTPS     | Google Cloud |
| **Actualización de documentación** | Después de cada cambio relevante              | Actualizar este programa | Repositorio                  |

---

## Plan de Acción para Futuras Mejoras de Seguridad

Conforme la aplicación evolucione y adquiera nuevas funcionalidades (usuarios, base de datos, backend, etc.), se implementará el siguiente **plan de acción progresivo**:

| Fase | Mejora | Descripción | Prioridad | Estado |
|-------|----------|-------------|------------|---------|
| **1** | **Autenticación segura (JWT / Firebase)** | Incorporar autenticación de usuarios con tokens seguros y expiración controlada. | Alta | Futuro |
| **2** | **Control de roles y permisos** | Establecer diferentes niveles de acceso (usuario, admin) para operaciones críticas. | Media | Futuro |
| **3** | **Cifrado de almacenamiento local** | Implementar `EncryptedSharedPreferences` y cifrado AES para datos sensibles. | Alta | Futuro |
| **4** | **Verificación de integridad del APK** | Añadir comprobación de firma digital para detectar manipulaciones. | Alta | Futuro |
| **5** | **Protección avanzada contra MITM** | Incorporar Certificate Pinning con `OkHttpClient`. | Alta | Pendiente |
| **6** | **Revisión externa de seguridad (pentesting)** | Contratar auditorías periódicas con herramientas o expertos externos. | Alta | Futuro |

---


