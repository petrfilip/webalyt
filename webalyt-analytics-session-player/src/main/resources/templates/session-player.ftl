<#include "./common/header.ftl">


    <nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">
                <div class="btn-group">
                    <button type="button" class="btn btn-primary"><span
                            class="glyphicon glyphicon-step-backward"></span>
                    </button>
                    <button type="button" class="btn btn-primary"><span
                            class="glyphicon glyphicon-play"></span></button>
                    <button type="button" class="btn btn-primary"><span
                            class="glyphicon glyphicon-step-forward"></span>
                    </button>
                </div>
                <div id="timeline">
                    <div id="playhead"></div>
                </div>
            </div>
        </div>
    </nav>

<div class="row">
    <div class="col-sm-2 session-sidenav">

        <#list pageviews as item>
            <div>
                <a href="#${item.getPageViewId()}"
                   onclick="loadData('${item.getPageViewId()}');">Replay ${item.getPageViewId()}</a>
            </div>
        <#else>
            <p>No page view recorded</p>
        </#list>

    </div>
    <div class="col-sm-8">


        <iframe scrolling="no"
                src=""
                id="dom"
                style="pointer-events: none; transform-origin: 0px 0px 0px; border: 1px solid black; width: 100%; height: 100%; transform: scale(0.5, 0.5);"></iframe>
    </div>
    <div class="col-sm-2 session-sidenav">
        <div>
            <h4>Loading performance</h4>
        </div>
        <div>
            <h4>Device info</h4>
        </div>
        <div>
            <h4>User informations</h4>
        </div>
        <div>
            <h4>Tags</h4>
        </div>
        <div>
            <h4>Notes</h4>
            <textarea></textarea>
        </div>
    </div>

</div>

</div>
<script>


    var patch = function (str, patch) {
        var i, j, l, len, newstr, p, si;
        newstr = [];
        i = 0;
        si = 0;
        for (j = 0, len = patch.length; j < len; j++) {
            p = patch[j];
            if (p.t === '=') {
                l = p.l;
                newstr.push(str.slice(si, si + l));
                i += l;
                si += l;
            }
            if (p.t === '+') {
                newstr.push(p.c.join(''));
                i += p.l;
            }
            if (p.t === '-') {
                si += p.l;
            }
        }
        return newstr.join('');
    };

    var reflect = function (el) {
        var nodeids;
        el.body.innerHTML = '<span id="mouse-pointer" style="display: block; position: absolute; background-color: red; padding: 5px;"></span>';
        nodeids = {};
        return {
            apply: function (e) {
                function addAtts(editedNode, atts) {
                    for (var i = node.attributes.length - 1; i >= 0; i--) {
                        node.removeAttribute(node.attributes[i].name);
                    }

                    for (var j = atts.length - 1; j >= 0; j--) {
                        node.setAttribute(atts[j]["key"], atts[j]["value"]);
                    }
                    return editedNode;

                }

                var afterNode, newdata, node, parent;
                switch (e.type) {
                    case 'add':
                        if (e.nodeType === 1) {
                            node = document.createElement(e.tag);
                            node = addAtts(node, e.attributes);
                        } else if (e.nodeType === 3) {
                            node = document.createTextNode("");
                        }
                        if (e.data != null) {
                            node.data = e.data;
                        }
                        if (e.innerHTML != null) {
                            node.innerHTML = e.innerHTML;
                        }
                        nodeids[e.id] = node;
                        parent = nodeids[e.parent] || el.body;
                        if (afterNode = nodeids[e.after]) {
                            return parent.insertBefore(node, afterNode.nextSibling);
                        } else {
                            return parent.insertBefore(node, parent.firstChild);
                        }
                        break;
                    case 'remove':
                        nodeids[e.id].remove();
                        return delete nodeids[e.id];
                    case 'edit':
                        node = nodeids[e.id];
                        newdata = patch(node.data, e.edits);
                        return node.data = newdata;


                }
            }
        };
    };


    var domPlayer = document.getElementById("dom"),
            iframedoc = domPlayer.contentDocument || domPlayer.contentWindow.document;


    var reflector = reflect(iframedoc);
    //var mousePointer = document.getElementById("mouse-pointer");

    var lastMousePosition = {x: 0, y: 0};

    function applyDomEvent(event) {
        reflector.apply(event);
    };

    function applyMouseMove(event) {
        lastMousePosition.x = event.x;
        lastMousePosition.y = event.y;
        iframedoc.getElementById("mouse-pointer").style.left = (iframedoc.body.scrollLeft + event.x) + "px";
        iframedoc.getElementById("mouse-pointer").style.top = (iframedoc.body.scrollTop + event.y) + "px";
    };

    function applyClickEvent(event) {
        applyMouseMove(event);
        iframedoc.getElementById("mouse-pointer").style.backgroundColor = "blue";
        setTimeout(function () {
            iframedoc.getElementById("mouse-pointer").style.backgroundColor = "red";
        }, 300);
    };

    function applyWindowResizeEvent(event) {
        domPlayer.style.width = event.width + "px";
        domPlayer.style.height = event.height + "px";
    };

    function applySrollEvent(event) {


        iframedoc.body.scrollTop = event.y;
        iframedoc.body.scrollLeft = event.x;
        applyMouseMove(lastMousePosition);
    }

    function applyEvent(event) {
        if (event.eventType === "MousePosition") {
            applyMouseMove(event.eventData);
        } else if (event.eventType === "Click") {
            applyClickEvent(event.eventData);
        } else if (event.eventType === "RecordedDom") {
            applyDomEvent(JSON.parse(event.eventData.mutation));
        } else if (event.eventType === "ScrollTracker") {
            applySrollEvent(event.eventData);
        } else if (event.eventType === "RecordedSize") {
            applyWindowResizeEvent(event.eventData);
        } else {
            console.log("not implemented: " + event.eventType);
        }
    }

    function playback(events) {
        var i, lasttime, speed;
        lasttime = 0;
        i = 0;
        if (speed == null) {
            speed = 1.0;
        }
        console.log(events[0].eventData.timestamp);

        play = function () {
            setTimeout(function () {
                var event;
                event = events[i];
                applyEvent(event);
                lasttime = event.eventData.timestamp;
                i++;
                console.log("playing event n. " + i + " -- " + event);
                return play();
            }, ((events[i].eventData.timestamp - lasttime) / speed) || 0);
        };
        play();

    }


    function gup(name, url) {
        if (!url) url = location.href;
        name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
        var regexS = "[\\?&]" + name + "=([^&#]*)";
        var regex = new RegExp(regexS);
        var results = regex.exec(url);
        return results == null ? null : results[1];
    }

    function loadData(pageViewId) {
        var url = "http://localhost:8081/pageview-activities/" + pageViewId;
        fetch(url).then(function (response) {
            response.text().then(function (text) {
                var events = JSON.parse(text);
                playback(events);


            });
        });
    };


</script>


<#include "./common/footer.ftl">
