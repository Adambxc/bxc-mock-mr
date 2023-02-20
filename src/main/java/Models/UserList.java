package Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserList {
  List<User> users;
  public void Users() {
    users = new ArrayList<>();
  }
}
