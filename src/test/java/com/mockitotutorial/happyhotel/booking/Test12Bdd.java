package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Test12Bdd {
	
	@InjectMocks
	private BookingService bookingService;
	
	@Mock
	private PaymentService paymentServiceMock;
	@Mock
	private RoomService roomServiceMock;
	@Mock
	private BookingDAO bookingDAOMock;
	@Mock
	private MailSender mailSenderMock;
	
	@Captor
	private ArgumentCaptor<Double> doubleCaptor;
	
	@Captor
	private ArgumentCaptor<BookingRequest> bookingRequestCaptor;

	
	@Test
	void should_CountAvailablePlaces_When_OneRoomAvailable() {
		// given
		given(this.roomServiceMock.getAvailableRooms()).willReturn(Collections.singletonList(new Room("Room1", 2)));
		int expected = 2;
		// when
		int actual = bookingService.getAvailablePlaceCount();
		// then
		assertEquals(expected, actual);
	}
	
	@Test
	void should_InvokePaymeny_When_Prepaid() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),2, true);
		
		//when
		bookingService.makeBooking(bookingRequest);
		//then
		then(paymentServiceMock).should(times(1)).pay(bookingRequest, 400.0);
		verifyNoMoreInteractions(paymentServiceMock);
	}
	

	


}
