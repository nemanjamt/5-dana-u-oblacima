Ručno pokretanje projekta:
- pozicionirati se unutar projekta
- izvršiti komandu ***mvn package***
- u okviru target direktorijuma će biti generisan jar fajl, npr ***pet-dana-u-oblacima-0.0.1-SNAPSHOT.jar***
- izvršiti komandu ***java -Xmx2048M -jar target/pet-dana-u-oblacima-0.0.1-SNAPSHOT.jar.original***

Napomena: potrebno je da se koristi vezija jave 19 i da maven bude instaliran


Pokretanje projekta kroz docker:
- pozicionirati se u korjenski direktorijum repozitorijuma
- izvršiti komandu ***docker build -t match_making -f ./.deploy/Dockerfile .***
- izvršiti komandu ***docker run -d -p 8080:8080 match_making***

Korištene tehnologije:
*Java 19*
*Sprint boot 3.3.5*
*H2 in-memory baza podataka*

