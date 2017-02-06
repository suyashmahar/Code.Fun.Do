package com.android.example.neighbours;

/**
 * Created by Suyash on 05-02-2017.
 */

public class Campaign {
    public String id;
    public String title;
    public String description;
    public String totalFunds;
    public String funds;
    public String fundsProgress;
    public String time;

    public Campaign(String description, String fundsProgress, String id, String title, String totalFunds, String funds) {
        this.description = description;
        this.fundsProgress = fundsProgress;
        this.id = id;
        this.title = title;
        this.totalFunds = totalFunds;
        this.funds = funds;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFundsProgress() {
        return fundsProgress;
    }

    public void setFundsProgress(String fundsProgress) {
        this.fundsProgress = fundsProgress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalFunds() {
        return totalFunds;
    }

    public void setTotalFunds(String totalFunds) {
        this.totalFunds = totalFunds;
    }
}
