package cs3560hw2;

//child to SysEntryVisitor
public class GroupTotalVisitor implements SysEntryVisitor {
    private int groupTotal;
    
    public void visitUser(User user){
        //not needed for the group total
    }
    
    public void visitGroup(Group group){
        if(group instanceof Group){
            setGroupTotal(group.getGroupNum());
        }
    }
    
    public int getGroupTotal(){
        return groupTotal;
    }
    
    public void setGroupTotal(int total){
        this.groupTotal = total;
    }
}
