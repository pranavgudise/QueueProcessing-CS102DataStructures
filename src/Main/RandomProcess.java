package Main;

import java.util.*;

class Process_b {
    String name;
    List<String> resources;

    public Process_b(String name, List<String> resources) {
        this.name = name;
        this.resources = resources;
    }
}

public class RandomProcess {
    private static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        List<String> allResources = Arrays.asList("A", "B", "C");

        // Generate initial process list
        Queue<Process_b> queue = new LinkedList<>();
        for (int i = 1; i <= 20; i++) {
            queue.add(new Process_b("P" + i, getRandomResources(allResources)));
        }

        List<String> resourcesInUse = new ArrayList<>();
        int cycles = 0;
        while (!queue.isEmpty() && cycles < 1000) {
            Iterator<Process_b> iterator = queue.iterator();
            while (iterator.hasNext()) {
                Process_b currentProcess = iterator.next();
                if (!Collections.disjoint(resourcesInUse, currentProcess.resources)) { 
                    continue;
                }
                resourcesInUse.addAll(currentProcess.resources);
                iterator.remove();
            }
            resourcesInUse.clear();
            cycles++;

            // Add two new processes
            queue.add(new Process_b("P" + (cycles + 20), getRandomResources(allResources)));
            queue.add(new Process_b("P" + (cycles + 21), getRandomResources(allResources)));

            if (cycles % 100 == 0) {
                System.out.println("Length of processes at cycle " + cycles + ": " + queue.size());
            }
        }

        if (!queue.isEmpty()) {
            System.out.println("After 1000 cycles, there are " + queue.size() + " processes left.");
        } else {
            System.out.println("Total number of cycles needed: " + cycles);
        }
    }

    private static List<String> getRandomResources(List<String> allResources) {
        int resourceCount = 1 + random.nextInt(3);
        Collections.shuffle(allResources);
        return new ArrayList<>(allResources.subList(0, resourceCount));
    }
}
