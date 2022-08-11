package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

class Test04MultipleThenReturnValues {

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
	void should_CountAvailablePlaces_When_OneRoomAvailable() {
		// given
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room1", 2)));
		int expected = 2;
		// when
		int actual = bookingService.getAvailablePlaceCount();
		// then
		assertEquals(expected, actual);
	}

	@Test
	void should_CountAvailablePlaces_When_MultipleRoomAvailable() {
		// given
		List<Room> rooms = Arrays.asList(new Room("Room1", 2),new Room("Room2", 7));
		when(this.roomServiceMock.getAvailableRooms())
		.thenReturn(rooms);
		int expected = 9;
		// when
		int actual = bookingService.getAvailablePlaceCount();
		// then
		assertEquals(expected, actual);
	}

}
