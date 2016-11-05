package com.example.a1342097.server;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 1342097 on 2016-10-28.
 */
public class User {


    public String url;
    public String name;
    public String password;
    public String email;

    class Links
    {
        class Self
        {
            public String href;
        }

        class InnerNote
        {
            public String href;
        }

        class CreatedBy
        {
            public String href;
        }


        Self self;
        InnerNote note;
        CreatedBy createdBy;
    }
    private Links _links;


    private static class Embedded
    {
        public Embedded()
        {
        }
        private User[] user;
    }

    private static class UserArray
    {
        private  Embedded _embedded = new Embedded();
    }




    public User()
    {

    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = encryptPassword(password);
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encryptPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPassword(String password)
    {
        return this.password.equals(encryptPassword(password));
    }

    @Override
    public String toString() {
        return "User{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public static User parse(String parseString)
    {
        System.out.println(parseString);

        User user = new Gson().fromJson(parseString, User.class);

        user.setUrl(user._links.self.href);

        return user;
    }

    public static User[] parseArray(String parseString)
    {
        User[] users = new Gson().fromJson(parseString, UserArray.class)._embedded.user;
        return users;
    }

    public String format()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
    }


    private String encryptPassword(String password)
    {
        MessageDigest md = null;
        byte[] textBytes = new byte[0];
        try {

            md = MessageDigest.getInstance("SHA-1");
            textBytes = password.getBytes("iso-8859-1");
            md.update(textBytes, 0, textBytes.length);
            byte[] sha1hash = md.digest();

            StringBuilder buf = new StringBuilder();
            for (byte b : sha1hash) {
                int halfbyte = (b >>> 4) & 0x0F;
                int two_halfs = 0;
                do {
                    buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                    halfbyte = b & 0x0F;
                } while (two_halfs++ < 1);
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

}



































