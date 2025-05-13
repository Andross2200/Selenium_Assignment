# Selenium Assignment
This is an assignment that I completed as a part of Software Quality and Testing course while studying for my Master's
degree in Computer Science at Eötvös Loránd University in Budapest, Hungary.

This course required me to write Selenium tests for an existing website. For my assignment I have chosen to run my tests
on [Steam Store](https://store.steampowered.com/). The requirements and their fulfilment can be seen in a
[fulfilment sheet](https://docs.google.com/spreadsheets/d/1SAckjKkOG6m_PRI45P-G8R4fjVl7bGJ9VpyVrO9-a1Q/edit?usp=sharing).

# Running tests
The tests can be run locally using Docker. Follow these steps to run the tests on your machine:
1. Ensure that you have Docker installed on your machine.
2. Clone this repository to your machine.
3. In terminal open the directory of repository.
4. Run following command to start the Docker containers: ```docker compose up```
5. When the containers are up and running, execute following command to enter inside the container: ```docker exec -it selenium-assignment-ubuntu-1 bash```
6. In the container go to the test directory: ```cd tests```
7. Run gradle tests with ```gradle test```