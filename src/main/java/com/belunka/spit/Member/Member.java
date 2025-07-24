package com.belunka.spit.Member;

import com.google.gson.Gson;

public class Member {

    private String username;
    private String Examine;
    private String AM;
    private String Etos;

    boolean is_clicked_specate = false;

    private member_my_catalogue my_catalogue;


    


    public Member (String username, String examine, String AM, String Etos){
        this.username = username;
        this.Examine = examine;
        this.AM = AM;
        this.Etos = Etos;
    }


    public void set_Member_Catalogue(member_my_catalogue update_Catalogue){
        this.my_catalogue = update_Catalogue;
    }

    public member_my_catalogue get_Member_Catalogue(){
        return  this.my_catalogue;
    }

    //getters and setters

    public String getUsername(){
        return  username;
    }

    public String getExamine(){
        return  Examine;
    }

    public String getAM(){
        return AM;
    }
    public void setAM(String update_am){
        this.AM = update_am;
    }
    public String getEtos(){
        return Etos;
    }

    public boolean get_is_spectate(){
        return is_clicked_specate;
    }

    public void set_is_spectate(boolean update){
        this.is_clicked_specate = update;
    }



//    public static void main(String[] args){
//        Gson gson = new Gson();
//        String json = "{\"my_courses_display\":[{\"name\":\"Αγγλικά για την Επιστήμη των Υπολογιστών Ι\",\"TeacherName\":\"Ευγενία Ευμοιρίδου\",\"Examine\":1,\"Etos\":1,\"info\":\"Σε αυτό το μάθημα γίνεται μία γενικότερη επανάληψη της γλώσσας σε μέσο επίπεδο όσον αναφορά το λεξιλόγια και τη γραμματική.\",\"examinoString\":\"Χειμερινό Εξάμηνο\",\"type\":\"Υποχρεωτικό\"},{\"name\":\"Αγγλικά για την Επιστήμη των Υπολογιστών Ι\",\"TeacherName\":\"Ευγενία Ευμοιρίδου\",\"Examine\":1,\"Etos\":1,\"info\":\"Σε αυτό το μάθημα γίνεται μία γενικότερη επανάληψη της γλώσσας σε μέσο επίπεδο όσον αναφορά το λεξιλόγια και τη γραμματική.\",\"examinoString\":\"Χειμερινό Εξάμηνο\",\"type\":\"Υποχρεωτικό\"},{\"name\":\"Αγγλικά για την Επιστήμη των Υπολογιστών Ι\",\"TeacherName\":\"Ευγενία Ευμοιρίδου\",\"Examine\":1,\"Etos\":1,\"info\":\"Σε αυτό το μάθημα γίνεται μία γενικότερη επανάληψη της γλώσσας σε μέσο επίπεδο όσον αναφορά το λεξιλόγια και τη γραμματική.\",\"examinoString\":\"Χειμερινό Εξάμηνο\",\"type\":\"Υποχρεωτικό\"}]}";
//        member_my_catalogue member_my_catalogue = gson.fromJson(json, member_my_catalogue.class);
//        System.out.println(json);
//        System.out.println(member_my_catalogue.getCourse(0).getName());
//
//
//
//
//    }

}
