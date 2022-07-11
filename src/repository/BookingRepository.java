package repository;


import models.Booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO singleton
public class BookingRepository {

    private Map<String, Booking> bookingDetails;

    private static BookingRepository instance = null;

    public static BookingRepository getInstance(){
        if(instance == null){
            synchronized (BookingRepository.class){
                if(instance == null){
                    instance = new BookingRepository();
                }
            }
        }
        return instance;
    }


    private BookingRepository() {
        this.bookingDetails = new HashMap<>();
    }

    public void addBooking(Booking booking){
        this.bookingDetails.put(booking.getId(), booking);
    }

    public boolean checkVehicleSlotAvailability(String vehicleId, int startDate, int endDate){
        List<Booking> allBooking = this.bookingDetails.values().stream()
                .filter(booking -> booking.getVehicleId().equals(vehicleId)).collect(Collectors.toList());
        for(Booking booking: allBooking){
            if(!(startDate >= booking.getEndDate() || endDate <= booking.getStartDate()))
                return false;
        }
        return true;
    }

}
