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

//class for the user view window
public class UserView {
    private VBox userView;
    
    private UserView(User newUser, List<User> userList){
        //construct the GUI
        Button tweet = new Button();
        tweet.setText("Tweet");
        
        Button follow = new Button();
        follow.setText("Follow");
        
        Label update = new Label();
        
        Label creation = new Label();
        creation.setText("Creation time: " + newUser.getCreationTime());
        
        TextField tweetText = new TextField();
        TextField followText = new TextField();
        ListView displayFollowing = new ListView();
        
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
            newUser.addFollowing(tempUser);
            tempUser.attach(newUser);
            displayFollowing.getItems().add(tempUser.getName());
        });
        
        tweet.setOnAction(actionEvent -> {
            String message;
            message = tweetText.getText();
            tweetText.clear();
            newUser.post(message);
            newUser.time();
        });
        ListView displayTweet = new ListView(newUser.getNewsFeed());
        
        //the update time must be refreshed (close user view and open again)
        update.setText("Last update: " + newUser.getUpdatedTime() + " milliseconds ago");
        
        HBox creationTime = new HBox(creation);
        HBox followSet = new HBox(follow, followText);
        HBox tweetSet = new HBox(tweet, tweetText);
        HBox findTime = new HBox(update);
        VBox vbox = new VBox(creationTime, followSet, displayFollowing, tweetSet, displayTweet, findTime);
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
        newWindow.setTitle(newUser.getName() + "'s User View");
        VBox vbox = viewUser.getBox();
        Scene secondScene = new Scene(vbox, 300, 250);
        newWindow.setScene(secondScene);
        newWindow.show();
    }
}
