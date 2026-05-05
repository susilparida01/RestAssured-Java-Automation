# Use an official Maven image with JDK 21 as the base
FROM maven:3.9.6-eclipse-temurin-21

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the application code
COPY . .

# Default command (can be overridden by Jenkins)
CMD ["mvn", "test"]
