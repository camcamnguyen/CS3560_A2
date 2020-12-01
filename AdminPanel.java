package cs3560hw2;

import javafx.application.Application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
//import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.*;


public class AdminPanel {
    private static AdminPanel control;
    private VBox panel;
    
    public static AdminPanel getInstance(){
        //if control is empty, create a new instance
        if(control == null){
            if(control == null){
                control = new AdminPanel();
            }
        }
        return control;
    }
    
    private AdminPanel(){
        //constructor for admin panel
        //the private constructor will ensure that we cannnot make a new admin
        //window in the main method
        
        //Group rootGroup = new Group("root");
        Group rootGroup = new Group("root");
        rootGroup.setName("root");
        TreeItem<SysEntry> root = new TreeItem<>(rootGroup); //TreeItem goes into TreeView
        root.setExpanded(true);
        TreeView<SysEntry> treeView = new TreeView<>(root);
        
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        
        //Stores every instance of a user
        List<User> dataList = new ArrayList<User>();
        
        //assume that the user knows to enter a user name in groups
        //no adding users in other users
        Button addUser = new Button();
        addUser.setText("Add User");
        TextField userText = new TextField();
        addUser.setOnAction(actionEvent -> {
            //TreeItem<SysEntry> merges the class with the tree item
            //check the group selected to add the user to the selected group
            TreeItem<SysEntry> selected = treeView.getSelectionModel().getSelectedItem();
            String input = userText.getText();
            userText.clear();
            User tempUser = new User();
            tempUser.setName(input);
            tempUser.setCreationTime();
            //add the user to the selected group
            ((Group)selected.getValue()).addToGroup(tempUser);
            //add the user object to the stored list for future reference in user view
            dataList.add(tempUser);
            //add to tree view
            selected.getChildren().add(new TreeItem(tempUser.getName()));
        });
        
        //adding a group
        Button addGroup = new Button();
        addGroup.setText("Add Group");
        TextField groupText = new TextField();
        addGroup.setOnAction(actionEvent -> {
            TreeItem<SysEntry> selected = treeView.getSelectionModel().getSelectedItem();
            String input = groupText.getText();
            Group tempGroup = new Group(input);
            tempGroup.setName(input);
            tempGroup.setCreationTime();
            //a SysEntry type must be stored in order to add into the group
            ((Group)selected.getValue()).addToGroup(tempGroup);
            selected.getChildren().add(new TreeItem<SysEntry>(tempGroup));
            groupText.clear();
            rootGroup.add();
        });
        
        //pass the user selected to the UserView class
        Button userView = new Button();
        userView.setText("User View");
        userView.setOnAction(actionEvent -> {
            TreeItem selected = (TreeItem)treeView.getSelectionModel().getSelectedItem();
            String value = (String)selected.getValue();
            User tempUser = new User();
            
            //compare the selected user with the stored users
            //this will match the correct object to the user selected
            //only users can be selected for the user view. Not groups
            if(dataList != null){
                //find correct user object and pass to user view
                for(int i = 0; i < dataList.size(); i++){
                    if(dataList.get(i).getName().equals(value)){
                        tempUser = dataList.get(i);
                    }
                }
            }
            UserView.getUserView(tempUser, dataList);
        });
        
        //finding the user total
        Button userTotal = new Button();
        userTotal.setText("User Total");
        Label totalUsersLabel = new Label();
        userTotal.setOnAction(actionEvent -> {
            CountUserVisitor total = new CountUserVisitor();
            for(int i = 0; i < dataList.size(); i++){
                dataList.get(i).accept(total);
            }
            totalUsersLabel.setText("Total users: " + total.getUserTotal());
        });
        
        //finding the group total
        Label totalGroupsLabel = new Label();
        Button groupTotal = new Button();
        groupTotal.setText("Group Total");
        groupTotal.setOnAction(actionEvent -> {
            GroupTotalVisitor total = new GroupTotalVisitor();
            rootGroup.accept(total);
            totalGroupsLabel.setText("Total number of groups: " + total.getGroupTotal());
        });
        
        //finding the tweet total
        Button tweetTotal = new Button();
        tweetTotal.setText("Tweet Total");
        Label totalTweetsLabel = new Label();
        tweetTotal.setOnAction(actionEvent -> {
            TweetTotalVisitor total = new TweetTotalVisitor();
            for(int i = 0; i < dataList.size(); i++){
                dataList.get(i).accept(total);
            }
            totalTweetsLabel.setText("Total number of tweets: " + total.getTotalTweets());
        });
        
        //the positive percent checks the tweets of each user and looks for
        //key words
        Button positive = new Button();
        positive.setText("Positive Percent");
        Label posPercentLabel = new Label();
        positive.setOnAction(actionEvent -> {
            PercentVisitor positivePercent = new PercentVisitor();
            for(int i = 0; i < dataList.size(); i++){
                dataList.get(i).accept(positivePercent);
            }
            posPercentLabel.setText("Positive percent: " + positivePercent.getPositivePercent());
        });
        
        //verify the user or group name
        Button verify = new Button();
        verify.setText("Validate");
        verify.setOnAction(actionEvent -> {
            String groupName = groupText.getText();
            Group tempGroup = new Group(groupName);
            tempGroup.setName(groupName);
            
            String userName = userText.getText();
            User tempUser = new User();
            tempUser.setName(userName);
            
            if(rootGroup.containsGroup(groupName) || rootGroup.containsUser(userName)){
                infoAlert.setContentText("Invalid name. Already been used");
                infoAlert.showAndWait();
            }
            else if(groupName.contains(" ") || userName.contains(" ")){
                infoAlert.setContentText("Invalid name. Cannot have spaces.");
                infoAlert.showAndWait();
            }
            else if(!rootGroup.containsGroup(groupName) || !rootGroup.containsUser(userName)){
                infoAlert.setContentText("Valid name.");
                infoAlert.showAndWait();
            }
        });
        
        //find the person who last updated
        Button lastUpdated = new Button();
        lastUpdated.setText("Last Updated User");
        Label timeLabel = new Label();
        lastUpdated.setOnAction(actionEvent -> {
            LastUpdatedVisitor update = new LastUpdatedVisitor();
            for(int i = 0; i < dataList.size(); i++){
                dataList.get(i).accept(update);
            }
            timeLabel.setText("Last update made by: " + update.getLastUpdatee());
        });
        
        VBox tree = new VBox(treeView);
        HBox userSection = new HBox(addUser, userText);
        HBox groupSection = new HBox(addGroup, groupText);
        VBox vbox1 = new VBox(userSection, groupSection);
        HBox userTotals = new HBox(userTotal, totalUsersLabel);
        HBox groupTotals = new HBox(groupTotal, totalGroupsLabel);
        HBox tweetTotals = new HBox(tweetTotal, totalTweetsLabel);
        HBox percent = new HBox(positive, posPercentLabel);
        HBox time = new HBox(lastUpdated, timeLabel);
        HBox misc = new HBox(userView, verify);
        VBox vbox2 = new VBox(userTotals, groupTotals, tweetTotals, percent, time);
        VBox vBox = new VBox(vbox1,tree, misc, vbox2);
        panel = vBox;
    }
    
    public VBox getVBox(){
        return panel;
    }
}
