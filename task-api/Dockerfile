FROM eclipse-temurin:17-jdk-alpine as build

WORKDIR /build

COPY .mvn .mvn/
COPY pom.xml mvnw mvnw.cmd ./
RUN --mount=type=cache,target=/root/.m2 \
  ./mvnw dependency:go-offline

COPY src src/
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests=true



FROM eclipse-temurin:17-jre-alpine
WORKDIR /application

COPY --from=build /build/target/*.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]