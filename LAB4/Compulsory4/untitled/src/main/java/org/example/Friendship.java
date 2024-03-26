package org.example;
import org.example.FriendshipStatus;
import java.time.LocalDate;
import java.util.Vector;

public class Friendship {

  private Integer requesterId;

  private Integer receipientId;

  private FriendshipStatus status;

  private Integer streak;

  private boolean close;

  private LocalDate since;

    /**
   * 
   * @element-type User
   */
  public Vector  myUser;
    public Vector  myGroup;

  public void getStreak() {
  }

  public void incrementStreak() {
  }

  public void resetStreak() {
  }

  public void acceptRequest() {
  }

  public void rejectRequest() {
  }

  public void deleteFriend() {
  }

  public void getStatus() {
  }

  public void newOperation() {
  }

}