public class Date {
    private String Full_Date;
    private Integer Starting_Year;
    private Integer Starting_Month;
    private Integer Stating_Date;

    public String getFull_Date() {
        return Starting_Year+ "/" +Starting_Month+ "/" +Stating_Date;
    }

    public void setFull_Date(String full_Date) {
        Full_Date = full_Date;
    }

    public Integer getStarting_Year() {
        return Starting_Year;
    }

    public void setStarting_Year(Integer starting_Year) {
        this.Starting_Year = starting_Year;
    }

    public Integer getStarting_Month() {
        return Starting_Month;
    }

    public void setStarting_Month(Integer starting_Month) {
        if ((starting_Month > 0) && (starting_Month < 13)) {
            this.Starting_Month = starting_Month;
        } else{
        throw new IllegalArgumentException("invalid Input,only 12 months in the Year");
    }

    }

    public Integer getStating_Date() {
        return Stating_Date;
    }

    public void setStating_Date(Integer stating_Date) {
        if ((stating_Date > 0) && (stating_Date < 21)) {
            this.Stating_Date = stating_Date;
        } else {
            throw new IllegalArgumentException("invalid Input,you can add the member only first 3 weeks on the month");
        }
    }



}


