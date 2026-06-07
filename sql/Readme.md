# Instrucciones: instalar MySQL y ejecutar los scripts SQL

Este README explica cómo instalar MySQL en macOS y cómo ejecutar los scripts SQL disponibles en esta carpeta.

Ubicación de los scripts (esta carpeta):

- `crearDb.sql` — crea la base de datos y las tablas.
- `poblarTablas.sql` — inserta datos de prueba.
- `limpiarTablas.sql` — vacía (TRUNCATE) las tablas.
- `dropTables.sql` — elimina las tablas (BORRA LA ESTRUCTURA).

Requisitos
- macOS
- Homebrew instalado (opcional pero recomendado)

1) Instalar MySQL (con Homebrew)

```bash
brew update
brew install mysql
```

2) Iniciar el servidor MySQL

Con Homebrew services (arranca en background y persiste):

```bash
brew services start mysql
```

O iniciar manualmente (temporal):

```bash
mysql.server start
```

3) Verificar instalación

```bash
mysql --version
mysql -u root
```

Si la conexión no pide contraseña, estás usando el `root` local creado por Homebrew.

4) Asegurar la instalación (opcional pero recomendado)

```bash
mysql_secure_installation
```

Sigue las preguntas para asignar contraseña a `root`, eliminar usuarios anónimos y la DB de prueba.

5) Ejecutar los scripts SQL (comandos copiados)

Abre la terminal y ve a esta carpeta (ruta absoluta de ejemplo):

```bash
cd "/RamirezCarsService/sql"
```

Crear la base y las tablas:

```bash
mysql -u root -p < crearDb.sql
```

Poblar con datos de prueba:

```bash
mysql -u root -p < poblarTablas.sql
```

Vaciar las tablas (sin eliminar estructura):

```bash
mysql -u root -p < limpiarTablas.sql
```

Eliminar las tablas (BORRA la estructura):

```bash
mysql -u root -p < dropTables.sql
```

Alternativa — ejecutar desde el cliente MySQL con `SOURCE`:

```sql
mysql -u root -p
mysql> SOURCE crearDb.sql;
mysql> SOURCE poblarTablas.sql;
mysql> SOURCE limpiarTablas.sql;
mysql> SOURCE dropTables.sql;
```

6) Orden recomendado

- Primero: `crearDb.sql` (si ya existe la DB puedes omitirlo).
- Segundo: `poblarTablas.sql` para insertar datos de prueba.
- Para limpiar: `limpiarTablas.sql`.
- Para eliminar las tablas: `dropTables.sql`.

7) Precauciones

- `limpiarTablas.sql` y `dropTables.sql` son destructivos. Revisa su contenido antes de ejecutarlos.
- Si tu versión de MySQL no acepta `DATE DEFAULT (CURRENT_DATE)`, modifica esas líneas en `crearDb.sql` a `DATE DEFAULT CURRENT_DATE`.

8) Solución de problemas rápidos

- Si `mysql` no se encuentra: asegúrate de que Homebrew añadió `/usr/local/bin` o `/opt/homebrew/bin` al `PATH` y vuelve a abrir la terminal.
- Si tienes errores de permisos, revisa el estado del servicio con `brew services list`.
- Error de clave foránea: ejecuta los scripts en el orden recomendado y desactiva temporalmente `FOREIGN_KEY_CHECKS` dentro de tus scripts si es necesario.

9) Comprobar, iniciar y detener el servidor MySQL

Estas son formas rápidas de comprobar si MySQL está corriendo y de arrancarlo/pararlo según cómo lo instalaste.

- Con Homebrew `services` (recomendado si instalaste con Homebrew):

```bash
# Ver estado de los servicios y filtrar por mysql
brew services list | grep mysql

# Iniciar
brew services start mysql

# Parar
brew services stop mysql

# Reiniciar
brew services restart mysql
```

- Con el script local `mysql.server` (inicia/para el servidor para la sesión actual):

```bash
mysql.server start
mysql.server stop
mysql.server restart
```

- Verificar que el servidor responde con `mysqladmin`:

```bash
mysqladmin ping -u root -p
# respuesta esperada: "mysqld is alive"
```

- Verificar procesos (comando alternativo):

```bash
pgrep -fl mysqld || ps aux | grep mysqld
```

- Ver estado del servicio (lista completa):

```bash
brew services list
```

Notas:

- No uses `sudo` con `brew services start/stop` normalmente.
- Si tu MySQL está configurado con otro usuario o puerto, añade `-P puerto` o `-h host` a los comandos cuando corresponda.
