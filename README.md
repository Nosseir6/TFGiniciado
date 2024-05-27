
# Proyecto: Mazmorra Java

## Descripción
Este proyecto es un videojuego de mazmorra desarrollado en Java. Este es un proyecto de un videojuego de mazmorra en el cual el objetivo era simular algo a los roguelike, pero me di cuenta que tener diversos niveles asi como mapas iba a resultar imposible de hacer a tiempo.


## Historia del Proyecto
Empece investigando como funcionaban los motores y que tenia que tener en cuenta a la hora de crear un juego, tambien vi varios videos de posibles juegos de los que guiarme, ya que no habia mucha informacion para poder crear juegos en java, en especial algunos que incluyeran graficos.
Los problemas llegaron a la hora de programar y ahi me di cuenta de porque la gente no hace juegos en Java. Es extremadamente tedioso asi como multiples problemas que encontre como por ejemplo que el formato mp3 no esta suporteado y es necesario usar un formato WAV,
el cual pesa una barbarida.
Ademas las rutas de los archivos son un poco aleatorias a veces funcionan escritas de una forma y otras veces no, por lo tanto hay que ir probando y todo a de ser Strings, por lo tanto si te equivocas y no te das cuenta de que es una falta ortografica a la hora 
de escribir la ruta vas a tardar un rato en darte cuenta.
Luego llego el problema de que dibujos usar, mi primera idea fue dibujarlos yo mismo, gran error ya que perdi mucho tiempo en cosas que al final no he usado ni acabado. A partir de ahi toco investigar paginas para encontrar dibujos que se adaptasen y su estetica me gustase.
Una vez encontrados tuvo problemas con el renderizado ya que hasta que encontre que fallaba en la formula la imagen iba a parpadeando o a veces saltaba de una animacion a otra.
Cuando lo arregle pase al problema de las colisiones y el renderizado de mapa:
Con las colisiones la matematica detras de ella es bastante simple pero hasta que la logre me costo un tiempo y tomar bastantes apuntes para comprenderla, una vez comprendida la logica basica el resto de las colisiones son bastante repetitivas(hasta que llego la hora de las
colisiones entre entidades, pero eso va a ser mas adelante). A la hora de renderizar en un principio dibujaba todo el mapa de una vez pero vi que se podia renderizar solo el mundo alrededor del jugador y a medida que este se mueve estos se carguen.
Tambien aprendi que la colision entre eobjetos y entre casillas no se debe tratar igual.
Una vez terminado todo el tema del mapa y de colision sencillas pase a la parte de entidades.
Las entidades me dieron problemas ya que al principio (y por algun motivo que no se muy bien cual anque tengo alguna sospecha) intente dibujar un slime sencillo el cual solo tuviese dos sprites de animacion, pero no funcionaba, por lo cual tuve que buscar algun dibujo
con el mismo numero de sprites que el del personaje principal, para que este funcionara con el mismo metodo, tambien aprendi a crear una IA para los enemigos(consiste en generar valores aleatoriamente y en funcion de cual se genere cambiar la direccion durante x updates).
## Características principales
- **Sistema de Colisiones:** Implementé un sistema de colisiones para evitar obstáculos y detectar interacciones entre el jugador, los enemigos y los objetos del juego.
- **Variedad de Objetos:** El juego cuenta con una variedad de enemigos y objetos, incluidas llaves para abrir puertas y cofres, así como trampas y obstáculos que desafían al jugador.
- **Interfaz de Usuario Intuitiva:** La interfaz de usuario proporciona información relevante al jugador, como el número de llaves recolectadas y el estado de la salud del personaje.
- **Renderizado de Mapa Progresivo:** Ha medida que el jugador avanza el mapa se va dibujando 


## Problemas Encontrados
- Uno de los principales desafíos fue la gestión de archivos de audio en Java. Aprendí sobre los formatos de audio compatibles y las limitaciones del API de sonido de Java.
- Tuve que investigar y solucionar problemas relacionados con el rendimiento del juego, como el parpadeo de sprites y la optimización del renderizado del mapa.
- Uno de los problemas fue a la hora de desarollar el metodo run(), ya que no consegui que la variable delta que es la que hace que el juego se mueva a una velocidad constante independientemente de los fps funcionara, por lo cual el juego tiene un problema y es que si
  aumentan los fps el juego ira mas rapido, lo cual no deberia ser asi.
- Otro de los grandes fallos es que cometi la mala praxis de no pasar parametros mediante getters y setters, ya que vi que era demasiado tedioso y que supondria un codigo bastante mas largo y complejo de lo que ya es de por si. 


## Uso
- Usa las teclas WASD para moverte por la mazmorra.
- Esquiva a los enemigos para no perder vida y evitar que te maten antes de llegar al altar
  
