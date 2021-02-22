package com.kocfinans.scoresegment.util;

public class ApiPaths{
  private static final String BASE_PATH = "/api";
  public static final class UserController{
    public static final String CONTROLLER = BASE_PATH + "/user";
  }

  public static final class MonthlyIncomeBracketController{
    public static final String CONTROLLER = BASE_PATH + "/monthlyIncomeBracket";
  }

  public static final class CityController{
    public static final String CONTROLLER = BASE_PATH + "/city";
  }
}
