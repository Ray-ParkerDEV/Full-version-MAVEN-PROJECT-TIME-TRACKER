<%--Created by Yaroslav Bodyak on 11.12.2018--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="spec" %>
<html>
<head>
    <title>Admin page</title>
    <link rel="stylesheet" type="text/css" href="/css/adminMain.css"/>
</head>
<body>
<div class="wrapperWelcomeInfo">
    <div class="welcomeElement">
        Administration service of TIMETRACKER.
        Hello ADMIN, <i>${user.getFirstName()} ${user.getSurName()}</i>!
    </div>
</div>
<div class="wrapperPageData">
    <%--Table overview users activity--%>
    <div class="tableElement">
        <fieldset>
            <legend align="center">OVERVIEW USERS ACTIVITIES</legend>
            <div class="activityInfoForm">
                <table>
                    <col width="150">
                    <col width="100">
                    <col width="250">
                    <col width="150">
                    <tr>
                        <th align="left">USERS</th>
                        <th>ACTIVITIES</th>
                        <th>ACTION</th>
                        <th align="left">NOTICE</th>
                    </tr>
                    <tr>
                        <td>
                            user1
                        </td>
                        <td>
                            <form class="formElement" name="actionForm" method="POST"
                                  action="controller">
                                <div class="wrapperButtons">
                                    <input type="hidden" name="command" value="overview"/>
                                    <input class="buttonElement" type="submit" value="overview"/>
                                </div>
                            </form>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="addAdmin"/>
                                                <input class="buttonElement" type="submit" value="add new activity"
                                                       style="height:20px; width:110px"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="removeAdmin"/>
                                                <input class="buttonElement" type="submit" value="remove activity"
                                                       style="height:20px; width:100px"/>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            info...
                        </td>
                    </tr>
                    <tr>
                        <td>
                            user2
                        </td>
                        <td>
                            <form class="formElement" name="actionForm" method="POST"
                                  action="controller">
                                <div class="wrapperButtons">
                                    <input type="hidden" name="command" value="overview"/>
                                    <input class="buttonElement" type="submit" value="overview"/>
                                </div>
                            </form>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="addAdmin"/>
                                                <input class="buttonElement" type="submit" value="add new activity"
                                                       style="height:20px; width:110px"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="removeAdmin"/>
                                                <input class="buttonElement" type="submit" value="remove activity"
                                                       style="height:20px; width:100px"/>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            wait for response
                        </td>
                    </tr>
                    <tr>
                        <td>
                            user3
                        </td>
                        <td>
                            <form class="formElement" name="actionForm" method="POST"
                                  action="controller">
                                <div class="wrapperButtons">
                                    <input type="hidden" name="command" value="overview"/>
                                    <input class="buttonElement" type="submit" value="overview"/>
                                </div>
                            </form>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="addAdmin"/>
                                                <input class="buttonElement" type="submit" value="add new activity"
                                                       style="height:20px; width:110px"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="removeAdmin"/>
                                                <input class="buttonElement" type="submit" value="remove activity"
                                                       style="height:20px; width:100px"/>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            info...
                        </td>
                    </tr>
                    <tr>
                        <td>
                            user4
                        </td>
                        <td>
                            <form class="formElement" name="actionForm" method="POST"
                                  action="controller">
                                <div class="wrapperButtons">
                                    <input type="hidden" name="command" value="overview"/>
                                    <input class="buttonElement" type="submit" value="overview"/>
                                </div>
                            </form>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="addAdmin"/>
                                                <input class="buttonElement" type="submit" value="add new activity"
                                                       style="height:20px; width:110px"/>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form class="formElement" name="actionForm" method="POST"
                                              action="controller">
                                            <div class="wrapperButtons">
                                                <input type="hidden" name="command" value="removeAdmin"/>
                                                <input class="buttonElement" type="submit" value="remove activity"
                                                       style="height:20px; width:100px"/>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            waiting for response
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
</div>

<div class="wrapperUserData">
    <%--Next table--%>
    <div class="tableElement">
        <fieldset>
            <legend align="center"> USER1 ACTIVITIES</legend>
            <div class="activityInfoForm">
                <table>
                    <col width="150">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="150">
                    <tr>
                        <th align="left">USERS</th>
                        <th align="left">STATUS</th>
                        <th align="left">TIME</th>
                        <th>ACTION</th>
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
                            00:00:00
                        </td>
                        <td>
                            <%--action--%>
                            <%--<form class="formElement" name="actionForm" method="POST"--%>
                                  <%--action="controller">--%>
                                <%--<div class="wrapperButtons">--%>
                                    <%--<input type="hidden" name="command" value="removeAct"/>--%>
                                    <%--<input class="buttonElement" type="submit" value="remove"/>--%>
                                <%--</div>--%>
                            <%--</form>--%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            activity2
                        </td>
                        <td>
                            pause
                        </td>
                        <td>
                            02:44:00
                        </td>
                        <td>
                            <%--action--%>
                            <%--<form class="formElement" name="actionForm" method="POST"--%>
                                  <%--action="controller">--%>
                                <%--<div class="wrapperButtons">--%>
                                    <%--<input type="hidden" name="command" value="removeAct"/>--%>
                                    <%--<input class="buttonElement" type="submit" value="remove"/>--%>
                                <%--</div>--%>
                            <%--</form>--%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            activity3
                        </td>
                        <td>
                            finished
                        </td>
                        <td>
                            13:17:03
                        </td>
                        <td>
                            <%--action--%>
                            <form class="formElement" name="actionForm" method="POST"
                                  action="controller">
                                <div class="wrapperButtons">
                                    <input type="hidden" name="command" value="removeAct"/>
                                    <input class="buttonElement" type="submit" value="remove"/>
                                </div>
                            </form>
                        </td>
                        <td>
                            waiting for response
                        </td>
                    </tr>
                    <tr >
                        <td >
                            <form class="formElement" name="formElement" method="POST" action="controller">
                                <div class="wrapperButtons">
                                    <input type="hidden" name="command" value="addActivityAdmin"/>
                                    <input type="submit" value="add new activity" style="height:20px; width:110px";/>
                                </div>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <div/>

    <!--LOGOUT-->
    <div class="logoutElement">
        <form name="logout" method="POST" action="controller">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" value="Log Out"/>
        </form>
    </div>
</body>
</html>