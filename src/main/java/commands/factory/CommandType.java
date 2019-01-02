package commands.factory;

import commands.BasicCommand;
import commands.implementations.DefaultCommand;
import commands.implementations.admin.*;
import commands.implementations.client.*;
import commands.implementations.user.*;

/**
 * Description: This class describes all type of using commands.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public enum CommandType {

    /*user commands*/
    LOGIN, LOGOUT, REGISTRATION, GOTOREGISTRATION, UPDATECLIENT, BACK, DEFAULT, SETLANGUAGE,

    /*admin commands*/
    CREATEACTIVITY, OVERVIEWCLIENT, BACKADMIN, ADDACTIVITY, REMOVEADMIN,

    /*client commands*/
    STARTTIME, STOPTIME, FINISH, REMOVE, ADD;

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
            case SETLANGUAGE:
                return new SetLanguageCommand();
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
            case CREATEACTIVITY:
                return new CreateActivityCommand();
            case OVERVIEWCLIENT:
                return new OverviewClientCommand();
            case BACKADMIN:
                return new BackAdminCommand();
            case REMOVEADMIN:
                return new RemoveAdminCommand();
            case ADDACTIVITY:
                return new AddActivityToUserCommand();
            case STARTTIME:
                return new StartTimeCommand();
            case STOPTIME:
                return new StopTimeCommand();
            case FINISH:
                return new FinishCommand();
            case REMOVE:
                return new RemoveCommand();
            case ADD:
                return new AddCommand();
            case DEFAULT:
                return new DefaultCommand();
            default:
                return new DefaultCommand();
        }
    }
}