/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.antifreeze.entity;

import com.wms.jdbtk3.VRecord;
import java.util.Iterator;

/**
 *
 * @author Matt
 */
public abstract interface AFEntityInterface {

    /**
     *
     */
    public enum AFField {

        Undefined,
        CusID,
        CusLastName,
        CusFirstName,
        CusMI,
        CusOldName,
        CusStreet1,
        CusStreet2,
        CusCity,
        CusState,
        CusZip,
        CusHome,
        CusWork,
        CusCell,
        CusFax,
        CusNotes,
        CusLastVisit,
        AE_CusId,
        Mail,
        Remind,
        ARNum,
        ARStatus,
        ARNow,
        AREver,
        ARDate,
        BalanceDue,
        PreviousDue,
        AR30,
        AR60,
        AR90,
        AR120,
        Contact,
        NonTaxable,
        CusLimit,
        Company,
        MgrID;

        public static String getCusFields()
        {
            String fields = null;
            StringBuilder sb = new StringBuilder();
            AFField values[] = AFField.values();
            for( int i = CusID.ordinal(); i < MgrID.ordinal(); i++ )
            {
                AFField field = values[i];
                sb.append(field.toString()).append(",");
            }
            fields = sb.toString();
            fields = fields.substring(0, fields.length() - 1); // remove the trailing ","

            return fields;
        }
    }

    public Iterator<String> getEntityKeys();

    public void syncToVRecord(VRecord recToSyncTo);

    public String valueForKey(String key);

    public VRecord getVRecord();
}
