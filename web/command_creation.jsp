<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Machine" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Command Creation</title>
</head>
<body>
    Page de creation 2 <br>
    <form method="post" action="${pageContext.request.contextPath}/command/creation/2">
    <%  ArrayList<Machine> machines = (ArrayList<Machine>)request.getAttribute("machines");
        for(int i = 0 ; i < machines.size() ; i++) {
            int machineCount = 1;
            Machine machine = machines.get(i);
            Machine nextMachine = new Machine();
            if(i < machines.size()-2) {
                nextMachine = machines.get(i+1);
            }
            Machine previousMachine = new Machine();
            if(i > 0) {
                previousMachine = machines.get(i-1);
            }
            if(machine.getFamily() != previousMachine.getFamily()) { %>
                <%=machine.getFamily()%><br>
    <%      }
            if(machine.getModel() != previousMachine.getModel()) { %>
                <%=machine.getModel()%> :
    <%      } else {
                machineCount++;
            }
            if(machine.getModel() != nextMachine.getModel()) { %>
                <label>Count<input type="number" name="<%=machine.getModel()%>" min="0" max="<%=machineCount%>" value="0"></label>Maximum : <%=machineCount%><br>
    <%      }
        }%>
        <input type="submit" value="Command"/>
    </form>
</body>
</html>
