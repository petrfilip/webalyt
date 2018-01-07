// Generated by CoffeeScript 1.10.0
(function () {
    var defaultComp, diff, diffcomp, disp, ex, observe, patch, playback, record, reflect, sortedInsert, strip;

    defaultComp = function (a, b) {
        return a > b;
    };

    sortedInsert = function (ary, item, greater) {
        var end, i, start;
        if (greater == null) {
            greater = defaultComp;
        }
        start = 0;
        end = ary.length;
        while (start < end) {
            i = Math.floor((start + end) / 2);
            if (greater(item, ary[i])) {
                start = i + 1;
            } else {
                end = i;
            }
        }
        ary.splice(start, 0, item);
        return ary;
    };

    disp = function (str1, str2, path) {
        var ret;
        ret = [];
        while (path) {
            if (path.dir === 0) {
                ret.unshift("-" + str1[path.prev.x]);
            }
            if (path.dir === 1) {
                ret.unshift("+" + str2[path.prev.y]);
            }
            if (path.dir === 2) {
                ret.unshift("=" + str1[path.prev.x]);
            }
            path = path.prev;
        }
        return ret.join('');
    };

    diffcomp = function (path1, path2) {
        return path1.d > path2.d;
    };

    diff = function (str1, str2) {
        var _t, c, chunks, d, dir, i, j, lastt, len, n, p, paths, prev, ref, ret, t, thisPath, visited, x, y;
        visited = {};
        paths = [
            {
                x: 0,
                y: 0,
                d: 0,
                dir: -1,
                prev: null
            }
        ];
        n = 0;
        while (!(!paths[0] || (paths[0].x === str1.length) && (paths[0].y === str2.length))) {
            thisPath = paths.shift();
            x = thisPath.x, y = thisPath.y, dir = thisPath.dir, d = thisPath.d, prev = thisPath.prev;
            if (visited[x + ":" + y] || (x > str1.length) || (y > str2.length)) {
                continue;
            }
            visited[x + ":" + y] = true;
            if (str1[x] && (str1[x] === str2[y])) {
                sortedInsert(paths, {
                    x: x + 1,
                    y: y + 1,
                    d: d,
                    dir: 2,
                    prev: thisPath
                }, diffcomp);
            } else if (dir === 0) {
                sortedInsert(paths, {
                    x: x + 1,
                    y: y,
                    d: d + 1,
                    dir: 0,
                    prev: thisPath
                }, diffcomp);
                sortedInsert(paths, {
                    x: x,
                    y: y + 1,
                    d: d + 1,
                    dir: 1,
                    prev: thisPath
                }, diffcomp);
            } else {
                sortedInsert(paths, {
                    x: x,
                    y: y + 1,
                    d: d + 1,
                    dir: 1,
                    prev: thisPath
                }, diffcomp);
                sortedInsert(paths, {
                    x: x + 1,
                    y: y,
                    d: d + 1,
                    dir: 0,
                    prev: thisPath
                }, diffcomp);
            }
        }
        p = paths[0];
        ret = [];
        lastt = null;
        chunks = {
            '-': [],
            '+': [],
            '=': []
        };
        while (p) {
            t = '-+='[p.dir];
            if (!p.prev || (t !== lastt && (t === '=' || lastt === '='))) {
                i = 0;
                ref = '+-=';
                for (j = 0, len = ref.length; j < len; j++) {
                    _t = ref[j];
                    if (!chunks[_t].length) {
                        continue;
                    }
                    i += 1;
                    ret.unshift({
                        t: _t,
                        c: chunks[_t]
                    });
                    chunks[_t] = [];
                }
            }
            if (chunks[t]) {
                c = p.dir === 1 ? str2[p.prev.y] : str1[p.prev.x];
                chunks[t].unshift(c);
            }
            lastt = t;
            p = p.prev;
        }
        return ret;
    };

    strip = function (patch) {
        var j, len, p, results;
        results = [];
        for (j = 0, len = patch.length; j < len; j++) {
            p = patch[j];
            if (p.t === '=' || p.t === '-') {
                results.push({
                    l: p.c.length,
                    t: p.t
                });
            } else {
                results.push(p);
            }
        }
        return results;
    };

    patch = function (str, patch) {
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

    observe = function (el, callback) {
        var addNode, editTimers, nodeid, nodeids, observer, oldVals, removeNode, removedids, timestamp;
        //el.innerHTML = "";
        nodeid = 0;
        nodeids = new Map();
        oldVals = new Map();
        removedids = new Map();
        editTimers = new Map();
        timestamp = Date.now();
        addNode = function (parent, node) {
            var child, id, j, len, ref, results;
            if (nodeids.get(node)) {
                return;
            }
            id = nodeid++;
            nodeids.set(node, id);
            if (node.data) {
                oldVals.set(node, node.data);
            }
            if ((nodeids.get(parent) == null) && parent !== el) {
                console.warn("parent unknown", parent);
            }
            if ((node.previousSibling != null) && (nodeids.get(node.previousSibling) == null)) {
                console.warn("sibling unknown", node.previousSibling, removedids.get(node.previousSibling));
            }


            var atts = node.attributes;
            if (atts !== undefined) {
                var n = atts.length;
                var attsForSave = [];
                for (var i = 0; i < n; i++) {
                    attsForSave.push({"key": atts[i].nodeName, "value": atts[i].nodeValue});
                }
            }


            callback({
                type: 'add',
                id: id,
                nodeType: node.nodeType,
                tag: node.tagName,
                parent: nodeids.get(parent),
                data: node.data,
                after: nodeids.get(node.previousSibling),
                timestamp: Date.now(),
                attributes: attsForSave
            });
            if (node.childNodes) {
                ref = node.childNodes;
                results = [];
                for (j = 0, len = ref.length; j < len; j++) {
                    child = ref[j];
                    results.push(addNode(node, child));
                }
                return results;
            }
        };
        removeNode = function (node) {
            var child, id, j, len, ref;
            if (node.childNodes) {
                ref = node.childNodes;
                for (j = 0, len = ref.length; j < len; j++) {
                    child = ref[j];
                    removeNode(child);
                }
            }
            id = nodeids.get(node);
            nodeids["delete"](node);
            oldVals["delete"](node);
            clearTimeout(editTimers.get(node));
            editTimers["delete"](node);
            removedids.set(node, id);
            if (id != null) {
                return callback({
                    type: 'remove',
                    id: id,
                    timestamp: Date.now()
                });
            }
        };
        observer = new MutationObserver(function (mutations) {


            var j, len, mut, results;
            results = [];
            for (j = 0, len = mutations.length; j < len; j++) {
                mut = mutations[j];
                results.push((function (mut) {
                    var addedNode, k, len1, len2, m, ref, ref1, removedNode, results1, target, timer;
                    if (mut.type === 'childList') {
                        ref = mut.addedNodes;
                        for (k = 0, len1 = ref.length; k < len1; k++) {
                            addedNode = ref[k];
                            addNode(mut.target, addedNode);
                        }
                        ref1 = mut.removedNodes;
                        results1 = [];
                        for (m = 0, len2 = ref1.length; m < len2; m++) {
                            removedNode = ref1[m];
                            results1.push(removeNode(removedNode));
                        }
                        return results1;
                    } else if (mut.type === 'characterData') {
                        target = mut.target;
                        if (editTimers.get(target)) {
                            return;
                        }
                        timer = setTimeout(function () {
                            var edits, id, newVal, oldVal;
                            editTimers["delete"](target);
                            oldVal = oldVals.get(target) || '';
                            newVal = target.data;
                            oldVals.set(target, newVal);
                            if (oldVal === newVal) {
                                return;
                            }
                            edits = strip(diff(oldVal, newVal));
                            id = nodeids.get(target);
                            if (id != null) {
                                return callback({
                                    type: 'edit',
                                    id: id,
                                    edits: edits,
                                    timestamp: Date.now()
                                });
                            }
                        }, Math.floor(Math.random() * 300) + 200);
                        return editTimers.set(target, timer);
                    } else if (mut.type === 'attributes') {

                        var id = nodeids.get(mut.target);
                        if (id != null) {
                            return callback({
                                type: 'attribute',
                                id: id,

                                timestamp: Date.now(),
                                nodeType: mut.target.nodeType,
                                tag: mut.target.tagName,
                                data: mut.target.data,
                                attributes: [{
                                    attributeName: mut.attributeName,
                                    attributeValue: mut.target.getAttribute(mut.attributeName)
                                }]
                            });
                        }

                    }
                })(mut));
            }
            return results;
        });
        observer.observe(el, {
            childList: true,
            characterData: true,
            subtree: true,
            attributes: true
        });
        return {
            initRoot: function (el) {
                return addNode(null, el);
            },
            unobserve: function () {
                return observer.disconnect();
            }
        };
    };

    reflect = function (el) {
        var nodeids;
        el.innerHTML = "";
        nodeids = {};
        return {
            apply: function (e) {
                var afterNode, newdata, node, parent;
                switch (e.type) {
                    case 'add':
                        if (e.nodeType === 1) {
                            node = document.createElement(e.tag);
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
                        parent = nodeids[e.parent] || el;
                        if (afterNode = nodeids[e.after]) {
                            return parent.insertBefore(node, afterNode.nextSibling);
                        } else {
                            return parent.insertBefore(node, parent.firstChild);
                        }
                    case 'remove':
                        nodeids[e.id].remove();
                        return delete nodeids[e.id];
                    case 'edit':
                        node = nodeids[e.id];
                        newdata = patch(node.data, e.edits);
                        return node.data = newdata;
                    case 'attribute':
                        console.log("changing attributes not implemented yet");
                        break;
                }
            }
        };
    };

    record = function (el, callback) {
        var edits, observer;
        if (callback == null) {
            callback = function () {
            };
        }
        edits = [];

        observer = observe(el, function (edit) {
            edits.push(edit);
            return callback(edit);
        });
        //console.log(observer.initRoot(el));

        edits.unobserve = observer.unobserve;
        edits.initRoot = observer.initRoot;
        return edits;
    };

    playback = function (el, edits, speed) {
        var i, lasttime, pause, play, reflector, setSpeed, timeout;
        if (speed == null) {
            speed = 1.0;
        }
        reflector = reflect(el);
        i = 0;
        lasttime = 0;
        timeout = null;
        pause = function () {
            return clearTimeout(timeout);
        };
        play = function () {
            if (!edits[i]) {
                return;
            }
            return timeout = setTimeout(function () {
                var edit;
                edit = edits[i];
                reflector.apply(edit);
                lasttime = edit.timestamp;
                i++;
                return play();
            }, ((edits[i].timestamp - lasttime) / speed) || 0);
        };
        setSpeed = function (_speed) {
            speed = _speed;
            pause();
            return play();
        };
        play();
        return {
            setSpeed: setSpeed,
            pause: pause,
            play: play
        };
    };

    ex = {
        observe: observe,
        reflect: reflect,
        record: record,
        playback: playback
    };

    if (typeof window === 'undefined') {
        module.exports = ex;
    } else {
        window.pere = ex;
    }

}).call(this);