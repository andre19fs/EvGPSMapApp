# Best Practices Implementadas en la Aplicación

Este documento detalla las **Buenas Prácticas (Best Practices)** de seguridad aplicadas en la aplicación Android desarrollada, la cual cuenta con un menú principal, integración con **Google Maps** y funcionalidad para **descargar imágenes**.

El objetivo principal de estas medidas es **asegurar el código, proteger los datos del usuario y garantizar comunicaciones seguras**, reduciendo las vulnerabilidades detectadas en el análisis realizado con MobSF.

---

##  Asegurar el código e infraestructura

### Intención
Evitar que terceros manipulen la aplicación, extraigan información sensible o modifiquen su comportamiento a través de depuración, análisis o ingeniería inversa.

### Práctica aplicada: Desactivar el modo Debug

Para impedir que alguien pueda depurar la aplicación o acceder a sus registros desde un dispositivo físico, se **deshabilitó el modo de depuración** dentro del archivo `AndroidManifest.xml`.

## Como mejora la seguridad
-Previene accesos no autorizados: evita que herramientas como ADB o Android Studio puedan conectarse a la app para inspeccionarla.
-Protege información sensible: bloquea la exposición de rutas, claves o datos internos en los registros del sistema.
-Dificulta la ingeniería inversa: la aplicación se comporta como una versión de producción, impidiendo manipulaciones o alteraciones del código.


##  Asegurar la comunicacion de red

### Intención
Evitar que los datos viajen sin cifrado, garantizando que todas las comunicaciones entre la aplicación y los servidores (como en las descargas de imágenes o el uso de Google Maps) sean seguras.

### Práctica aplicada: Forzar HTTPS en las solicitudes

Se verificó que todas las solicitudes realizadas desde la aplicación utilicen HTTPS, asegurando que los datos se transmitan de forma cifrada.

### Práctica aplicada: Bloquear el uso de HTTP en toda la aplicación

Se creó el archivo network_security_config.xml para prohibir el tráfico no cifrado y Luego, se aplico la configuración en el AndroidManifest.xml

## Como mejora la seguridad
-Previene ataques MITM (Man-In-The-Middle): los datos no pueden ser interceptados o modificados durante la transmisión.
-Bloquea conexiones inseguras: el sistema Android rechaza automáticamente cualquier intento de conexión HTTP.
-Asegura la integridad de la información: los datos viajan cifrados, reduciendo el riesgo de manipulación o robo de información sensible.

