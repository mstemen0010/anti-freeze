/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze.gui.dashboard;

import java.awt.event.KeyListener;

/**
 *
 * @author Matt
 */
public interface DashKeyInterface extends KeyListener{

    enum KeyOp // usable only in the package. This is because we use it below...
    {
        KeyOpRangeBegin(-501),
        Divide(-500),
        Multipl(-499),
        Minus(-498),
        Plus(-497),
        Modulus(-496),
        Power(-495),

        KeyOpRangeEnd(-300);
        int v = -501; // key op range begin

        KeyOp( int i )
        {
            v = i;
        }

        boolean isKeyOp( int keyOpValue )
        {
            return keyOpValue > KeyOpRangeBegin.ordinal() && keyOpValue < KeyOpRangeEnd.ordinal();
        }

        static int doAddOp(int a, int b)
        {
            return a + b;
        }

        static int doMinusOp(int a, int b)
        {
            return a - b;
        }

        static int doMultOp(int a, int b )
        {
            return a * b;
        }

        static int doDivideOp(int a, int b)
        {
            return a/b;
        }

        static int doModOp( int a, int b )
        {
            return a%b;
        }

        static int doPowOp(int a, int b)
        {
            return a^b;
        }

        static long doAddOp( long a, long b)
        {
            return a + b;
        }

        static double doAddOp( double a, double b)
        {
            return a + b;
        }

        static float doAddOp( float a, float b)
        {
            return a + b;
        }

        /// and so on.....


    }

    public enum KeyValue
    {
        KeyValueBegin(-1),
        Zero(0),
        One(1),
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        NumLock(10),
        Clear(11),
        Divide(KeyOp.Divide.ordinal()),
        Plus(KeyOp.Plus.ordinal()),
        Minus(KeyOp.Minus.ordinal()),
        Multil(KeyOp.Multipl.ordinal()),
        Modulus(KeyOp.Modulus.ordinal()),
        Enter(998),
        Equals(999),
        KeyValueEnd(1000);


        int kv = -2;
        int compValue = -2;

        KeyValue( int v )
        {
            kv = v;
        }

        boolean isComputable()
        {
            return kv >= Zero.ordinal() && kv <= Nine.ordinal();
        }

        public int doAddOp(int a, int b)
        {
            compValue = KeyOp.doAddOp(a,b);
            return compValue;
        }

        public int getCompValue()
        {
            return compValue;
        }
    }

    public KeyValue getKeyValue();


    public KeyOp getKeyOp();
}
