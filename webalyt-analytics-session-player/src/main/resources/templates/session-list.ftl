<h1>Sessionsss</h1>
<#list deviceIds as item>
    <div>
        <a href="/session-player/${item.pageViewId}">Replay</a> ${item.pageViewId}
    </div>
<#else>
  <p>No users
</#list>
