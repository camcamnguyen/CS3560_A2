package cs3560hw2;

import java.util.*;

//child to SysEntryVisitor
public class PercentVisitor implements SysEntryVisitor{
    private double positiveCount;
    private double totalTweets;
    private double positivePercent;
    private List<String> keyWords = new ArrayList<String>(Arrays.asList("inspirational", "motivating"));
    
    public void visitUser(User user){
        //need to access the user's tweets and check each one
        for(String message: user.getTotalTweets()){
            totalTweets += 1;
            for(String p: keyWords){
                if(message.toLowerCase().contains(p.toLowerCase())){
                    positiveCount += 1;
                    break;
                }
            }
        }
    }
    
    public void visitGroup(Group group){
        //do not add to count
        //groups are not needed
    }
    
    public void setPositivePercent(double positivePercent){
        this.positivePercent = positivePercent;
    }
    
    public double getPositivePercent(){
        setPositivePercent((positiveCount/totalTweets) * 100);
        return positivePercent;
    }
}
