package room;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class Hotel {
    private String name;
    private double till;
    private ArrayList<Bedroom> bedrooms;
    private ArrayList<ConferenceRoom> conferencerooms;
    private ArrayList<DiningRoom> diningrooms;

    public Hotel(String name, double till) {
        this.name = name;
        this.till = till;
        this.bedrooms = new ArrayList<>();
        this.conferencerooms = new ArrayList<>();
        this.diningrooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getTill() {
        return till;
    }

    public int getBedroomsCount() {
        return bedrooms.size();
    }

    public int getConferenceroomsCount() {
        return conferencerooms.size();
    }

    public int getDiningroomsCount() {
        return diningrooms.size();
    }

    public void addBedroom(Bedroom bedroom) {
        bedrooms.add(bedroom);
    }

    public double bedroomPrice(){
        return bedroomPrice();
    }

    public double conferenceRoomCost(){
        return conferenceRoomCost();
    }

    public void addConferenceRoom(ConferenceRoom conferenceroom) {
        conferencerooms.add(conferenceroom);
    }

    public void addDiningroom(DiningRoom diningroom){
        diningrooms.add(diningroom);
    }

    public void giveBedroomsANumber() {
        int i = 1;
        for (Bedroom bedroom : this.bedrooms) {
              bedroom.setRoomnumber(i); i++ ;

        }

    }

    public void checkGuestIntoBedroom(Guest guest, Bedroom bedroom, int nights) {
        bedroom.addGuestToRoom(guest);
        till += (bedroom.roomPrice() * nights);
        guest.payforRoom(bedroom.roomPrice() * nights) ;
    }

    public void checkGuestIntoConferenceroom(Guest guest, ConferenceRoom conferenceroom) {
        conferenceroom.addGuestToRoom(guest);
        till += conferenceroom.roomPrice();
        guest.payforRoom(conferenceroom.roomPrice());
    }


    public int checkVacantBedrooms(){
        ArrayList<Bedroom> emptyrooms = new ArrayList<>();
        for (Bedroom bedroom : this.bedrooms) {
            if (bedroom.countGuestsInRoom() == 0){
            emptyrooms.add(bedroom);}
            }  return emptyrooms.size();
    }


    public int checkVacantConferencerooms() {
        ArrayList<ConferenceRoom> emptyrooms = new ArrayList<>();
        for (ConferenceRoom conferenceroom : this.conferencerooms) {
            if (conferenceroom.countGuestsInRoom() == 0){
                emptyrooms.add(conferenceroom);}
        }  return emptyrooms.size();
    }

    public void checkGuestsOut(Room room) {
        room.checkOutGuestsFromRoom();
    }


    public ArrayList getNameOfGuestsInHotel() {
        ArrayList<String> allguests = new ArrayList<>();

        ArrayList<Room> allrooms = new ArrayList<>();

        for(Bedroom bedroom: this.bedrooms){
            allrooms.add(bedroom);
        }
        for(ConferenceRoom conferenceroom: this.conferencerooms){
            allrooms.add(conferenceroom);

        }

        for(DiningRoom diningroom: this.diningrooms){
            allrooms.add(diningroom);

        } for(Room room : allrooms){allguests.add(room.getNamesOfPeopleInRooms().toString());}

        return allguests;
    }
}

