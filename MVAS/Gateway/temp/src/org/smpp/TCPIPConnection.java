// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TCPIPConnection.java

package org.smpp;

import java.io.*;
import java.net.*;
import org.smpp.debug.Debug;
import org.smpp.debug.Event;
import org.smpp.util.ByteBuffer;

// Referenced classes of package org.smpp:
//            Connection, SmppObject, Data

public class TCPIPConnection extends Connection
{

    public TCPIPConnection(int port)
    {
        address = null;
        this.port = 0;
        socket = null;
        inputStream = null;
        outputStream = null;
        opened = false;
        receiverSocket = null;
        connType = 0;
        ioBufferSize = 2048;
        maxReceiveSize = 0x20000;
        if(port >= 100 && port <= 65535)
            this.port = port;
        else
            SmppObject.debug.write("Invalid port.");
        connType = 2;
    }

    public TCPIPConnection(String address, int port)
    {
        this.address = null;
        this.port = 0;
        socket = null;
        inputStream = null;
        outputStream = null;
        opened = false;
        receiverSocket = null;
        connType = 0;
        ioBufferSize = 2048;
        maxReceiveSize = 0x20000;
        if(address.length() >= 7)
            this.address = address;
        else
            SmppObject.debug.write("Invalid address.");
        if(port >= 100 && port <= 65535)
            this.port = port;
        else
            SmppObject.debug.write("Invalid port.");
        connType = 1;
        setReceiveBufferSize(4096);
    }

    public TCPIPConnection(Socket socket)
        throws IOException
    {
        address = null;
        port = 0;
        this.socket = null;
        inputStream = null;
        outputStream = null;
        opened = false;
        receiverSocket = null;
        connType = 0;
        ioBufferSize = 2048;
        maxReceiveSize = 0x20000;
        connType = 1;
        this.socket = socket;
        address = socket.getInetAddress().getHostAddress();
        port = socket.getPort();
        initialiseIOStreams(socket);
        opened = true;
        setReceiveBufferSize(4096);
    }

    public void open()
        throws IOException
    {
        SmppObject.debug.enter(7, this, "open");
        IOException exception = null;
        if(!opened)
        {
            if(connType == 1)
                try
                {
                    socket = new Socket(address, port);
                    initialiseIOStreams(socket);
                    opened = true;
                    SmppObject.debug.write(7, "opened client tcp/ip connection to " + address + " on port " + port);
                }
                catch(IOException e)
                {
                    SmppObject.debug.write("IOException opening TCPIPConnection " + e);
                    SmppObject.event.write(e, "IOException opening TCPIPConnection");
                    exception = e;
                }
            else
            if(connType == 2)
                try
                {
                    receiverSocket = new ServerSocket(port);
                    opened = true;
                    SmppObject.debug.write(7, "listening tcp/ip on port " + port);
                }
                catch(IOException e)
                {
                    SmppObject.debug.write("IOException creating listener socket " + e);
                    exception = e;
                }
            else
                SmppObject.debug.write("Unknown connection type = " + connType);
        } else
        {
            SmppObject.debug.write("attempted to open already opened connection ");
        }
        SmppObject.debug.exit(7, this);
        if(exception != null)
            throw exception;
        else
            return;
    }

    public void close()
        throws IOException
    {
        SmppObject.debug.enter(7, this, "close");
        IOException exception = null;
        if(connType == 1)
            try
            {
                inputStream.close();
                outputStream.close();
                socket.close();
                socket = null;
                opened = false;
                SmppObject.debug.write(7, "closed client tcp/ip connection to " + address + " on port " + port);
            }
            catch(IOException e)
            {
                SmppObject.debug.write("IOException closing socket " + e);
                SmppObject.event.write(e, "IOException closing socket");
                exception = e;
            }
        else
        if(connType == 2)
            try
            {
                receiverSocket.close();
                receiverSocket = null;
                opened = false;
                SmppObject.debug.write(7, "stopped listening tcp/ip on port " + port);
            }
            catch(IOException e)
            {
                SmppObject.debug.write("IOException closing listener socket " + e);
                SmppObject.event.write(e, "IOException closing listener socket");
                exception = e;
            }
        else
            SmppObject.debug.write("Unknown connection type = " + connType);
        SmppObject.debug.exit(7, this);
        if(exception != null)
            throw exception;
        else
            return;
    }

    public void send(ByteBuffer data)
        throws IOException
    {
        SmppObject.debug.enter(7, this, "send");
        IOException exception = null;
        if(outputStream == null)
        {
            SmppObject.debug.exit(7, this);
            throw new IOException("Not connected");
        }
        if(connType == 1)
            try
            {
                socket.setSoTimeout((int)getCommsTimeout());
                outputStream.write(data.getBuffer(), 0, data.length());
                outputStream.flush();
                SmppObject.debug.write(7, "sent " + data.length() + " bytes to " + address + " on port " + port);
            }
            catch(IOException e)
            {
                SmppObject.debug.write("TCPIPConnection.send() " + e);
                if(exception == null)
                    exception = e;
            }
        else
        if(connType == 2)
            SmppObject.debug.write("Attempt to send data over server type connection.");
        else
            SmppObject.debug.write("Unknown connection type = " + connType);
        SmppObject.debug.exit(7, this);
        if(exception != null)
            throw exception;
        else
            return;
    }

