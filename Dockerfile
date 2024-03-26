# Use the eclipse-temurin:17-jdk-alpine image as the base image and name it 'build'
FROM eclipse-temurin:17-jdk-alpine as build

# Set the working directory inside the container to '/workspace/app'
WORKDIR /workspace/app

# Copy the 'mvnw' file from the host to the current working directory in the container
COPY mvnw .

# Run the 'sed' command to remove any Windows-style line endings from the 'mvnw' file
RUN sed -i 's/\r$//' mvnw

# Copy the '.mvn' directory from the host to the current working directory in the container
COPY .mvn .mvn

# Copy the 'pom.xml' file from the host to the current working directory in the container
COPY pom.xml .

# Copy the 'src' directory from the host to the current working directory in the container
COPY src src

# Run the './mvnw install -DskipTests' command to build and install the project dependencies, skipping tests
RUN ./mvnw install -DskipTests

# Create a directory 'target/dependency' and extract the JAR file's dependencies into it
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Use the eclipse-temurin:17-jdk-alpine image as the base image for the final stage
FROM eclipse-temurin:17-jdk-alpine

# Create a volume '/tmp' that can be used for temporary file storage
VOLUME /tmp

# Set the 'DEPENDENCY' build argument to '/workspace/app/target/dependency'
ARG DEPENDENCY=/workspace/app/target/dependency

# Copy the libraries from the 'DEPENDENCY/BOOT-INF/lib' directory to '/app/lib' in the container
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib

# Copy the META-INF directory from the 'DEPENDENCY' directory to '/app/META-INF' in the container
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF

# Copy the application classes from the 'DEPENDENCY/BOOT-INF/classes' directory to '/app' in the container
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Set the entrypoint for the container to run the Java application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.youcode.servicema.ServicemaApplication"]