package cs3560hw2;

import java.util.*;

public class Group implements SysEntry{
    private int groupNum = 1;
    private String groupName;
    private long creationTime;
    private List<SysEntry> groups = new ArrayList<SysEntry>();
    
    public Group(String name){
        //this.groupName = name;
    }
    
    public void setName(String nom){
        groupName = nom;
    }
    
    @Override
    public String getName(){
        return this.groupName;
    }
    
    public void setCreationTime(){
        creationTime = System.currentTimeMillis();
    }
    
    public long getCreationTime(){
        return creationTime;
    }
    
    public void addToGroup(SysEntry newMember){
        this.groups.add(newMember);
    }
    
    public void accept(SysEntryVisitor visitor){
        visitor.visitGroup(this);
    }
    
    public void add(){
        groupNum++;
    }
    
    public int getGroupNum(){
        return groupNum;
    }
    
    public boolean containsGroup(String ID){
        for(SysEntry member: groups){
            if(member instanceof User){
                continue;
            }
            else if(member instanceof Group){
                if(member.getName().equals(ID)){
                    return true;
                }
                else{
                    if(((Group)member).containsGroup(ID)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean containsUser(String ID){
        for(SysEntry member: groups){
            if(member instanceof User){
                if(member.getName().equals(ID)){
                    return true;
                }
            }
            else if(member instanceof Group){
                if(((Group)member).containsUser(ID)){
                    return true;
                }
            }
        }
        return false;
    }
    
}
