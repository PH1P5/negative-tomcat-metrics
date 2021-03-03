# This is a sample project to reproduce negative graphite metrics
Negative values (peaks) appear when we deploy (restart) our application via CI pipeline. This seems to start happening with version update spring boot 2.2 -> 2.3.

Since I started to create this sample and tried to reproduce the negative peaks I could see no consistent behavior. Sometimes there are negative peaks on shutdown and sometimes not.

### How to reproduce
1. start graphite container + spring boot app
2. request e.g. http://localhost:8080/actuator/metrics/tomcat.global.request several times
3. goto http://localhost:19090/
4. clock path: Metrics -> namespace -> to -> my  -> micrometer -> tomcatGlobalRequest -> name -> http-nio-8080 -> m1_rate (see graph)
5. now shut down the application (eventually negative values already appears)
6. adjust application.properties e.g. remove or change 'server.max-http-header-size' and start the app again
7. repeat steps 2 to 5
8. have a look at the tomcatGlobalRequest m1_rate graph in graphite which sometimes appears negative
9. if there are no negative values repeat steps 5 to 8


### How to start graphite container locally

```

docker run --name test-graphite --restart=always -p 19090:80 -p 12003:2003 graphiteapp/graphite-statsd

```
