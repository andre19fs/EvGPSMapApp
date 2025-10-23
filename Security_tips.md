# Security Tips Implementados

Este documento detalla las **medidas de seguridad (Security Tips)** aplicadas en la aplicación Android desarrollada, cuyo alcance actual se centra en un **menú principal**, **uso del servicio de Google Maps** y **descarga de una imagen desde Internet**.

Si bien la app aún no posee base de datos, autenticación de usuarios ni manejo avanzado de datos, las medidas aquí descritas son esenciales tanto para su seguridad presente como para su futura escalabilidad.

---

## Contexto general

En la etapa actual, algunas recomendaciones de seguridad avanzadas no aplican directamente —como protección contra inyección SQL o gestión de sesiones—, pero son **fundamentales si la aplicación evoluciona** para incorporar usuarios, datos persistentes o servicios propios.

Las medidas implementadas actualmente se centran en **proteger las comunicaciones, asegurar el uso de la API de Google Maps y reducir la exposición a ataques externos.**

---

## Tip 1: Autenticación y Autorización Seguras

### Intención
Proteger el acceso al servicio de **Google Maps**, el cual utiliza autenticación mediante una **API Key**.  
Aunque la aplicación no maneja usuarios o roles internos, la API Key debe protegerse para evitar su uso indebido por terceros.

### Implementación

Para asegurar esta autenticación, se utilizó la **plataforma de Google Cloud Console**, aplicando las siguientes medidas:

1. **Restricción por tipo de dispositivo:**  
   La API Key solo puede ser utilizada desde aplicaciones Android, evitando que sea ejecutada desde navegadores u otros entornos.

2. **Restricción por aplicación específica:**  
   Se limitó el uso de la clave a un **SHA-1 específico** y al **nombre del paquete** de la app, asegurando que solo esta versión pueda usar el servicio.

3. **Control de acceso adicional:**  
   Se configuró la API para **solicitar identificación y validación del dispositivo**, previniendo el abuso o el robo de la clave.

### Cómo mejora la seguridad

- **Evita el uso no autorizado de la API:** terceros no pueden reutilizar la API Key incluso si logran extraerla del APK.  
- **Previene costos y abusos:** protege al propietario de la clave frente a consumo indebido de recursos en Google Cloud.  
- **Mantiene la integridad del servicio:** garantiza que las peticiones a Google Maps provengan solo de la app legítima y autenticada.

---

## Tip 2: Protección contra ataques de red (MITM)

### Intención
Proteger los datos transmitidos entre la aplicación y los servicios externos (por ejemplo, Google Maps o servidores de descarga de imágenes), evitando su interceptación o modificación.

### Implementación

1. **Uso obligatorio de HTTPS:**  
   Se verificó que todas las conexiones salientes usen **protocolo HTTPS** en lugar de HTTP, asegurando una comunicación cifrada.

2. **Bloqueo de tráfico no cifrado:**  
   Se configuró el archivo res/xml/network_security_config.xml para prohibir conexiones HTTP, reforzando el uso de HTTPS de manera obligatoria.

3. **Validación de certificados:**  
   Al usar HTTPS, Android valida automáticamente los certificados SSL emitidos por autoridades de confianza, evitando conexiones a sitios falsos o inseguros.

### Cómo mejora la seguridad

- **Previene ataques MITM (Man-in-the-Middle): impide que terceros intercepten, modifiquen o suplanten las comunicaciones de red.
- **Cifra la información enviada y recibida: protege las imágenes descargadas y los datos de ubicación
- **Aumenta la confiabilidad del servicio: garantiza que la información proveniente de Google Maps o servidores externos no haya sido alterada.

  

