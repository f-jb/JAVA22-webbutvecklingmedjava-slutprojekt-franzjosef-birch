<%@page import="controller.JokeController"%>
<%@page import="accountManagement.AccountsManager"%>
<footer>
<div>
<p>*WAP stands for Weather Application Project, not the other <a href="https://en.wikipedia.org/wiki/WAP_(song)">WAP</a></p>
</div>

<div id="joke">
<% 
String[] joke = controller.JokeController.getJoke();
if(joke.length == 1){
	out.println("<p>" + joke[0] + "</p>");
} else {
	out.println("<p id=\"setup\">" + joke[0] + "</p>");
	out.println("<p id=\"delivery\">" + joke[1] + "</p>");

}

%>
</div>
</footer>