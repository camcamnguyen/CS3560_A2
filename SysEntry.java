package cs3560hw2;

//interface to user and group class
public interface SysEntry {
    public String getName();
    public void accept(SysEntryVisitor visitor);
}
