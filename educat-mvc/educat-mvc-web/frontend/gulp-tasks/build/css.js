'use strict';

const gulp = require('gulp');
const cleanCss = require('gulp-clean-css');
const gulpIf = require('gulp-if');
const autoprefixer = require('gulp-autoprefixer');

const isProduction = process.env.NODE_ENV === 'prod';

module.exports = function(options) {

  return function() {
    const cleanCssDev = cleanCss({
      level: {
        1: {
          all: false,
          cleanupCharsets: true,
          optimizeFontWeight: true,
          removeEmpty: true,
          removeNegativePaddings: true,
          removeWhitespace: true,
          replaceTimeUnits: true,
          specialComments: 'all'
        }
      },
      compatibility: {
        properties: {
          colors: true,
          urlQuotes: false,
          zeroUnits: true
        }
      },
      format: 'beautify'
    });

    const cleanCssProd = cleanCss({
      level: {
        1: {
          all: false,
          cleanupCharsets: true,
          optimizeFontWeight: true,
          removeEmpty: true,
          removeNegativePaddings: true,
          removeWhitespace: true,
          replaceTimeUnits: true,
          specialComments: 'all',
          tidyBlockScopes: true
        }
      },
      compatibility: {
        properties: {
          colors: true,
          urlQuotes: false,
          zeroUnits: true
        }
      }
    });

    return gulp.src(options.src, {base: options.base})
        .pipe(gulpIf(isProduction, cleanCssProd, cleanCssDev))
        .pipe(autoprefixer())
        .pipe(gulp.dest(options.dist));
  }

};
