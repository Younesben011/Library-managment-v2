package DatabaseManagment.Inv;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Inv {
    String dateString = "2023-05-20";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date = LocalDate.parse(dateString, formatter);

    public LocalDate getCloseDate() {
        return date;
    }

}
