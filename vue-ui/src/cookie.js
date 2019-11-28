export function setCookie(key,value) {
  window.document.cookie = key + " " + value;
}

//读取cookie
export function getCookie(param) {
  return window.document.cookie;
}

export function getRole() {
  var data =  window.document.cookie;
  if(data.length > 0 ){
    var arr = document.cookie.split(' ');
    return arr[1];
  }
  return '';
}
