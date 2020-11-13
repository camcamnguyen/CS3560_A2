package cs3560hw2;

//a separate hirearchy for visitor instances
public interface SysEntryVisitor {
    public void visitUser(User user);
    public void visitGroup(Group group);
}
