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
