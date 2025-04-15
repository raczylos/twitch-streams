
### Install Dependencies
Ensure that the following are installed on your system:
* Docker
* Docker Compose
* Docker Desktop

### Run database, frontend, backend containers:
* Run following command:
* docker-compose up

### Run application:
* frontend - http://localhost:4200/
* backend - http://localhost:8080/swagger-ui/index.html

### How to use:
* fetch data from twitch by clicking button on frontend and wait several minutes for results
* (alternative way) fetch data using swagger to fill database

### Additional information:
Under normal circumstances, .env files containing sensitive credentials should never be committed to GitHub. However, in this case, the values are included for seamless project setup. The Twitch API credentials are from a temporary test account and will not be used beyond this demonstration.
