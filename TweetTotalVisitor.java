package cs3560hw2;

//child to SysEntryVisitor
//should go through every user and check their tweets
public class TweetTotalVisitor implements SysEntryVisitor {
    private int totalTweets;
    
    public void visitUser(User user){
        setTotalTweets(getTotalTweets() + user.getTotalTweets().size());
    }
    
    public void visitGroup(Group group){
        //do not add to total count
    }
    
    public void setTotalTweets(int total){
        this.totalTweets = total;
    }
    
    public int getTotalTweets(){
        return totalTweets;
    }
    
}
