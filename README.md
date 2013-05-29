# Spring Rest SBT and Maven example

This GIST demonstrates the use of both Maven and SBT building of a basic Spring MVC rest service that can run on
Tomcat and (with xsbt-web-plugin) on Jetty. You must have either Maven or SBT installed. Note that this is derived
from an awesome example by [Yong Mook Kim](http://www.mkyong.com/).

In order to run this GIST, you need the following packages installed.

1. [Maven >2.0](http://maven.apache.org)
2. [Simple Build Tool](https://github.com/harrah/xsbt)
3. Java 1.6+ build environment (J2SE)
4. A suitable IDE

Follow the instructions for your operating system to install the packages and make sure that they are on your path.
Note that this has only been tested this on OSX, so if there are tricks on the other OS's, please make a note here.
There are all sorts of dependencies will get downloaded automatically. This may take a few minute....

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
This will produce a file `target/SpringMovies.war`. Copy this to your tomcat webapp directory for deployment. Note
that  I have not worked out how to deploy this to Jetty. Test it by issuing the following command:

```
$ curl localhost:8080/example/movies/
{
    "1": {
        "name": "Alien",
        "rating": "pg16",
        "director": "Ridley Scott"
    },
    "2": {
        "name": "Natural Born Killers",
        "rating": "pg16",
        "director": "Oliver Stone"
    }
}```

## SBT builds
Simple Build Tool is the Scala build system which can build Java projects too. It uses convention over configuration
 and results in terse project definitions:

```
$ sbt package
```

At this point, you can deploy `target/springmovies-0.1.war` to Tomcat's webapp directory for deployment. The URL is
the same as for the maven build.

```
$ sbt
> container:start
```
This will build the project and deploy it to Jetty. Note that the `spring-rest-example-0.1` path is not required. I
am looking into how to make this consistent between Tomcat and Jetty.

You can get more details on how to use the SBT web plugin [here](https://github.com/siasia/xsbt-web-plugin/wiki).

For now, each one of these options results in a different URL to interact with the example. I will address this in
the  next day or so.

## Using the service
The WAR files produced by either SBT or Maven can simply be copied to a Tomcat container for deployment.
Alternatively, the application can be run using `JettyRunner`. This deploys the application using an embedded Jetty
container. Simply run `JettyContainer.java` from the IDE.
