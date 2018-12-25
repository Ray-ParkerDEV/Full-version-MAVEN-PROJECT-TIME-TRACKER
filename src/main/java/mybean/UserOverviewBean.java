package mybean;

import entities.User;
import entities.UserType;

public class UserOverviewBean {
    private Integer userId;
    private String firstName;
    private String surName;
    private String login;
    private String password;
    private UserType userType;

    public UserOverviewBean() {
    }

    public UserOverviewBean(Integer userId, String firstName, String surName,
                            String login, String password, UserType userType) {
        this.userId = userId;
        this.firstName = firstName;
        this.surName = surName;
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOverviewBean that = (UserOverviewBean) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (surName != null ? !surName.equals(that.surName) : that.surName != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return userType != null ? userType.equals(that.userType) : that.userType == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserOverviewBean{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

    public static UserOverviewBean setAttributesToBean(User user){
        UserOverviewBean userBean = new UserOverviewBean(user.getUserId(),user.getFirstName(),user.getSurName(),
                user.getLogin(),user.getPassword(),user.getUserType());
        return userBean;
    }
}
