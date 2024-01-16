package school.faang.user_service.controller.mentorship;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.faang.user_service.service.mentorship.MentorshipService;

@RestController
@RequestMapping("/mentorship")
@RequiredArgsConstructor
public class MentorshipController {
    private final MentorshipService mentorshipService;

    @DeleteMapping("/mentee")
    public void deleteMentee(@RequestParam @Positive long mentorId, @RequestParam @Positive long menteeId) {
        if (mentorId == menteeId) {
            throw new UnsupportedOperationException("You cannot delete the mentee-user who is the same as the mentor-user.");
        }
        mentorshipService.deleteMentee(mentorId, menteeId);
    }
}