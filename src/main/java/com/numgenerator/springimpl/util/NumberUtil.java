package com.numgenerator.springimpl.util;

import com.numgenerator.springimpl.bean.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class NumberUtil {
    final static int MIN_VALUE = 10;
    final static int MAX_VALUE = 30;

    public static List<String> getTaskResult(List<Task> tasks) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for(Task task : tasks){
            int goal = Integer.parseInt(task.getGoal());
            int step = Integer.parseInt(task.getStep());
            list.add(NumberUtil.getNumbers(goal, step));
        }
        return list;
    }

    public static String getNumbers(int goal, int step) throws InterruptedException {
        StringJoiner joiner = new StringJoiner(",");
        while(goal >= 0){
            joiner.add(String.valueOf(goal));
            goal = goal-step;
            int randomNum = ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1);
            TimeUnit.SECONDS.sleep(randomNum);
        }
        return joiner.toString();
    }
}
