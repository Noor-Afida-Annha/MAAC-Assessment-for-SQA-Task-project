package Utils;

import SetUp.SetUp;
import SetUp.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utilsf extends SetUp {



    public static void saveUsers(UserModel model) throws IOException, ParseException {
        String fileLocation="./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray userArray = (JSONArray) parser.parse(new FileReader(fileLocation));

        JSONObject userObj = new JSONObject();
        userObj.put("fullName",model.getFullname());
        userObj.put("email",model.getEmail());
        userObj.put("phone",model.getPhone());
        userObj.put("password",model.getPassword());

        userArray.add(userObj);
        FileWriter writer = new FileWriter(fileLocation);
        writer.write(userArray.toJSONString());
        writer.flush();
        writer.close();

    }

    public static JSONArray readJSONData() throws IOException, ParseException {
        String fileLocation = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray userArray = (JSONArray) parser.parse(new FileReader(fileLocation));
        return userArray;
    }

    public static int generateRandomId(int min, int max){
        double random=Math.random()*(max-min)+min;
        int randId=(int) random;
        return randId;
    }



}
