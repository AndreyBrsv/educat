'use strict';

const gulp = require('gulp');


// =============================================================================
// функция "ленивого" подключения модуля
// =============================================================================

function lazyRequireTasks(taskName, path, options) {
  options = options || {};
  options.taskName = taskName;
  gulp.task(taskName, function(callback) {
    let task = require(path).call(this, options);
    return task(callback);
  })
}


// =============================================================================
// html
// =============================================================================

lazyRequireTasks('build:html', './gulp-tasks/build/html', {
  src: [
    'src/**/*.html'
  ],
  base: 'src/',
  dist: 'dist/'
});

lazyRequireTasks('clean:html', './gulp-tasks/clean', {
  dist: [
    'dist/**/*.html'
  ]
});


// =============================================================================
// очистка сборки
// =============================================================================

lazyRequireTasks('clean', './gulp-tasks/clean', {
  dist: 'dist/'
});


// =============================================================================
// наблюдение за исходниками
// =============================================================================

gulp.task('watch', function() {

  gulp.watch('src/**/*.html', gulp.series('clean:html', 'build:html'))
      .on('all', function(event, path) {
        console.log('Task initiator: "' + path + '"' + ' Event: ' + event);
      });

});


// =============================================================================
// варианты сборки
// =============================================================================

gulp.task('default', gulp.series('clean', 'build:html', 'watch'));
