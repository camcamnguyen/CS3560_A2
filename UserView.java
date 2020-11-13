package cs3560hw2;

import javafx.application.Application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.*;

public class UserView {
    private VBox userView;
    
    private UserView(User newUser, List<User> userList){
        //construct the GUI
        Button tweet = new Button();
        tweet.setText("Tweet");
        
        Button follow = new Button();
        follow.setText("Follow");
        
        TextField tweetText = new TextField();
        TextField followText = new TextField();
        ListView displayFollowing = new ListView();
        
        System.out.println("new user: " + newUser.getName());
        if(newUser.getFollowing() != null){
            for(int i = 0; i < newUser.getFollowing().size(); i++){
                displayFollowing.getItems().add(newUser.getFollowing().get(i).getName());
            }
        }
        
        //assume the user they want to follow exists
        follow.setOnAction(actionEvent -> {
            User tempUser = new User();
            String followUser = followText.getText();
            followText.clear();
            for(int i = 0; i < userList.size(); i++){
                if(userList.get(i).getName().equals(followUser)){
                    tempUser = userList.get(i);
                }
            }
            System.out.print("temp user: " + tempUser.getName());
            newUser.addFollowing(tempUser);
            tempUser.attach(newUser);
            displayFollowing.getItems().add(tempUser.getName());
        });
        
        /*
        if(newUser.getNewsFeed() != null){
            for(int i = 0; i < newUser.getNewsFeed().size(); i++){
                displayTweet.getItems().add(newUser.getNewsFeed().get(i));
            }
        }
        */
        
        tweet.setOnAction(actionEvent -> {
            String message;
            message = tweetText.getText();
            tweetText.clear();
            newUser.post(message);
        });
        ListView displayTweet = new ListView(newUser.getNewsFeed());
        
        HBox followSet = new HBox(follow, followText);
        HBox tweetSet = new HBox(tweet, tweetText);
        VBox vbox = new VBox(followSet, displayFollowing, tweetSet, displayTweet);
        userView = vbox;
    }
    
    public VBox getBox(){
        return userView;
    }
    
    public static void getUserView(User newUser, List<User> userList){
        //get the GUI
        User user = newUser;
        System.out.println(newUser.getName());
        UserView viewUser = new UserView(user, userList);
        
        Stage newWindow = new Stage();
        newWindow.setTitle("User View");
        VBox vbox = viewUser.getBox();
        Scene secondScene = new Scene(vbox, 300, 250);
        newWindow.setScene(secondScene);
        newWindow.show();
    }
}
