package ARG.libs.enums;

public enum CapMode
{
    ROUND(2), soft(4) ,Hard(1);


    private final int _value;
    CapMode(int _value) { this._value = _value; }
    public int getValue() { return _value; }
}
