# Use Maven with JDK 21
FROM maven:3.9.6-eclipse-temurin-21

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy project
COPY . .

# Default command
CMD ["mvn", "test"]