# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Maven configuration and source files
COPY pom.xml .
COPY src ./src

# Copy the .env file to the working directory in the Docker container
COPY .env /app/.env

# Install Maven
RUN apt-get update && apt-get install -y maven

# Download dependencies
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Build the application, skipping tests
RUN mvn clean package -DskipTests

# Final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/pricing-manager-0.0.1-SNAPSHOT.jar /app/pricing-manager.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/app/pricing-manager.jar", "--spring.profiles.active=${PM_PROFILE:dev}"]
