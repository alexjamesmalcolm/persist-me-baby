# persist-me-baby

This uses google's oauth2 api so you're going to need a client_id and client_secret from them.
You can get that from here: https://console.developers.google.com/apis/credentials.
In order to get this working you're going to have to create a gradle.properties file.
Here's an example of what that would look like:
```properties
CLIENT_ID = [CLIENT_ID]
CLIENT_SECRET = [CLIENT_SECRET]
DATABASE_URL = jdbc:h2:./database
```
The square brackets should be replaced with the client_id and client_secret I was talking about earlier.

I've deployed this to Heroku at https://no-fun-messaging.herokuapp.com/.
You can deploy to Heroku as well but you're going to have to assign environment variables for client_id and client_secret.
The database_url you won't have to manually enter, intead you'll attach a postgres database supplied by Heroku.
After you've got all this done it should work, if not message me on slack.
