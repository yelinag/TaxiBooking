package taxibooking.service;

import taxibooking.dto.BookedDto;
import taxibooking.dto.BookingDto;

public interface BookingService {
    BookedDto bookTaxi(BookingDto requestBooking);
    void initializeAll();
}
