package cs3560hw2;

import java.util.*;

public class Group implements SysEntry{
    private String groupName;
    private List<SysEntry> groups = new ArrayList<SysEntry>();
    
    public Group(String name){
        groupName = name;
    }
    
    public String getName(){
        return groupName;
    }
    
    public void addToGroup(SysEntry newMember){
        groups.add(newMember);
    }
    
    public void accept(SysEntryVisitor visitor){
        visitor.visitGroup(this);
    }
}
