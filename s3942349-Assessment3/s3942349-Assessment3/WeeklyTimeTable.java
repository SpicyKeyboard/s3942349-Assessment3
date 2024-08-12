import java.util.Objects;
import java.util.Scanner;

public class WeeklyTimeTable{

    //fields
    private Session[][] ticketTimeTable;
    private String[][] addTimeTable;
    private int timeTableWeekNum = 1;
    private int sessionTime;
    private Session sessionSession;
    private int sessionDay;

    //constructor
    public WeeklyTimeTable(int weekNum) {
        this.timeTableWeekNum = weekNum;
    }

    //methods
    public boolean checkAvailability(int day, int time) {
        setSession(ticketTimeTable[time][day]);
        if (getSession() == null) { //input session doesn't exist
            try {
            setSession(ticketTimeTable[time-1][day]);
                if (getSession()!= null) { //time slot before exists
                    if (getSession().getMovie().getDuration() <= 120) { //time slot before is less than 120 minutes
                        return true;
                    }
                    else {
                        return false; //time slot is > 120
                    }
                }
                else {
                    return true; //no time slot before
                }
            }
            catch (Exception e) {
                return true; //time slot before is out of range / does not exist
            }
        }
        else { //input session exists
            return false;
        }
    }

    public boolean checkAvailability(String day, String time) { //change the day and time to string values and the -=1 to the previous day
        int newTime = 0;
        int newDay = 0;
        day = day.toLowerCase().substring(0,3);
        String[] addTime = new String[7];
        addTime[0] = "08:00";
        addTime[1] = "10:00";
        addTime[2] = "12:00";
        addTime[3] = "14:00";
        addTime[4] = "16:00";
        addTime[5] = "18:00";
        addTime[6] = "20:00";
        for (int i = 0; i < addTime.length; i++) {
            if (Objects.equals(time, addTime[i])) {
                newTime = i;
            }
        }
        String[] addDay = new String[7];
        addDay[0] = "mon";
        addDay[1] = "tue";
        addDay[2] = "wed";
        addDay[3] = "thu";
        addDay[4] = "fri";
        addDay[5] = "sat";
        addDay[6] = "sun";
        for (int i = 0; i < addDay.length; i++) {
            if (day.equals(addDay[i])) {
                newDay = i;
            }
        }
        setSession(ticketTimeTable[newTime][newDay]);
        if (getSession() == null) { //input session doesn't exist
            try {
                setSession(ticketTimeTable[newTime-1][newDay]);
                if (getSession()!= null) { //time slot before exists
                    if (getSession().getMovie().getDuration() <= 120) { //time slot before is less than 120 minutes
                        return true;
                    }
                    else {
                        return false; //time slot is > 120
                    }
                }
                else {
                    return true; //no time slot before
                }
            }
            catch (Exception e) { //time slot before is out of range / does not exist
                return true;
            }
        }
        else { //input session exists
            return false;
        }
}

    public boolean addSession(Session s, int day, int time) { //make it so that it returns false if it is too long of a movie
        addTimeTable[time][day] = String.valueOf(s);
        ticketTimeTable[time][day] = s;
        return true;
    }

    public boolean addSession(Session s, String day, String time) {
        int newTime = 0;
        int newDay = 0;
        day = day.toLowerCase().substring(0,3);
        String[] addTime = new String[7];
        addTime[0] = "08:00";
        addTime[1] = "10:00";
        addTime[2] = "12:00";
        addTime[3] = "14:00";
        addTime[4] = "16:00";
        addTime[5] = "18:00";
        addTime[6] = "20:00";
        for (int i = 0; i < addTime.length; i++) {
            if (Objects.equals(time, addTime[i])) {
                newTime = i;
            }
        }
        String[] addDay = new String[7];
        addDay[0] = "mon";
        addDay[1] = "tue";
        addDay[2] = "wed";
        addDay[3] = "thu";
        addDay[4] = "fri";
        addDay[5] = "sat";
        addDay[6] = "sun";
        for (int i = 0; i < addDay.length; i++) {
            if (day.equals(addDay[i])) {
                newDay = i;
            }
        }
        addTimeTable[newTime][newDay] = String.valueOf(s);
        ticketTimeTable[newTime][newDay] = s;
        return true;
    }

    public void setSession(Session inputSession) {
        this.sessionSession = inputSession;
    }

