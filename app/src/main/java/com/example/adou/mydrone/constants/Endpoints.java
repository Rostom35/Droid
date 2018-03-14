package com.example.adou.mydrone.constants;

/**
 * Created by hakima on 3/12/18.
 */

public class Endpoints {
    //acount resource
    public static final String BASE = "loaclahost:8080/api";
    public static final String REGISTER = "/register";
    public static final String ACTIVATE = "activate";
    public static final String IS_AUTHENTICATED = "/authenticate";
    public static final String ACCOUNT = "/account";
    public static final String ACCOUNT_CHANGE_PWD = "/account/change-password";
    public static final String ACCOUNT_RESET_PWD_INIT = "/account/reset-password/init";
    public static final String ACCOUNT_RESET_PWD_FINISH = "/account/reset-password/finish";
    public static final String MISSIONS = "/missions";
    public static final String MISSIONS_ORDER_MOVE = "/missions/{id}/orderMove";
    public static final String MISSION_ORDER_MOVE_PICTURE = "/missions/{id}/orderMovePicture";
    public static final String MISSION_ID = "/missions/{id}";
}
