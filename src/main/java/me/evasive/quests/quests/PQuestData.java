package me.evasive.quests.quests;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter @Setter
public class PQuestData {
    private ArrayList<Quest> QL = new ArrayList();
    private LocalDate date = null;
    private ArrayList<Integer> Progress = new ArrayList<Integer>() {
        {
            for (int i = 0; i < 13; i++){
                add(0);
            }
        }
    };
    private ArrayList<Boolean> Completed = new ArrayList<Boolean>() {
        {
            for (int i = 0; i < 13; i++){
                add(false);
            }
        }
    };

    public int getQuestProgress(int questNum, PQuestData progress){
        //Progress Array List Loop
        if (progress.Progress.get(questNum - 1) != null) return progress.Progress.get(questNum - 1);
        return 0;
    }

    public boolean checkQuestComplete(int questNum, PQuestData progress){
        //Completed Array List Loop
        if (progress.Completed.get(questNum - 1) != null) return progress.Completed.get(questNum - 1);
        return false;
    }

    public void setQuestComplete(int questNum, PQuestData progress){
        //Completed Array List Set
        progress.Completed.set(questNum - 1, true);
    }

    public void setQuestProgress(int questNum, PQuestData progress, int input){
        //Progress Array List Loop
        progress.Progress.set(questNum - 1, input);
    }

    public void addProgress(int questNum, PQuestData progress) {
        //Make sure to replace function
        setQuestProgress(questNum, progress, getQuestProgress(questNum, progress) + 1);
    }
}
