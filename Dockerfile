# Maven + JDK 21 base image
FROM maven:3.9.6-eclipse-temurin-21

# Working directory inside container
WORKDIR /app

# Copy project files
COPY pom.xml .

# Download dependencies (cached layer)
RUN mvn dependency:go-offline -B

# Copy full project
COPY . .

# Default command (used if no override)
CMD ["mvn", "test"]