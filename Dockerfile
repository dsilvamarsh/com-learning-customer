# Use the official Eclipse Temurin JRE 21 image as the base
FROM eclipse-temurin:21-jre

# These lines are commented out, but show how to create a dedicated user/group for security
# If uncommented, the application runs with reduced privileges
#RUN addgroup -S spring 
#RUN adduser -S spring -G spring
#USER spring:spring

# Define an argument for the JAR file path, defaulting to target/*.jar
ARG JAR_FILE=target/*.jar

# Copy the host machine's application JAR into the container as app.jar
COPY ${JAR_FILE} app.jar

# Define the command to run when the container starts
ENTRYPOINT ["java","-jar","/app.jar"]
