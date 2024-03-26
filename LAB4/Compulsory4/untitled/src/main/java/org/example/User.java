package org.example;
import java.time.LocalDate;
import java.util.Vector;

public class User  {

  private String name;

  private LocalDate birthday;

  private String email;

  private String password;

  private Integer Id;

  private Image picture;

    /**
   * 
   * @element-type Friendship
   */
  public Vector  myFriendship;
    /**
   * 
   * @element-type Group
   */
  public Vector  myGroup;
    public Image myImage;
    /**
   * 
   * @element-type GroupMember
   */
  public Vector  myGroupMember;
    public Location myLocation;

  public void changePass( String newPassword) {
  }

  public void logIn(String name,String  password) {
  }

  public void signUp( String name,String  birthday,String  email,String  password) {
  }

  public void addPicture( String image) {
  }

  public void changePicture( String image) {
  }

}