# Autaomted Online-Shop-tests 🤖🤖

## Introduction 📣
This project contains some basic tests for [this](https://shop.demoqa.com/), which is a demo online store page. Some tests are simple some are more complicated. Tests are written in Selenium with Java.
I incorporated rules of the Page Object Model in this project. I focused mainly on POM, but I also added TestNG and Allure reports. Dependencies in the project are managed by Maven.
I plan to develop this project further to learn more. 

## Technologies 💻

<img align="left" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" title="Java 17">
<img align="left" src="https://img.shields.io/badge/-selenium-%43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium" title="Selenium 4.0.0">
<img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="Apache Maven" title="Apache Maven 3.8.5">
 
These three are my main technologies ⬆️</br>
Besides that, I am using: </br>
- **TesNG version 7.5** </br>
- **Allure version 2.22.1** </br>
- **Intelli J**  <img width="80" height="20" src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white" alt="IntelliJ" title="IntelliJ">

## Setup 🔨
To start this project you will need to install Java 17 on your PC and add variables. [Here](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/How-do-I-install-Java-on-Windows) is a tutorial how to do it. Adding Maven is also required to handle all dependencies. Also installing the correct chrome driver is a must as I am using Selenium 4.0.0 and for now, all tests are set up for Chrome driver only. In the <code>config.proprties</code> file you have to change the <code>driverPath</code> to yours. For usage the allure reports it's alos required to set correct variable in your system. Here is [documentation](https://docs.qameta.io/allure/)


## Plans 📓
1. Refactor PropertiesFileReader to be nicer
2. Change to Selenium 4.6.0 or higher ⬆️
3. Add more forms of tests ✍️
4. Add screen capture for failure tests in Allure 📷
5. Add parallel tests 

