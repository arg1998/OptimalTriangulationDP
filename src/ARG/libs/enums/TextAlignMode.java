package ARG.libs.enums;

public enum TextAlignMode
{
    LEFT(39), CENTER(3) ,RIGHT(37);


    private final int _value;
    TextAlignMode(int _value) { this._value = _value; }
    public int getValue() { return _value; }
}
