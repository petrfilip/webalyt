<!DOCTYPE html>

<html lang="en">
<head>
    <style>
        body {
            margin: 0px;
            padding: 0px;
            background-color: aliceblue;
            overflow:hidden"
        }


    </style>
</head>

<body>


<#list availablePlugins as item>
    <div>
        <a onclick="loadContent('${item.getDefaultOperation()}');"> ${item.getName()}</a>
    </div>
<#else>
  <p>No session recorded
</#list>



    <iframe width="100%" height="500px" id="iframe" style="border: 0px;"
            src=""></iframe>


    <script>


        function ChangeUrl(page, url) {
            if (typeof (history.pushState) != "undefined") {
                var obj = {Page: page, Url: url};
                history.pushState(obj, obj.Page, obj.Url);
            } else {
                window.location.href = "homePage";
                alert("Browser does not support HTML5.");
            }
        }


        function loadContent(operation) {
            var url = "http://localhost:8082" + operation;
            urlParts = /^(?:\w+\:\/\/)?([^\/]+)(.*)$/.exec(url);
            hostname = urlParts[1]; // www.example.com
            path = urlParts[2]; // /path/to/somwhere

            ChangeUrl("test", path);

            var xhr = new XMLHttpRequest();
            xhr.onload = function () {
                console.log(this.responseText);

                var iframe = document.getElementById("iframe");
                iframe.src = this.responseText;
            };
            console.log(url);
            xhr.open("POST", url);
            xhr.send();

        }

        window.addEventListener("load", function (e) {
            if (location.pathname != "/") {
                loadContent(location.pathname);
            }
        });
    </script>


</body>
</html>