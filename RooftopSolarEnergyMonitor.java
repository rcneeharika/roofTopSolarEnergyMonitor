import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

// ==========================================
// MODULE 4: OOP, Encapsulation & toString()
// ==========================================
class SolarReading implements Comparable<SolarReading> {
private String timestamp; // Module 5: String abstraction
private double powerOutput; // in kilowatts

public SolarReading(String timestamp, double powerOutput) {
this.timestamp = timestamp;
this.powerOutput = powerOutput;
}

public double getPowerOutput() { return powerOutput; }
public String getTimestamp() { return timestamp; }

@Override
public String toString() {
return timestamp + " : " + powerOutput + " kW";
}

// MODULE 5: Comparable interface for ordering objects
@Override
public int compareTo(SolarReading other) {
return Double.compare(this.powerOutput, other.powerOutput);
}
}

class SolarEnergyMonitor {
// MODULE 6: Collections Framework (List, Map) & Generics
private List<SolarReading> readings;

public SolarEnergyMonitor() {
this.readings = new ArrayList<>();
}

public void addReading(SolarReading reading) {
readings.add(reading);
}

public List<SolarReading> getReadings() {
return readings;
}

// MODULE 3: Summation & Average techniques
public double totalEnergy() {
return readings.stream()
.mapToDouble(SolarReading::getPowerOutput)
.sum();
}

public double averagePower() {
return readings.stream()
.mapToDouble(SolarReading::getPowerOutput)
.average()
.orElse(0.0);
}

// MODULE 6: Stream Pipelines (max, filter, collect)
public Optional<SolarReading> peakPower() {
return readings.stream()
.max(Comparator.comparingDouble(SolarReading::getPowerOutput));
}

// MODULE 3: Recursion example (e.g., recursive count of readings above threshold)
public int countAboveThreshold(double threshold, int index) {
if (index >= readings.size()) {
return 0;
}
int count = (readings.get(index).getPowerOutput() > threshold) ? 1 : 0;
return count + countAboveThreshold(threshold, index + 1);
}

// MODULE 6: Collectors (groupingBy for advanced reports)
public Map<Boolean, List<SolarReading>> partitionByPerformance(double threshold) {
return readings.stream()
.collect(Collectors.partitioningBy(r -> r.getPowerOutput() >= threshold));
}

public void displayReadings() {
readings.forEach(System.out::println);
}
}

public class RooftopSolarMonitorApp {

// MODULE 4: Exception Handling using 'throws'
public static void main(String[] args) throws IOException {
// MODULE 1: Console I/O with Scanner & Primitive variables
Scanner scanner = new Scanner(System.in);
SolarEnergyMonitor monitor = new SolarEnergyMonitor();

System.out.println("Enter solar panel readings (<timestamp> <powerOutput>). Type 'end' to finish:");

// MODULE 2: Control Flow (while loop, break, continue, if-else)
while (true) {
try {
String input = scanner.nextLine();

// MODULE 5: String comparison and manipulation (equalsIgnoreCase, trim, split)
if (input.equalsIgnoreCase("end")) {
break;
}

String[] parts = input.trim().split("\\s+");
if (parts.length != 2) {
System.out.println("Invalid input format. Use: <timestamp> <powerOutput>");
continue; // Module 2: continue statement
}

String timestamp = parts[0];
// MODULE 1: Typecasting / parsing wrapper classes
double powerOutput = Double.parseDouble(parts[1]);

monitor.addReading(new SolarReading(timestamp, powerOutput));

2620030332_Neeharika:
} catch (NumberFormatException e) {
// MODULE 4: Exception handling (try-catch block)
System.out.println("Invalid power value. Please enter a numeric value.");
} finally {
// MODULE 4: try-catch-finally block demonstration
// (Cleanup or execution tracking can go here)
}
}

// Display basic results using formatted printing (Module 1)
System.out.println("\n--- Solar Panel Energy Readings ---");
monitor.displayReadings();

System.out.printf("Total Energy Generated: %.2f kW\n", monitor.totalEnergy());
System.out.printf("Average Power Output: %.2f kW\n", monitor.averagePower());

monitor.peakPower().ifPresent(p -> System.out.println("Peak Power Output: " + p));

// ==========================================
// MODULE 5 & 6: File I/O & File Streams
// ==========================================
Path filePath = Paths.get("solar_report.txt");

// Writing text files using modern java.nio.file APIs & try-with-resources
try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
writer.write("=== SOLAR MONITOR REPORT ===\n");
for (SolarReading r : monitor.getReadings()) {
writer.write(r.toString() + "\n");
}
writer.write("Total: " + monitor.totalEnergy() + " kW\n");
System.out.println("\nSuccessfully saved report to file: " + filePath.toAbsolutePath());
} catch (IOException e) {
System.out.println("Error writing to file: " + e.getMessage());
}

// Reading file lines back as a stream (Module 6 feature)
System.out.println("\n--- Reading file lines back using Stream API ---");
try (Stream<String> lines = Files.lines(filePath)) {
lines.limit(4).forEach(System.out::println);
}

scanner.close();
}
}
