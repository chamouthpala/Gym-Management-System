import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;


public class UserInterface extends Application {
    static MongoClient mongoClient = new MongoClient("localhost", 27017);
    static MongoDatabase database = mongoClient.getDatabase("PP_2_CW_2");
    static MongoCollection collection = database.getCollection("Body_Builders");

    static Button display_all_btn = new Button("Dispaly All Records");
    static Button serach_btn = new Button("Serach Here");
    static Button reset = new Button("Reset");
    static TableView table = new TableView();
    static TableColumn Id_column = new TableColumn();
    static TableColumn fname_column = new TableColumn();
    static TableColumn lname_column = new TableColumn();
    static TableColumn member_date_column = new TableColumn();
    static TableColumn member_type_column = new TableColumn();
    static TableColumn age_column = new TableColumn();
    static TableColumn school_column = new TableColumn();
    static TextField input_txt = new TextField();
    static RadioButton select_id = new RadioButton("Member Id");
    static RadioButton select_fname = new RadioButton("Member First Name");
    static ToggleGroup group = new ToggleGroup();
    static Label lbl1 = new Label();


    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        Scene table_view = new Scene(root,1000,500);
        primaryStage.setTitle("Gym Manager");
        primaryStage.setScene(table_view);
        primaryStage.show();

        input_txt.setLayoutY(251);
        input_txt.setLayoutX(34);

        display_all_btn.setLayoutY(42);
        display_all_btn.setLayoutX(59);
        display_all_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.getItems().clear();
                table.getColumns().clear();
                DispalyAll();
            }
        });



        serach_btn.setLayoutX(71);
        serach_btn.setLayoutY(330);

        table.setLayoutX(320);
        table.setLayoutY(0);
        table.setPrefSize(650,500);

        lbl1.setLayoutY(400);
        lbl1.setLayoutX(10);
        select_id.setLayoutX(42);
        select_id.setLayoutY(172);

        select_fname.setLayoutX(42);
        select_fname.setLayoutY(210);

        select_id.setToggleGroup(group);
        select_fname.setToggleGroup(group);

        serach_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(select_id.isSelected()){
                    searchId();
                }else if(select_fname.isSelected()){
                    searchName();
                }
            }
        });



        root.getChildren().addAll(input_txt,display_all_btn,serach_btn,table,select_id,select_fname,lbl1);

    }
    static void DispalyAll(){
        Id_column.setCellValueFactory(new PropertyValueFactory<>("Member_Id"));
        Id_column.setText("Member Id");
        fname_column.setCellValueFactory(new PropertyValueFactory<>("Member_fname"));
        fname_column.setText("First Name");
        lname_column.setCellValueFactory(new PropertyValueFactory<>("Member_lname"));
        lname_column.setText("Last Name");
        member_date_column.setCellValueFactory(new PropertyValueFactory<>("Member_date"));
        member_date_column.setText("Member Date");
        school_column.setCellValueFactory(new PropertyValueFactory<>("Member_School"));
        school_column.setText("Member School");
        age_column.setCellValueFactory(new PropertyValueFactory<>("Member_Age"));
        age_column.setText("Member Age");
        member_type_column.setCellValueFactory(new PropertyValueFactory<>("Member_Type"));
        member_type_column.setText("Member Type");

        table.getColumns().addAll(Id_column,fname_column,lname_column,member_date_column,school_column,age_column,member_type_column);

        FindIterable<Document> findIterable = collection.find();


        ObservableList ViewAll = FXCollections.observableArrayList();

        for(Document doc:findIterable){
            GuiDisplay output = new GuiDisplay();
            output.setMember_Id(doc.getInteger("Member Id"));
            output.setMember_fname(doc.getString("First Name"));
            output.setMember_lname(doc.getString("Last Name"));
            output.setMember_School(doc.getString("Member School"));
            output.setMember_date(doc.getString("Membership Date"));
            output.setMember_Age(doc.getString("Member Age"));
            output.setMember_Type(doc.getString("Member Type"));
            ViewAll.add(output);

        }
        table.setItems(ViewAll);

    }
    public static void searchId(){
        Id_column.setCellValueFactory(new PropertyValueFactory<>("Member_Id"));
        Id_column.setText("Member Id");
        fname_column.setCellValueFactory(new PropertyValueFactory<>("Member_fname"));
        fname_column.setText("First Name");
        lname_column.setCellValueFactory(new PropertyValueFactory<>("Member_lname"));
        lname_column.setText("Last Name");
        member_date_column.setCellValueFactory(new PropertyValueFactory<>("Member_date"));
        member_date_column.setText("Member Date");
        school_column.setCellValueFactory(new PropertyValueFactory<>("Member_School"));
        school_column.setText("Member School");
        age_column.setCellValueFactory(new PropertyValueFactory<>("Member_Age"));
        age_column.setText("Member Age");
        member_type_column.setCellValueFactory(new PropertyValueFactory<>("Member_Type"));
        member_type_column.setText("Member Type");

        table.getColumns().addAll(Id_column,fname_column,lname_column,member_date_column,school_column,age_column,member_type_column);

        FindIterable<Document> findIterable = collection.find();
        Document query = new Document();
        query.put("Member Id",Integer.valueOf(input_txt.getText()));
        ObservableList ViewAll1 = FXCollections.observableArrayList();
        for(Document doc:findIterable){
            GuiDisplay output = new GuiDisplay();
            output.setMember_Id(doc.getInteger("Member Id"));
            output.setMember_fname(doc.getString("First Name"));
            output.setMember_lname(doc.getString("Last Name"));
            output.setMember_School(doc.getString("Member School"));
            output.setMember_date(doc.getString("Membership Date"));
            output.setMember_Age(doc.getString("Member Age"));
            output.setMember_Type(doc.getString("Member Type"));
            ViewAll1.add(output);
        }
        table.setItems(ViewAll1);

    }
    public static void searchName(){
        Id_column.setCellValueFactory(new PropertyValueFactory<>("Member_Id"));
        Id_column.setText("Member Id");
        fname_column.setCellValueFactory(new PropertyValueFactory<>("Member_fname"));
        fname_column.setText("First Name");
        lname_column.setCellValueFactory(new PropertyValueFactory<>("Member_lname"));
        lname_column.setText("Last Name");
        member_date_column.setCellValueFactory(new PropertyValueFactory<>("Member_date"));
        member_date_column.setText("Member Date");
        school_column.setCellValueFactory(new PropertyValueFactory<>("Member_School"));
        school_column.setText("Member School");
        age_column.setCellValueFactory(new PropertyValueFactory<>("Member_Age"));
        age_column.setText("Member Age");
        member_type_column.setCellValueFactory(new PropertyValueFactory<>("Member_Type"));
        member_type_column.setText("Member Type");

        table.getColumns().addAll(Id_column,fname_column,lname_column,member_date_column,school_column,age_column,member_type_column);

        FindIterable<Document> findIterable = collection.find();
        Document query = new Document();
        query.put("First Name",input_txt.getText());
        ObservableList ViewAll2 = FXCollections.observableArrayList();
        for(Document doc:findIterable){
            GuiDisplay output = new GuiDisplay();
            output.setMember_Id(doc.getInteger("Member Id"));
            output.setMember_fname(doc.getString("First Name"));
            output.setMember_lname(doc.getString("Last Name"));
            output.setMember_School(doc.getString("Member School"));
            output.setMember_date(doc.getString("Membership Date"));
            output.setMember_Age(doc.getString("Member Age"));
            output.setMember_Type(doc.getString("Member Type"));
            ViewAll2.add(output);
        }
        table.setItems(ViewAll2);

    }
}

