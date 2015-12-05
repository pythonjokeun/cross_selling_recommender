/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Code;

/**
 *
 * @author Ichsan Hariadi
 */
public class ComboValue {
    
    private String _Key;
    private String _Value;

    /**
     * @return the _Key
     */
    public String getKey() {
        return _Key;
    }

    /**
     * @param _Key the _Key to set
     */
    public void setKey(String _Key) {
        this._Key = _Key;
    }

    /**
     * @return the _Value
     */
    public String getValue() {
        return _Value;
    }

    /**
     * @param _Value the _Value to set
     */
    public void setValue(String _Value) {
        this._Value = _Value;
    }
    
    @Override
    public String toString()
    {
        return this._Value;
    }
}
