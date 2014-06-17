function TodoCtrl($scope, $http) {
    var headers = {
        "Content-Type": "application/json;odata=verbose"
    };
    var sms_notifier_url = "http://serene-mountain-7282.herokuapp.com/sms_notifier/index.php";

    var site_base_url = "http://safe-ocean-7286.herokuapp.com/todo/";
    //fallback
    site_base_url = "http://thawing-sands-2974.herokuapp.com/todos/";

    // site_base_url = "http://localhost:8080/todos/";

    var serializeData = function(data) {

        // If this is not an object, defer to native stringification.
        if (!angular.isObject(data)) {

            return ((data == null) ? "" : data.toString());

        }

        var buffer = [];

        // Serialize each key in the object.
        for (var name in data) {

            if (!data.hasOwnProperty(name)) {

                continue;

            }

            var value = data[name];

            buffer.push(
                encodeURIComponent(name) +
                "=" +
                encodeURIComponent((value == null) ? "" : value)
            );

        }

        // Serialize the buffer and clean it up for transportation.
        var source = buffer
            .join("&")
            .replace(/%20/g, "+");

        return (source);

    };

    var myfunction = function(url, dataType, method, data, headers) {
        if (method === "GET") return $http({
            url: url,
            dataType: dataType,
            method: method,
            params: data,
            headers: headers
        });
        else
            return $http({
                url: url,
                dataType: dataType,
                method: method,
                data: data,
                headers: headers
            });
    };

    $scope.refreshData = function(direction) {
        var get_url = site_base_url;
        if (direction) {
            get_url = $scope.response._links[direction].href.replace("http://safe-ocean-7286.herokuapp.com/todo/", site_base_url);
        }
        myfunction(get_url,
            'json', 'GET', null, null).then(function(dataResponse) {
            //debugger;
            $scope.response = dataResponse.data;
            $scope.todos = dataResponse.data._embedded.todos;
        });

        $scope.todoTitle = '';
        $scope.todoBody = '';

    };
    $scope.close = function(result) {
        dialog.close(result);
    };
    $scope.testmydata = function(todo) {

        $scope.update_todo = todo;
        $scope.todo_sms_number = "8145121677";
        $scope.flag_send_sms = false;
    };
    $scope.testme = function(id) {
        $scope.updateTodo($scope.update_todo);
    };
    $scope.updateTodo = function(todo) {
        // alert(id + "--" + flag);
        //alert("flag " + $scope.flag_send_sms);
        var headers1 = {
            "Content-Type": "application/x-www-form-urlencoded"
        };

        var data = {
            "done": !todo.done

        };
        console.log(todo);
        myfunction(site_base_url + todo.id,
            'json', 'PATCH', serializeData(data), headers1).then(function(dataResponse) {
            var msg = {
                "msg": todo.title
            };
            if ($scope.flag_send_sms) {
                myfunction(sms_notifier_url + "?msg=" + encodeURIComponent(todo.title) + "&num=" + encodeURIComponent($scope.todo_sms_number),
                    'json', 'GET', null, null).then(function(dataResponse) {
                    //debugger;

                });
            }
            $scope.refreshData();

        });


    };
    $scope.addTodo = function() {
        //debugger;

        var headers1 = {
            "Content-Type": "application/x-www-form-urlencoded"
        };
        var data = {
            "title": $scope.todoTitle,
            "body": $scope.todoBody
        };
        myfunction(site_base_url,
            'application/json', 'POST', serializeData(data), headers1).then(function(dataResponse) {
            debugger;


            $scope.refreshData();
        });

    };

    $scope.remaining = function() {
        var count = 0;
        angular.forEach($scope.todos, function(todo) {
            count += todo.done ? 0 : 1;
        });
        return count;
    };

    $scope.refreshData();
}