angular.module('ui.bootstrap.demo', ['ngAnimate', 'ui.bootstrap']);
angular.module('ui.bootstrap.demo').controller('CarouselDemoCtrl', function ($scope) {
  $scope.myInterval = -1;
  $scope.noWrapSlides = true;
  var slides = $scope.slides = [];
  $scope.addSlide = function() {
    var newWidth =  slides.length + 1;
    slides.push({
      //home/pc9/bootstrapon/380
     
     // http://placekitten.com/602/300
      
      image: 'static/js/380/'+newWidth +'.jpg'
      
    });
  };
  for (var i=0; i<77; i++) {
    $scope.addSlide();
  }
});