package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

class Test03ReturningCustomValues {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	@BeforeEach
	void setup() {
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);

		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

		System.out.println("List returned " + roomServiceMock.getAvailableRooms());
		System.out.println("Object returned " + roomServiceMock.findAvailableRoomId(null));
		System.out.println("Primitive returned " + roomServiceMock.getRoomCount());

	}

	
	@Test
	void should_CountAvailablePlaces_When_MultipleRoomAvailable() {
		// given
		List<Room> rooms = Arrays.asList(new Room("Room1", 2),new Room("Room2", 7));
		when(this.roomServiceMock.getAvailableRooms())
		.thenReturn(rooms)
		.thenReturn(Collections.emptyList());
		int expected1 = 9;
		int expected2 = 0;
		// when
		int actual1 = bookingService.getAvailablePlaceCount();
		int actual2 = bookingService.getAvailablePlaceCount();
		// then
		assertAll(
				()->assertEquals(expected1, actual1),
				()->assertEquals(expected2, actual2)
				);
		
	}

}
