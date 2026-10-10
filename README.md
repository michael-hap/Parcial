# QuindíoExpress — Gestión de operaciones de un centro de distribución

### Integrantes: 
- Andres Felipe Garcia Castaño
- Michael Hernan Acevedo Porras

## 1. Descripción del proyecto

**QuindíoExpress** es una aplicación de consola desarrollada en Java para representar las operaciones básicas de un centro de distribución. Permite registrar paquetes y repartidores, consultar paquetes por código, agruparlos por municipio, organizar su atención por orden de llegada o prioridad, asignar paquetes a repartidores compatibles con su zona y registrar las entregas realizadas.

El proyecto también implementa una lista simplemente enlazada propia y varios algoritmos de búsqueda, recursión y divide y vencerás.

## 2. Objetivos

- Gestionar el registro y la consulta de paquetes.
- Controlar los repartidores registrados y su disponibilidad.
- Mantener los paquetes organizados por orden de llegada y por prioridad.
- Agrupar los paquetes según su municipio de destino.
- Conservar un historial de entregas.
- Implementar estructuras de datos y algoritmos como parte del ejercicio académico.

## 3. Tecnologías y requisitos

- **Lenguaje:** Java.
- **Versión configurada del proyecto:** Java JDK 21.
- **Entorno recomendado:** IntelliJ IDEA.
- **Gestor de construcción:** Maven, según la configuración del proyecto (`pom.xml`).

Antes de ejecutar el proyecto, verifica que IntelliJ IDEA esté usando un JDK compatible con la configuración de `pom.xml`.

## 4. Estructura del proyecto

Las clases se organizan en los siguientes paquetes:

```text
src/main/java/uniquindio/edu/co/
├── Main.java
├── comparadores/
│   ├── ComparadorPeso.java
│   ├── ComparadorPrioridad.java
│   ├── ComparadorTiempo.java
│   └── PruebaComparadores.java
├── estructuras/
│   ├── Coleccion.java
│   ├── ListaSimplementeEnlazada.java
│   ├── Nodo.java
│   └── PruebaLista.java
├── modelo/
│   ├── Paquete.java
│   ├── Repartidor.java
│   └── PruebaModelos.java
└── servicio/
    ├── AlgoritmosPaquetes.java
    ├── PruebaAlgoritmosPaquetes.java
    ├── PruebaIntegracionCompleta.java
    ├── PruebaSistemaQuindioExpress.java
    └── SistemaQuindioExpress.java
```

## 5. Modelo del sistema

### Paquete

Cada paquete contiene código identificador, municipio de destino, peso, prioridad y tiempo estimado.

La clase valida que el código y el destino no sean nulos ni vacíos, que el peso sea positivo y finito, que la prioridad esté entre 1 y 5 y que el tiempo estimado sea positivo. Además, implementa `Comparable<Paquete>` para establecer el orden natural por código ascendente.

### Repartidor

Cada repartidor contiene identificación, nombre, zona de atención y estado de disponibilidad.

La clase valida la identificación, el nombre y la zona. La disponibilidad cambia según la asignación y finalización de una entrega.

## 6. Estructuras de datos

| Estructura | Uso en el sistema |
|---|---|
| `ArrayList<Paquete>` | Mantener los paquetes pendientes de entrega. |
| `HashMap<String, Paquete>` | Consultar paquetes por código. |
| `HashMap<String, Repartidor>` | Consultar repartidores por identificación. |
| `HashSet<String>` | Registrar municipios sin duplicados. |
| `TreeSet<String>` | Mantener municipios ordenados alfabéticamente. |
| `HashMap<String, List<Paquete>>` | Agrupar paquetes por municipio de destino. |
| `ArrayDeque<Paquete>` | Representar la cola de llegada. |
| `PriorityQueue<Paquete>` | Organizar paquetes por prioridad, tiempo estimado y código. |
| `ListaSimplementeEnlazada<Paquete>` | Guardar el historial de entregas con una estructura propia. |

### Lista simplemente enlazada propia

La estructura se implementa con `Nodo`, `Coleccion` y `ListaSimplementeEnlazada`. Incluye operaciones para agregar, eliminar, obtener por posición, consultar tamaño, comprobar si está vacía e iterar sobre los elementos.

La lista utiliza referencias entre nodos para almacenar sus elementos; no usa `ArrayList` ni `LinkedList` como almacenamiento interno.

## 7. Reglas de ordenamiento

- **`ComparadorPrioridad`:** prioridad descendente; en caso de empate, tiempo estimado ascendente y después código ascendente.
- **`ComparadorPeso`:** peso descendente.
- **`ComparadorTiempo`:** tiempo estimado ascendente.
- **`Paquete.compareTo()`:** código ascendente.

