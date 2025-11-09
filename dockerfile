FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Ajustes Java opcionales (memoria, GC, etc.)
ENV JAVA_OPTS=""

# El perfil te lo paso por env en compose si lo usas
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
