<%@ page import="models.Good" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="stylesheets/goods-table.css">
    <title>HOME</title>
</head>
<body>
<jsp:include page="header.html"/>
<br/><br/>
<table align="center">
    <tr>
        <%
            List<Good> goods = (List<Good>) request.getAttribute("goods");
            for (Good good : goods) {
                out.println("<td align=\"center\">" +
                        "<a href=\"?category=" + good.getCategory() + "&id=" + good.getId() + "\">" +
                        "<img src=\"" + good.getMem() + "\"/>" +
                        "<br/>" + good.getName() +
                        "</a></td>");
                if ((goods.indexOf(good) + 1) % 4 == 0) out.print("</tr><tr>");
            }
        %>
    </tr>
</table>
</body>
</html>
