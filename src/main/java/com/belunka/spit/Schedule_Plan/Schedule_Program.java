package com.belunka.spit.Schedule_Plan;

import android.util.Log;

import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.CoursePackage.CoursesList;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Schedule_Program {

    ArrayList<Course> mathimata = new ArrayList<Course>();

    

    public Schedule_Program() {
            Course course;
            //initialize courses for CSE-UOI

            /** PRWTO ETOS*/

            //A-EXAMINO

            mathimata.add(new Course("Αγγλικά για την Επιστήμη των Υπολογιστών Ι", "Ευγενία Ευμοιρίδου", 1, 1, "Σε αυτό το μάθημα γίνεται μία γενικότερη επανάληψη της γλώσσας σε μέσο επίπεδο όσον αναφορά το λεξιλόγια και τη γραμματική.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Απειροστικός Λογισμός Ι", "Βασίλειος Μπενέκας", 1, 1, "Συναρτήσεις μιας μεταβλητής (παραμετρικές, μονότονες, αντίστροφες αλγεβρικές, τριγωνομετρικές, λογαριθμικές, εκθετικές, υπερβολικές). Όριο συναρτήσεως. Συνέχεια συναρτήσεως. Παραγώγιση (ορισμός, φυσική και γεωμετρική ερμηνεία, ιδιότητες, σχέση με συνέχεια, θεωρήματα π.χ. Rolle, μέσης τιμής, de l Hospital κλπ., παραγώγιση γνωστών συναρτήσεων).", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Γενική Φυσική", "Μαρίνα Τσελεπή", 1, 1, "Ηλεκτρομαγνητισμός: Βασικές αρχές και νόμοι. Ηλεκτρική δύναμη και πεδίο. Δυναμικό και ηλεκτρική δυναμική ενέργεια. Ηλεκτρικό ρεύμα και κυκλώματα συνεχούς ρεύματος. Μαγνητικό πεδίο και δύναμη. Επαγωγή και κυκλώματα εναλλασσομένου ρεύματος. Εξισώσεις Maxwell και φάσμα ηλεκτρομαγνητικής ακτινοβολίας.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Γραμμική Άλγεβρα", "Δεν δηλώνει", 1, 1, "Εισαγωγικές έννοιες από την Θεωρία Συνόλων, σχέσεις ισοδυναμίας, απεικονίσεις, μαθηματική επαγωγή. Εισαγωγικά στοιχεία στους πίνακες, άθροισμα, γινόμενο και αντιστροφή πίνακα. Γραμμικά συστήματα, ισοδυναμία πινάκων, ορίζουσες, επίλυση γραμμικών συστημάτων.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Εισαγωγή στον Προγραμματισμό", "Νικόλαος Μαμουλής", 1, 1, "Βασικές προγραμματιστικές έννοιες, δομές και τεχνικές. Μεταβλητές, τύποι μεταβλητών, εκφράσεις, αριθμητικοί υπολογισμοί. Δομές ελέγχου μιας γλώσσας, συνθήκες, δομές απόφασης, δομές επανάληψης-βρόχοι. Είσοδος/έξοδος δεδομένων, αμυντικός προγραμματισμός. Συναρτήσεις και διαδικασίες, αναδρομή. Δομημένοι τύποι, αλφαριθμητικά, λίστες, πίνακες.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Εισαγωγή στους Η/Υ και στην Πληροφορική", "Μαρίνα Πλησίτη", 1, 1, "Εισαγωγή στα συστήματα υπολογιστών, ιστορική εξέλιξη, μοντέλο Η/Υ, υλικό και λογισμικό. Η/Υ και αριθμοί. Εισαγωγή στη ψηφιακή λογική, ψηφιακά κυκλώματα. Αρχιτεκτονική υπολογιστών, κεντρική μονάδα επεξεργασίας, οργάνωση και προσπέλαση μνήμης.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time

            //B-EXAMINO

            course = new Course("Αγγλικά για την Επιστήμη των Υπολογιστών II", "Ευγενία Ευμοιρίδου", 2, 1, "Οι φοιτητές εξοικειώνονται με τη βασική ορολογία του αντικειμένου τους και με τις γλωσσικές μορφές που απαντώνται σε κείμενα πληροφορικής σε επίπεδο λεξιλογίου, συνδυασμού λέξεων και γραμματικής.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");

            /**
             *  update for 2023
             * */
            List<String> teaching_days = new ArrayList<String>();
            List<String> teaching_hours = new ArrayList<String>();
            teaching_days.add("Friday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("Αμφιθέατρο 1");
            mathimata.add(course);

            /**
             *
             *  Ends here
             * */


            course = new Course("Απειροστικός Λογισμός ΙΙ", "Μάριος - Γεώργιος Σταματάκης", 2, 1, "Εισαγωγή στους μιγαδικούς αριθμούς. Συναρτήσεις πολλών μεταβλητών. Όρια. Συνέχεια. Mερική παραγώγιση. Αλυσιδωτή παραγώγιση. Παράγωγος κατά κατεύθυνση. Ανάπτυγμα Taylor. Διανυσματική ανάλυση. Παραγώγιση βαθμωτών και διανυσματικών πεδίων. Πεπλεγμένες συναρτήσεις.  Πολλαπλά ολοκληρώματα. Επικαμπύλια και επιφανειακά ολοκληρώματα. Βασικά θεωρήματα Green, Gauss και Stokes. Στοιχεία διαφορικών εξισώσεων.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            /**
             *  update for 2023
             * */

            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("09:00-11:00");
            teaching_days.add("Thursday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingHours(teaching_hours); //update here
            course.set_TeachingDays(teaching_days); //update here
            course.set_target_room("Αμφιθέατρο 1");
            mathimata.add(course);

            /**
             * ends here
             * */



            /**
             * update for 2023
             * */
            course = new Course("Βασικές Αρχές Κυκλωμάτων", "Γεωργία Τσιριμώκου", 2, 1, "Μοντέλο κυκλώματος, Στοιχεία δύο ακροδεκτών, Αναπαράσταση σήματος, Δίκτυα αντιστάσεων, Νόμοι Kirchhoff, Ανάλυση κυκλώματος, Εξαρτώμενες πηγές, Θεωρήματα δικτύων (Μέθοδος Κόμβου, Μέθοδος Βρόγχου, Υπέρθεση, Θεωρήματα Thevenin/Norton), Δίθυρα δικτυώματα, Ανάλυση μικρού σήματος, Ανάλυση κατά συχνότητα.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Wednesday");
            teaching_hours.add("14:00-16:00");
            teaching_days.add("Friday");
            teaching_hours.add("13:00-15:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("Αίθουσα Ι5");
            mathimata.add(course);

            /**
             *  Ends here
             * */



            /**
             * update for 2023
             * */

            course = new Course("Διακριτά Μαθηματικά Ι", "Ιωσήφ Πολενάκης", 2, 1, "Εισαγωγή στη μαθηματική λογική: Προτασιακή λογική, σημασιολογική προσέγγιση. Προτασιακός λογισμός και τυπικές αποδείξεις, συντακτική προσέγγιση.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("16:00-18:00");
            teaching_days.add("Wednesday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("Αμφιθέατρο 1");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */
            course = new Course("Τεχνικές Αντικειμενοστρεφούς Προγραμματισμού", "Παναγιώτης Τσαπάρας", 2, 1, "Βασικές έννοιες και τεχνικές αντικειμενοστραφούς προγραμματισμού (κλάσεις, αντικείμενα, ενθυλάκωση και αφαίρεση, σύνθεση, ιεραρχίες και κληρονομικότητα, εξαιρέσεις) Αφηρημένοι τύποι δεδομένων (έννοιες, συλλογές και οι χρήσεις τους). Πρακτική εξάσκηση με τη γλώσσα προγραμματισμού Java.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("12:00-14:00");
            teaching_days.add("Thursday");
            teaching_hours.add("12:00-14:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("Αμφιθέατρο 1");
            mathimata.add(course);
            //set here days and time

            /**
             *  Ends here
             * */

            /** DEYTERO ETOS*/

            //3o examino

            mathimata.add(new Course("Ανάπτυξη Λογισμικού", "Παναγιώτης Βασιλειάδης", 3, 2, "Επανάληψη θεμελιωδών αρχών του αντικειμενοστρεφούς παραδείγματος. Τεχνικές μοντελοποίησης αντικειμενοστρεφούς λογισμικού. Μέθοδος για αποτύπωση προδιαγραφών, ανάλυση και σχεδίαση στο αντικειμενοστρεφές υπόδειγμα προγραμματισμού. Θεμελιώδεις αρχές και μετρικές σχεδίασης. Εισαγωγή στον έλεγχο και τη συντήρηση λογισμικού. Υλοποίηση ευμεγέθους προγραμματιστικής άσκησης (project) σε φάσεις.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Διακριτά Μαθηματικά ΙΙ", "Σπυρίδων Κοντογιάννης", 3, 2, "Στοιχεία θεωρίας αριθμών: Διαιρετότητα. Πρώτοι αριθμοί. Κόσκινο του Ερατοσθένη. b-αδικές παραστάσεις αριθμών. Κριτήρια διαιρετότητας και κριτήρια για το πότε ένας αριθμός είναι πρώτος. Μέγιστος κοινός διαιρέτης και ελάχιστο κοινό πολλαπλάσιο. Αλγόριθμος του Ευκλείδη. Θεώρημα πηλίκου-υπολοίπου.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Δομές Δεδομένων", "Λουκάς Γεωργιάδης", 3, 2, "Αφηρημένοι τύποι δεδομένων. Στατικές, δυναμικές δομές δεδομένων. Αναδρομή. Πίνακες, λίστες, στοίβες, ουρές, σωροί. Δένδρα, δένδρα αναζήτησης, ισοζυγισμένα δένδρα. Λίστες παράλειψης. Δομές εύρεσης-ένωσης. Γράφοι. Κατακερματισμός. Ψηφιακά ευρετήρια.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Πιθανότητες και Στατιστική", "Κωνσταντίνος Μπλέκας", 3, 2, "Αξιώματα πιθανοτήτων, Δεσμευμένη πιθανότητα και στοχαστική ανεξαρτησία, Κανόνας του Bayes,Τυχαίες μεταβλητές, Συναρτήσεις κατανομής και πυκνότητας πιθανότητας, Αναμενόμενη τιμή και διακύμανση, Βασικές διακριτές και συνεχείς κατανομές,", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Ψηφιακή Σχεδίαση Ι", "Μαρία Ελένη Δούναβη", 3, 2, "Σχεδίαση συνδυαστικών κυκλωμάτων, υλοποίηση με ψηφιακές πύλες, αθροιστές, πολυπλέκτες, αποπλέκτες, συγκριτές, κωδικοποιητές, αποκωδικοποιητές, μνήμη ROM, flip-flops, τεχνικές σχεδίασης σύγχρονων ακολουθιακών κυκλωμάτων, μετρητές, καταχωρητές, μονάδες μνήμης, αλγοριθμικές μηχανές καταστάσεων, σχεδίαση σε επίπεδο μεταφοράς καταχωρητή (Ορολογία, Αλγοριθμικές Μηχανές Καταστάσεων, Γλώσσες Περιγραφής Υλικού, Verilog).", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time

            //4o examino

            /**
             * update for 2023
             * */
            course = new Course("Αρχές Γλωσσών Προγραμματισμού", "Χρήστος Νομικός", 4, 2, "Κατηγορίες γλωσσών προγραμματισμού. Μέθοδοι υλοποίησης: μετάφραση, διερμηνεία και υβριδικές μέθοδοι. Σύνταξη και σημασιολογία. BNF και συντακτικά διαγράμματα. ", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("11:00-12:00");
            teaching_days.add("Wednesday");
            teaching_hours.add("18:00-20:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("Αμφ 1 - Ι5");

            mathimata.add(course);

            /**
             *  Ends here
             * */



            /**
             * update for 2023
             * */

            course = new Course("Εισαγωγή στην Αριθμητική Ανάλυση", "Κωνσταντίνος Παρσόπουλος", 4, 2, "κάτι με κουκίδες, δείτε το site του", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("16:00-18:00");
            teaching_days.add("Friday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("Αμφιθέατρο 1");
            mathimata.add(course);


            /**
             *  Ends here
             * */



            /**
             * update for 2023
             * */
            course = new Course("Ηλεκτρονική", "Γεώργιος Τσιατούχας", 4, 2, "Εισαγωγή στην ηλεκτρονική. Ενισχυτές – Τελεστικοί ενισχυτές. Θεωρία ημιαγωγών. Η p-n επαφή – Δίοδοι. Κυκλώματα διόδων (ανορθωτές, ψαλιδιστές). Τρανζίστορ επίδρασης πεδίου και διπολικά τρανζίστορ επαφής: α) δομή και φυσική λειτουργία, β) χαρακτηριστικές ρεύματος-τάσης, γ) DC λειτουργία – πόλωση, δ) μοντέλα ασθενούς σήματος. Βασικές τοπολογίες ενισχυτών με τρανζίστορ: πόλωση και τρόποι λειτουργίας. Διαφορικοί ενισχυτές. Ενισχυτές πολλών σταδίων. Απόκριση συχνότητας. Ανάδραση.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Wednesday");
            teaching_hours.add("16:00-18:00");
            teaching_days.add("Thursday");
            teaching_hours.add("16:00-18:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I5");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */
            course = new Course("Σχεδίαση και Ανάλυση Αλγορίθμών", "Σταύρος Νικολόπουλος", 4, 2, "Bασικές τεχνικές σχεδιασμού και ανάλυσης αλγορίθμων. Αλγοριθμική πολυπλοκότητα. Εργαλεία έκφρασης πολυπλοκότητας. Ασυμπτωτική μελέτη συναρτήσεων, αναδρομικές σχέσεις, ταξινόμηση, εύρεση μέσου, δένδρα διερεύνησης, σωροί, κατακερματισμός, διαίρει-και-βασίλευε, δυναμικός σχεδιασμός, επιμεριστική ανάλυση, αλγόριθμοι γραφημάτων, ελάχιστες διαδρομές, ελάχιστα σκελετικά δένδρα, αλγόριθμοι ροής σε δίκτυα, ταίριασμα προτύπων, δίκτυα ταξινόμησης, υπολογισμοί πολυωνύμων και πινάκων, παράλληλοι αλγόριθμοι, NP-πληρότητα.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("18:00-20:00");
            teaching_days.add("Tuesday");
            teaching_hours.add("18:00-20:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I5");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */
            course = new Course("Ψηφιακή Σχεδίαση ΙΙ", "Βασίλης Τενέντες", 4, 2, "Εισαγωγή Σχεδίασης, Βασικά Ψηφιακά-Αριθμητικά Κυκλώματα, Σχεδίαση σε Επίπεδο Μεταφοράς Καταχωρητών, Εισαγωγή στην VHDL (Βασικοί και Σύνθετοι Τύποι Δεδομένων, Μοντελοποίηση Συμπεριφοράς & Δομής, Υπορουτίνες, Πακέτα, Βιβλιοθήκες, Εξομοίωση, Συνθέσιμη Σχεδίαση με VHDL), Τελικά Βήματα Σχεδίασης (Διαμέριση, Χωροθέτηση, Τοποθέτηση, Γενική και Ειδική Διαδρόμηση), Προγραμματιζόμενες Συσκευές (PLAs, PLDs, CPLDs, FPGAs), Είσοδος/Έξοδος.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Thursday");
            teaching_hours.add("11:00-14:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I5");
            mathimata.add(course);

            /**
             *  Ends here
             * */

            /** TRITOS ETOS*/

            //5o examino

            mathimata.add(new Course("Θεωρία Υπολογισμού", "Λεωνίδας Παληός", 5, 3, "Πεπερασμένα αυτόματα, κανονικές εκφράσεις, κανονικές γλώσσες, ιδιότητες κλειστότητας, λήμμα άντλησης, αλγόριθμοι. Αιτιοκρατία, μη αιτιοκρατία. Aυτόματα στοίβας, γραμματικές χωρίς συμφραζόμενα, γλώσσες χωρίς συμφραζόμενα, ιδιότητες κλειστότητας, λήμμα άντλησης, αλγόριθμοι. Κανονική μορφή Chomsky. Mηχανές Turing, ισοδυναμία διαφορετικών μοντέλων. Αναγνωρίσιμες, διαγνώσιμες, απαριθμήσιμες γλώσσες. Tο δόγμα των Church-Turing.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Προγραμματισμός Συστημάτων", "Βασίλειος Δημακόπουλος", 5, 3, "Η γλώσσα προγραμματισμού C: στοιχειώδης C (βασικοί τύποι δεδομένων, εκφράσεις, τελεστές, δομές ελέγχου ροής, συναρτήσεις), προχωρημένα στοιχεία (δείκτες, πίνακες, δομές), δυναμική διαχείριση μνήμης, είσοδος/έξοδος, προεπεξεργαστής.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Σήματα και Συστήματα", "Χριστόφορος Νίκου", 5, 3, "Σήματα και Συστήματα Συνεχούς και Διακριτού Χρόνου, Ειδικά Σήματα, Κατηγορίες Σημάτων και Συστημάτων, Γραμμικά Χρονικά Αμετάβλητα Συστήματα, (Γ.Χ.Α.Σ.). Κρουστική Απόκριση Γ.Χ.Α.Σ., Συνέλιξη, Ιδιότητες Συνέλιξης, Απόκριση Συχνοτήτων, Ευστάθεια. Μετασχηματισμός Fourier (Μ.F.), μελέτη Γ.Χ.Α. συστημάτων με Μ.F., Σειρές Fourier. Μετασχηματισμός Ζ, Μετασχηματισμός Fourier Διακριτού Χρόνου, Ανάλυση Συστημάτων Διακριτού Χρόνου, Διακριτός Mετασχηματισμός Fourier (DFT), Γραμμική και Κυκλική Συνέλιξη Σημάτων Διακριτού Χρόνου.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Υπολογιστικά Μαθηματικά", "Κωνσταντίνος Βλάχος", 5, 3, "Το πρόβλημα αρχικών τιμών (ΠΑΤ) για συνήθεις διαφορικές εξισώσεις: Ύπαρξη και μοναδικότητα λύσεων, παραδείγματα μη ύπαρξης και μη μοναδικότητας λύσεων.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Αρχιτεκτονική Υπολογιστών", "Αριστείδης Ευθυμίου", 5, 3, "Σύντομη ιστορία των υπολογιστών και των μικροεπεξεργαστών. Βασική δομή του υπολογιστή. Βασικά ψηφιακά κυκλώματα.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time

            //6o examino

            /**
             * update for 2023
             * */

            course = new Course("Λειτουργικά Συστήματα", "Στέργιος Αναστασιάδης", 6, 3, "Εισαγωγή και ιστορία υπολογιστικών συστημάτων, διεργασίες, νήματα, συγχρονισμός, αδιέξοδο, χρονοδρομολόγηση επεξεργαστή, διαχείριση μνήμης, εικονική μνήμη, διαχείριση συσκευών, συστήματα αρχείων, ασφάλεια.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Tuesday");
            teaching_hours.add("15:00-17:00");
            teaching_days.add("Thursday");
            teaching_hours.add("14:00-16:00");
            teaching_days.add("Friday");
            teaching_hours.add("16:00-18:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I5");
            mathimata.add(course);


            /**
             *  Ends here
             * */



            /**
             * update for 2023
             * */

            course = new Course("Τεχνητή Νοημοσύνη", "Αριστείδης Λύκας", 6, 3, "Εισαγωγή στην Τεχνητή Νοημοσύνη, το πρόβλημα της αναζήτησης, αλγόριθμοι τυφλής αναζήτησης, αλγόριθμοι ευρετικής αναζήτησης, προβλήματα ικανοποίησης περιορισμών, παίγνια, αναπαράσταση γνώσης και αιτιολόγηση, προτασιακός και κατηγορηματικός λογισμός, η γλώσσα Prolog, συστήματα αιτιολόγησης προς τα εμπρός, η γλώσσα CLIPS, τεχνολογία γνώσης, μηχανική μάθηση, δέντρα απόφασης, αιτιολόγηση με αβεβαιότητα, δίκτυα πεποιθήσεων, συστήματα ασαφούς λογικής.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Friday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I5");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */

            course = new Course("Τηλεπικοινωνιακά Συστήματα", "Λυσίμαχος Παύλος Κόντης", 6, 3, "Αναλογικές τηλεπικοινωνίες. Διαμόρφωση πλάτους (DSB, AM, SSB). Διαμόρφωση γωνίας (FM, PM). Επίδραση του θορύβου στις αναλογικές τηλεπικοινωνίες. ", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("12:00-14:00");
            teaching_days.add("Tuesday");
            teaching_hours.add("12:00-14:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I5");
            mathimata.add(course);


            /**
             *  Ends here
             * */


            /** TETARTO ETOS*/

            //7o examino

            mathimata.add(new Course("Βάσεις Δεδομένων", "Ευαγγελία Πιτουρά", 7, 4, "Εισαγωγή στα συστήματα διαχείρισης BΔ. Εννοιολογικός σχεδιασμός, το μοντέλο Οντοτήτων/Συσχετίσεων. Το σχεσιακό μοντέλο.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Γραφικά Υπολογιστών και Συστήματα Αλληλεπίδρασης", "Ιωάννης Φούντος", 7, 4, "Βασικές έννοιες, υλικό για αλληλεπίδραση και γραφικά. Βασικά μαθηματικά εργαλεία για γραφικά. Μοντελοποίηση του ανθρώπινου μηχανισμού επικοινωνίας, βασικά μοντέλα αντίληψης και αντίδρασης, αισθήσεις και αισθητήρια όργανα του ανθρώπου.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time
            mathimata.add(new Course("Δίκτυα Υπολογιστών Ι", "Ευάγγελος Παπαπέτρου", 7, 4, "Εισαγωγή στη δικτύωση υπολογιστών. Ιστορική εξέλιξη των δικτυακών τεχνολογιών και του Διαδικτύου. Αρχιτεκτονικές, τύποι και τοπολογίες δικτύων. Σχεδίαση Δικτύων: διαστρωμάτωση, πρωτόκολλα και πρότυπα, συνδεσμοστραφείς και ασυνδεσμικές υπηρεσίες, το μοντέλο αναφοράς OSI.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time


            //8o examino


            /**
             * update for 2023
             * */

            course = new Course("Δίκτυα Υπολογιστών ΙΙ", "Χρήστος Λιάσκος", 8, 4, "Επισκόπηση των δικτύων υπολογιστών και του Διαδικτύου. Το μοντέλο αναφοράς TCP/IP. Το πρωτόκολλο ΙP: μοντέλο υπηρεσιών, διευθυνσιοδότηση και βασικές αρχές δρομολόγησης. Επίπεδο μεταφοράς: βασικές αρχές, αξιόπιστη μεταφορά δεδομένων, UDP και TCP.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Thursday");
            teaching_hours.add("15:00-18:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("Αμφιθέατρο 1");
            mathimata.add(course);
            //set here days and time

            /**
             *  Ends here
             * */



            /**
             * update for 2023
             * */
            course = new Course("Μεταφραστές", "Γεώργιος Μανής", 8, 4, "Εισαγωγή στη σχεδίαση και κατασκευή μεταφραστών. Λεκτική Ανάλυση. Συντακτική ανάλυση, συντακτικά κατευθυνόμενη μετάφραση. Σημασιολογική ανάλυση. Πίνακες συμβόλων και διαχείριση μνήμης. Παραγωγή ενδιαμέσου κώδικα. Παραγωγή τελικού κώδικα. Βελτιστοποίηση κώδικα.", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Tuesday");
            teaching_hours.add("10:00-12:00");
            teaching_days.add("Thursday");
            teaching_hours.add("09:00-11:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I5");
            mathimata.add(course);
            //set here days and time
            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */

            course = new Course("Τεχνολογία Λογισμικού", "Απόστολος Ζάρρας", 8, 4, "Η ύλη του μαθήματος περιλαμβάνει θέματα που αφορούν γενικά στην διεργασία ανάπτυξης λογισμικού, καθώς και στις επιμέρους φάσεις που την αποτελούν: Διεργασίες ανάπτυξης λογισμικού, απαιτήσεις (συλλογή, ανάλυση, τεκμηρίωση, ποιότητα απαιτήσεων), σχεδίαση (αρχιτεκτονική και τεχνική σχεδίαση, τεκμηρίωση σχεδίου, ποιότητα σχεδίου, στυλ αρχιτεκτονικής), υλοποίηση, έλεγχος (έλεγχος μονάδων, ολοκλήρωσης, συστήματος), παράδοση (εκπαίδευση χρηστών και τεκμηρίωση συστήματος).", "Εαρινό Εξάμηνο", "Υποχρεωτικό");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Tuesday");
            teaching_hours.add("17:00-20:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);
            //set here days and time

            /**
             *  Ends here
             * */

            /** PEMPTO ETOS*/

            //9o examino
            mathimata.add(new Course("Μικροεπεξεργαστές", "Βασίλης Τενέντες", 9, 5, "Ο στόχος του μαθήματος είναι να παρουσιάσει λεπτομερή θέματα τόσο σχεδιασμού μικροεπεξεργαστών χαμηλής κατανάλωσης ισχύος όσο και διαχείρισης της λειτουργίας τους στο πεδίο της εφαρμογής τους.", "Χειμερινό Εξάμηνο", "Υποχρεωτικό"));
            //set here days and time


            /** EPILOGIS MATHIMATA XEIMERINO*/
            //epilogis

            mathimata.add(new Course("Ανάπτυξη Λογισμικού ΙΙ", "Απόστολος Ζάρρας", -1, -1, "Καλές πρακτικές για την συγγραφή λογισμικού (ονοματολογία, τεκμηρίωση, οργάνωση λογισμικού, διαχείριση σφαλμάτων), τεχνικές αναδόμησης λογισμικού (σύνθεση μεθόδων, απλοποίηση συνθηκών & μεθόδων, μετακινήσεις, γενικεύσεις), εισαγωγή στα σχεδιαστικά πρότυπα.", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time
            mathimata.add(new Course("Βελτιστοποίηση", "Κωνσταντίνος Παρσόπουλος", -1, -1, "Κάτι με κουκίδες πάλι", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time
            mathimata.add(new Course("Εξόρυξη Δεδομένων", "Παναγιώτης Τσαπάρας", -1, -1, "Το μάθημα καλύπτει τις βασικές αρχές, αλγόριθμους και εφαρμογές της εξόρυξης γνώσης από μεγάλα σύνολα δεδομένων.", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time
            mathimata.add(new Course("Κυκλώματα VLSI", "Γεώργιος Τσιατούχας", -1, -1, "Θεωρία MOS τρανζίστορ. Η CMOS τεχνολογία. Επισκόπηση κατασκευής CMOS κυκλωμάτων. Η CMOS συνδυαστική λογική σχεδίασης, σύνθετες πύλες και CMOS λογικές οικογένειες (στατική, δυναμική, Domino, CVSL, τρανζίστορ διέλευσης).", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time
            mathimata.add(new Course("Πολυμέσα", "Λυσίμαχος Παύλος Κόντης", -1, -1, "Μέσα επικοινωνίας και πηγή πληροφορίας. Πολυμέσα: Ψηφιοποίηση και συμπίεση. Συμπίεση εικόνας και σχετικά πρότυπα. Συμπίεση βίντεο και σχετικά πρότυπα. Συμπίεση ήχου και σχετικά πρότυπα. Error resilience και error concealment για βίντεο. Μετάδοση βίντεο μέσω δικτύων.", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time
            mathimata.add(new Course("Υπολογιστική Νοημοσύνη", "Αριστείδης Λύκας", -1, -1, "Εισαγωγή στην Υπολογιστική Νοημοσύνη, βιολογικά νευρωνικά δίκτυα, τεχνητά νευρωνικά δίκτυα, μάθηση με παραδείγματα, κατηγορίες μάθησης, το perceptron, το πολυεπίπεδο perceptron, δίκτυα RBF, μάθηση και γενίκευση, ανταγωνιστική μάθηση, ο αλγόριθμος LVQ, το δίκτυο SOM, το δίκτυο Ηοpfield, νευρο-ασαφή συστήματα.", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time
            mathimata.add(new Course("Υπολογιστική Πολυπλοκότητα", "Χρήστος Νομικός", -1, -1, "Τύποι υπολογιστικών προβλημάτων. Ντετερμινιστικές και μη ντετερμινιστικές μηχανές Turing. Μέτρα πολυπλοκότητας.", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time
            mathimata.add(new Course("Τεχνολογίες Διαδικτύου", "Στέργιος Αναστασιάδης", -1, -1, "Εισαγωγικές έννοιες και αρχιτεκτονικές του Διαδικτύου (π.χ., Παγκόσμιος Ιστός, Υπολογιστική Νέφους). Γλώσσες και εργαλεία προγραμματισμού (π.χ., JavaScript, Ruby on Rails). Προγραμματισμός στην πλευρά του χρήστη. Προγραμματισμός στην πλευρά του διακομιστή (π.χ., πρότυπο Model-View-Controller).", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time
            mathimata.add(new Course("Ασύρματες Ζεύξεις", "Χρήστος Λιάσκος", -1, -1, "Βασικές αρχές διάδοσης σήματος. Μοντελοποίηση του τρόπου διάδοσης. Αναλυτικά μοντέλα εκτίμησης των απωλειών: απώλειες επίπεδης γης, απώλειες δύο ακτινών, απώλειες περίθλασης, ζώνες Fresnel, ισοζύγιο ζεύξης (Link Budget). Εμπειρικά μοντέλα εκτίμησης των απωλειών: μοντέλα εξωτερικού χώρου (Okumura Hata, Egli, IEEE, ITU-R P1546, WINNER), μοντέλα εσωτερικού χώρου (Απλής κλισης, COST 231, ITU-R P1238).", "Χειμερινό Εξάμηνο", "Επιλογής"));
            //set here days and time

            /** EPILOGIS MATHIMATA EARINO*/

            /**
             * update for 2023
             * */

            course = new Course("Αλληλεπίδραση Ανθρώπου Υπολογιστή", "Φούντος Ιωάννης", -1, -1, "Εισαγωγή. Ορισμός του προβλήματος-παραδείγματα. Ευχρηστία λογισμικού. Μέτρηση και ποσοτικοποίηση της ευχρηστίας.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Wednesday");
            teaching_hours.add("14:00-17:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);

            /**
             *  Ends here
             * */

            /**
             * update for 2023
             * */

            course = new Course("Μηχανική Μάθηση", "Κωνσταντίνος Μπλέκας", -1, -1, "Κάτι με bullets", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Tuesday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);


            /**
             *  Ends here
             * */



            /**
             * update for 2023
             * */

            course = new Course("Ανάκτηση Πληροφορίας", "Ευαγγελία Πιτουρά", -1, -1, "Εισαγωγή στα συστήματα ανάκτησης πληροφορίας. Μοντελοποίηση: δυαδικά, συνολοθεωρητικά, πιθανοκρατικά γλωσσικά και διανυσματικά μοντέλα.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Tuesday");
            teaching_hours.add("12:00-14:00");
            teaching_days.add("Wednesday");
            teaching_hours.add("13:00-14:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("Δεν δηλώνει");
            mathimata.add(course);


            /**
             *  Ends here
             * */


            course = new Course("Αρχιτεκτονική Υπολογιστών ΙΙ", "Αριστείδης Ευθυμίου", -1, -1, "Αρχιτεκτονική σύνολου εντολών: σχεδίαση, επίδοση, μετροπρογράμματα (benchmarks), τύποι σύνολου εντολών Προγραμματισμός μικροεπεξεργαστών, γλώσσα μηχανής, συμβολική γλώσσα μηχανής (assembly).", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Tuesday");
            teaching_hours.add("14:00-17:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I2");
            mathimata.add(course);

            //set here days and time
            mathimata.add(course);



            /**
             * update for 2023
             * */


            course = new Course("Ασύρματα Δίκτυα", "Ευάγγελος Παπαπέτρου", -1, -1, "Εισαγωγή στην ασύρματη δικτύωση. Ιστορική εξέλιξη ασύρματων δικτύων, Πρότυπα και συμβατότητα. Ειδικά ζητήματα της ασύρματης και κινητής επικοινωνίας.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Friday");
            teaching_hours.add("16:00-19:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I2");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */

            course = new Course("Θεωρία Γραφημάτων", "Ιωσήφ Πολενάκης", -1, -1, "Το μάθημα καλύπτει τις βασικές έννοιες και ορισμούς που αφορούν κλασικά γραφοθεωρητικά προβλήματα. Το μάθημα καλύπτει επίσης μια σειρά από εφαρμογές των οποίων η μοντελοποίηση σε γράφημα είναι γνωστό ότι οδηγεί σε αποτελεσματική λύση.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("18:00-20:00");
            teaching_days.add("Wednesday");
            teaching_hours.add("12:00-13:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I2");
            mathimata.add(course);


            /**
             *  Ends here
             * */



            /**
             * update for 2023
             * */

            course = new Course("Παράλληλα Συστήματα και Προγραμματισμός", "Βασίλειος Δημακόπουλος", -1, -1, "Bασικές αρχές παραλληλισμού. Eπίδοση, επιτάχυνση και αποδοτικότητα των παράλληλων αλγορίθμων. Οργάνωση κοινόχρηστης μνήμης. Συνοχή (coherency) και συνέπεια (consistency) μνήμης. Οργάνωση κατανεμημένης μνήμης.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingDays(teaching_days);
            course.set_TeachingHours(teaching_hours);
            course.set_target_room("I2");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */

            course = new Course("Προηγμένη Σχεδίαση Αλγορίθμων και Δομών", "Λουκάς Γεωργιάδης", -1, -1, "Επιλεγμένα θέματα από τις ακόλουθες περιοχές: Προβλήματα βελτιστοποίησης σε δίκτυα: Αλγόριθμοι (ελαφρύτατες διαδρομές, μέγιστες ροές, συνεκτικότητα, μέγιστα ταιριάσματα, ροές ελάχιστου κόστους) και σχετικές δομές δεδομένων (σωροί Fibonacci, δυναμικά δένδρα).", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Thursday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);

            /**
             *  Ends here
             * */



            /**
             * update for 2023
             * */


            course = new Course("Προχωρημένα Θέματα Τεχνολογίας και Εφαρμογών Βάσεων Δεδομένων", "Παναγιώτης Βασιλειάδης", -1, -1, "Αρχιτεκτονική της εσωτερικής δομής ενός Συστήματος Διαχείρισης Βάσεων Δεδομένων. Επεξεργασία ερωτήσεων. Βελτιστοποίηση ερωτήσεων.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Thursday");
            teaching_hours.add("17:00-20:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);


            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */

            course = new Course("Ρομποτική", "Κωνσταντίνος Βλάχος", -1, -1, "Εισαγωγή στα ρομποτικά συστήματα (ιστορική αναδρομή, παρουσίαση τεχνολογίας). Δομή και ταξινόμηση των Ρομπότ. ", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Friday");
            teaching_hours.add("13:00-16:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);



            /**
             *  Ends here
             * */




            /**
             * update for 2023
             * */

            course = new Course("Υπολογιστική Γεωμετρία", "Λεωνίδας Παληός", -1, -1, "Βασικά γεωμετρικά αντικείμενα και αναπαράστασή τους στον υπολογιστή. Το θεώρημα της Πινακοθήκης. Μέθοδος σάρωσης επιπέδου.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Wednesday");
            teaching_hours.add("17:00-20:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);
            //set here days and time

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */

            course = new Course("Ψηφιακή Επεξεργασία Εικόνας", "Χριστόφορος Νίκου", -1, -1, "Εισαγωγή στις ψηφιακές εικόνες, πεδία εφαρμογής της επεξεργασίας εικόνας. Στοιχεία οπτικής αντίληψης, λήψη εικόνας, δειγματοληψία και κβαντισμός.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Monday");
            teaching_hours.add("15:00-18:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);
            //set here days and time


            /**
             *  Ends here
             * */

            /**
             * update for 2023
             * */

            course = new Course("Διαχείριση Σύνθετων Δεδομένων", "Νικόλαος Μαμουλής", -1, -1, "Το μάθημα εστιάζει στη διαχέιριση σύνθετων τύπων δεδομένων από επεκταμένα συστήματα βάσεων δεδομένων. Δίνει έμφαση στην αποτελεσματική δεικτοδότηση τέτοιων δεδομένων με στόχο την αποδοτική επεξεργασία επερωτήσεων πάνω σε αυτά.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Friday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("Ι1");
            mathimata.add(course);

            /**
             *  Ends here
             * */

            /**
             * update for 2023
             * */

            course = new Course("Επιχειρηματικότητα στην Πληροφορική", "Μαρία Ελένη Δούναβη", -1, -1, "Βασικά στοιχεία νεοφυούς επιχειρηματικότητας (τεχνολογική καινοτομία, επιχειρηματική ιδέα και ευκαιρία, έρευνα αγοράς και ανταγωνισμός, ανάπτυξη προϊόντος, διοίκηση επιχείρησης), Επιχειρηματικά μοντέλα (λογισμικό, ηλεκτρονικό/κινητό εμπόριο, ανοιχτή επιχειρηματικότητα), Χρηματοδότηση (δανειοδότηση, επιχειρηματικό κεφάλαιο, συμμετοχική χρηματοδότηση), Υπηρεσίες υποστήριξης (καθοδηγητές, θερμοκοιτίδες, επιταχυντές), Προστασία πνευματικής ιδιοκτησίας (άδειες λογισμικού, πνευματικά δικαιώματα, εμπορικό σήμα, δίπλωμα ευρεσιτεχνίας).", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Wednesday");
            teaching_hours.add("09:00-12:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */

            course = new Course("Υπολογιστική Όραση", "Άγγελος Γιώτης", -1, -1, "Γραμμικό φιλτράρισμα, Ανίχνευση ακμών, Τοπικά χαρακτηριστικά: γωνίες, Τοπικά χαρακτηριστικά: χώρος κλίμακας και σημεία ενδιαφέροντος, Μέθοδοι μαθηματικής μορφολογίας, Αναπαράσταση υφής, Αναπαράσταση και περιγραφή σχημάτων, Πυραμίδες εικόνας και ταίριασμα προτύπου, Γεωμετρικοί μετασχηματισμοί, Βαθμονόμηση κάμερας, Στερεοσκοπία, Οπτική ροή, Παρακολούθηση της κίνησης σε βίντεο, Τμηματοποίηση εικόνας, Υπέρθεση εικόνων.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Thursday");
            teaching_hours.add("12:00-15:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I2");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            /**
             * update for 2023
             * */

            course = new Course("Διδακτική της Πληροφορικής", "Μαρία Χρόνη", -1, -1, "Η Πληροφορική ως γνωστικό αντικείμενο και γνωστικό εργαλείο.", "Εαρινό Εξάμηνο", "Επιλογής");
            teaching_days = new ArrayList<String>();
            teaching_hours = new ArrayList<String>();
            teaching_days.add("Tuesday");
            teaching_hours.add("17:00-20:00");
            course.set_TeachingHours(teaching_hours);
            course.set_TeachingDays(teaching_days);
            course.set_target_room("I3");
            mathimata.add(course);

            /**
             *  Ends here
             * */


            //here we need somthing to change the time

    }


    public List<Course> getMathimata(){
            return mathimata; //return the mathimata
    }


}
