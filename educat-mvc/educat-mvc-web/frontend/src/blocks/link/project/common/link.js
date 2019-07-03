/**
 * По умолчанию мобильная версия Safari не использует состояние :active, если
 * не установлен обработчик события touchstart
 */

function link() {
  let link = document.getElementsByClassName('link');

  for (let i = 0, j = link.length; i < j; i++) {
    link[i].addEventListener("touchstart", function(){}, true);
  }
}

link();
