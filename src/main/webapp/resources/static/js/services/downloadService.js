/**
 * Created by pc8 on 27.11.15.
 */

app.service('downloadService', function($http){

    this.download = function(){

        $http.get('http://localhost:8080/download')
            //10.251.0.21
            .success(function (data) {
            })
            .error(function (err) {
                return err;
            });
    }


});