package cs3560hw2;

import javafx.application.Application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.control.TextField;
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
        Button addUser = new Button();
        addUser.setText("Add User");
        TextField userText = new TextField();
        
        Button addGroup = new Button();
        addGroup.setText("Add Group");
        TextField groupText = new TextField();
        
        Button userView = new Button();
        userView.setText("User View");
        
        Button userTotal = new Button();
        userTotal.setText("User Total");
        
        Button groupTotal = new Button();
        groupTotal.setText("Group Total");
        
        Button tweetTotal = new Button();
        tweetTotal.setText("Tweet Total");
        
        Button positive = new Button();
        positive.setText("Positive Percent");
        
        Group rootGroup = new Group("root");
        TreeItem<SysEntry> root = new TreeItem<SysEntry>(rootGroup); //TreeItem goes into TreeView
        root.setExpanded(true);
        TreeView<SysEntry> treeView = new TreeView<SysEntry>(root);
        
        //Stores every instance of a user
        List<User> dataList = new ArrayList<User>();
        
        //assume that the user knows to enter a user name in groups
        //no adding users in other users
        addUser.setOnAction(actionEvent -> {
            //TreeItem<SysEntry> merges the class with the tree item
            //check the group selected to add the user to the selected group
            TreeItem<SysEntry> selected = treeView.getSelectionModel().getSelectedItem();
            String input = userText.getText();
            userText.clear();
            User tempUser = new User();
            tempUser.setName(input);
            //add the user to the selected group
            ((Group)selected.getValue()).addToGroup(tempUser);
            //add the user object to the stored list for future reference in user view
            dataList.add(tempUser);
            //add to tree view
            selected.getChildren().add(new TreeItem(tempUser.getName()));
        });
        
        addGroup.setOnAction(actionEvent -> {
            TreeItem<SysEntry> selected = treeView.getSelectionModel().getSelectedItem();
            String input = groupText.getText();
            Group tempGroup = new Group(input);
            groupText.clear();
            //a SysEntry type must be stored in order to add into the group
            ((Group)selected.getValue()).addToGroup(tempGroup);
            selected.getChildren().add(new TreeItem(tempGroup));
        });
        
        userView.setOnAction(actionEvent -> {
            TreeItem selected = (TreeItem)treeView.getSelectionModel().getSelectedItem();
            String value = (String)selected.getValue();
            System.out.println("value: " + value);
            User tempUser = new User();
            
            //compare the selected user with the stored users
            //this will match the correct object to the user selected
            //only users can be selected for the user view. Not groups
            System.out.print("Name: ");
            System.out.println(dataList.get(1).getName());
            if(dataList != null){
                //find correct user object and pass to user view
                for(int i = 0; i < dataList.size(); i++){
                    if(dataList.get(i).getName().equals(value)){
                        tempUser = dataList.get(i);
                    }
                }
            }
            System.out.println("temp user: " + tempUser.getName());
            UserView.getUserView(tempUser, dataList);
        });
        
        //finding the user total
        userTotal.setOnAction(actionEvent -> {
            CountUserVisitor total = new CountUserVisitor();
            rootGroup.accept(total);
            
            //display the total
        });
        
        groupTotal.setOnAction(actionEvent -> {
            GroupTotalVisitor total = new GroupTotalVisitor();
            //pass the tree and count the groups?
            rootGroup.accept(total);
            
            //display the toal
        });
        
        tweetTotal.setOnAction(actionEvent -> {
            TweetTotalVisitor total = new TweetTotalVisitor();
            rootGroup.accept(total);
            
            //display the total
        });
        
        positive.setOnAction(actionEvent -> {
            PercentVisitor positivePercent = new PercentVisitor();
            rootGroup.accept(positivePercent);
            
            //display the total
        });
        
        VBox tree = new VBox(treeView);
        HBox userSection = new HBox(addUser, userText);
        HBox groupSection = new HBox(addGroup, groupText);
        VBox vbox1 = new VBox(userSection, groupSection);
        HBox totals = new HBox(userTotal, groupTotal);
        HBox misc = new HBox(tweetTotal, positive);
        VBox vbox2 = new VBox(totals, misc);
        VBox vBox = new VBox(vbox1,tree, userView, vbox2);
        panel = vBox;
    }
    
    public VBox getVBox(){
        return panel;
    }
}