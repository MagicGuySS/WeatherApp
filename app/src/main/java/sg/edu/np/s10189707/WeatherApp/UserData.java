package sg.edu.np.s10189707.WeatherApp;

public class UserData {

    private String MyUserName;
    private String MyPassword;

    public UserData(){

    }

    public UserData(String myUserName, String myPassword){
        MyPassword = myPassword;
        MyUserName = myUserName;
    }

    public String getMyUserName() {
        return MyUserName;
    }

    public void setMyUserName(String myUserName) {
        MyUserName = myUserName;
    }

    public String getMyPassword() {
        return MyPassword;
    }

    public void setMyPassword(String myPassword) {
        MyPassword = myPassword;
    }
}
