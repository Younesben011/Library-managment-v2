package DatabaseManagment.Issue;

import java.time.LocalDate;

public class IssueBook {
    int issue_id;
    int library_num ;
    int member_id;
    int copy_id;
    LocalDate issue_date;
    LocalDate return_date;
    LocalDate real_return_date;
    boolean notify;

    public IssueBook(int issue_id, int library_num, int member_id, int copy_id, LocalDate issue_date, LocalDate return_date, LocalDate real_return_date,boolean notify) {
        this.issue_id = issue_id;
        this.library_num = library_num;
        this.member_id = member_id;
        this.copy_id = copy_id;
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.real_return_date = real_return_date;
        this.notify =notify;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public IssueBook(int issue_id, int library_num, int member_id, int copy_id, LocalDate issue_date, LocalDate return_date) {
        this.issue_id = issue_id;
        this.library_num = library_num;
        this.member_id = member_id;
        this.copy_id = copy_id;
        this.issue_date = issue_date;
        this.return_date = return_date;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public int getLibrary_num() {
        return library_num;
    }

    public void setLibrary_num(int library_num) {
        this.library_num = library_num;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getCopy_id() {
        return copy_id;
    }

    public void setCopy_id(int copy_id) {
        this.copy_id = copy_id;
    }

    public LocalDate getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(LocalDate issue_date) {
        this.issue_date = issue_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public LocalDate getReal_return_date() {
        return real_return_date;
    }

    public void setReal_return_date(LocalDate real_return_date) {
        this.real_return_date = real_return_date;
    }
}
