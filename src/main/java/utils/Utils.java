package utils;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.apiModels.CreateUserModel;
import utils.apiModels.LogInUserResponse;
import utils.apiModels.LoginUserModel;

import java.util.Random;

public class Utils {

    public static String getRandomEmail(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String getRandomName (){
        Faker faker = new Faker();
        return faker.name().firstName();

    }

    public static String getRandomPassword(int length){
        String s = "abcdefghigk123456789";
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < length; i++) {
            str.append(s.charAt(new Random().nextInt(s.length())));
        }
        return str.toString();
    }

    @Step("Delete user after test")
    public static void deleteUser(String email, String password){
        LoginUserModel loginUserModel = new LoginUserModel(email,password);
        Response logInResponse = ApiClient.postLogInUser(loginUserModel);
        String token = logInResponse.body().as(LogInUserResponse.class).getAccessToken();
        if (token != null){
            ApiClient.deleteUser(token);
        }

    }

    @Step("Register new user for test")
    public static void registerUser(String email, String password,String name){
        CreateUserModel createUserModel = new CreateUserModel(email,password,name);
        Response createResponse = ApiClient.postCreateUser(createUserModel);
        createResponse.then().statusCode(200);
    }


}
