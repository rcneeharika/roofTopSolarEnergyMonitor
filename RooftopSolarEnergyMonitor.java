import java.util.Scanner;

public class RooftopSolarEnergyMonitor {

    // METHOD 1: Calculates daily energy generated (kWh = kW * hours)
    static double calculateEnergy(double power, double hours) {
        return power * hours; // Arithmetic operator (*)
    }

    // METHOD 2: Calculates total cumulative energy using a FOR-EACH loop
    static double calculateTotal(double[] energyReadings) {
        double total = 0.0;
        
        // CONCEPT: FOR-EACH LOOP
        for (double singleDayEnergy : energyReadings) {
            total += singleDayEnergy; // Arithmetic operator (+=)
        }
        
        return total;
    }

    public static void main(String[] args) {
        // CONCEPT: SCANNER CLASS
        Scanner sc = new Scanner(System.in);

        // CONCEPT: DATA TYPES (String, int, double, boolean)
        String ownerName;
        int numberOfDays;
        double panelPower;
        double totalEnergy;
        boolean isSystemActive = true;

        System.out.println("=== Rooftop Solar Energy Monitor System ===");
        
        System.out.print("Enter Owner Name: ");
        ownerName = sc.nextLine();

        System.out.print("Enter Solar Panel Power Rating (in kW): ");
        panelPower = sc.nextDouble();

        System.out.print("Enter Number of Days to Monitor: ");
        numberOfDays = sc.nextInt();

        // Array data type to store daily generation
        double[] dailyEnergy = new double[numberOfDays];

        // CONCEPT: FOR LOOP (Used for taking daily readings)
        System.out.println("\n--- Enter Sunlight Hours ---");
        for (int day = 0; day < numberOfDays; day++) {
            System.out.print("Day " + (day + 1) + " Sunlight Hours: ");
            double sunlightHours = sc.nextDouble();
            
            // Method call
            dailyEnergy[day] = calculateEnergy(panelPower, sunlightHours);
        }

        // Method call using FOR-EACH LOOP inside calculateTotal()
        totalEnergy = calculateTotal(dailyEnergy);

        // Display Summary Section
        System.out.println("\n=================================");
        System.out.println("     SOLAR ENERGY REPORT        ");
        System.out.println("=================================");
        System.out.println("System Owner : " + ownerName);
        System.out.println("Panel Capacity: " + panelPower + " kW");

        // CONCEPT: WHILE LOOP (Used for displaying daily array elements)
        System.out.println("\n--- Daily Breakdown ---");
        int dayCounter = 0;
        while (dayCounter < numberOfDays) {
            System.out.println("Day " + (dayCounter + 1) + ": " + dailyEnergy[dayCounter] + " kWh");
            dayCounter++; // Arithmetic operator (++)
        }

        System.out.println("\nTotal Generation: " + totalEnergy + " kWh");

        // CONCEPT: CONDITIONAL STATEMENTS (if - else if - else ladder)
        double benchmarkThreshold = numberOfDays * panelPower * 4.0;
        
        if (totalEnergy >= benchmarkThreshold) {
            System.out.println("System Performance Status: Excellent");
        } else if (totalEnergy >= (benchmarkThreshold - 10.0)) { // Arithmetic operator (-)
            System.out.println("System Performance Status: Good / Average");
        } else {
            System.out.println("System Performance Status: Needs Inspection (Low Efficiency)");
        }

        // CONCEPT: DO-WHILE LOOP (Used to keep menu active)
        int menuChoice;
        do {
            System.out.println("\n--- Interactive Menu Options ---");
            System.out.println("1. Show Total Generation Summary");
            System.out.println("2. Calculate Average Daily Output");
            System.out.println("3. Exit Program");
            System.out.print("Select an option (1-3): ");
            menuChoice = sc.nextInt();

            // CONCEPT: SWITCH CASE (Used for menu selection)
            switch (menuChoice) {
                case 1:
                    System.out.println("Result: Total Energy generated over " + numberOfDays + " day(s) is " + totalEnergy + " kWh.");
                    break;

                case 2:
                    double averageEnergy = totalEnergy / numberOfDays; // Arithmetic operator (/)
                    System.out.println("Result: Average Daily Generation is " + averageEnergy + " kWh/day.");
                    break;

                case 3:
                    System.out.println("Exiting Rooftop Solar Monitor. Thank you!");
                    isSystemActive = false;
                    break;

                default:
                    System.out.println("Invalid Selection! Please choose 1, 2, or 3.");
                    break;
            }

        } while (menuChoice != 3 && isSystemActive);

        sc.close();
    }
}