    public Session getSession() {
        return this.sessionSession;
    }

    public void setDay(int day) {
        this.sessionDay = day;
    }

    public int getDay() {
        int day;
        Scanner addSessionObj = new Scanner(System.in);
        System.out.println("Enter the day:");
        showSessions();
        do {
            try {
                day = Integer.parseInt(addSessionObj.nextLine());
                if (day < 1 || day > 7) {
                    System.out.println("Not valid input, try again ...");
                }
            }
            catch (Exception e) {
                System.out.println("Not valid input, try again ...");
                day = 0;
            }
        }
        while (day < 1 || day > 7);
        day -= 1;
        return day;
    }

    public void setTime(int time) {
        this.sessionTime = time;
    }

    public int getTime() {
        int time;
        Scanner addSessionObj = new Scanner(System.in);
        System.out.println("Enter the time:");
        showSessions();
        do {
            try {
                time = Integer.parseInt(addSessionObj.nextLine());
                if (time < 1 || time > 7) {
                    System.out.println("Not valid input, try again ...");
                }
            }
            catch (Exception e) {
                System.out.println("Not valid input, try again ...");
                time = 0;
            }
        }
        while (time < 1 || time > 7);
        time -= 1;
        return time;
    }

    public String getSessionType() {
        String type = null;
        String isNormal = null;
        String sessionType = null;
        Scanner addSessionObj = new Scanner(System.in);
        System.out.println("Is this a normal session? Y/N");
        do {
            try {
                isNormal = addSessionObj.nextLine();
                if (!Objects.equals(isNormal, "Y") && !Objects.equals(isNormal, "N")) {
                    System.out.println("Not valid input, try again ...");
                }
                else {
                    if (Objects.equals(isNormal, "N")) { //is a special session
                        System.out.println("Is this a kids or sparse session? Kids/Sparse");

                        do {
                            try {
                                type = addSessionObj.nextLine();
                                if (Objects.equals(type, "Kids")) { //input is valid for Kids session
                                    sessionType = "Kids";
                                } else if (Objects.equals(type, "Sparse")) { //input is valid for Sparse session
                                    sessionType = "Sparse";
                                } else { //input is invalid
                                    System.out.println("Not valid input, try again ...");
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Not valid input, try again ...");
                            }
                        }
                        while (!Objects.equals(type, "Kids") && !Objects.equals(type, "Sparse"));
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Not valid input, try again ...");
                sessionType = null;
            }
        }
        while (!Objects.equals(isNormal, "Y") && !Objects.equals(isNormal, "N"));
        return sessionType;
    }

    public void createSessionBase() {
        addTimeTable = new String[7][7];
        for (int i = 0; i<7; i++) { // j is rows, i is columns
            for (int j = 0; j<7; j++) {
                addTimeTable[i][j] = "  ---    ";
            }
        }
        ticketTimeTable = new Session[7][7];
    }

    public void showSessions() {
        System.out.println("             Mon      Tue      Wed      Thu      Fri      Sat      Sun\n" +
                           "-------------------------------------------------------------------------");
        String[] time = new String[7];
        time[0] = "08:00      ";
        time[1] = "10:00      ";
        time[2] = "12:00      ";
        time[3] = "14:00      ";
        time[4] = "16:00      ";
        time[5] = "18:00      ";
        time[6] = "20:00      ";
        //prints out the session timetable
        for (int i = 0; i<7; i++) { // j is rows, i is columns
            System.out.print(time[i]);
            for (int j = 0; j<7; j++) {
                System.out.print(addTimeTable[i][j].substring(0,7) + "  ");
            }
            System.out.println();
        }
    }

    public void showTickets() { //default is 60 tickets, unless it is sparse then it is 20 tickets
        System.out.println("             Mon      Tue      Wed      Thu      Fri      Sat      Sun\n" +
                "-------------------------------------------------------------------------");
        String[] time = new String[7];
        time[0] = "08:00      ";
        time[1] = "10:00      ";
        time[2] = "12:00      ";
        time[3] = "14:00      ";
        time[4] = "16:00      ";
        time[5] = "18:00      ";
        time[6] = "20:00      ";
        //prints out the session timetable
        for (int i = 0; i<7; i++) { // j is rows, i is columns
            System.out.print(time[i]);
            for (int j = 0; j<7; j++) {
                System.out.print(addTimeTable[i][j].substring(0,7) + "  ");
            }
            System.out.println();
            System.out.print("             ");
            //prints out the amount of tickets not sold
            for (int h = 0; h<7; h++) {
                if (ticketTimeTable[i][h] == null) {
                    System.out.print("         ");
                }
                else {
                    int remainingTickets = ticketTimeTable[i][h].getRemainingTickets();
                    System.out.print(remainingTickets + "       ");
                }
            }
            System.out.println();
        }
    }

    public void showSales() {
        System.out.println("             Mon      Tue      Wed      Thu      Fri      Sat      Sun\n" +
                "-------------------------------------------------------------------------");
        String[] time = new String[7];
        time[0] = "08:00      ";
        time[1] = "10:00      ";
        time[2] = "12:00      ";
        time[3] = "14:00      ";
        time[4] = "16:00      ";
        time[5] = "18:00      ";
        time[6] = "20:00      ";
        //prints out the session timetable
        for (int i = 0; i<7; i++) { // j is rows, i is columns
            System.out.print(time[i]);
            for (int j = 0; j<7; j++) {
                System.out.print(addTimeTable[i][j].substring(0,7) + "  ");
            }
            System.out.println();
            System.out.print("            ");
            //prints out the amount of tickets sold
            for (int h = 0; h<7; h++) {
                if (ticketTimeTable[i][h] == null) {
                    System.out.print("         ");
                }
                else {
                    int soldTickets = ticketTimeTable[i][h].getCapacity() - ticketTimeTable[i][h].getRemainingTickets();
                    System.out.print("x " + soldTickets + "     ");
                }
            }
            System.out.println();
            System.out.print("            ");
            //prints out the money gained from the session, aka tickets sold * session price - fee [will be different for KidsSession and SparseSession]
            for (int h = 0; h<7; h++) {
                if (ticketTimeTable[i][h] == null) {
                    System.out.print("         ");
                }
                else {
                    int soldTickets = ticketTimeTable[i][h].getCapacity() - ticketTimeTable[i][h].getRemainingTickets();
                    setSession(ticketTimeTable[i][h]);
                    int profit = soldTickets*getSession().profit();
                    int fee = getSession().getMovie().getFee();
                    System.out.print("$" + (profit - fee) + "      ");
                }
            }
            System.out.println();
        }
    }

    public void getArraySession() {
        int day = getDay();
        setDay(day);
        int time = getTime();
        setTime(time);
        setSession(ticketTimeTable[time][day]);
        showTickets();
        System.out.println("[" + (ticketTimeTable[time][day]).getMovie().getTitle() + "] selected, enter quantity:");
        int ticketAmount;
        Scanner ticketAmountObj = new Scanner(System.in);
        do { //makes sure that input is valid and won't crash the program
            try {
                ticketAmount = Integer.parseInt(ticketAmountObj.nextLine());
                if (ticketAmount < 0 || ticketAmount > getSession().getRemainingTickets()) {
                    System.out.println("Not valid input or not enough tickets available, try again ...");
                }
            } catch (Exception e) {
                System.out.println("Not valid input or not enough tickets available, try again ...");
                ticketAmount = -1;
            }
        }
        while (ticketAmount < 0 || ticketAmount > getSession().getRemainingTickets());
        getSession().sellTickets(ticketAmount);
        System.out.println(ticketAmount + " x " + getSession().getPrice() + " = $" +
                (ticketAmount*getSession().getPrice()) + " charged, go back.");
        System.out.println();
    }

    public int menuSelect() {

            int menuSelect = 0;
            Scanner menuObj = new Scanner(System.in);
            System.out.println("""
                        1.Show the timetable of the week\s
                        2.Show available tickets of the week\s
                        3.Show sales report of the week\s
                        4.Add a session by day and time\s
                        5.Sale tickets by day and time\s
                        6.Quit\s""");
            do {
                try {
                    menuSelect = Integer.parseInt(menuObj.nextLine());
                    if (menuSelect < 1 || menuSelect > 6) {
                        System.out.println("Not valid input, try again ...");
                    }
                }
                catch(Exception e) {
                    System.out.println("Not valid input, try again ...");
                    menuSelect = 0;
                }
            }
            while (menuSelect < 1 || menuSelect > 6);

        return menuSelect;
    }
}