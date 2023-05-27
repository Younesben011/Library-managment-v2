package DatabaseManagment.verify;

import java.time.LocalDate;

public class Verify {
    private LocalDate Close_date;
    private boolean state;
    private LocalDate current_date;

    public Verify(boolean state) {
//        Close_date = ;
        this.state = state;
        this.current_date = LocalDate.now();
    }
}
