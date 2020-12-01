package cs3560hw2;

public class LastUpdatedVisitor implements SysEntryVisitor{
    private String lastUpdate;
    private long temp = 0;
    
    @Override
    public void visitUser(User user){
        if(temp < user.getMyUpdateTime()){
            temp = user.getMyUpdateTime();
            setLastUpdate(user.getName());
        }
    }
    
    public void visitGroup(Group group){
        
    }
    
    public void setLastUpdate(String name){
        lastUpdate = name;
    }
    
    public String getLastUpdatee(){
        return lastUpdate;
    }
}
