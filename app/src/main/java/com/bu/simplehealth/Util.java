package com.bu.simplehealth;

import com.bu.simplehealth.database.entity.Condition;
import com.bu.simplehealth.database.entity.Exercise;
import com.bu.simplehealth.database.entity.MindGame;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * This is Util class which holds reusable functions
 *
 * @author Seema Palora
 */
public class Util {

    /**
     * Method to generate hash password using SECURE HASHING ALGORITHM with SALT
     *
     * @param pwd
     * @param salt
     * @return
     */
    public static String getSecurePwd(String pwd, byte[] salt) {

        String securePwd = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] b = md.digest(pwd.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < b.length; i++) {
                sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
            }
            securePwd = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error occurred when trying to encrypt password");
        }
        return securePwd;
    }

    /**
     * Generate SALT
     *
     * @return salt object used to encrypt password
     */
    public static byte[] getSalt() {
        SecureRandom rnd = new SecureRandom();
        byte[] salt = new byte[16];
        rnd.nextBytes(salt);
        return salt;
    }

    /**
     * Util method to construct list of mind game to be inserted into DB
     *
     * @return
     */
    public static List<MindGame> insertMindGames() {
        List<MindGame> mindgames = new ArrayList<>();
        mindgames.add(new MindGame("Tic Tac Toe", "https://playtictactoe.org/", 1));
        mindgames.add(new MindGame("Maze", "https://blockly.games/maze", 2));
        mindgames.add(new MindGame("Trivia", "https://www.randomtriviagenerator.com/#/", 3));
        return mindgames;
    }

    /**
     * Util method to construct list of exercise  to be inserted into DB, more data can be added
     *
     * @return
     */
    public static List<Exercise> insertExercises() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Ab Roller", "Core", "https://www.youtube.com/watch?v=Ulduc3QuBXg"));
        exercises.add(new Exercise("Air Bike", "Core", "https://www.youtube.com/watch?v=rbmIkBB-dJY"));
        exercises.add(new Exercise("Barbell Rollout", "Core", "https://www.youtube.com/watch?v=s_elM3ciI8g"));
        exercises.add(new Exercise("Bench Sit Ups", "Core", "https://www.youtube.com/watch?v=GDFu_oqbhV4"));

        exercises.add(new Exercise("Cycling", "Cardio", "https://www.youtube.com/watch?v=YKCy0HlH55M"));
        exercises.add(new Exercise("Squats", "Legs", "https://www.youtube.com/watch?v=otzWCWpuW-A"));
        exercises.add(new Exercise("Pushups", "Shoulder", "https://www.youtube.com/watch?v=MO10KOoQx5E"));
        return exercises;
    }

    /**
     * Util method to construct list of conditions to be inserted into DB, more data can be added
     *
     * @return
     */
    public static List<Condition> insertConditions() {
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition("Anxiety", "IN", 1));
        conditions.add(new Condition("Depression", "IN", 2));
        conditions.add(new Condition("Migraines", "IN", 3));
        conditions.add(new Condition("Diabetes", "IN", 4));
        return conditions;
    }

}
