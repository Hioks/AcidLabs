## AcidLabs

Repositorio creado para la prueba técnica de AcidLabs, la cual consiste en: Implementar un webservice REST que exponga una URL para cargar datos de usuarios. Además de un cliente web que consuma dicho webservice y obtenga las respuestas esperadas de acuerdo a cada caso.

## Solución

Se generó una solución en Spring Framework 3, junto a un servidor Apache CXF como servidor rest, el mapeo de datos se hizo con la librería jackson.

## Installación

Se uso eclipse como ide, sólamente se debe descargar el proyecto e instalarlo ya que se subieron todas las librerías necesarias. Se usó Apache Tomcat 7 como servidor de aplicaciones bajo eclipse.

## Tests

La solución consta de un formulario, en el cual se ingresa un usuario y se selecciona una imagen, en el caso de que el usuario sea "usuario1", se devolverá la imagen en Base 64, en el caso contrario se mostrará una pantalla de error 401, con el mensaje desde el servicio rest.
