package cs3560hw2;
import java.util.*;

//abstract class to user class
public abstract class Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    
    public void attach(Observer observer){
        observers.add(observer);
    }
    
    public void notifyObserver(String message){
        for(Observer observer: this.observers){
            observer.updateTweets(this, message); //this refers to the suject
        }
    }
    
    public void notifyTimeChange(long time){
        //notify each follower of the last updated time
        for(Observer observer: this.observers){
            observer.updateTime(this, time);
        }
    }
}
