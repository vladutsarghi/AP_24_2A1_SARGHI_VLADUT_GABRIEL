package org.example;
import java.util.List;
import java.util.Vector;

public class Group {

  private List<Integer> usersIds;

  private List<Message> messages;

  private String name;

  private Image picture;

    public Vector  myFriendship;
    /**
   * 
   * @element-type User
   */
  public Vector  myUser;
    public Image myImage;
    /**
   * 
   * @element-type GroupMember
   */
  public Vector  myGroupMember;

  public void sendMessage( ) {
  }

  public void sendImage( ) {
  }

}