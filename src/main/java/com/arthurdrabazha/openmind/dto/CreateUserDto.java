package com.arthurdrabazha.openmind.dto;

import com.arthurdrabazha.openmind.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Username should be set")
    private String username;

    @Email
    private String email;

    @Size(min = 8, max = 255)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,255}$",
            message = "Password should contain at least one upper and lower case character, " +
                    "digit and special character.")
    private String password;

    @NotBlank(message = "Password should be repeated")
    private String passwordRepeat;

    @NotNull(message = "Birth date should be set")
    @Past(message = "Birth date is invalid")
    private LocalDate birthDate;

    @Size(
            min = 1,
            message = "Select at least one category, so we could know what content you're interested in"
    )
    private List<Category> categories;

    private List<NonActivePeriod> nonActivePeriods;

    @NotNull
    private NonActivePeriod nonActivePeriod;

    public Integer getDaysBeforeDelete() {
        int daysBeforeDelete = 0;

        if (nonActivePeriod == NonActivePeriod.TEN_DAYS) {
            daysBeforeDelete = 10;
        } else if (nonActivePeriod == NonActivePeriod.ONE_MONTH) {
            daysBeforeDelete = 30;
        } else if (nonActivePeriod == NonActivePeriod.THREE_MONTH) {
            daysBeforeDelete = 90;
        } else if (nonActivePeriod == NonActivePeriod.SIX_MONTH) {
            daysBeforeDelete = 180;
        } else if (nonActivePeriod == NonActivePeriod.ONE_YEAR) {
            daysBeforeDelete = 365;
        }

        return daysBeforeDelete;
    }
}
