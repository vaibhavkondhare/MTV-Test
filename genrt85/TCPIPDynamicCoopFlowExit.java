//######################################################################
//#             Communication Infrastructure                           #
//# CA Gen                                                             #
//# Copyright (c) 2013 CA. All rights reserved.                        #
//#                                                                    #
//#--------------------------------------------------------------------#
//# who  !   when   !        what                                      #
//#------!----------!--------------------------------------------------#
//# THOD ! 03/22/99 ! 10087008 Created                                 #
//# THOD ! 06/28/99 ! 10138090 Modified to support getInstance approach#
//# THOD ! 11/08/99 ! 10143209 Made methods final for performance.     #
//# THOD ! 11/08/99 ! 10143209 Modified to support freeInstance.       #
//# THOD ! 12/30/99 ! 10141946 Moved packages and build projects       #
//# THOD ! 02/09/00 ! 10144263 Made methods not-final for functionality.#
//# THOD ! 04/16/01 ! 10141946 Added getMaximumBufferSize              #
//# TTT  ! 04/26/02 !          Eliminated need to synchronize which    #
//#      !          !          causes application to hang on WebSphere.#
//# JMS  ! 01/30/03 ! 12479392 runtimeObject acces                     #
//# THOD ! 07/10/03 ! 12812053 do double check in getInstance          #
//# THOD ! 01/23/04 !          Handle new TranData fields.             #
//#levin ! 11/10/08 ! 17400752 support non persistent client connection#
//# THOD ! 11/30/10 ! 19846272 Remove UNLIMITED_MESSAGE_SIZE,          #
//#      !          !          DEFAULT_MESSAGE_SIZE and                #
//#      !          !          getMaximumBufferSize()                  #
//#--------------------------------------------------------------------#
//######################################################################

package com.ca.gen85.exits.coopflow.tcpip;

import com.ca.gen85.csu.exception.*;

/**
 * This class will be called prior to performing a TCPIP connection
 * from the TCPIPDynamicCoopFlow.  The class will be instantiated with
 * various data and methods will be called to override that data.
 */
public class TCPIPDynamicCoopFlowExit {
    private String hostName = null;
    private Integer port = null;
    private boolean clientPersistence = true;
    private static final TCPIPDynamicCoopFlowExit[] freeArray = new TCPIPDynamicCoopFlowExit[20];
    private static int countFree = 0;

