FROM openjdk:8
EXPOSE 8080
ADD target/qr-ph-1.0.jar qr-ph-1.0.jar
ENTRYPOINT ["java", "-jar", "qr-ph-1.0.jar"]
