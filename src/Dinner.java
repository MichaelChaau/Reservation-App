public class Dinner extends Reservation {
    int year;
    int month;
    int day;
    int hour;
    int minute;

    public Dinner(int year, int month, int day, int hour, int minute, String description) {
        super(hour, minute, description);
        System.out.println("Please enter the information for the reservation.");
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public boolean occursOn(int year, int month, int day, int hour, int minute) {
        return year == this.year &&
                month == this.month &&
                day == this.day &&
                hour == this.hour &&
                minute == this.minute;
    }
    @Override
    public String toString() {
        return description + " created for " + year + "/" + month + "/" + day + "/" + hour + ":" + minute;
    }
}