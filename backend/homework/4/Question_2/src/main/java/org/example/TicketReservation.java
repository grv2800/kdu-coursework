package org.example;
import java.util.*;
public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();
    public void setConfirmedlist(List<Passenger> confirmedList){
        this.confirmedList=confirmedList;
    }
    public List<Passenger> getConfirmedList(){
        return confirmedList;
    }
    public void setWaitingList(Deque<Passenger> waitingList){
        this.waitingList=waitingList;
    }
    public Deque<Passenger> getWaitingList(){return waitingList;}
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass,String confirmationNumber) {

        Passenger passenger=new Passenger(firstName,lastName,age,gender,travelClass,confirmationNumber);

        if ( (travelClass.equals("business") || travelClass.equals("economy")) && (confirmationNumber.matches("[a-zA-Z0-9]+"))){
            if (confirmedList.size() < CONFIRMEDLIST_LIMIT) {
                confirmedList.add(passenger);
                return true;
            } else if (waitingList.size() < WAITINGLIST_LIMIT) {
                waitingList.add(passenger);
                return true;
            }
        }
        return false;
    }

    public boolean cancel(String confirmationNumber) {
        Iterator<Passenger> confirmedListIterator=confirmedList.iterator();
        boolean removeConfirmedPassenger=removePassenger(confirmedListIterator,confirmationNumber);
        if(removeConfirmedPassenger) {
            if (!waitingList.isEmpty()) {
                Passenger waitingPassenger = waitingList.poll();
                confirmedList.add(waitingPassenger);
                }
            return true;
        }
        else {
            Iterator<Passenger> waitingListIterator=waitingList.iterator();
            return  removePassenger(waitingListIterator,confirmationNumber);
        }
    }

    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
       while (iterator.hasNext()){
           Passenger passenger=iterator.next();
           if(passenger.getConfirmationNumber().equals(confirmationNumber)){
               iterator.remove();
               return true;
           }
       }
       return false;
    }
}
