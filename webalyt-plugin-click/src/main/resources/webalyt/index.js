var wpc_click_tracker = [];
document.body.addEventListener('click', function (e) {
    var cp = {};
    // cp.t = new Date();
    cp.x = e.clientX;
    cp.y = e.clientY;
    wpc_click_tracker.push(cp);
});


var onClick = {
    shortName: "cp",
    fullName: "On click information",
    clicks: [],
    methodBody: function () {
        window.addEventListener('click', function (e) {
            var cp = {};
            var wri = {};
            wri.timestamp = new Date();
            cp.wri = wri;
            cp.domPath = onClick.getDomPath(e.target);
            cp.x = e.clientX;
            cp.y = e.clientY;
            onClick.clicks.push(cp);
        });
    },
    onInit: function () {
        this.clicks = [];
    },
    onAfterSend: function () {
        this.onInit();
    },
    getDataForSending: function () {
        return this.clicks;
    },
    getDomPath: function (el) {
        if (!el) {
            return;
        }
        var stack = [];
        var isShadow = false;
        while (el.parentNode != null) {
            // console.log(el.nodeName);
            var sibCount = 0;
            var sibIndex = 0;
            // get sibling indexes
            for (var i = 0; i < el.parentNode.childNodes.length; i++) {
                var sib = el.parentNode.childNodes[i];
                if (sib.nodeName == el.nodeName) {
                    if (sib === el) {
                        sibIndex = sibCount;
                    }
                    sibCount++;
                }
            }
            // if ( el.hasAttribute('id') && el.id != '' ) { no id shortcuts, ids are not unique in shadowDom
            //   stack.unshift(el.nodeName.toLowerCase() + '#' + el.id);
            // } else
            var nodeName = el.nodeName.toLowerCase();
            if (isShadow) {
                nodeName += "::shadow";
                isShadow = false;
            }
            if (sibCount > 1) {
                stack.unshift(nodeName + ':nth-of-type(' + (sibIndex + 1) + ')');
            } else {
                stack.unshift(nodeName);
            }
            el = el.parentNode;
            if (el.nodeType === 11) { // for shadow dom, we
                isShadow = true;
                el = el.host;
            }
        }
        stack.splice(0, 1); // removes the html element
        return stack.join(' > ');
    }

};
webalyt.addPlugin(onClick);