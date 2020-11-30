package com.numgenerator.springimpl.bean;

public class Task {
    String goal;
    String step;

    public Task(String goal, String step){
        this.goal = goal;
        this.step = step;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

}
