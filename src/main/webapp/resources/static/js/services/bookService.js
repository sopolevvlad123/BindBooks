/**
 * Created by pc8 on 27.11.15.
 */
app.factory('bookDesc', ['$http', function($http) {
    return $http.get('https://s3.amazonaws.com/codecademy-content/courses/ltp4/forecast-api/forecast.json')
        .success(function(data) {
           alert("bookDesc");
            return data;
        })
        .error(function(err) {
            return err;
        });
}]);