<#include "./common/header.ftl">


<h1>Sessions</h1>
<#list sessionIds as item>
    <div>
        <a href="/session-player/${item.getSessionId()}">Replay</a> ${item.getSessionId()}
    </div>
<#else>
  <p>No session recorded
</#list>



<#include "./common/footer.ftl">