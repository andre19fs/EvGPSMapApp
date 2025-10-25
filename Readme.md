# Aplicación Android — Seguridad y Mejora Continua

## Descripción

Este proyecto es una **aplicación Android** que integra funcionalidades básicas como:
- Un **menú principal**.  
- La utilización del servicio de **Google Maps** mediante una **API Key protegida**.  
- La **descarga de una imagen desde Internet**.  

El enfoque principal del proyecto es **evaluar, reforzar e implementar medidas de seguridad** que protejan la aplicación frente a vulnerabilidades comunes en entornos móviles.

---

## Vulnerabilidades Identificadas

Durante el análisis inicial realizado con **MobSF**, se detectaron las siguientes vulnerabilidades:

- **Comunicación no segura (HTTP)**  
- **Exposición de claves (API Key de Google Maps)**  
- **Configuraciones inseguras (modo debug habilitado, permisos excesivos)**  


---

## Mejoras Implementadas

Para mitigar las vulnerabilidades y fortalecer la seguridad general, se aplicaron las siguientes mejoras:

- **Desactivación del modo debug ** en `AndroidManifest.xml`.  
- **Comunicación segura mediante HTTPS**, bloqueando el uso de HTTP a través del archivo `network_security_config.xml`.  
- **Protección de la API Key de Google Maps**, restringiendo su uso en Google Cloud Console.   


Estas medidas mejoran la **confidencialidad, integridad y disponibilidad** de la aplicación.

---

## Documentación Técnica

Toda la documentación referente al proceso de mejora y pruebas de seguridad se encuentra disponible en los siguientes archivos:

- [Vulnerabilidades](vulnerabilities.md)  
- [Best Practices](best_practices.md)  
- [Security Tips](security_tips.md)  
- [Security Improvement Program](security_improvement_program.md)

---

## Cómo Ejecutar la Aplicación de Forma Segura

**Clonar el repositorio:**
   ```bash
   git clone https://github.com/andre19fs/EvGPSMapApp.git
