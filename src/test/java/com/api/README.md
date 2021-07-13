### Create new API
To add a new API feature you need to create two files one for models and one for factories package
- Create a file `TokenModel.java` inside the models package
- Example: 
```java
    public class TokenModel extends BaseModel {
        public String username;
        public String password;
    }
```

- Now create `TokenFactory.java` inside factories package
- Example:
```java
    public class TokenFactory extends BaseFactory {
        public TokenFactory() {
            ApiHelper.setBasePath("/api-token-auth/gen/");
        }
    
        public Response create() {
            TokenModel model = new TokenModel();
            model.username = User.username;
            model.password = User.password;
            String payload = model.toJson();
            return ApiHelper.postRequest(payload);
        }
    }
```
- Set api base URI and Token/apikey inside the `BaseFactory.java`
- Example:
```java
    public abstract class BaseFactory implements Factory {
        public BaseFactory() {
            ApiHelper.setBaseUrl(User.baseURI);
            ApiHelper.setToken(User.token);
        }
    }
```
### Api Directory Structure
```
.
├── factories
│   ├── AccountFactory.java
│   ├── BaseFactory.java
│   ├── Factory.java
│   ├── PrescriptionFactory.java
│   └── TokenFactory.java
├── models
│   ├── AccountModel.java
│   ├── BaseModel.java
│   ├── Model.java
│   ├── PrescriptionModel.java
│   └── TokenModel.java
├── ApiHelper.java
└── README.md
```