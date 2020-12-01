package cs3560hw2;

//interface to user class
public interface Observer {
    public void updateTweets(Subject subject, String message);
    public void updateTime(Subject subject, long time);
}
