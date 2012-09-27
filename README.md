# Spring Rest SBT and Maven example

This GIST demonstrates the use of both Maven and SBT building of a basic Spring MVC rest service that can run on Tomcat and (with xsbt-web-plugin) on Jetty. You must have either Maven or SBT installed. Note that this is derived from an awesome example by [Yong Mook Kim](http://www.mkyong.com/).

In order to run this GIST, you need the following packages installed.

1. [Tomcat V7](http://tomcat.apache.org/). Make a note of where the "webapp" directory is as you will have to copy the resulting war file to this directory in order to have it visible in Tomcat. 
2. [Maven >2.0](http://maven.apache.org) 
3. [Simple Build Tool](https://github.com/harrah/xsbt)
4. Java 1.6 build environment (J2SE)

Follow the instructions for your operating system to install the packages and make sure that they are on your path. Note that I have only tested this on OSX, so if there are tricks on the other OS's, please make a note here. Note that all sorts of dependencies will get downloaded automatically.

Clone the project as follows:

```
$ git clone https://github.com/IMQS/spring-rest-example.git
$ cd spring-rest-example
```

## Maven builds
This is easy:
```
$ mvn package
```
This will produce a file `target/SpringMovies.war`. Copy this to your tomcat webapp directory for deployment. Note that I have not worked out how to deploy this to Jetty. Test it by issuing the following command:

```
SpringMVC$ curl localhost:8080/spring-rest-example-0.1/rest/movieDB/list
[{"name":"Alien","rating":"pg16","director":"Ridley Scott"}]
```

## SBT builds
Simple Build Tool is the Scala build system which can build Java projects too. It uses convention over configuration and results in terse project definitions:

```
$ sbt package
```

At this point, you can deploy `target/springmovies-0.1.war` to Tomcat's webapp directory for deployment. The URL is the same as for the maven build.

```
$ curl localhost:8080/spring-rest-example-0.1/rest/movieDB/list
[{"name":"Alien","rating":"pg16","director":"Ridley Scott"}]
```

Alternatively, you can deploy to a local Jetty server as follows:

```
$ sbt
> container:start
```
This will build the project and deploy it to Jetty. Note that the `spring-rest-example-0.1` path is not required. I am looking into how to make this consistent between Tomcat and Jetty.

```
$ curl localhost:8080/rest/movieDB/list
[{"name":"Alien","rating":"pg16","director":"Ridley Scott"}]
```

You can get more details on how to use the SBT web plugin [here](https://github.com/siasia/xsbt-web-plugin/wiki).

For now, each one of these options results in a different URL to interact with the example. I will address this in the next day or so.
