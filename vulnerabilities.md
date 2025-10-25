# Vulnerabilities Report

## Resumen
- **Total vulnerabilities found:** 5  
- **Critical:** 0  
- **High:** 3  
- **Medium:** 1  
- **Low:** 1  

---

## Vulneravilidades encontradas

### 1. Componentes exportados sin protección
**Descripción:**  
Las actividades principales de la aplicación (mapa, imagen y menú) se encuentran exportadas sin la debida protección, permitiendo que aplicaciones externas puedan acceder a ellas o invocarlas directamente.

**Severidad:** Alta  

**Impacto:**  
Permite la ejecución de acciones sensibles desde aplicaciones de terceros, comprometiendo la integridad y confidencialidad de los datos del usuario.

**Pasos para corregir:**  
1. Inspeccionar el archivo `AndroidManifest.xml`.  
2. Revisar los atributos `android:exported="true"` en las actividades.  
3. Intentar invocar una `Activity` desde otra app.  

**Correccion:**  
Establecer `android:exported="false"` para las actividades internas o requerir permisos explícitos de acceso.

---

### 2. Permisos excesivos o sensibles
**Descripción:**  
La aplicación solicita permisos como `ACCESS_COARSE_LOCATION` y `ACCESS_FINE_LOCATION` para funcionalidades de mapas y seguimiento. Sin embargo, estos permisos permiten a terceros acceder a la ubicación aproximada o precisa del usuario.

**Severidad:** Alta  

**Impacto:**  
Exposición de la ubicación del usuario a terceros, lo que compromete la privacidad y puede facilitar rastreo no autorizado.

**Pasos para corregir:**  
1. Revisar los permisos declarados en el `AndroidManifest.xml`.  
2. Analizar los permisos utilizados en tiempo de ejecución.  

**Correccion:**  
Solicitar únicamente los permisos estrictamente necesarios y utilizar permisos de ubicación mientras la app está en uso (`ACCESS_FINE_LOCATION` con control contextual).

---

### 3. Claves expuestas (API Key)
**Descripción:**  
La aplicación contiene una API Key de Google Cloud directamente en el código fuente.  

**Severidad:** Alta  

**Impacto:**  
Terceros pueden extraer la clave, generando costos indebidos, interrumpiendo servicios o accediendo a datos privados asociados a la cuenta.  

**Pasos para corregir:**  
1. Analizar el código fuente o el paquete APK con MobSF.  
2. Buscar cadenas con patrones de claves API (`AIza...`, `sk-...`).  

**Correccion:**  
Eliminar las claves del código y almacenarlas de forma segura en el backend o en el Keystore de Android.

---

### 4. Tráfico HTTP inseguro
**Descripción:**  
La aplicación utiliza el protocolo HTTP en lugar de HTTPS para la comunicación con los servicios web.

**Severidad:** Media  

**Impacto:**  
El tráfico puede ser interceptado o modificado (ataques MITM), exponiendo datos del usuario.  

**Pasos para corregir:**  
1. Interceptar las comunicaciones con un proxy (por ejemplo, Burp Suite).  
2. Observar solicitudes HTTP sin cifrado.  

**Correccion:**  
Implementar HTTPS mediante certificados válidos y configurar un `network_security_config.xml` para rechazar conexiones no seguras.

---

### 5. Modo Debug habilitado
**Descripción:**  
El `android:debuggable` está establecido en `true`, lo que permite a terceros acceder a los logs y depurar la aplicación.

**Severidad:** Baja  

**Impacto:**  
Posible acceso a información sensible a través de registros del sistema o conexiones de depuración USB.

**Pasos para corregir:**  
1. Revisar el manifiesto de la app (`AndroidManifest.xml`).  
2. Confirmar que `android:debuggable="true"`.  

**Correccion:**  
Configurar `android:debuggable="false"` antes de la publicación de la app.

---

## Evaluacion de riesgo

| Nivel  | Cantidad | Impacto |
|--------|----------|---------|
| Alto   |    3     |Altamente peligroso |
| Medio  |    1     | Peligroso |
| Bajo   |    1     | Poco riesgo |

---

## Observaciones generales
- **Herramienta utilizada:** MobSF (análisis estático y dinámico).  
- **Score general:** 45/100 — nivel de seguridad bajo.  
- **SDK objetivo:** 26 (Android 8), sin soporte ni parches recientes.  
- **Riesgo global:** Medio–Alto.  

---
