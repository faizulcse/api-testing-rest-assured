# API testing using Rest-Assured
[![](https://img.shields.io/badge/Java-1.8.0-brightgreen)]() [![](https://img.shields.io/badge/Maven-3.8.1-brightgreen)]() [![](https://img.shields.io/badge/Rest--Assured-4.0.0-brightgreen)]() [![](https://img.shields.io/badge/Cucumber-3.1.2-brightgreen)]() [![](https://img.shields.io/badge/Docker-20.10.5-brightgreen)]() [![](https://img.shields.io/badge/IntelliJ%20IDEA-2018.2.8-brightgreen)]()

### Description
This is an API testing automation project based on Rest-Assured API libraries. Apache Maven is used to building and run the project and Cucumber-Gherkin is used for writing test scenarios and reporting.

### Setup Project
First of all, you need to clone the project from Github. Use the following command
```commandline
git clone https://github.com/faizulcse/api-testing-rest-assured.git
```

### Add more feature and Scenario

- Go to project features package `src/test/java/com/features`
- Create a new file `account.feature`
- Add a new scenario in the file
- Example:
```gherkin
    @account
    Feature: Create a new account
    
      @test-1
      Scenario: As a test engineer I can create a new account
        When I create a new account using API call
        Then I should see that a new account is created successfully
```

- Go to project featureSteps package `src/test/java/com/featureSteps`
- Create a new file `AccountSteps.java`
- Add necessary feature steps in the file
- Example: 
```java
    public class AccountSteps {
        @When("^I create a new account using API call$")
        public void iCreateANewAccountUsingApiPostCall() {
        }
    
        @Then("^I should see that a new account is created successfully$")
        public void iShouldSeeThatANewAccountIsCreatedSuccessfully() {
        }
    }
```
- Write code inside the steps method

### Before Run 
- Go to project root directory
- Open `User.java` file from `src/test/java/com/User.java`
- Changed the credentials
- To keep your credentials secret please set your credentials as system variable
- Example:
```java
        public class User {
            public static String token;
            public static String username = "testdoc";
            public static String password = "Test123456";
            public static String baseURI = "https://nmed-c.zssbd.com";
            public static final String TO_EMAIL = System.getenv("TO_EMAIL");
            public static final String FROM_EMAIL = System.getenv("FROM_EMAIL");
            public static final String EMAIL_PASSWORD = System.getenv("EMAIL_PASSWORD");
        }
```
- Open `CucumberRunner.java` file from `src/test/java/com/testRunner/CucumberRunner.java`
- Add the tests tag those tests will execute
- To run multiple tag same time use comma among the tags
- To skip a test tag use `~` before the tag as `~@account`
- Example: 
 ```java
        @CucumberOptions(
                features = {"src/test/java/com/features"},
                glue = {"com/featureSteps"},
                tags = {"@token, @prescription, ~@account"},
                plugin = {"pretty", "json:target/cucumber.json"}
        )

        @RunWith(Cucumber.class)
        public class CucumberRunner {
        
        }
```
#### To add new api model and factories
 - Follow this instruction `src/test/java/com/api/README.md`

### Local run 
- Go to testRunner package and open this `CucumberRunner.java` file
- Right click on the file
- Select `Run 'CucumberRunner'` option
- Then click on it
- It will run all the test that mention in the tags (`tags = {"@token, @prescription, ~@account"}`)

##### Maven run
- Apache Maven should install on your local machine
- Open terminal
- Go to root directory
- Run command `mvn clean install `
- Run a single test `mvn install -Dcucumber.options="--tags @test-1"`

### Docker run 
- Open terminal
- Go to root directory
- Build docker image using command `docker build -t ${imageName} .`
- Run  docker image using command `docker run -t --name ${containerName} ${imageName} mvn install test -Dcucumber.options='--tags ${Cucumber_Tags}'`

### Reports and Logs
- Project reports are generated inside the directory `target/cucumber-reports/cucumber-html-reports/`
- Cucumber html reports `target/cucumber-reports/cucumber-html-reports/overview-features.html`
- Api logs are generated on `logs/api-log.log`

### Directory Structure
``` bash
.
├── logs
│   └── api-log.log
├── src
│   └── test
│       ├── java
│       │   └── com
│       │       ├── api
│       │       │   ├── factories
│       │       │   │   ├── AccountFactory.java
│       │       │   │   ├── BaseFactory.java
│       │       │   │   ├── Factory.java
│       │       │   │   ├── PrescriptionFactory.java
│       │       │   │   └── TokenFactory.java
│       │       │   ├── models
│       │       │   │   ├── AccountModel.java
│       │       │   │   ├── BaseModel.java
│       │       │   │   ├── CampaignModel.java
│       │       │   │   ├── Model.java
│       │       │   │   ├── PrescriptionModel.java
│       │       │   │   └── TokenModel.java
│       │       │   ├── ApiHelper.java
│       │       │   └── README.md
│       │       ├── featureSteps
│       │       │   ├── AccountSteps.java
│       │       │   ├── Hooks.java
│       │       │   ├── PrescriptionSteps.java
│       │       │   └── TokenSteps.java
│       │       ├── features
│       │       │   ├── account.feature
│       │       │   ├── prescription.feature
│       │       │   └── token.feature
│       │       ├── testData
│       │       │   └── prescription.json
│       │       ├── testRunner
│       │       │   └── CucumberRunner.java
│       │       ├── utils
│       │       │   ├── Helpers.java
│       │       │   └── MailHelper.java
│       │       ├── EnvData.java
│       │       └── User.java
│       └── resources
│           └── log4j2.properties
├── Dockerfile
├── Jenkinsfile
├── README.md
└── pom.xml
```
#### Questions
If you have trouble getting set up, or if you have any questions, please don't hesitate to reach out to me. I'm happy to help!

[![](https://img.shields.io/badge/Email--Address%3A-faizulcse%40gmail.com-blue)]()