    public ByteBuffer receive()
        throws IOException
    {
        SmppObject.debug.enter(8, this, "receive");
        IOException exception = null;
        ByteBuffer data = null;
        if(connType == 1)
        {
            data = new ByteBuffer();
            long endTime = Data.getCurrentTime() + getReceiveTimeout();
            int bytesToRead = 0;
            int bytesRead = 0;
            int totalBytesRead = 0;
            try
            {
                socket.setSoTimeout((int)getCommsTimeout());
                bytesToRead = receiveBufferSize;
                SmppObject.debug.write(8, "going to read from socket");
                SmppObject.debug.write(8, "comms timeout=" + getCommsTimeout() + " receive timeout=" + getReceiveTimeout() + " receive buffer size=" + receiveBufferSize);
                do
                {
                    bytesRead = 0;
                    try
                    {
                        bytesRead = inputStream.read(receiveBuffer, 0, bytesToRead);
                    }
                    catch(InterruptedIOException e)
                    {
                        SmppObject.debug.write(8, "timeout reading from socket");
                    }
                    if(bytesRead == -1)
                    {
                        inputStream.close();
                        throw new IOException("Connection was closed by SMSC.");
                    }
                    if(bytesRead > 0)
                    {
                        SmppObject.debug.write(8, "read " + bytesRead + " bytes from socket");
                        data.appendBytes(receiveBuffer, bytesRead);
                        totalBytesRead += bytesRead;
                        SmppObject.debug.write(8, "more data (" + bytesToRead + " bytes) remains in the socket");
                    } else
                    {
                        SmppObject.debug.write(8, "no more data remains in the socket");
                    }
                    bytesToRead = inputStream.available();
                    if(bytesToRead > receiveBufferSize)
                        bytesToRead = receiveBufferSize;
                    if(totalBytesRead + bytesToRead > maxReceiveSize)
                        bytesToRead = maxReceiveSize - totalBytesRead;
                } while(bytesToRead != 0 && Data.getCurrentTime() <= endTime && totalBytesRead < maxReceiveSize);
                SmppObject.debug.write(7, "totally read " + data.length() + " bytes from socket");
            }
            catch(IOException e)
            {
                SmppObject.debug.write("IOException: " + e.getMessage());
                SmppObject.event.write(e, "IOException receive via TCPIPConnection");
                exception = e;
            }
        } else
        if(connType == 2)
            SmppObject.debug.write("Attempt to receive data from server type connection.");
        else
            SmppObject.debug.write("Unknown connection type = " + connType);
        SmppObject.debug.exit(8, this);
        if(exception != null)
            throw exception;
        else
            return data;
    }

    public Connection accept()
        throws IOException
    {
        SmppObject.debug.enter(8, this, "receive");
        IOException exception = null;
        Connection newConn = null;
        if(connType == 2)
        {
            try
            {
                receiverSocket.setSoTimeout((int)getReceiveTimeout());
            }
            catch(SocketException e) { }
            Socket acceptedSocket = null;
            try
            {
                acceptedSocket = receiverSocket.accept();
            }
            catch(IOException e)
            {
                SmppObject.debug.write(8, "Exception accepting socket (timeout?)" + e);
            }
            if(acceptedSocket != null)
                try
                {
                    newConn = new TCPIPConnection(acceptedSocket);
                }
                catch(IOException e)
                {
                    SmppObject.debug.write("IOException creating new client connection " + e);
                    SmppObject.event.write(e, "IOException creating new client connection");
                    exception = e;
                }
        } else
        if(connType == 1)
            SmppObject.debug.write("Attempt to receive data from client type connection.");
        else
            SmppObject.debug.write("Unknown connection type = " + connType);
        SmppObject.debug.exit(8, this);
        if(exception != null)
            throw exception;
        else
            return newConn;
    }

    private void initialiseIOStreams(Socket socket)
        throws IOException
    {
        if(connType == 1)
        {
            inputStream = new BufferedInputStream(socket.getInputStream(), ioBufferSize);
            outputStream = new BufferedOutputStream(socket.getOutputStream(), ioBufferSize);
        } else
        if(connType == 2)
            SmppObject.debug.write("Attempt to initialise i/o streams for server type connection.");
        else
            SmppObject.debug.write("Unknown connection type = " + connType);
    }

    public void setIOBufferSize(int ioBufferSize)
    {
        if(!opened)
            this.ioBufferSize = ioBufferSize;
    }

    public void setReceiveBufferSize(int receiveBufferSize)
    {
        this.receiveBufferSize = receiveBufferSize;
        receiveBuffer = new byte[receiveBufferSize];
    }

    public void setMaxReceiveSize(int maxReceiveSize)
    {
        this.maxReceiveSize = maxReceiveSize;
    }

    private String address;
    private int port;
    private Socket socket;
    private BufferedInputStream inputStream;
    private BufferedOutputStream outputStream;
    private boolean opened;
    private ServerSocket receiverSocket;
    private byte connType;
    private static final byte CONN_NONE = 0;
    private static final byte CONN_CLIENT = 1;
    private static final byte CONN_SERVER = 2;
    private static final int DFLT_IO_BUF_SIZE = 2048;
    private static final int DFLT_RECEIVE_BUFFER_SIZE = 4096;
    private static final int DFLT_MAX_RECEIVE_SIZE = 0x20000;
    private int ioBufferSize;
    private int receiveBufferSize;
    private byte receiveBuffer[];
    private int maxReceiveSize;
}
