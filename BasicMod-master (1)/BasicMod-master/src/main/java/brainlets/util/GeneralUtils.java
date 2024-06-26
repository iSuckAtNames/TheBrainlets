package brainlets.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

public class GeneralUtils {
    public static String arrToString(Object[] arr) {
        if (arr == null)
            return null;
        if (arr.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length - 1; ++i) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[arr.length - 1]);
        return sb.toString();
    }

    public static String removePrefix(String ID) {
        return ID.substring(ID.indexOf(":") + 1);
    }

    public static ArrayList<AbstractCard> cardsCreatedThisTurn = new ArrayList<>();
    public static ArrayList<AbstractCard> cardsCreatedThisCombat = new ArrayList<>();

    public static void receiveOnPlayerTurnStart() {
        cardsCreatedThisTurn.clear();
    }

    public static void receiveOnBattleStart(AbstractRoom abstractRoom) {
        cardsCreatedThisTurn.clear();
        cardsCreatedThisCombat.clear();
    }

    public static void onGenerateCardMidcombat(AbstractCard c) {
        cardsCreatedThisCombat.add(c);
        cardsCreatedThisTurn.add(c);
    }
}
