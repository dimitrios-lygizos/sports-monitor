How to run:
  locally <>
  
  Set the following environment variables to be read by the application.yaml properties file
  - Set the database URL at environment variable with name: "POSTGRES_DB"
  - Set POSTGRESQL Username at environment variable with name "POSTGRES_USER"
  - Set POSTGRESQL Password at environment variable with name "POSTGRES_PASSWORD"
    Then you have set up your application and you're ready to run "mvn package" in order to create the .jar file.
    with java -jar ./target/sports-monitor-0.0.1-SNAPSHOT.jar, you can execute the application and enjoy.

 as a containerized WEB APP <>
 from project’s root folder perform the following:

 - run "docker-compose up" to pull the images, create the containers and deploy.
 - run "docker-compose down" to delete the web application.

Be careful, the URL endpoint has prefix "/sportMonitor"
