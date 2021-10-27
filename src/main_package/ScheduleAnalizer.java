package main_package;

import java.util.ArrayList;

public class ScheduleAnalizer {
    public ArrayList<Integer> minMaxPoints; // list with all the extremums - minimum and maximum points of the schedule
    int[] schedule; // array with the schedule values: schedule[x] = y;
    int width, height;
    // width - width of the screen;
    // height - height of the screen;

    int scheduleHeight; // height of the schedule - height without upper and lower empty parts
    double minDiffOfHeight; // minimal needed difference of height we need to determine if a point is Min or Max or none

    final int percent = 1; // percent of schedule height with which we calculate minDiffOfHeight

    //final Color black = new Color(0, 0, 0);

    private static final double quarter = 0.25;

    ScheduleAnalizer() {
        ScheduleConverter converter = new ScheduleConverter();
        schedule = converter.schedule;
        width = converter.width;
        height = converter.height;

        setScheduleHeight();
        setMinDiffOfHeight();

        getMinMaxPoints();
        getClosest();
    }
    private void setMinDiffOfHeight() { // setter of minDiffOfHeight - it's (percent/100) * height (of the schedule)
        minDiffOfHeight = (double) scheduleHeight * (percent/100.0);
    }

    private void setScheduleHeight() {
        int min = height, max = 0;
        for (int x=0; x<width; x++) {
            min = Math.min(min, schedule[x]);
            max = Math.max(max, schedule[x]);
        }
        scheduleHeight = max - min + 1;
    }

    private boolean isMinPoint(int pointX) {
        int pointY = schedule[pointX];
        boolean minLeft = false, minRight = false;
        for (int x=pointX-1; x>=0; x--) {
            int diff = schedule[x] - pointY;
            if (diff>=0) return false;
            if (-diff>minDiffOfHeight) {
                minLeft = true; break;
            }
        }
        for (int x=pointX+1; x<width; x++) {
            int diff = schedule[x] - pointY;
            if (diff>0) return false;
            if (-diff>minDiffOfHeight) {
                minRight = true; break;
            }
        }
        if (minLeft && minRight) return true;
        return false;
    }

    private boolean isMaxPoint(int pointX) {
        int pointY = schedule[pointX];
        boolean minLeft = false, minRight = false;
        for (int x=pointX-1; x>=0; x--) {
            int diff = schedule[x] - pointY;
            if (diff<=0) return false;
            if (diff>minDiffOfHeight) {
                minLeft = true; break;
            }
        }
        for (int x=pointX+1; x<width; x++) {
            int diff = schedule[x] - pointY;
            if (diff<0) return false;
            if (diff>minDiffOfHeight) {
                minRight = true; break;
            }
        }
        if (minLeft && minRight) return true;
        return false;
    }

    private void getMinMaxPoints() {
        minMaxPoints = new ArrayList<>();
        minMaxPoints.add(0);
        for (int x=1; x<width-1; x++) {
            if (isMinPoint(x) || isMaxPoint(x)) {
                minMaxPoints.add(x);
            }
        }
        minMaxPoints.add(width - 1);
    }

    int getMaxJump() {
        int maxJump = width;
        for (int i=0; i<minMaxPoints.size() - 1; i++) {
            maxJump = Math.min(maxJump, minMaxPoints.get(i+1) - minMaxPoints.get(i));
        }
        return maxJump;
    }

    double calculatePart(int jump) {
        boolean[] take = new boolean[width];
        if (jump>0) {
            for (int x=0; x<width; x+=jump) {
                take[x] = true;
            }
        }
        for (int x:minMaxPoints) take[x] = true;

        int sum = 0, cnt = 0;
        int prev = 0;
        for (int x=1; x<width; x++) {
            if (take[x]) {
                //System.out.println(Math.abs(schedule[x] - schedule[prev]));
                sum += Math.abs(schedule[x] - schedule[prev]);
                prev = x;
                cnt++;
            }
        }
        double average = (double) sum/cnt;
        //System.out.println(average+" "+sum+" "+cnt);
        return average/(scheduleHeight + 2);
    }

    void getClosest() {
        int maxJump = getMaxJump();
        /*System.out.println(Arrays.toString(schedule));
        System.out.println(MinMaxPoints);
        System.out.println(maxJump);*/
        double optimalPart = 1, optimalDiff = 1;
        int optimalJump = -1;
        for (int jump = 1; jump<=maxJump; jump++) {
            double part = calculatePart(jump);
            if (Math.abs(quarter - part)<optimalDiff) {
                optimalPart = part;
                optimalDiff = Math.abs(quarter - part);
                optimalJump = jump;
            }

        }
        double part = calculatePart(-1);
        if (Math.abs(quarter - part)<optimalDiff) {
            optimalPart = part;
            optimalDiff = Math.abs(quarter - part);
            optimalJump = -1;
        }
        System.out.println(optimalPart+" "+optimalJump+" "+optimalDiff);
    }
    public static void main(String[] args) {
        new ScheduleAnalizer();
    }
}
