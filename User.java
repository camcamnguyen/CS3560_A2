package cs3560hw2;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

public class User extends Subject implements SysEntry, Observer{
    private String name;
    private long creationTime;
    private long myLastUpdate;
    private long lastUpdateTime;
    private List<User> following = new ArrayList<User>();
    private List<String> myTweets = new ArrayList<String>();
    private List<String> newsFeed = new ArrayList<String>();
    private ObservableList<String> newsFeedList = FXCollections.observableList(newsFeed);
    
    public void setName(String userName){
        name = userName;
    }
    
    public String getName(){
        return name;
    }
    
    public List<User> getFollowing(){
        return following;
    }
    
    public void addFollowing(User newUser){
        following.add(newUser);
    }
    
    public void setCreationTime(){
        creationTime = System.currentTimeMillis();
    }
    
    public long getCreationTime(){
        return creationTime;
    }
    
    public long getUpdatedTime(){
        //gives the most recent update time
        return lastUpdateTime;
    }
    
    public List<String> getTotalTweets(){
        return myTweets;
    }
    
    public ObservableList<String> getNewsFeed(){
        return newsFeedList;
    }
    
    public long getMyUpdateTime(){
        return myLastUpdate;
    }
    
    //updating for the observers
    @Override
    public void updateTweets(Subject subject, String message){
        //type cast the subject as a user in order to get their name
        String nom = ((User)subject).getName();
        this.newsFeedList.add(nom + ": " + message);
    }
    
    public void post(String message){
        //save what you tweeted
        myTweets.add(message);
        newsFeedList.add(this.getName() + ": " + message);
        notifyObserver(message);
    }
    
    @Override
    public void updateTime(Subject subject, long time){
        this.lastUpdateTime = time;
    }
    
    public void time(){
        myLastUpdate = System.currentTimeMillis();
        lastUpdateTime = System.currentTimeMillis();
        notifyTimeChange(myLastUpdate);
    }
    
    @Override
    public void accept(SysEntryVisitor visitor){
        visitor.visitUser(this);
    }
    
}
