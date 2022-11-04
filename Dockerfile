FROM openjdk:17-ea-11-jdk-slim
EXPOSE 9004
ENV TZ Asia/Seoul
COPY build/libs/cosmostpopularity-1.0.jar ./Popularity.jar
ENTRYPOINT ["java", "-jar", "Popularity.jar"]