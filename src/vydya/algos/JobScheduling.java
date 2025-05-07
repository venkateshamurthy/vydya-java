package vydya.algos;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling {

    static class Job {
        char id;
        int deadline;
        int profit;

        public Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }

        public int getProfit() {
            return profit;
        }

        public String toString() {
            return id + " " + deadline + " " + profit;
        }
    }

    public static void main(String[] args) {

        //numJobs = 5
        // a 2 100
        // b 1 19
        // c 2 27
        // d 1 25
        // e 3 15
        // slots = 3
        //answer c a e
        runTC1();
        /*
        Scanner scanner = new Scanner(System.in);
        int numJobs  = scanner.nextInt();
        Job[] jobs = new Job[numJobs];
        for (int i = 0; i < numJobs; i++) {
            char id = scanner.next().charAt(0);
            int deadline = scanner.nextInt();
            int profit = scanner.nextInt();
            jobs[i] = new Job(id, deadline, profit);
        }
        int numSlots = scanner.nextInt();
        jobScheduling(jobs, numSlots);
        */

    }

    static void runTC1() {
        Job[] jobs = new Job[]{
                new Job('a', 2, 100),
                new Job('b', 1, 19),
                new Job('c', 2, 27),
                new Job('d', 1, 25),
                new Job('e', 3, 15)
        };
        Job[] jobs2 = new Job[]{
                new Job('a', 4, 20),
                new Job('b', 1, 1),
                new Job('c', 1, 40),
                new Job('d', 1, 30)
        };
        jobScheduling(jobs, 3);
        jobScheduling(jobs2, 2);
    }

    private static void jobScheduling(Job[] jobs, int numSlots) {
        Arrays.sort(jobs,
                (a, b) -> b.profit - a.profit
                //Comparator.comparingInt(Job::getProfit).reversed()
        );
        System.out.println("Jobs:="+Arrays.toString(jobs));

        char[] result = new char[numSlots];
        boolean[] slot = new boolean[numSlots];
        for (Job job : jobs) {
            System.out.println("numSlots = "+ numSlots+" " + job.id + " "  + job.deadline+ " min="+(Integer.min(numSlots, job.deadline) - 1));
            for (int j = Integer.min(numSlots, job.deadline) - 1; j >= 0; j--) {
                if ( ! slot[j]) {
                    result[j] = job.id;
                    slot[j] = true;
                    break;
                }
            }
        }

        for (char jobId : result) {
            System.out.print(jobId + " ");
        }
        System.out.println();
    }


}
