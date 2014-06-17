var express = require("express");
var app = express();
var ElasticSearchClient = require('elasticsearchclient');

var serverOptions = {
    host: 'dwalin-us-east-1.searchly.com',
    port: 80,
    secure: false,
    auth: {
        username: 'todosearch',
        password: 'kjgayw5tfyipyuod9awbfsxjp3iufszf'
    }
};

var elasticSearchClient = new ElasticSearchClient(serverOptions);
/* serves main page */
app.get("/", function(req, res) {
    res.send('hello');
});
app.get("/search", function(req, res) {
    var qryObj = {
        "query": {
            "query_string": {
                "fields": ["title^2", "body"],
                "query": "*" + req.query.q + "*"
            }
        }
    };

    elasticSearchClient.search('todorest', 'default', qryObj)
        .on('data', function(data) {
            res.send(data);
        }).on('error', function(error) {
            console.log(error)
        }).exec();

});


var port = process.env.PORT || 3300;
app.listen(port, function() {
    console.log("Listening on " + port);
});