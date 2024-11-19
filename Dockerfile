# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim
LABEL authors="kunalladhani"

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build output (JAR file) to the container
COPY target/book-my-flight-latest.jar /app/book-my-flight-latest.jar

# Expose the port on which the application will run
EXPOSE 3001

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "book-my-flight-latest.jar"]

# Optional: Add a health check for the application
# HEALTHCHECK --interval=30s --timeout=10s CMD curl -f http://localhost:3001/actuator/health || exit 1
