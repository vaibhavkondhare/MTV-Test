// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.ca.gen85.csu.exception;

import com.ca.gen85.csu.trace.Trace;
import java.io.*;

public class CSUException extends Exception
{

    public CSUException(String s, String s1)
    {
        super(s1);
        function = null;
        function = s;
        if(Trace.isTracing(1))
        {
            CharArrayWriter chararraywriter = new CharArrayWriter();
            PrintWriter printwriter = new PrintWriter(chararraywriter);
            printStackTrace(printwriter);
            Trace.dump(1, "CSUException", "CSUException created:", chararraywriter.toString());
        }
    }

    public CSUException(Throwable throwable)
    {
        super(throwable.toString());
        function = null;
        StringWriter stringwriter = new StringWriter();
        printStackTrace(new PrintWriter(stringwriter));
        LineNumberReader linenumberreader = new LineNumberReader(new StringReader(stringwriter.toString()));
        try
        {
            linenumberreader.readLine();
            String s = linenumberreader.readLine();
            function = s.substring(s.indexOf("at ") + 3, s.indexOf("("));
        }
        catch(Exception exception) { }
        if(Trace.isTracing(1))
            Trace.dump(1, "CSUException", "CSUException created:", stringwriter.toString());
    }

    public CSUException(String s)
    {
        super(s);
        function = null;
        StringWriter stringwriter = new StringWriter();
        printStackTrace(new PrintWriter(stringwriter));
        LineNumberReader linenumberreader = new LineNumberReader(new StringReader(stringwriter.toString()));
        try
        {
            linenumberreader.readLine();
            String s1 = linenumberreader.readLine();
            function = s1.substring(s1.indexOf("at ") + 3, s1.indexOf("("));
        }
        catch(Exception exception) { }
        if(Trace.isTracing(1))
            Trace.dump(1, "CSUException", "CSUException created:", stringwriter.toString());
    }

    public String getFunction()
    {
        return function;
    }

    public String getMessage()
    {
        return (new StringBuilder()).append(" [Function: ").append(function).append("]").append(super.getMessage()).toString();
    }

    public void setFunction(String s)
    {
        function = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append(": ").append(getMessage()).toString();
    }

    protected String function;
}
