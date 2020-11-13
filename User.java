package cs3560hw2;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

public class User extends Subject implements SysEntry, Observer{
    private String name;
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
    
    public ObservableList<String> getNewsFeed(){
        return newsFeedList;
    }
    
    //updating for the observers
    @Override
    public void update(Subject subject, String message){
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
    
    public void accept(SysEntryVisitor visitor){
        visitor.visitUser(this);
    }
    
}