    /*
     * To preserve encapsulation, the runtimeObject exists as a generic Object
     * type to not break. In a web context, the HttpServletRequest object will
     * be available as the runtimeObject. Otherwise, this is the Gen Runtime's
     * request object.
     *
     * To utilize this object as an HttpServletRequest object, add the following:
     * 1. import javax.servlet.*;
     * 2. import javax.servlet.http.*;
     * Add the following code to any method that uses the object:
     * if ((runtimeObject != null) && (runtimeObject instanceof HttpServletRequest)) {
     *   HttpServletRequest request = (HttpServletRequest) runtimeObject;
     *   ... // REST OF THE USER CODE GOES HERE
     * }
     */

/**
 * This method will be called to retrieve an instance of the exit
 * class.  By default, a new instance is created for each
 * request if one does not already exist in the free array.
 *
 * This algorthm may not be optimal for a given customer environment,
 * therefore, it may be changed.  The runtimes will always call
 * getInstance when one is desired, then freeInstance when the
 * runtime is done with it.
 *
 * However, care must be taken to avoid any multithreaded issues
 * that might result from object reuse.
 *
 * To indicate an error condition, throw a CSUException
 *   (i.e.  throw new CSUException("TCPIPDynamicCoopFlowExit:getInstance",
 *   "error message"))
 */

public static TCPIPDynamicCoopFlowExit getInstance(String newHostName,
                                                   Integer newPort,
                                                   boolean newClntPersist,
                                                   String newNextLocation,
                                                   String newProgramID,
                                                   String newTranCode,
                                                   String newProcedureName,
                                                   String newProcedureSourceName,
                                                   String newModelName,
                                                   String newModelShortName,
                                                   String newJavaContext,
                                                   String newJavaPackage,
                                                   Object runtimeObject)
    throws CSUException {
    TCPIPDynamicCoopFlowExit result;

    if (countFree <= 0)
    {
        result = new TCPIPDynamicCoopFlowExit();
    }
    else
    {
        synchronized (freeArray)
        {
            if (countFree <= 0)
            {
                result = new TCPIPDynamicCoopFlowExit();
            }
            else
            {
                result = freeArray[--countFree];
                freeArray[countFree] = null;
            }
        }
    }

    result.init(newHostName,
                newPort,
                newClntPersist,
                newNextLocation,
                newProgramID,
                newTranCode,
                newProcedureName,
                newProcedureSourceName,
                newModelName,
                newModelShortName,
                newJavaContext,
                newJavaPackage,
                runtimeObject);

    return result;
}

public final void freeInstance()
{
//*****************************************************
// Removed following code so WebSphere does not hang.
//    synchronized (freeArray)
//    {
//        if (countFree < freeArray.length)
//            freeArray[countFree++] = this;
//    }
//    return;
//*****************************************************
}

private TCPIPDynamicCoopFlowExit() {
    super();
}

private void init(String newHostName,
                        Integer newPort,
                        boolean newClntPersist,
                        String newNextLocation,
                        String newProgramID,
                        String newTranCode,
                        String newProcedureName,
                        String newProcedureSourceName,
                        String newModelName,
                        String newModelShortName,
                        String newJavaContext,
                        String newJavaPackage,
                        Object runtimeObject) {
    if (newHostName == null)
    {
        hostName = "";
    }
    else
    {
        hostName = newHostName;
    }

    if (newPort == null)
    {
        port = new Integer(0);
    }
    else
    {
        port = newPort;
    }

    clientPersistence = newClntPersist;
}

/**
 * This method will be called to retrieve the hostname to be
 * used for the TCPIP communications.  The default value is set
 * by the constructor with the original value the runtime contained.
 *
 * To override, simply return the desired String.
 *
 * To indicate an error condition, throw a CSUException
 *   (i.e.  throw new CSUException("TCPIPDynamicCoopFlowExit:getHostName",
 *   "error message"))
 */
public String getHostName() throws CSUException {
    return hostName;
}
/**
 * This method will be called to retrieve the port to be
 * used for the TCPIP communications.  The default value is set
 * by the constructor with the original value the runtime contained.
 *
 * To override, simply return the desired Integer.
 *
 * To indicate an error condition, throw a CSUException
 *   (i.e.  throw new CSUException("TCPIPDynamicCoopFlowExit:getPort",
 *   "error message"))
 */
public Integer getPort() throws CSUException {
    return port;
}
/**
 * This method will be called to retrieve the client socket connection
 * persistence state for the TCPIP communications. The default value is
 * set by the constructor with the original value the runtime contained.
 * A value of "true" indicates a persistent client socket connection.
 * A value of "false" indicates a non persistent client socket connection
 * where the client socket connection is closed after the response is 
 * received from the server.
 *
 * To override, simply return true or false.
 *
 * The default runtime value is 'true'.
 *
 * To indicate an error condition, throw a CSUException
 *   (i.e.  
 *   throw new CSUException("TCPIPDynamicCoopFlowExit:getClientPersistence",
 *   "error message"))
 */
public boolean getClientPersistence() throws CSUException {
    return clientPersistence;
}
/**
 * This method will be called whenever an exception has occurred
 * performing a TCPIP operation (open, read, write, etc.).  This exit
 * allow the programmer to control the processing of the exception.
 * Really only 2 options are available to the programmer:
 *   throw the exception
 *   retry the cooperative flow
 *
 * These 2 conditions are indicated by returning true to retry the
 * flow and false to go ahead and process/throw the exception.
 *
 * NOTE: This exit is only called for exceptions that occur during the
 * outbound portion of an asynchronous flow.  The inbound listener portion
 * is done on a separate thread and all the necessary information needed
 * to restart the request is not available, so the exit is never called
 * and the exceptions will automatically be processed.
 *
 * The default algorithm, looks for certain keywords in the exception
 * message and retries under certain conditions.   The retries by default
 * are capped at 2 times.
 */
public boolean processException(int attempts, CSUException e) {
    System.out.println("Attempt:"+attempts);
    if (attempts > 1)
    {
        // Have already retried at least once, do not retry again.
        return false;
    }

    // Get the exception message
    String message = e.getMessage();
    System.out.println("Error Message:"+message);
    if(message != null && !message.isEmpty())
    {
    	return true;
    }
/*
    if (message.indexOf("Connection reset") != -1 ||
        message.indexOf("Connection unexpectantly closed by server") != -1||
        message.indexOf("Connection unexpectedly closed by server") != -1)
    {
        // Retry is desired.
        return true;
    }
*/
    // No retry was desired.
    return false;
}
}
