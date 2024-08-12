/*
Put Your Student ID, Name here
S3942349, Heath Yates
*/

import java.util.*;

public class A3_P1_2022{

    public static void main(String[] args){

        try {
            //these two lines underneath are necessary for the program to run
            WeeklyTimeTable week1 = new WeeklyTimeTable(1); //creating object reference
            week1.createSessionBase(); //initialises a baseline timetable, just a 7x7 of "---"

            //the below commented lines can be used to test the program, it will need something similar to run it
//            //manually adding movie objects (ill leave these here for testing if you need, but please call the Movie array "movieList" otherwise the program wont work)
//            Movie[] movieList = new Movie[5];
//            movieList[0] = new Movie("Snowpiercer", 126, "MA", 400);
//            movieList[1] = new Movie("Top Gun: Maverick", 130, "M", 600);
//            movieList[2] = new Movie("Monsters, Inc.", 92, "G", 90);
//            movieList[3] = new Movie("Coraline", 100, "PG", 40);
//            movieList[4] = new Movie("Goodbye", 140, "PG", 60);
//
//            //manually adding session objects (ill leave these here for testing if you need but please call the Session array "sessionList" otherwise the program wont work)
//            Session[] sessionList = new Session[5];
//            sessionList[0] = new Session(movieList[0], 14);
//            sessionList[1] = new Session(movieList[1], 13);
//            sessionList[2] = new Session(movieList[2], 12);
//            sessionList[3] = new Session(movieList[3], 11);
//            sessionList[4] = new Session(movieList[4]); //default price is 15
//
//            //manually populating the timeTable array with method addSession (ill leave these here for testing if you need)
//            week1.addSession(sessionList[0], "monday", "20:00");
//            week1.addSession(sessionList[1], 1, 3);
//            week1.addSession(sessionList[2], 2, 1);
//            week1.addSession(sessionList[3], 3, 4);
//            week1.addSession(sessionList[4], 4, 5);


            //the menu selection and data handling / method calling part
            int menuSelect = 0;
            while (menuSelect != 6) {
                menuSelect = week1.menuSelect();
                //menu option 1
                if (menuSelect == 1) {
                    week1.showSessions();
                    System.out.println();
                }
                //menu option 2
                else if (menuSelect == 2) {
                    week1.showTickets();
                }
                //menu option 3
                else if (menuSelect == 3) {
                    week1.showSales();
                }
                //menu option 4
                else if (menuSelect == 4) {

                    int sessionNum; //init

                    Scanner addSessionObj = new Scanner(System.in); //prints out all movie objects from movieList[]
                    System.out.println("Select a movie from below:");
                    for (int i = 0; i < movieList.length; i++) {
                        System.out.println(i + 1 + "." + String.format(String.valueOf(movieList[i])));
                    }

                    do { //makes sure that input is valid and won't crash the program
                        try {
                            sessionNum = Integer.parseInt(addSessionObj.nextLine());
                            if (sessionNum < 1 || sessionNum > movieList.length) {
                                System.out.println("Not valid input, try again ...");
                            }
                        } catch (Exception e) {
                            System.out.println("Not valid input, try again ...");
                            sessionNum = 0;
                        }
                    }
                    while (sessionNum < 1 || sessionNum > movieList.length);
                    week1.setSession(sessionList[sessionNum-1]);
                    String sessionType = week1.getSessionType();

                    Session inputSession = null;
                    int time = 0;
                    int day = 0;

                    if (sessionType == null) { //for regular sessions
                        day = week1.getDay();
                        week1.setDay(day);
                        time = week1.getTime();
                        week1.setTime(time);
                        for (int i = 0; i < movieList.length; i++) {
                            if (sessionNum-1 == i) {
                                sessionList[sessionList.length-1] = new Session(movieList[i]); //creates a new session object with the movie object selected
                                inputSession = sessionList[sessionList.length-1];
                            }
                        }
                    }

                    else if (sessionType.equals("Kids")) { //for kids sessions
                        String sessionRating = week1.getSession().getMovie().getRating();
                        if (!Objects.equals(sessionRating, "G") && !Objects.equals(sessionRating, "PG")) {
                            System.out.println("Incorrect rating for a kids session.");
                            System.out.println();
                            continue;
                        }
                        day = week1.getDay();
                        week1.setDay(day);
                        time = week1.getTime();
                        if (time > 4) {
                            while (time > 4) {
                                System.out.println("Too late for a kids session , try again...");
                                time = week1.getTime();
                            }
                        }
                        week1.setTime(time);
                        for (int i = 0; i < movieList.length; i++) {
                            if (sessionNum-1 == i) {
                                sessionList[sessionList.length-1] = new KidsSession(movieList[i]); //creates a new session object with the movie object selected
                                int inputSessionPrice = sessionList[sessionList.length-1].getPrice();
                                int newInputSessionPrice = (int) (inputSessionPrice*0.4);
                                sessionList[sessionList.length-1].setPrice(newInputSessionPrice);
                                inputSession = sessionList[sessionList.length-1];
                            }
                        }
                    }

                    else { //for sparse sessions
                        day = week1.getDay();
                        week1.setDay(day);

                        time = week1.getTime();
                        week1.setTime(time);

                        for (int i = 0; i < movieList.length; i++) {
                            if (sessionNum-1 == i) {
                                int inputSessionFee = movieList[i].getFee();
                                int newInputSessionFee = (inputSessionFee - 1000);
                                Movie sparseMovie = new Movie(movieList[i].getTitle(),
                                        movieList[i].getDuration(), movieList[i].getRating(), movieList[i].getFee());
                                sparseMovie.setFee(newInputSessionFee);
                                sessionList[sessionList.length-1] = new SparseSession(sparseMovie); //creates a new session object with the movie object selected
                                sessionList[sessionList.length-1].setCapacitySparse();
                                inputSession = sessionList[sessionList.length-1];
                            }
                        }
                    }
                    if(week1.checkAvailability(day, time)) {
                        week1.addSession(inputSession, day, time); //adds session to the array, not really creating a session object
                    }
                    else {
                        System.out.println("That session is already full.");
                    }

                    week1.showSessions();
                    System.out.println();
                }
                //menu option 5
                else if (menuSelect == 5) {
                    week1.getArraySession();
                }
                //menu option 6
                else {
                    System.out.println("Program exited.");
                }
            }
        }
        catch(Exception e){ //this will go off if anything in the program is incorrect
            System.err.println(e.getMessage());
        }
    }
}
//issues
//can add a 120+ minute movie before a movie already on the array
//no exception classes are implemented
//kids and spare session work, but the class most likely isn't implemented properly
//weekly timetable objects are not ever checked
//some methods have overloaded methods, but they will not work properly because of how the variables are input