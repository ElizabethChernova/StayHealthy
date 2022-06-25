package com.example.entities;

import android.content.Context;


import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Storage {
    private static final String FILE_NAME = "user_information.json";

    public static boolean exportToJSON(Context context, Person user) {

        Gson gson = new Gson();
        UserInformation userInformation = new UserInformation();
        userInformation.setUser(user);
        String jsonString = gson.toJson(userInformation);

        try (FileOutputStream fileOutputStream =
                     context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Person importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            UserInformation userInformation = gson.fromJson(streamReader, UserInformation.class);
            return  userInformation.getUser();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }
    private static class UserInformation {
        private Person user;

        Person getUser() {
            return user;
        }
        void setUser(Person user) {
            this.user = user;
        }
    }
}
