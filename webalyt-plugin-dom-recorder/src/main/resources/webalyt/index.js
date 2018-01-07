var domRecorder = {
    shortName: "dom-recorder",
    fullName: "Record dom",
    urls: [],
    methodBody: function () {
        //on load
        window.addEventListener('load', function (e) {





            console.log("observing");
            //unobserve()
            var container = document.documentElement || document.body;

            var edits = pere.record(container, function(edit) {
                //console.log(edit);
                //var editsel = '[\n' + edits.map(JSON.stringify).join(',\n') + '\n]';


                var o = {
                    timestamp: new Date(),
                    mutation: JSON.stringify(edit)
                };
                // console.log(o);
                domRecorder.urls.push(o);
            });
            console.log(edits);
            edits.initRoot(container);


        });

    },
    onInit: function () {
        this.urls = [];
    },
    onAfterSend: function () {
        this.onInit();
    },
    getDataForSending: function () {
        return this.urls;
    }

};

webalyt.addPlugin(domRecorder);

