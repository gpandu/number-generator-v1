# number-generator
  This project is to create Spring Boot Application for generating sequence of Numbers. REST APIs can be consumed to access the functionality


# Documentation
  
* Clone the git project from https://github.com/gpandu/number-generator-v1.git
* This is a Maven project, we can use InteliJ or Eclipse IDE to import this repository as Maven Project.
* In InteliJ, Navigate to **File** -> **New** -> **Project from Existing Sources** -> **Select the git repo directory from local**
* Select **Maven** as the project in the next page.
* Project will start building by downloading dependencies provided in the **pom.xml**, it will take few minutes to build the project.
* NOTE: Add jdk repository to the IDE if not already pointed.
* Once build completes, navigate to source path **src/main/java/com/numgenerator/springimpl**. Run NumberGeneratorApplication.java file which will execute main method and starts SpringBoot Application on port mentioned in **application.properties**.


# Sample REST API Request and Response

**POST Request** :  http://127.0.0.1:3000/api/generate
               Body : {"goal":"6", "step": "2"}

**Response** : {
                 "task": "30a60655-dce0-428b-a58b-fbf83e7aa725"
               }
