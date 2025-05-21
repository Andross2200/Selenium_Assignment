# Selenium Assignment
This is an assignment that I completed as a part of Software Quality and Testing course while studying for my Master's
degree in Computer Science at Eötvös Loránd University in Budapest, Hungary.

This course required me to write Selenium tests for an existing website. For my assignment I have chosen to run my tests
on [Steam Store](https://store.steampowered.com/). The requirements and their fulfilment can be seen in a
[fulfilment sheet](https://docs.google.com/spreadsheets/d/1SAckjKkOG6m_PRI45P-G8R4fjVl7bGJ9VpyVrO9-a1Q/edit?usp=sharing).

# Running tests
The tests were run on a Windows machine using Google Chrome browser and Chrome Drive.
To run the tests on your machine do the following steps:
1. Clone the repository to your machine.
2. Download Chrome Drive executable and put it into the root directory of this project.
3. The project requires login credentials to run some of the tests. Contact me for the file with credentials and put it
into the ```src/test/java/config``` directory.
4. Ensure that you have Gradle to compile and run the tests.

After fulfilling these steps, you should be able to run the selenium tests.

## Using your own credentials
You can use your own credentials for the tests. For this create a ```config.properties``` file in 
```src/test/java/config``` directory and include following values:
 - ```steam.username```
 - ```steam.password```
 - ```mail.imap```
 - ```mail.address```
 - ```mail.password```

The email must be the same that is used by Steam to verify your login with SteamGuard.