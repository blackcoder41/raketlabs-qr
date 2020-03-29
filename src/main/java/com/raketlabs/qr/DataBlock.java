package com.raketlabs.qr;

import java.util.ArrayList;

import com.raketlabs.util.CRC;

public class DataBlock {
        
    private ArrayList<DataField> mDataFields = new ArrayList<DataField>();
    
    
    public DataBlock addField (String id, String value) {
        DataField field = new DataField(id, value);
        mDataFields.add(field);
        
        
        return this;
    }
    
    public DataField getField (String id) {
        DataField ret = null;
        
        for (DataField f : mDataFields) {
            if (f.getId() == id) {
                ret = f;
                break;
            }
        }
    
        return ret;
    }
    
    @Override
    public String toString () {
        
        String data = "";
        
        for (DataField f : mDataFields) {
            data += f;
        }
        
        return data;
    }

    public class DataField {
        
        String id;
        String value;
        
        public DataField (String id, String value) {
            setId(id);
            setValue(value);
        }
        
        public String getId () {
            return this.id;
        }
        
        public void setId (String id) {
            this.id  = id;
        }
        
        public String getValue () {
            return this.value;
        }
        
        public void setValue (String value) {
            if (value == null)
                this.value = "";
            else
                this.value = value;
        }
        
        public String getLength () {
            return String.format("%02d", value.length());
        }
        
        @Override
        public String toString () {
            return getId() + getLength() + getValue();
        }
        
    }
}
