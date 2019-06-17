package handler;

import qualifiers.StringHandlerQ;

import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless @StringHandlerQ
public class StringHandler implements ICanHandleIt {
    private static final Logger _stringHandlerLogger = Logger.getLogger(StringHandler.class.getName());

    @Override
    public boolean handleMessage(String message){
        try{
            _stringHandlerLogger.log(Level.INFO, "StringHandler trying to handle the message");
            String msg = message;
            _stringHandlerLogger.log(Level.INFO, "String: {0}", msg);
            return true;

        }catch(Exception ex){
            _stringHandlerLogger.log(Level.WARNING, "StringHandler couldn't handle the message, {0}", ex);
            return false;
        }
    }
}
