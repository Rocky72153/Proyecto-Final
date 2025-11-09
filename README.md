# ProcureIT Backend (Spring Boot)

Starter del backend para el proyecto **ProcureIT**.

## Requisitos
- Java 17+
- Maven 3.9+
- MySQL 8+ (BD `PROCUREIT` creada con el script proporcionado)

## Configurar
1. Ajusta credenciales en `src/main/resources/application.properties`.
2. Ejecuta el script SQL de la BD (no alteres el esquema).

## Ejecutar
```bash
mvn spring-boot:run
```
Swagger UI en: `http://localhost:8080/swagger-ui.html`

## Paquetes
- `entity`: mapeo JPA fiel al esquema SQL.
- `repository`: repos `JpaRepository`.
- `service`: interfaces + implementaciones con reglas iniciales.
- `controller`: endpoints REST con paginación (`size`: 5 por defecto).
- `util.IdGenerator`: genera `solicitud_id` y `codigo_seguimiento` conforme al enunciado.
- `config`: Swagger y Security (toggle `app.security.disabled=true` en dev).
- `templates/notificacion.html`: plantilla de correo HTML.

## Próximos pasos
- Integrar seguridad real (JWT o sesión) + roles por usuario.
- Implementar escalamiento de 48h y bitácora completa.
- Especificaciones para `/reportes` (KPI y filtros).
- Validaciones (@Valid) y excepciones personalizadas por caso de uso.
- Subida de adjuntos (S3 o filesystem) y hash SHA-256.


## SMTP real
Para habilitar correos reales edita `application.properties`:
```
spring.mail.host=smtp.tu-proveedor.com
spring.mail.port=587
spring.mail.username=TU_USUARIO
spring.mail.password=TU_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
app.security.disabled=false
```
Prueba: `POST /api/notificaciones/test?to=tu@correo.com`
