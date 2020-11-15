package cs3560hw2;

//child to SysEntryVisitor
public class CountUserVisitor implements SysEntryVisitor{
    private int totalUsers;
    
    public void visitUser(User user){
        //implement the counting process
        if(user instanceof User){
            setUserTotal(getUserTotal() + 1);
        }
    }
    
    public void visitGroup(Group group){
        //dont add to count
    }
    
    public int getUserTotal(){
        return totalUsers;
    }
    
    public void setUserTotal(int total){
        this.totalUsers = total;
    }
}
