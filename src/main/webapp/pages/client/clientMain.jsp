<%--Created by Yaroslav Bodyak on 11.12.2018--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="spec" %>
<html>
<head>
    <title>Clients account page</title>
    <link rel="stylesheet" type="text/css" href="/css/clientMain.css"/>
</head>
<body>
<div class="wrapperWelcomeInfo">
    <div class="welcomeElement">
        Welcome to TIMETRACKER, <i>${user.getFirstName()} ${user.getSurName()}</i>!
    </div>
</div>
<div class="wrapperPageData">
    <div class="trackingTable">
        <fieldset>
            <legend align="left">YOUR ACTIVITIES TRACKING</legend>
            <div class="activityInfoForm">
                <table>
                    <col width="150">
                    <col width="100">
                    <col>
                    <col width="100">
                    <col width="200">
                    <tr>
                        <th align="left">ACTIVITIES</th>
                        <th align="left">STATUS</th>
                        <th>ACTION</th>
                        <th align="left">TIME</th>
                        <th align="left">NOTICE</th>
                    </tr>
                    <tr>
                        <td>
                            activity1
                        </td>
                        <td>
                            new
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="actionStart"/>
                                                <input class="buttonElement" type="submit" value="start"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="actionStop"/>
                                                <input class="buttonElement" type="submit" value="stop"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td align="center">
                                        <form class="formElement" name="finishForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="finish"/>
                                                <input class="buttonElement" type="submit" value="finish"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td align="center">
                                        <form class="formElement" name="finishForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="remove"/>
                                                <input class="buttonElement" type="submit" value="remove"/>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            00:00:00
                        </td>
                        <td>
                            <%--info...--%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            activity2
                        </td>
                        <td>
                            in progress
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="actionStart"/>
                                                <input class="buttonElement" type="submit" value="start"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="actionStop"/>
                                                <input class="buttonElement" type="submit" value="stop"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td align="center">
                                        <form class="formElement" name="finishForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="finish"/>
                                                <input class="buttonElement" type="submit" value="finish"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td align="center">
                                        <form class="formElement" name="finishForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="remove"/>
                                                <input class="buttonElement" type="submit" value="remove"/>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            01:52:42
                        </td>
                        <td>
                            <%--info...--%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            activity3
                        </td>
                        <td>
                            pause
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="actionStart"/>
                                                <input class="buttonElement" type="submit" value="start"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="actionStop"/>
                                                <input class="buttonElement" type="submit" value="stop"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td align="center">
                                        <form class="formElement" name="finishForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="finish"/>
                                                <input class="buttonElement" type="submit" value="finish"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td align="center">
                                        <form class="formElement" name="finishForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="remove"/>
                                                <input class="buttonElement" type="submit" value="remove"/>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            04:45:02
                        </td>
                        <td>
                            <%--info...--%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            activity4
                        </td>
                        <td>
                            finished
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="actionStart"/>
                                                <input class="buttonElement" type="submit" value="start"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="actionStop"/>
                                                <input class="buttonElement" type="submit" value="stop"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td align="center">
                                        <form class="formElement" name="finishForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="finish"/>
                                                <input class="buttonElement" type="submit" value="finish"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td align="center">
                                        <form class="formElement" name="finishForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="remove"/>
                                                <input class="buttonElement" type="submit" value="remove"/>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            22:04:36
                        </td>
                        <td>
                            waiting for admin response
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <div>
                                <form class="formElement" name="formElement" method="POST" action="controller">
                                    <div class="wrapperButtons">
                                        <input type="hidden" name="command" value="addActivityClient"/>
                                        <input type="submit" value="add new activity" style="height:20px; width:110px";/>
                                    </div>
                                </form>
                            </div>
                        </td>
                        <td>
                            waiting for admin response
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
</div>
<!--LOGOUT-->
<div class="logoutElement">
    <form name="logout" method="POST" action="controller">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" value="Log Out"/>
    </form>
</div>
</body>
</html>