package tests.main;

// For drawing cards (ie the action).
//
// example of usage by Yu Nakagawa:
//
// public class AnyCardPicker implements CardPicker {
//     int code;
//     AnyCardPicker(int code) {
//         this.code = code;
//     }
//
//     int pickCard (int[] cards) {
//         return code;
//     }
// }
//
// roma.useActionDieForCards(6, AnyCardPicker(CardCode.FORUM));
//
// for more information, see
// https://cgi.cse.unsw.edu.au/~cs2911cgi/10s1/cgi-bin/ikonboard/ikonboard.cgi?act=ST;f=10;t=48

public interface CardPicker {
   int pickCard(int[] cards);
}
