package Main;

import java.util.*;
import java.io.*;

class Process {
    String name;
    List<String> resources;

    public Process(String name, List<String> resources) {
        this.name = name;
        this.resources = resources;
    }
}

public class ProcessScheduling {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("Test Cases - Project1.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            Queue<Process> queue = new LinkedList<>();
            List<String> resourcesInUse = new ArrayList<>();
            int cycles = 0;

            // Parse input and initialize queue
            String[] processes = line.split(";");
            for (String process : processes) {
                String name = process.split("\\(")[0].trim();
                List<String> resources = Arrays.asList(process.split("\\(")[1].replace(")", "").split(","));
                queue.add(new Process(name, resources));
            }

            // Process queue
            while (!queue.isEmpty()) {
                Iterator<Process> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    Process currentProcess = iterator.next();
                    if (!Collections.disjoint(resourcesInUse, currentProcess.resources)) { 
                        continue;
                    }
                    resourcesInUse.addAll(currentProcess.resources);
                    iterator.remove();
                }
                resourcesInUse.clear();
                cycles++;
            }

            System.out.println("Total number of cycles needed: " + cycles);
        }

        reader.close();
    }
}
