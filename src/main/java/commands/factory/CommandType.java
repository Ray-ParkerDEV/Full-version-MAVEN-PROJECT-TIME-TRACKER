package commands.factory;

import commands.BasicCommand;
import commands.implementations.DefaultCommand;
import commands.implementations.user.*;

/**
 * Description: This class describes all type of using commands.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public enum CommandType {

    /*user commands*/
    LOGIN, LOGOUT, REGISTRATION, GOTOREGISTRATION, UPDATECLIENT, BACK, DEFAULT;

    /**
     * This method directs the control to the corresponding class. The transfer of the control to the corresponding class
     * is carried out by determining the value of the parameter "command" from request. The current request is generated
     * from the "form" placed on the jsp page.
     *
     * @return      - the current class will be processed.
     */
    public BasicCommand getCurrentCommand() {
        switch (this) {
            case LOGIN:
                return new LoginCommand();
            case LOGOUT:
                return new LogoutCommand();
            case REGISTRATION:
                return new RegistrationCommand();
            case GOTOREGISTRATION:
                return new GotoRegistrationCommand();
            case UPDATECLIENT:
                return new UpdateClientCommand();
            case BACK:
                return new BackCommand();
            case DEFAULT:
                return new DefaultCommand();
            default:
                return new DefaultCommand();
        }
    }
}