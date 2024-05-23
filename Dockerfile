FROM openjdk:17
ADD target/medilabo_note-0.0.1-SNAPSHOT.jar medilabo_note.jar
ENTRYPOINT ["java","-jar","/medilabo_note.jar"]
EXPOSE 8082