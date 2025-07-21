FROM eclipse-temurin:17.0.13_11-jre-noble

WORKDIR /service

COPY target/*.jar ./app.jar

ARG PROFILE=prod

ENV SPRING_PROFILES_ACTIVE=$PROFILE
ENV PAGAMENTO_MOCK_HOST=pagamento-mock
ENV PEDIDO_SERVICE_HOST=app
ENV DB_NAME=lanch_pag_db
#ENV DB_USER=lanch_pag_user
#ENV DB_PASSWORD=lanch_pag@1234
ENV DB_HOST=mongodbpag
ENV DB_PORT=27017

CMD ["java", "-jar", "./app.jar"]
