package brainlets.util;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.ArrayList;

public class BuffLossManager {
    public static int buffsLostThisCombat;
    public static ArrayList<Integer> activeBuffAmounts = new ArrayList<>();

    public static void resetBuffTracker(){
        activeBuffAmounts.clear();
        buffsLostThisCombat = 0;
    }

    public static void onPowersModified(){
        ArrayList<Integer> currentBuffAmounts = new ArrayList<>();
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p.type == AbstractPower.PowerType.BUFF){
                currentBuffAmounts.add(p.amount);
            }
        }

        if(activeBuffAmounts.size() > 0){
            activeBuffAmounts.removeAll(currentBuffAmounts);
            for (int i = 0; i < activeBuffAmounts.size(); i++) {
                onBuffLost();
            }
        }
        activeBuffAmounts = currentBuffAmounts;
    }

    public static void onBuffLost(){
        buffsLostThisCombat++;
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p instanceof OnBuffLossPower){
                ((OnBuffLossPower) p).onBuffLoss();
            }
        }
    }
}
