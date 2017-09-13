package no.martinpedersen.valutakalkulator;

/**
 * Created by marti on 13.09.2017.
 */

public enum Rate {

    A("7"),
    B("8"),
    C("9"),
    D("4"),
    E("5"),
    F("6"),
    G("1"),
    H("2"),
    I("3"),
    J("0"),
    K("Veksle");

    CharSequence mText; // Display Text

    Rate(CharSequence text) {
        mText = text;
    }

    public CharSequence getText() {
        return mText;
    }
}
