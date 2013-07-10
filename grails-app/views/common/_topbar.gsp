<div id="menu" align="right">
<%--<g:link controller="user" action="login">Create</g:link>--%>
  <g:link controller="user" action="login">Login</g:link>
</div>
<nobr>
  <g:if test="${session.user}">
  <b>${session.user?.firstName}&nbsp;${session.user?.lastName}</b> |
   <g:link controller="user" action="logout">Logout</g:link>
</g:if>
<g:else>

</g:else>
</nobr>