## 8. Algoritmos implementados

Los métodos están en `AlgoritmosPaquetes.java`.

### Peso total por municipio — recursión

`calcularPesoTotalPorMunicipio` suma recursivamente los pesos de los paquetes cuyo destino coincide con el municipio indicado.

- Complejidad temporal: `O(n)`.
- Complejidad espacial: `O(n)` por las llamadas recursivas en el peor caso.

### Conteo por prioridad mínima — recursión

`contarPaquetesPorPrioridad` cuenta los paquetes cuya prioridad es mayor o igual al valor mínimo recibido.

- Complejidad temporal: `O(n)`.
- Complejidad espacial: `O(n)` por la recursión.

### Búsqueda binaria por código

`busquedaBinaria` busca un paquete por código y devuelve su índice o `-1` si no lo encuentra.

- **Precondición:** la lista debe estar ordenada ascendentemente por código.
- Complejidad temporal: `O(log n)`.
- Complejidad espacial: `O(1)`, porque utiliza variables auxiliares y no es recursiva.

### Mayor peso — divide y vencerás

`encontrarPaqueteMayorPeso` utiliza `encontrarMayorPesoRecursivo`. Este divide el rango de la lista en dos mitades, encuentra el paquete más pesado de cada mitad y compara los resultados.

- **Dividir:** separar el rango en dos mitades.
- **Vencer:** resolver recursivamente cada mitad.
- **Combinar:** comparar los dos paquetes resultantes.
- Complejidad temporal: `O(n)`.
- Complejidad espacial: `O(log n)` por la profundidad de la recursión.

## 9. Operaciones del sistema

La clase `SistemaQuindioExpress` concentra operaciones como:

- Registrar paquetes y rechazar códigos duplicados.
- Consultar paquetes por código.
- Consultar municipios y paquetes agrupados por destino.
- Registrar repartidores y rechazar identificaciones duplicadas.
- Comprobar disponibilidad y compatibilidad de zona.
- Despachar paquetes por orden de llegada o por prioridad.
- Marcar al repartidor como ocupado durante la asignación.
- Registrar entregas, actualizar paquetes pendientes y liberar al repartidor.
- Consultar el historial de entregas.

## 10. Datos de demostración

El programa principal (`Main.java`) utiliza:

- 30 paquetes.
- Códigos de paquete no consecutivos.
- Cinco municipios de destino.
- Seis repartidores iniciales.

La demostración incluye registro y consulta de paquetes, rechazo de duplicados, visualización de colas, búsqueda de un repartidor compatible, despacho, registro de una entrega y consulta del historial.

## 11. Cómo ejecutar el proyecto

### Desde IntelliJ IDEA

1. Abrir la carpeta del proyecto en IntelliJ IDEA.
2. Esperar a que Maven termine de importar las dependencias, si corresponde.
3. Verificar que el proyecto utilice JDK 21.
4. Abrir `src/main/java/uniquindio/edu/co/Main.java`.
5. Ejecutar el método `main`.
6. Revisar la salida de la consola.

### Ejecutar las pruebas manuales

Las clases de prueba incluidas se pueden ejecutar de forma independiente desde IntelliJ IDEA:

- `PruebaComparadores.java`
- `PruebaLista.java`
- `PruebaModelos.java`
- `PruebaAlgoritmosPaquetes.java`
- `PruebaSistemaQuindioExpress.java`
- `PruebaIntegracionCompleta.java`

Estas clases comprueban distintos componentes del sistema. La ejecución satisfactoria de sus casos no sustituye la revisión de todos los requisitos de la guía.

## 12. Pruebas realizadas

Durante la revisión se ejecutaron las pruebas de comparadores, lista, algoritmos, sistema e integración sin errores en los casos ejecutados. También se ejecutó `Main.java` y se observaron las operaciones principales.

## 13. Integrantes y contribuciones

Completar esta sección con los datos reales del equipo:

| Integrante | Contribución |
|---|---|
| [Nombre del integrante 1] | [Describir las tareas realizadas] |
| [Nombre del integrante 2] | [Describir las tareas realizadas] |
| [Nombre del integrante 3, si aplica] | [Describir las tareas realizadas] |

## 14. Observaciones

- La búsqueda binaria requiere una lista ordenada ascendentemente por código.
- El cálculo recursivo del peso compara destinos distinguiendo mayúsculas y minúsculas.
- Conviene confirmar el comportamiento esperado cuando el paquete de mayor prioridad no corresponde a la zona del repartidor.
- Antes de entregar, comparar esta documentación con la guía completa y confirmar que todos los requisitos solicitados estén cubiertos.

---

**Proyecto académico:** QuindíoExpress — Gestión de operaciones de un centro de distribución.
