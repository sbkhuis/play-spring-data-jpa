name := "play-spring-data-jpa"

version := "1.0-SNAPSHOT"

playJavaSettings ++ QueryDSLPlugin.queryDSLSettings

val current = project.in(file(".")).configs(QueryDSLPlugin.QueryDSL)

ebeanEnabled := false

libraryDependencies ++= Seq(
    javaCore,
    javaJpa,
    "org.springframework" % "spring-context" % "4.0.3.RELEASE",
    "javax.inject" % "javax.inject" % "1",
    "org.springframework.data" % "spring-data-jpa" % "1.5.1.RELEASE",
    "org.springframework" % "spring-expression" % "4.0.3.RELEASE",
    "org.hibernate" % "hibernate-entitymanager" % "4.3.4.Final",
    "org.mockito" % "mockito-core" % "1.9.5" % "test"
)