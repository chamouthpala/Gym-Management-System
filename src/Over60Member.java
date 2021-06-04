import static java.lang.Integer.parseInt;

public class Over60Member extends DefaultMember {
    private String Member_Age;

    public String getMember_Age() {
        return Member_Age;
    }

    public void setMember_Age(String member_Age) {
        if (parseInt(Member_Age)>60) {
            Member_Age = member_Age;
        }else{throw new IllegalArgumentException("You can Add only Age >60 Members");}
    }
}
