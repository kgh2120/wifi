function getQueryStringObject() {
  var queryString = window.location.search.substring(1).split('&');
  if (queryString == "") return {};
  var queryStringObject = {};
  for (var i = 0; i < queryString.length; ++i) {
    var p = queryString[i].split('=', 2);
    if (p.length == 1)
      queryStringObject[p[0]] = "";
    else
      queryStringObject[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
  }
  return queryStringObject;
}

function fetchBookmarkData(id){
 fetch(`/bookmark?id=${id}`)
  .then(res => res.json())
  .then(data => {
      let rowData = document.getElementsByClassName("bookmark-delete-row-data");
      rowData[0].innerText = data.group_name;
    rowData[1].innerText = data.wifi_name;
    rowData[2].innerText = data.created_at;
    let deleteBtn = document.querySelector(".bookmark-delete-btn");
    deleteBtn.addEventListener('click', () => onDeleteButtonClicked(data.id))
  })
}

const onDeleteButtonClicked = (id) => {
  if(confirm("정말 삭제하시겠습니까?")){
  fetch(`/bookmark?id=${id}`,{
    method : "DELETE"
  }).then(()=>window.location.replace("/view/bookmarkList.jsp"))
  .catch(err => console.error(err));
  }
}


function init(){
  let query = getQueryStringObject();
  fetchBookmarkData(query.id)
}

init();