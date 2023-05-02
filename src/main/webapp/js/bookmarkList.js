function fetchBookmarkList(){
  fetch(`/bookmark`)
  .then(res => res.json())
  .then(data => {
    let body = document.querySelector(".bookmark-table-body");
    data.forEach(datum => {
      let row = document.createElement("tr");
      row.innerHTML = `
      <td>${datum.id}</td>
      <td>${datum.group_name}</td>
      <td><a href="/view/wifiDetail.jsp?historyId=${datum.history_id}&wifiId=${datum.wifi_id}">${datum.wifi_name}</a></td>
      <td>${datum.created_at}</td>
      <td><a href="/view/bookmarkDelete.jsp?id=${datum.id}">삭제</a></td>
      `
      body.append(row);
    })

  })
}



function init(){
  fetchBookmarkList();
}

init();