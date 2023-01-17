# IngegneriaSoftware_22-23
![alt text](https://comunicatidelcredito.it/wp-content/uploads/2019/09/BANCA-D-ITALIA.png)

Scopo del progetto è quello di implementare uno sportello ATM [ATM](https://www.repstatic.it/content/nazionale/img/2021/10/22/144253807-30f920af-3df7-439e-95f7-9e55d5f1a461.jpg) attraverso un programma JAVA (orientato agli oggetti). Il risultato finale permette di interagirci grazie a un form grafico (GUI).
Il programma utilizza un DataBase mysql per la gestione degli utenti.

## Documentazione
La seguente documentazione comprende i documenti realizzati per la progettazione del problema, verranno prima elencati i diagrammi delle classi in UML poi la documentazione del codice (javadoc).

### UML
Abbiamo realizzato i vari diagrammi grazie a STARUML.
- [CASI D'USO]()
- [ATTIVITÀ]()
- [CLASSI](Documentazione/Diagrammi/Diagramma classi.jpg)
- [PACKAGE](Documentazione/Diagrammi/Diagramma Package.jpg)
- [SEQUENZE](Documentazione/Diagrammi/Diagramma delle sequenze.jpg)

### JavaDoc
La seguente documentazione include una descrizione per la maggiore parte delle classi e dei metodi utilizzati, segue le tecniche di documentazione di Java e può essere consultata al seguente indirizzo: [Javadoc](https://github.com/giorgiopiazza/ing-sw-2019-27/tree/master/docs)

### Librerie e Plugins
|Libreria/Plugin|Descrizione|
|---------------|-----------|
|__maven__|strumento di gestione per software basati su Java e build automation|
|__junit__|framework dedicato a Java per unit testing|
|__jacoco__|strumento di supporto al testing per evidenziare le linne di codice coperte dagli unit test|
|__mockito__|strumento di supporto al unit testing per realizzare oggetti "dummy"|
|__gson__|libreria per il supporto al parsing di file in formato json|
|__JavaFx__|libreria grafica di Java|

### Jars
I seguenti jar sono stati utilizzati per la consegna del progetto, permettono quindi il lancio del gioco secondo le funzionalità descritte nell'introduzione. Le funzionalità realizzate secondo la specifica del progetto sono elencate nella prossima sezione mentre i dettagli per come lanciare il sistema saranno definiti nella sezione chiamata __Esecuzione dei jar__. La cartella in cui si trovano il software del client e del server si trova al seguente indirizzo: [Jars](https://github.com/giorgiopiazza/ing-sw-2019-27/tree/master/Deliveries/jar).

## Funzionalità
### Funzionalità Sviluppate
- Regole Complete
- CLI
- GUI
- Socket
- RMI

### Funzionalità Aggiuntive Sviluppate
- Terminator
- Persistenza

## Esecuzione dei JAR
### Client
Il client viene eseguito scegliendo l'interfaccia con cui giocare, le possibili scelte sono da linea di comando o interfaccia grafica. Le seguenti sezioni descrivono come eseguire il client in un modo o nell'altro.
#### CLI
Per una migliore esperienza di gioco da linea di comando è necessario lanciare il client con un terminale che supporti la codifica UTF-8 e gli ANSI escape.
Per lanciare il client in modalità CLI digitare il seguente comando:
```
java -jar client.jar cli
```
#### GUI
Per poter lanciare il client con l'interfaccia grafica è necessario importare le dipendenze di JavaFx. Per poter lanciare la modalità GUI è quindi necessario: scaricare l'SDK relativo al proprio sistema operativo da https://gluonhq.com/products/javafx/ e posizionare la relativa cartella estratta nella stessa posizione del client.jar.

A questo punto si deve digitare il seguente comando che importa le dipendenze e lancia il client:
```
java --module-path javafx-sdk-11.0.2/lib --add-modules javafx.controls --add-modules javafx.fxml -jar client.jar
```

### Server
Per eseguire il server è solamente necessario configurare alcune delle sue caratteristiche attraverso un file di configurazione in formato json.
Un esempio di questo file, di cui poi verranno spiegati i campi, è il seguente:
```
{
  "start_time": 10,
  "move_time": 180,
  "socket_port": 2727,
  "rmi_port": 7272
}
```
#### Options
- `start_time`: tempo di attesa prima che la partita inizi una volta aver raggiunto il numero minimo di 3 giocatori;
- `move_time`: tempo di cui dispone ciascun giocatore per eseguire un'azione, se viene superato il giocatore è espulso dalla partita;
- `socket_port`: porta del server che usa le socket;
- `rmi_port`: porta del server che usa il servizio RMI.

L'esecuzione del server avviene quindi attraverso il seguente comando, di cui verranno poi definiti i parametri:
```
java -jar server.jar [-l configFilePath] [-b true/false] [-s numSkulls] [-r]
```
#### Parameters
- `-l configFilePath`: permette di specificare il percorso del file di configurazione. Se non specificato il valore di default è __conf.json__;
- `-b true/false`: permette di aggiungere il terminator alla partita. Se non specificato il valore di default è false;
- `-s numSkulls`: permette di specificare con quanti teschi giocare la partita. Se non specificato il valore di default è 5;
- `-r`: permette di caricare una partita precedentemente salvata il cui file di salvataggio dovrà essere posizionato nella stessa posizione del server.jar.

## Componenti del gruppo
- [__Giorgio Piazza__](https://github.com/giorgiopiazza)
- [__Francesco Piro__](https://github.com/Megapiro)
- [__Lorenzo Tosetti__](https://github.com/tosettil-polimi)
