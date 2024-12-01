Projekat za kvalifikacije za hakaton [5 dana u oblacima](https://5danauoblacima.com/ "5 dana u oblacima")

Ručno pokretanje projekta:
- pozicionirati se unutar projekta 5-dana-u-oblacima
- izvršiti komandu ***mvn clean package***
- u okviru target direktorijuma će biti generisan jar fajl, npr ***pet-dana-u-oblacima-0.0.1-SNAPSHOT.jar***
- izvršiti komandu ***java -Xmx2048M -jar target/pet-dana-u-oblacima-0.0.1-SNAPSHOT.jar***
- api je dostupan na portu 8080

Napomena: potrebno je da se koristi vezija jave 19 i da maven bude instaliran


Pokretanje projekta kroz docker:
- pozicionirati se u korjenski direktorijum repozitorijuma
- izvršiti komandu ***docker build -t match_making -f ./.deploy/Dockerfile .***
- izvršiti komandu ***docker run -d -p 8080:8080 match_making***
- api je dostupan na portu 8080
  
Napomena: ukoliko na lokalnoj mašini želite da zauzmete neki drugi port, na primjer 9091, docker run komanda bi bila<br>
  ***docker run -d -p 9091:8080 match_making***

<br>

Korištene tehnologije:
- *Java 19*
- *Sprint Boot 3.3.5*
- *H2 in-memory baza podataka*

