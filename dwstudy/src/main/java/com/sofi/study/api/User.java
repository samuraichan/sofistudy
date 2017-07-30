package com.sofi.study.api;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="system_user")
//@Where(clause="active_flag = 'Y'")
@NamedQueries ({
  @NamedQuery(name="com.sofi.study.api.User.findAll", query="SELECT u FROM User u"),
  @NamedQuery(name="com.sofi.study.api.User.findByUserName", query="SELECT u FROM User u WHERE u.username = :username") 
})
public class User extends BaseEntity {

  private String username;
  
  private String password;
  
  public User() {}
  
  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }
}
