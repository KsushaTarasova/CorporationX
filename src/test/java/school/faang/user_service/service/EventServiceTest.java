package school.faang.user_service.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.faang.user_service.entity.event.Event;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.repository.event.EventRepository;
import school.faang.user_service.service.event.EventService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private EventService eventService;

    @Test
    @DisplayName("Успешное удаление события по верному Id")
    public void testSuccessDeleteEventById() {
        Event eventDelete = Event.builder().id(5L).maxAttendees(5).build();
        long eventId = eventDelete.getId();

        Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(eventDelete));

        eventService.deleteEvent(eventId);

        Mockito.verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    @DisplayName("Неуспешное удаление события по неверному Id")
    public void testFailedDeleteEventByIncorrectId() {
        long wrongId = 15L;

        Mockito.when(eventRepository.findById(wrongId)).thenReturn(Optional.empty());

        assertThrows(DataValidationException.class, () -> eventService.deleteEvent(wrongId));

        Mockito.verify(eventRepository, Mockito.never()).deleteById(wrongId);
    }

}
