import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import org.bson.Document;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGymManager {
    static Scanner scanner = new Scanner(System.in);
    //------------------Create db connection(PP_2_CW_2)---------------------------------------------------
    static MongoClient mongoClient = new MongoClient("localhost", 27017);
    static MongoDatabase database = mongoClient.getDatabase("PP_2_CW_2");
    static int membercount;

    public static void main(String[] args) {
        Logger errorfix = Logger.getLogger("org.mongodb.driver");
        errorfix.setLevel(Level.SEVERE);

        //--------------------Selecting Oparation------------------------------------------------------------

        while (true) {
            System.out.print("Select your Oparation (Member_Add,Member_Delete,Print_List,Save_File,Sort_File,GUI):  ");
            Scanner scanner = new Scanner(System.in);
            String operation = scanner.next();
            if (database.getCollection("Body_Builders") == null) {
                database.createCollection("Body_Builders");
            }

            //-------------------------Access the collection---------------------------------------------

            MongoCollection collection = database.getCollection("Body_Builders");
            membercount = (int) collection.countDocuments();
            System.out.println("There are  " + membercount + " ,Members in the date base");

            //------------------------------Progress----------------------------------------------------------

            if (membercount <= 100) {
                if (operation.equals("Member_Add")) {
                    System.out.println("you can add members only first 3 weeks only");
                    System.out.print("Select MemberShip Type (DefaultMember,StudentMember,Over60Member): ");
                    Scanner member = new Scanner(System.in);
                    String Member_Operation = member.next();

                    //-------------------------DefaultMember Inputs-------------------------------------------

                    if (Member_Operation.equals("DefaultMember")) {
                        Default_Member();

                        //----------------------------StudentMember Inputs----------------------------------------

                    } else if (Member_Operation.equals("StudentMember")) {
                        Student_Member();


                        //----------------------Over60Member Inputs----------------------------------------------------

                    } else if (Member_Operation.equals("Over60Member")) {
                        Over_60_Member();


                    } else {
                        System.out.println("Invalid Input");

                    }

                    //--------------------------Member_Delete Function--------------------------------------------

                } else if (operation.equals("Member_Delete")) {
                    Delete_Member();

                    //-------------------------Print_List operation----------------------------------------------------------

                } else if (operation.equals("Print_List")) {
                    Print_The_List();

                    //---------------------------Save_File Operation---------------------------------------------------------

                } else if (operation.equals("Save_File")) {
                    Save_File();
                    //--------------------------Sort_File---------------------------------------------------------------------
                } else if (operation.equals("Sort_File")) {
                    Sort_File();

                } else if (operation.equals("GUI")) {

                    try {
                        Application.launch(UserInterface.class);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else
                    System.out.println("Invalid Input");

            }
        }

    }

    static void Default_Member() {
        while (true) {
            System.out.println("Enter New Member First Name : ");
            String member_fname = scanner.next();
            System.out.println("Enter New Member Last Name : ");
            String member_lname = scanner.next();
            System.out.println("Enter New Member Id : ");
            Integer member_id = scanner.nextInt();
            System.out.println("Enter Membership Date(YYYY/MM/DD) : ");
            String member_date = scanner.next();
            Date membership_date = new Date();
            try {
                membership_date.setStarting_Year(Integer.valueOf((member_date.substring(0, 4))));
                membership_date.setStarting_Month(Integer.valueOf((member_date.substring(5, 7))));
                membership_date.setStating_Date(Integer.valueOf((member_date.substring(8, 10))));
            } catch (Exception error) {
                error.printStackTrace();
                continue;

            }
            DefaultMember defaultMember = new DefaultMember();
            defaultMember.setMember_Id(member_id);
            defaultMember.setMember_fname(member_fname);
            defaultMember.setMember_lname(member_lname);
            defaultMember.setMember_date(membership_date.getFull_Date());

            //-------------------create a collection(Body_Builders)---------------------------------------------------

            if (database.getCollection("Body_Builders") == null) {
                database.createCollection("Body_Builders");
            }

            //-------------------------Access the collection---------------------------------------------

            MongoCollection collection = database.getCollection("Body_Builders");

            //------------------------Create a document in the collection-----------------------------------

            Document document1 = new Document();
            document1.put("First Name", defaultMember.getMember_fname());
            document1.put("Last Name", defaultMember.getMember_lname());
            document1.put("Member Id", defaultMember.getMember_Id());
            document1.put("Membership Date", defaultMember.getMember_date());
            document1.put("Member Type", "Default Member");


            collection.insertOne(document1);
            break;
        }

    }

    static void Student_Member() {
        while (true) {
            System.out.println("Enter New Member First Name : ");
            String member_fname = scanner.next();
            System.out.println("Enter New Member Last Name : ");
            String member_lname = scanner.next();
            System.out.println("Enter New Member Id : ");
            Integer member_id = scanner.nextInt();
            System.out.println("Enter Member School Name : ");
            String member_school = scanner.next();
            System.out.println("Enter Membership Date (YYYY/MM/DD) : ");
            String member_date = scanner.next();
            Date membership_date = new Date();
            try {
                membership_date.setStarting_Year(Integer.valueOf((member_date.substring(0, 4))));
                membership_date.setStarting_Month(Integer.valueOf((member_date.substring(5, 7))));
                membership_date.setStating_Date(Integer.valueOf((member_date.substring(8, 10))));
            } catch (Exception error) {
                error.printStackTrace();
                continue;
            }

            StudentMember studentMember = new StudentMember();
            studentMember.setMember_Id(member_id);
            studentMember.setMember_fname(member_fname);
            studentMember.setMember_lname(member_lname);
            studentMember.setMember_date(member_date);
            studentMember.setMember_School(member_school);

            //-------------------create a collection(Body_Builders)---------------------------------

            if (database.getCollection("Body_Builders") == null) {
                database.createCollection("Body_Builders");
            }

            //-------------------------Access the collection---------------------------------------------

            MongoCollection collection = database.getCollection("Body_Builders");


            //------------------------Create a document in the collection--------------------------

            Document document1 = new Document();
            document1.put("First Name", studentMember.getMember_fname());
            document1.put("Last Name", studentMember.getMember_lname());
            document1.put("Member Id", studentMember.getMember_Id());
            document1.put("Membership Date", studentMember.getMember_date());
            document1.put("Member School", studentMember.getMember_School());
            document1.put("Member Type", "Student Member");


            collection.insertOne(document1);
            break;
        }
    }


    static void Over_60_Member() {
        while (true) {
            System.out.println("Enter New Member First Name : ");
            String member_fname = scanner.next();
            System.out.println("Enter New Member Last Name : ");
            String member_lname = scanner.next();
            System.out.println("Enter New Member Id : ");
            Integer member_id = scanner.nextInt();
            System.out.println("Enter Member Age : ");
            String member_age = scanner.next();
            System.out.println("Enter Membership Date (YYYY/MM/DD): ");
            String member_date = scanner.next();
            Date membership_date = new Date();
            try {
                membership_date.setStarting_Year(Integer.valueOf((member_date.substring(0, 4))));
                membership_date.setStarting_Month(Integer.valueOf((member_date.substring(5, 7))));
                membership_date.setStating_Date(Integer.valueOf((member_date.substring(8, 10))));
            } catch (Exception error) {
                error.printStackTrace();
                continue;
            }

            Over60Member over60member = new Over60Member();
            over60member.setMember_Id(member_id);
            over60member.setMember_fname(member_fname);
            over60member.setMember_lname(member_lname);
            over60member.setMember_date(member_date);
            over60member.setMember_Age(member_age);

            //-------------------create a collection(Body_Builders)---------------------------------------------------

            if (database.getCollection("Body_Builders") == null) {
                database.createCollection("Body_Builders");
            }

            //-------------------------Access the collection---------------------------------------------

            MongoCollection collection = database.getCollection("Body_Builders");

            //------------------------Create a document in the collection-----------------------------------

            Document document1 = new Document();
            document1.put("First Name", over60member.getMember_fname());
            document1.put("Last Name", over60member.getMember_lname());
            document1.put("Member Id", over60member.getMember_Id());
            document1.put("Membership Date", over60member.getMember_date());
            document1.put("Member age", over60member.getMember_Age());
            document1.put("Member Type ", "Over 60 Member");


            collection.insertOne(document1);
            break;
        }

    }
    static void Delete_Member(){
        System.out.println("Enter Delete Id : ");
        String member_del_id = scanner.next();

        //-------------------create a collection(Body_Builders)----------------------------------------------

        if (database.getCollection("Body_Builders") == null) {
            database.createCollection("Body_Builders");
        }

        //-------------------------Access the collection---------------------------------------------

        MongoCollection collection = database.getCollection("Body_Builders");
        Document selected_Id = new Document();
        selected_Id.put("Member Id",member_del_id);
        FindIterable<Document> findIterable = collection.find(selected_Id);
        for (Document new_document:findIterable){
            System.out.println(new_document.get("Member Type"));
        }
        Document doc_del = new Document();
        doc_del.put("Member Id", member_del_id);
        collection.deleteOne(doc_del);
        membercount=(int) collection.countDocuments();
        System.out.println("Now  "+(membercount-1)+" Members in the database\n");
    }

    static void Print_The_List() {
        //-------------------create a collection(Body_Builders)----------------------------------------------

        if (database.getCollection("Body_Builders") == null) {
            database.createCollection("Body_Builders");
        }

        //-------------------------Access the collection---------------------------------------------

        MongoCollection collection = database.getCollection("Body_Builders");

        FindIterable<Document> findIterable = collection.find();
        for (Document doc : findIterable) {
            System.out.println(doc);
        }
    }

    static void Save_File(){
        //-------------------create a collection(Body_Builders)---------------------------------------------------

        if (database.getCollection("Body_Builders") == null) {
            database.createCollection("Body_Builders");
        }

        //-------------------------Access the collection---------------------------------------------

        MongoCollection collection = database.getCollection("Body_Builders");

        try{
            FileWriter gym_member_file = new FileWriter("Gym_Management.txt");
            FindIterable<Document> findIterable2 = collection.find();
            for (Document document1 : findIterable2){
                gym_member_file.write(document1.getString("First Name") + ":-");
                gym_member_file.write(document1.getString("Last Name") + ":-");
                gym_member_file.write(document1.getString("Member Id") + ":-");
                gym_member_file.write(document1.getString("Membership Date") + ":-");
                gym_member_file.write(document1.getString("Member School") + ":-");
                gym_member_file.write(document1.getString("Member Age") + ":-\n");
            }
            gym_member_file.close();
        }
        catch (IOException e)
        {e.printStackTrace();}

    }
    static void Sort_File(){
        //-------------------create a collection(Body_Builders)---------------------------------------------------

        if (database.getCollection("Body_Builders") == null) {
            database.createCollection("Body_Builders");
        }

        //-------------------------Access the collection---------------------------------------------

        MongoCollection collection = database.getCollection("Body_Builders");
        FindIterable<Document> findIterable = collection.find().sort(new BasicDBObject("Frist Name",1));
        MongoCursor<Document> coursor = findIterable.iterator();
        while (coursor.hasNext()){
            System.out.println(coursor.next());
        }
    }
}

