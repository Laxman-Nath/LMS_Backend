# 1️⃣ Base image: download official OpenJDK 21 image with Alpine Linux (lightweight)
FROM eclipse-temurin:21-jdk-alpine

# 2️⃣ Set working directory inside the container
WORKDIR /app

# 3️⃣ Copy the built JAR file from your local project to the container’s /app directory
COPY target/*.jar app.jar

# 4️⃣ Expose port 8080 so Docker (or Railway) knows which port your app will run on
EXPOSE 8080

# 5️⃣ Set the command to run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
