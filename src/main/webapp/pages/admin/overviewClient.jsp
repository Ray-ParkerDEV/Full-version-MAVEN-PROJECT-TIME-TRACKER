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

<div class="wrapperUserData">
    <%--Next table--%>
    <fieldset>
        <legend align="center"> USER1 ACTIVITIES</legend>
        <div class="activityInfoForm">
            <table>
                <col width="200">
                <col width="150">
                <col width="100">
                <col width="150">
                <col width="200">
                <tr>
                    <th align="left">ACTIVITIES</th>
                    <th align="left">STATUS</th>
                    <th align="left">TIME</th>
                    <th>ACTION</th>
                    <th align="left">NOTICE</th>
                </tr>
                <c:forEach items="${activityUserList}" var="activity">
                    <tr>
                        <td>
                                ${activity}<br>
                        </td>
                        <td>
                            status
                        </td>
                        <td>
                            time
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
                            info...
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </fieldset>
    <%--Table available activity--%>
    <div class="wrapperTableActivity">
        <fieldset>
            <legend align="center">AVAILABLE ACTIVITIES</legend>
            <div class="activityInfoForm">
                <table style=width:330px>
                    <col width="100">
                    <%--@elvariable id="activityList" type="java.util.List"--%>
                    <c:forEach items="${activityList}" var="activity">
                        <tr>
                            <td >
                                <form class="formElement" name="actionForm" method="POST"
                                      action="controller">
                                    <div class="wrapperButtons">
                                        <input type="hidden" name="activityName" value="${activity}"/>
                                        <input type="hidden" name="command" value="addActivity"/>
                                        <input class="buttonElement" type="submit" value="add activity"
                                               style="height:20px; width:80px"/>
                                    </div>
                                </form>
                            </td>
                            <td>
                                    ${activity}<br>
                            </td>
                        </tr>
                    </c:forEach>
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
<!--BACK-->
<div class="backElement">
    <form name="backForm" method="POST" action="controller">
        <input type="hidden" name="command" value="backAdmin"/>
        <input type="submit" value="Back"/>
    </form>
</div>
</body>
</html>