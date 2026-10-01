import java.io.*;

public class J {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int pno[] = new int[275];
    static String name[] = new String[275];
    static String phno[] = new String[275];
    static int age[] = new int[275];
    static int cl[] = new int[275];

    static int pcount = 0;
    static int pnum = 1;

    static int max1 = 75;   // AC
    static int max2 = 125;  // First
    static int max3 = 175;  // Sleeper

    public static void main(String[] args) throws Exception {
        doMenu();
    }

    public static void doMenu() throws Exception {

        int cho = 0;

        do {
            System.out.println("\n");
            doHeading();

            System.out.println("1. Book ticket");
            System.out.println("2. Cancel ticket");
            System.out.println("3. Search passenger");
            System.out.println("4. Reservation chart");
            System.out.println("5. Display unbooked tickets");
            System.out.println("6. Exit");

            System.out.println("Please enter your choice");
            cho = Integer.parseInt(br.readLine());

            switch (cho) {

                case 1:
                    doBook();
                    break;

                case 2:
                    doCancel();
                    break;

                case 3:
                    doSearch();
                    break;

                case 4:
                    doDispList();
                    break;

                case 5:
                    doDispUnbooked();
                    break;

                case 6:
                    doExit();
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (cho != 6);
    }

    private static void doHeading() {
        System.out.println("======================================");
        System.out.println("   Railway Reservation For Kabul Express");
        System.out.println("======================================");
    }

    private static void doBook() throws Exception {

        System.out.println("Please enter the class of ticket");
        System.out.println("1. AC");
        System.out.println("2. First");
        System.out.println("3. Sleeper");

        int c = Integer.parseInt(br.readLine());

        if (c < 1 || c > 3) {
            System.out.println("Invalid class.");
            return;
        }

        System.out.println("Please enter no. of tickets");
        int t = Integer.parseInt(br.readLine());

        if (t <= 0) {
            System.out.println("Number of tickets must be greater than 0.");
            return;
        }

        if (pcount + t > 275) {
            System.out.println("Maximum passenger limit reached.");
            return;
        }

        int available = 0;

        if (c == 1) {
            available = max1;
        } else if (c == 2) {
            available = max2;
        } else {
            available = max3;
        }

        if (available < t) {
            System.out.println("Sorry! Only " + available
                    + " tickets are available in this class.");
            return;
        }

        for (int i = 0; i < t; i++) {

            pno[pcount] = pnum;

            System.out.println("Please enter your name");
            name[pcount] = br.readLine();

            System.out.println("Please enter your age");
            age[pcount] = Integer.parseInt(br.readLine());

            System.out.println("Please enter your phno");
            phno[pcount] = br.readLine();

            cl[pcount] = c;

            System.out.println("Ticket successfully booked.");
            System.out.println("Passenger number = " + pnum);

            pcount++;
            pnum++;
        }

        if (c == 1) {
            max1 -= t;
            System.out.println("Please pay Rs." + (t * 1500));
        } else if (c == 2) {
            max2 -= t;
            System.out.println("Please pay Rs." + (t * 1200));
        } else {
            max3 -= t;
            System.out.println("Please pay Rs." + (t * 1000));
        }
    }

    private static void doCancel() throws Exception {

        System.out.println("Please enter your passenger no.");
        int p = Integer.parseInt(br.readLine());

        int index = -1;

        // Find passenger
        for (int i = 0; i < pcount; i++) {
            if (pno[i] == p) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Passenger not found.");
            return;
        }

        int cancelledClass = cl[index];

        // Increase available seats
        if (cancelledClass == 1) {
            max1++;
            System.out.println("Please collect refund of Rs.1500");
        } else if (cancelledClass == 2) {
            max2++;
            System.out.println("Please collect refund of Rs.1200");
        } else if (cancelledClass == 3) {
            max3++;
            System.out.println("Please collect refund of Rs.1000");
        }

        // Shift all records after the cancelled passenger
        for (int i = index; i < pcount - 1; i++) {

            pno[i] = pno[i + 1];
            name[i] = name[i + 1];
            phno[i] = phno[i + 1];
            age[i] = age[i + 1];
            cl[i] = cl[i + 1];
        }

        // Clear last record
        pno[pcount - 1] = 0;
        name[pcount - 1] = null;
        phno[pcount - 1] = null;
        age[pcount - 1] = 0;
        cl[pcount - 1] = 0;

        pcount--;

        System.out.println("Ticket successfully cancelled.");
    }

    private static void doDispList() {

        System.out.println("\nPassenger list in AC class");
        System.out.println("pno\tname\t\tage\tphno");

        for (int i = 0; i < pcount; i++) {
            if (cl[i] == 1) {
                System.out.println(
                        pno[i] + "\t" +
                        name[i] + "\t\t" +
                        age[i] + "\t" +
                        phno[i]
                );
            }
        }

        System.out.println("\nPassenger list in First class");
        System.out.println("pno\tname\t\tage\tphno");

        for (int i = 0; i < pcount; i++) {
            if (cl[i] == 2) {
                System.out.println(
                        pno[i] + "\t" +
                        name[i] + "\t\t" +
                        age[i] + "\t" +
                        phno[i]
                );
            }
        }

        System.out.println("\nPassenger list in Sleeper class");
        System.out.println("pno\tname\t\tage\tphno");

        for (int i = 0; i < pcount; i++) {
            if (cl[i] == 3) {
                System.out.println(
                        pno[i] + "\t" +
                        name[i] + "\t\t" +
                        age[i] + "\t" +
                        phno[i]
                );
            }
        }
    }

    private static void doSearch() throws Exception {

        System.out.println("Please enter passenger no. to search");
        int p = Integer.parseInt(br.readLine());

        int passengerFound = 0;

        for (int i = 0; i < pcount; i++) {

            if (pno[i] == p) {

                System.out.println("\nDetail found");
                System.out.println("Passenger no. = " + pno[i]);
                System.out.println("Name = " + name[i]);
                System.out.println("Class = " + getClassName(cl[i]));
                System.out.println("Phone no. = " + phno[i]);
                System.out.println("Age = " + age[i]);

                passengerFound = 1;
                break;
            }
        }

        if (passengerFound == 0) {
            System.out.println("No such passenger.");
        }
    }

    private static String getClassName(int c) {

        if (c == 1) {
            return "AC";
        } else if (c == 2) {
            return "First";
        } else if (c == 3) {
            return "Sleeper";
        }

        return "Unknown";
    }

    private static void doDispUnbooked() {

        System.out.println("\nNumber of unbooked tickets");
        System.out.println("AC class      : " + max1);
        System.out.println("First class   : " + max2);
        System.out.println("Sleeper class : " + max3);
    }

    private static void doExit() {

        System.out.println("\nThank you for using Railway Reservation System.");
        System.out.println("Program exited successfully.");
    }
}
