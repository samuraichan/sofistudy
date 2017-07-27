# dwstudy

How to start the dwstudy application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dwstudy-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

OR
mvn package -Dmaven.test.skip=true && java -jar target/dwstudy-1.0-SNAPSHOT.jar server config.yml

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
