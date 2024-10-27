# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim
LABEL authors="kunalladhani"

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build output (JAR file) to the container
# Assuming the JAR file is named 'BookMyFlight.jar'
COPY target/BookMyFlight-1.0.0.jar /app/BookMyFlight.jar

# Expose the port on which the application will run
EXPOSE 3001

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "BookMyFlight.jar"]

# Optional: Add a health check for the application
HEALTHCHECK --interval=30s --timeout=10s CMD curl -f http://localhost:3001/actuator/health || exit 1
