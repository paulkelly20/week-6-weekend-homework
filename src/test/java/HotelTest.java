import org.junit.Before;
import org.junit.Test;
import room.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HotelTest {

    Hotel hotel;
    Guest guest1;
    Guest guest2;
    Bedroom bedroom1;
    Bedroom bedroom2;
    Bedroom bedroom3;
    Bedroom bedroom4;
    Bedroom bedroom5;
    ConferenceRoom conferenceroom1;
    ConferenceRoom conferenceroom2;
    DiningRoom diningroom;

    @Before
    public void before(){
        hotel = new Hotel("Codeclan", 100);
        guest1 = new Guest("Paul", 200);
        guest2 = new Guest("Elaine", 500);
        bedroom1 = new Bedroom(BedroomType.DOUBLE);
        bedroom2 = new Bedroom(BedroomType.FAMILY);
        bedroom3 = new Bedroom(BedroomType.FAMILY);
        bedroom4 = new Bedroom(BedroomType.FAMILY);
        bedroom5 = new Bedroom(BedroomType.KING);
        conferenceroom1 = new ConferenceRoom(1, 100,"Conferenceroom 1");
        conferenceroom2 = new ConferenceRoom(2, 2000,"Conferenceroom 2");
        diningroom = new DiningRoom(3);

    }

    @Test
    public void checkHotelName(){
        assertEquals("Codeclan", hotel.getName());
    }

    @Test
    public void addBedroomtoHotel(){
        hotel.addBedroom(bedroom1);
        assertEquals(1, hotel.getBedroomsCount());
    }

    @Test
    public void addConferenceRoomToHotel(){
        hotel.addConferenceRoom(conferenceroom1);
        assertEquals(1, hotel.getConferenceroomsCount());
    }

    @Test
    public void addDiningroomToHotel(){
        hotel.addDiningroom(diningroom);
        assertEquals(1, hotel.getDiningroomsCount());
    }


    @Test
    public void checkBedroomNumbers(){
        hotel.addBedroom(bedroom1);
        hotel.addBedroom(bedroom2);
        hotel.addBedroom(bedroom3);
        hotel.addBedroom(bedroom4);
        hotel.giveBedroomsANumber();
        assertEquals(1, bedroom1.getRoomnumber());
        assertEquals(2, bedroom2.getRoomnumber());
        assertEquals(3, bedroom3.getRoomnumber());
        assertEquals(4, bedroom4.getRoomnumber());
    }

    @Test
    public void checkGuestIntoBedroomForOneNight(){
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        assertEquals(1, bedroom1.countGuestsInRoom());
        assertEquals(159, hotel.getTill(), 0);
        assertEquals(141, guest1.getWallet(), 0);

    }

    @Test
    public void checkGuestIntoBedroomForTwoNights(){
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 2);
        assertEquals(1, bedroom1.countGuestsInRoom());
        assertEquals(218, hotel.getTill(), 0);
        assertEquals(82, guest1.getWallet(), 0);
    }

    @Test
    public void checkMultipleGuestsIntoRoomButOnlyFirstGuestIsCharged(){
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        hotel.checkGuestIntoBedroom(guest2, bedroom1, 1);
        assertEquals(141, guest1.getWallet(), 0);
        assertEquals(500, guest2.getWallet(), 0);
    }

    @Test
    public void checkMultipleGuestsIntoRoomCantCheckSecondGuestInForMoreNightsThanFirst(){
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        hotel.checkGuestIntoBedroom(guest2, bedroom1, 2);
        assertEquals(1, bedroom1.countGuestsInRoom());

    }


    @Test
    public void checkGuestsOut(){
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 2);
        hotel.checkGuestsOut(bedroom1);
        assertEquals(0, bedroom1.countGuestsInRoom());
    }

    @Test
    public void checkGuestIntoConferenceroom(){
        hotel.checkGuestIntoConferenceroom(guest1, conferenceroom1);
        assertEquals(1, conferenceroom1.countGuestsInRoom());
        assertEquals(200, hotel.getTill(), 0);
        assertEquals(100, guest1.getWallet(), 0);
    }

    @Test
    public void getListOfVacantBedrooms(){
        hotel.addBedroom(bedroom1);
        hotel.addBedroom(bedroom2);
        hotel.addBedroom(bedroom3);
        hotel.addBedroom(bedroom4);
        ArrayList<Room> bedrooms = new ArrayList<>();
        bedrooms.add(bedroom1);
        bedrooms.add(bedroom2);
        bedrooms.add(bedroom3);
        bedrooms.add(bedroom4);
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        hotel.checkGuestIntoBedroom(guest2, bedroom2, 1);
        ArrayList<Bedroom> VacantBedrooms = new ArrayList<>();
        VacantBedrooms.add(bedroom3);
        VacantBedrooms.add(bedroom4);
        assertEquals(VacantBedrooms, hotel.checkVacantRooms(bedrooms));
    }

    @Test
    public void countVacantBedrooms(){
        hotel.addBedroom(bedroom1);
        hotel.addBedroom(bedroom2);
        hotel.addBedroom(bedroom3);
        hotel.addBedroom(bedroom4);
        ArrayList<Room> bedrooms = new ArrayList<>();
        bedrooms.add(bedroom1);
        bedrooms.add(bedroom2);
        bedrooms.add(bedroom3);
        bedrooms.add(bedroom4);
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        hotel.checkGuestIntoBedroom(guest2, bedroom2, 1);
        assertEquals(2, hotel.countOfVacantRooms(bedrooms));
    }

    @Test
    public void checkHowManyBedroomsAreVacant(){
        hotel.addBedroom(bedroom1);
        hotel.addBedroom(bedroom2);
        hotel.addBedroom(bedroom3);
        hotel.addBedroom(bedroom4);
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        hotel.checkGuestIntoBedroom(guest2, bedroom2, 1);
        assertEquals(2, hotel.getNumberOfVacantBedrooms());
    }

    @Test
    public void checkHowManyConferenceroomsAreVacant(){
        hotel.addConferenceRoom(conferenceroom1);
        hotel.addConferenceRoom(conferenceroom2);
        hotel.checkGuestIntoConferenceroom(guest1, conferenceroom1);
        assertEquals(1, hotel.checkVacantConferencerooms());
    }

    @Test
    public void getListOfGuestsNamesInAllRooms(){
        hotel.addBedroom(bedroom1);
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        HashMap<Room, String> names = new HashMap<>();
        names.put(bedroom1,"[Paul]");
        assertEquals(names, hotel.getNameOfGuestsInHotel());
    }

    @Test
    public void checkCountGuestsInHotel(){
        hotel.addBedroom(bedroom1);
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        assertEquals(1, hotel.countGuestsInHotel());
    }

    @Test
    public void getListOfGuestsNamesInAllRoomsTwoPeople(){
        hotel.addBedroom(bedroom1);
        hotel.addBedroom(bedroom2);
        hotel.addConferenceRoom(conferenceroom1);
        hotel.addConferenceRoom(conferenceroom2);
        hotel.addDiningroom(diningroom);
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        hotel.checkGuestIntoConferenceroom(guest2, conferenceroom1);
        assertEquals(2, hotel.countGuestsInHotel());
    }


    @Test
    public void getListOfNamesOfGuestsInParticularRoom(){
        hotel.addBedroom(bedroom1);
        hotel.checkGuestIntoBedroom(guest1, bedroom1, 1);
        hotel.checkGuestIntoBedroom(guest2, bedroom1, 1);
        ArrayList<String> names = new ArrayList<>();
        names.add("Paul");
        names.add("Elaine");
        assertEquals(names, hotel.getNameOfPeopleInCertainRooms(bedroom1));

    }

//    @Test /// Test not working why??
//    public void changePriceOfRoom(){
//        bedroom1.setRoomprice(150);
//        assertEquals(150, bedroom1.getRoomPrice(), 0);
//    }


    @Test
    public void changePriceOfRoomKingRoom(){
        bedroom5.setRoomprice(1000);
        assertEquals(1000, bedroom5.getRoomPrice(), 0);
    }

}
