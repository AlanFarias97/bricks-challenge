# Bricks-Challenge
Challenge técnico proceso de seleccion para puesto Java dev SSR, el proyecto es una API que administra productos de un comercio

# Requisitos Previos
Tener instalado Java 17 y Postman

# Instalación
Descargar el repositorio desde
```bash
  $ git clone https://github.com/AlanFarias97/bricks-challenge.git
```
Luego importar la coleccion de postman ubicada en la carpeta dev

# Uso
1. El primer paso consiste en cargar la tabla Category ejecutando el JOB con el servicio llamado "Job-Execute" dentro del folder "backoffice" en postman

2. Luego crear 1 producto con el servicio "CREATE" dentro del folder "PRODUCT", el mismo debe contener { "name": "nuevo producto ", "price":150.50, "stock":25, "categoryId":7, "active":true } categoryId lo pueden conseguir con el servicio "LIST" dentro del folder "CATEGORY"
3. Finalmente pueden probar los demas servicios sin problemas 🙂
