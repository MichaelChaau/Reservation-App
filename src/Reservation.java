
public abstract class Reservation {
    int hour;
    int minute;
    String description;

    /**
     * Create reservation
     * @param description String
     * @param hour int
     * @param minute int
     */
    public Reservation(int hour, int minute,String description) {

        this.hour = hour;
        this.minute = minute;
        this.description = description;
    }
    /**
     Get hour
     @return double
     */
    public Double getHour() {
        return (double) hour;
    }

    /**
     Get minute
     @return double
     */
    public Double getMinute() {
        return (double) minute;
    }

    /**
     Get description
     @return String
     */
    public String getDescription() {

        return description;
    }

    /**
     * Set description
     * @param hour int
     */
    public void setHour(int hour) {

        this.hour = hour;
    }

    /**
     * Set description
     * @param minute int
     */
    public void setMinute(int minute) {

        this.minute = minute;
    }

    /**
     * Set description
     * @param description String
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * Returns whether an reservation occurs on a given date
     * @param year int
     * @param month int
     * @param day int
     * @param hour int
     * @param minute int
     * @return boolean
     */
   public abstract boolean occursOn(int year, int month, int day, int hour, int minute);
}
