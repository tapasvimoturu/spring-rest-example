seq(webSettings :_*)

name := "spring-rest-example"

// Don't need Scala stuff here.
autoScalaLibrary := false

// Removes the scala-2.x.x prefix on targets
crossPaths := false

version := "0.1"

{
    val springVersion = "3.1.0.RELEASE"   
    libraryDependencies ++= Seq(
    "org.springframework" % "spring-core" % springVersion,
    "org.springframework" % "spring-web" %  springVersion,
    "org.springframework" % "spring-webmvc" % springVersion,
    "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.9",
    "org.apache.tomcat" % "servlet-api" % "6.0.35",
    "org.mortbay.jetty" % "jetty" % "6.1.26" % "container"     // Needed for xsbt-web plugin
    )
}



