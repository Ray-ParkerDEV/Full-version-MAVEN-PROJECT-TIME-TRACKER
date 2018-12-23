<%--Created by Yaroslav Bodyak on 11.12.2018--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="spec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%--Table overview users activity--%>
<div class="wrapperPageData">
    <fieldset>
        <legend align="center">OVERVIEW USERS ACTIVITIES</legend>
        <div class="activityInfoForm">
            <table>
                <col width="200">
                <col width="100">
                <col width="200">
                <col width="230">
                <tr>
                    <th align="left">USERS</th>
                    <th>ACTIVITIES</th>
                    <th>REQUEST FROM CLIENT</th>
                    <th align="left">NOTICE</th>
                </tr>
                <%--@elvariable id="userList" type="java.util.List"--%>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>
                                ${user}<br>
                        </td>
                        <td>
                            <form class="formElement" name="actionForm" method="POST"
                                  action="controller">
                                <div class="wrapperButtons">
                                    <input type="hidden" name="user" value="${user}"/>
                                    <input type="hidden" name="command" value="overviewClient"/>
                                    <input class="buttonElement" type="submit" value="overview"
                                           style="height:20px; width:70px"/>
                                </div>
                            </form>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <button class="mockButton blue">add new activity
                                        </button>
                                    </td>
                                    <td>
                                        <button class="mockButton red">remove finished activity
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            info...
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </fieldset>
    <%--Table available activity--%>
    <div class="wrapperTableActivityAdmin">
        <fieldset>
            <legend align="center">AVAILABLE ACTIVITIES</legend>
            <div class="activityInfoForm">
                <table style=width:330px>
                    <col width="100">
                    <tr>
                        <td width="350" align="left">
                            <form class="formElement" name="actionForm" method="POST"
                                  action="controller">
                                <div class="wrapperButtons">
                                    <input type="hidden" name="command" value="createActivity"/>
                                    <input class="buttonElement" type="submit" value="add new activity"
                                           style="height:20px; width:110px"/>
                                    <input class="inputElement" type="text" name="activityName" value=""
                                           style="height:20px; width:220px"/>
                                </div>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%--@elvariable id="activityList" type="java.util.List"--%>
                            <c:forEach items="${activityList}" var="activity">
                                ${activity}<br>
                            </c:forEach>
                        </td>
                    </tr>
                </table>
                <table style=width:330px>
                    <tr>
                        <td>
                            <div>
                                </br>${operationMessage}
                            </div>
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