<h1>Session player</h1>
<#list events as event>
<div>
    ${event.timestamp?datetime}
</div>
<#else>
  <p>No event
</#list>


<script></script>