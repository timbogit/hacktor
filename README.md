# hacktor
Hacking with KTor service in Kotlin

# Running the project locally
* build and run via `$ ./gradlew run`
  * service will be available at `http://127.0.0.1:8080/json/gson`

# Deploy to, and run on, heroku
```
$ heroku create
$ git push heroku master
$ heroku open
```
* deployed here already: [https://evening-fortress-09301.herokuapp.com/json/gson](https://evening-fortress-09301.herokuapp.com/json/gson)

## Examples
* [https://evening-fortress-09301.herokuapp.com/]
HELLO WORLD!
* [https://evening-fortress-09301.herokuapp.com/json/gson]
{
hello: "world"
}
* [https://evening-fortress-09301.herokuapp.com/type/word/list/4] 
Inside List(type=Type(name=word), page=4)
* [https://evening-fortress-09301.herokuapp.com/type/meh/edit] 
Inside Edit(type=Type(name=meh))


## References (to explore)
https://ktor.io/quickstart/guides/api.html
https://ktor.io/quickstart/quickstart/gradle.html
https://ktor.io/samples/app/youkube.html
https://github.com/ktorio/ktor-samples/blob/master/app/youkube/README.md
https://proandroiddev.com/roll-your-own-streaming-music-service-with-kotlin-e5fef9edfa9f
https://ktor.io/servers/deploy/hosting/heroku.html
https://github.com/johnrengelman/shadow
https://github.com/orangy/ktor-heroku-start
https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku
https://docs.aws.amazon.com/polly/latest/dg/longer-cli.html
https://wiki.postgresql.org/wiki/BinaryFilesInDB
https://www.postgresql.org/docs/current/largeobjects.html
https://jdbc.postgresql.org/documentation/80/binary-data.html
https://stackoverflow.com/questions/35567696/has-anybody-ever-stored-an-mp3-in-a-redis-cache
https://github.com/ktorio/ktor-clients
https://www.baeldung.com/jackson-vs-gson
https://stackoverflow.com/questions/33526612/how-to-change-name-of-jar-created-using-shadowjar
https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Jar.html#org.gradle.api.tasks.bundling.Jar:destinationDirectory
https://github.com/timbogit/hacktor

