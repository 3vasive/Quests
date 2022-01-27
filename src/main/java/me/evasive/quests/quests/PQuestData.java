package me.evasive.quests.quests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
@Getter @Setter
public class PQuestData {
    private int P1 = 0;
    private int P2 = 0;
    private int P3 = 0;
    private int P4 = 0;
    private int P5 = 0;
    private int P6 = 0;
    private int P7 = 0;
    private int P8 = 0;
    private int P9 = 0;
    private int P10 = 0;
    private int P11 = 0;
    private int P12 = 0;
    private ArrayList<Quest> QL = new ArrayList();
    private Boolean PC1 = false;
    private Boolean PC2 = false;
    private Boolean PC3 = false;
    private Boolean PC4 = false;
    private Boolean PC5 = false;
    private Boolean PC6 = false;
    private Boolean PC7 = false;
    private Boolean PC8 = false;
    private Boolean PC9 = false;
    private Boolean PC10 = false;
    private Boolean PC11 = false;
    private Boolean PC12 = false;
    private LocalDate date = null;

    public int getQuestProgress(int questNum, PQuestData progress){
        if (questNum == 1) return progress.P1;
        if (questNum == 2) return progress.P2;
        if (questNum == 3) return progress.P3;
        if (questNum == 4) return progress.P4;
        if (questNum == 5) return progress.P5;
        if (questNum == 6) return progress.P6;
        if (questNum == 7) return progress.P7;
        if (questNum == 8) return progress.P8;
        if (questNum == 9) return progress.P9;
        if (questNum == 10) return progress.P10;
        if (questNum == 11) return progress.P11;
        if (questNum == 12) return progress.P12;
        return 0;
    }

    public boolean checkQuestComplete(int questNum, PQuestData progress){
        if (questNum == 1) return progress.PC1;
        if (questNum == 2) return progress.PC2;
        if (questNum == 3) return progress.PC3;
        if (questNum == 4) return progress.PC4;
        if (questNum == 5) return progress.PC5;
        if (questNum == 6) return progress.PC6;
        if (questNum == 7) return progress.PC7;
        if (questNum == 8) return progress.PC8;
        if (questNum == 9) return progress.PC9;
        if (questNum == 10) return progress.PC10;
        if (questNum == 11) return progress.PC11;
        if (questNum == 12) return progress.PC12;
        return false;
    }

    public void setQuestComplete(int questNum, PQuestData progress){
        if (questNum == 1) progress.PC1 = true;
        if (questNum == 2) progress.PC2 = true;
        if (questNum == 3) progress.PC3 = true;
        if (questNum == 4) progress.PC4 = true;
        if (questNum == 5) progress.PC5 = true;
        if (questNum == 6) progress.PC6 = true;
        if (questNum == 7) progress.PC7 = true;
        if (questNum == 8) progress.PC8 = true;
        if (questNum == 9) progress.PC9 = true;
        if (questNum == 10) progress.PC10 = true;
        if (questNum == 11) progress.PC11 = true;
        if (questNum == 12) progress.PC12 = true;
    }

    public void setQuestProgress(int questNum, PQuestData progress, int input){
        if (questNum == 1) progress.P1 = input;
        if (questNum == 2) progress.P2 = input;
        if (questNum == 3) progress.P3 = input;
        if (questNum == 4) progress.P4 = input;
        if (questNum == 5) progress.P5 = input;
        if (questNum == 6) progress.P6 = input;
        if (questNum == 7) progress.P7 = input;
        if (questNum == 8) progress.P8 = input;
        if (questNum == 9) progress.P9 = input;
        if (questNum == 10) progress.P10 = input;
        if (questNum == 11) progress.P11 = input;
        if (questNum == 12) progress.P12 = input;
    }

    public void addProgress(int questNum, PQuestData progress) {
        setQuestProgress(questNum, progress, getQuestProgress(questNum, progress) + 1);
    }
}
