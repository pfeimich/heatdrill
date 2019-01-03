# heatdrill
REST service wrapper um die Datenbank-Funktionalität "EWS Nadelstich"

Inhalt der Dokumentation:
1. API-Dokumentation (Benutzerdokumentation für den REST-Dienst)
2. Betriebs-Dokumentation
3. Entwickler-Dokumentation

## API-Dokumentation
Der Nadelstich wird über [Basis-URL]/service mit den Parametern x und y aufgerufen.
x und y sind die ganzzahligen Punktkoordinaten im Koordinatensystem EPSG:2056.

### Aufruf
Beispiel:
```http
http://localhost/service?x=2600000&y=1200000  
```

### Antwort bei Erfolg
Bei erfolgreicher Durchführung wir mit HTTP Status-Code 200 die wie folgt strukturierte JSON-Antwort zurückgegeben:
```json
{
	"requestId": 99,
	"permitted": false,
	"depth": null,
	"gwsZone": true,
	"gwPresent": null,
	"spring": true,
	"gwRoom": true,
	"wasteSite": false,
	"infoTextRows": ["Erste Zeile", "Zweite Zeile", "", "Dritte Zeile nach Leerzeile"]
}
```

Erläuterung der Rückgabewerte im JSON: 
* requestId: Ganzzahl, nicht null. ID des Requests. Kann zwecks Nachvollzug von inhaltlichen Fehlern der Abfrage verwendet werden.
* permitted: Boolean, nicht null. Gibt an, ob an der abgefragten Stelle eine Bohrung möglich ist.
* depth: Ganzzahl, optional. Gibt, wenn permitted=true, die maximal zulässige Bohrtiefe in Metern an.
* gwsZone, gwPresent, spring, gwRoom, wasteSite: Boolean, optional. Geben an, welche für den Bohrentscheid wichtigen Untergrundeigenschaften an der Bohrstelle zutreffen.
* infoTextRows: Array von Strings, optional. Erläuternder Text zum Bohrentscheid. Jede Zelle des Arrays entspricht einer Zeile des erläuternden Textes.

### Antwort bei Fehler
Bei einem Fehler wird ein nicht 200 HTTP-Status-Code zurückgegeben. HTTP Body: Generische HTML-Seite mit der Fehlermeldung

## Betriebs-Dokumentation

Bei jedem Git Push wird mittels Travis das Docker-Image neu gebildet und als sogis/heatdrill mit den tags [Travis Buildnummer] und [latest] auf dem Docker hub abgelegt.

Konfiguriert werden müssen:
* HTTP URL und Port, unter welchem der Service erreichbar sein wird
* Datenbank-Verbindungsparameter (siehe folgend)

Das Image braucht im Bertieb kein persistent Volume. 

### Datenbank-Verbindungsparameter
Dei Verbindungs-Parameter werden über die Umgebungsvariable SPRING_APPLICATION_JSON definiert.

Beispiel für das JSON:
```json
{
	"db-connection": {
		"url": "jdbc:postgresql://localhost:5432/dev",
		"userName": "ddluser",
		"password": "ddluser"
	}
}
```

Variablen:
* url: JDBC-Connection URL für die "Nadelstich-Datenbank"
* userName: Zu verwendender Datenbank-Benutzername
* password: Passwort für den Datenbankzugriff

## Entwickler-Dokumentation

Die Spring-Boot-Applikation:
1. Wandelt die HTTP-Aufruf-Parameter um in eine Datenbank-Abfrage.
2. Ruft die Datenbankfunktion entscheidung.entscheidung_detail(..) auf. 
3. Wandelt den Rückgabewert in JSON um, und schickt diesen als HTTP-Antwort zurück.

[zu 2.] Die Datenbankfunktion sowie die gesamte darunterliegende Funktionalität stammt von einem Softwarelieferanten und wird vom Amt für Umwelt gewartet, sprich ist nicht Teil dieses Repositories.

### Ausführen des Programmes
Als Spring-Boot Applikation starten. Nicht vergessen, die Umgebungsvariable zu setzen (Datenbank-Verbindungsparameter, siehe Betriebsdokumentatkion).

### Organisation des Git-Repository
* src/main/java: Heimat des Java-Sourcecode 
* docker/heatdrill: Dateien zum Erstellen und lokalen Ausführen des Docker-Image der Applikation
* docker/mock_db: Dateien zum Erstellen und lokalen Ausführen des Docker-Image einer "Mock-DB" zum lokalen Entwickeln.





